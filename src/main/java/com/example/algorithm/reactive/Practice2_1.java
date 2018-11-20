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

/*
 * publisher -> [Data1] -> Op1 > [Data2] -> Op2 -> [Data3] -> Subscriber
 *
 *   - pub -> data1 -> mapPub -> data2 -> logSub : downStream
 *                  < - subscribe(logSub)        : upStream
 *                  -> onSubscritbe(s)
 *                  -> onNext
 *                  -> onNext
 *                  -> onComplete
 * 1. map (d1 -> f -> d2)
 */

@Slf4j
public class Practice2_1 {

  @Test
  public void test() {

    Publisher<Integer> pub = iterPub(
        Stream.iterate(1, a -> a + 1).limit(10).collect(Collectors.toList()));

    //Publisher<Integer> mapPub = mapPub(pub, (Function<Integer, Integer>) s -> s * 10);
    //Publisher<Integer> map2Pub = mapPub(pub, (Function<Integer, Integer>) s -> -s);
    //Publisher<Integer> sumPub = sumPub(pub);
    Publisher<Integer> reducePub = reducePub(pub, 0,
        (BiFunction<Integer, Integer, Integer>) (a, b) -> a + b);

    //sub가 구독을 시작함.
    reducePub.subscribe(logSub());
  }

  private Publisher<Integer> reducePub(Publisher<Integer> pub, int init,
      BiFunction<Integer, Integer, Integer> bf) {

    return new Publisher<Integer>() {
      @Override
      public void subscribe(Subscriber<? super Integer> sub) {
        pub.subscribe(new DelegateSub(sub){

          int result = init;

          @Override
          public void onNext(Integer i) {
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


  private Publisher<Integer> sumPub(Publisher<Integer> pub) {

    return new Publisher<Integer>() {
      @Override
      public void subscribe(Subscriber<? super Integer> sub) {
        pub.subscribe(new DelegateSub(sub) {

          int sum = 0;

          @Override
          public void onNext(Integer i) {
            //sub.onNext(i);
            sum += i;
          }

          @Override
          public void onComplete() {
            sub.onNext(sum);
            sub.onComplete();
          }
        });
      }
    };
  }

  private Publisher<Integer> mapPub(Publisher<Integer> pub, Function<Integer, Integer> f) {

    return new Publisher<Integer>() {

      @Override
      public void subscribe(Subscriber<? super Integer> sub) {

        pub.subscribe(new DelegateSub(sub) {

          @Override
          public void onNext(Integer i) {
            super.onNext(f.apply(i));
          }
        });
      }
    };
  }

  private Subscriber<Integer> logSub() {
    return new Subscriber<Integer>() {
      @Override
      public void onSubscribe(Subscription s) {
        log.debug("onSubscribe:");
        s.request(Long.MAX_VALUE);
      }

      @Override
      public void onNext(Integer i) {
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

  private Publisher<Integer> iterPub(List<Integer> iter) {

    return new Publisher<Integer>() {
      @Override
      public void subscribe(Subscriber<? super Integer> sub) {
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
      }
    };
  }

}
