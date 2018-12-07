package com.example.algorithm.reactive;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class Practice2_2 {

  @Test
  public void test() {

    Publisher<Integer> pub = iterPub(
        Stream.iterate(1, a -> a + 1).limit(10).collect(Collectors.toList()));

//    Publisher<String> mapPub = biMapPub(pub,
//        (Function<Integer, String>) (s) -> "["+ s+ "]");
//
//    mapPub.subscribe(logSub());

//    Publisher<String> reducePub = reducePub(pub, "",
//        (BiFunction<String, Integer, String>) (a, b) -> a + "-" + b);

    Publisher<StringBuilder> reducePub = reducePub(pub, new StringBuilder(),
        (BiFunction<StringBuilder, Integer, StringBuilder>) (a, b) -> a.append(b+","));
    reducePub.subscribe(logSub());


  }

  private <T, R> Publisher<R> reducePub(Publisher<T> pub, R init,
      BiFunction<R, T, R> bf) {

    return new Publisher<R>() {
      @Override
      public void subscribe(Subscriber<? super R> sub) {
        pub.subscribe(new BiDelegateSub<T, R>(sub) {

          R result = init;

          @Override
          public void onNext(T i) {
            result = bf.apply(result, i);
          }

          @Override
          public void onComplete() {
            sub.onNext(result);
            sub.onComplete();
          }
        });
      }
    };
  }

  private <T> Subscriber<? super T> logSub() {

    return new Subscriber<T>() {
      @Override
      public void onSubscribe(Subscription s) {
        log.debug("onSubscribe:");
        s.request(Long.MAX_VALUE);
      }

      @Override
      public void onNext(T i) {
        log.debug("onNext:{}", i);
      }

      @Override
      public void onError(Throwable t) {
        log.debug("onError:{}", t);
      }

      @Override
      public void onComplete() {
        log.debug("onComplete");
      }
    };
  }

  private <T> Publisher<T> mapPub(Publisher<T> pub, Function<T, T> f) {

    return new Publisher<T>() {
      @Override
      public void subscribe(Subscriber<? super T> sub) {
        pub.subscribe(new DelegateSubGeneral<T>(sub) {

          @Override
          public void onNext(T i) {
            super.onNext(f.apply(i));
          }
        });
      }
    };
  }

  private <T, R> Publisher<R> biMapPub(Publisher<T> pub, Function<T, R> f) {

    return new Publisher<R>() {
      @Override
      public void subscribe(Subscriber<? super R> sub) {
        pub.subscribe(new BiDelegateSub<T, R>(sub) {

          @Override
          public void onNext(T i) {
            sub.onNext(f.apply(i));
          }
        });
      }
    };
  }

  private Publisher<Integer> iterPub(List<Integer> iter) {

    return (Subscriber<? super Integer> sub) -> {
      sub.onSubscribe(new Subscription() {
        @Override
        public void request(long n) {

          try {
            iter.forEach(s -> sub.onNext(s));
            sub.onComplete();
          } catch (Throwable t) {
            sub.onError(t);
          }

        }

        @Override
        public void cancel() {

        }
      });
    };
  }

}
