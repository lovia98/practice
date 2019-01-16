package com.example.study.reactive;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class SchedulerEx {

  // Reactive Streams
  @Test
  public void test() {

    Publisher<Integer> pub = sub -> {
      sub.onSubscribe(new Subscription() {
        @Override
        public void request(long n) {
          log.debug("request()");

          sub.onNext(1);
          sub.onNext(2);
          sub.onNext(3);
          sub.onNext(4);
          sub.onNext(5);
          sub.onComplete();
        }

        @Override
        public void cancel() { }
      });

    };//pub

    //operator - subscribeOn
    Publisher<Integer> subOnPub = sub -> {
      ExecutorService es = Executors.newSingleThreadExecutor();
      es.execute(() -> pub.subscribe(sub));
//      pub.subscribe(sub);
    };

    Publisher<Integer> pubOnPub = sub -> {
      pub.subscribe(new Subscriber<Integer>() {

        ExecutorService es = Executors.newSingleThreadExecutor();

        @Override
        public void onSubscribe(Subscription s) {
          sub.onSubscribe(s);
        }

        @Override
        public void onNext(Integer integer) {
          es.execute(()->sub.onNext(integer));
          //sub.onNext(integer);
        }

        @Override
        public void onError(Throwable throwable) {
          es.execute(()->sub.onError(throwable));
          es.shutdown();
          //sub.onError(throwable);
        }

        @Override
        public void onComplete() {
          es.execute(()->sub.onComplete());
          es.shutdown();
          //sub.onComplete();
        }
      });
    };

    //sub
    subOnPub.subscribe(new Subscriber<Integer>() {
      @Override
      public void onSubscribe(Subscription s) {
        log.debug("onSubscribe");
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
    });

    System.out.println("exit");
  }

}
