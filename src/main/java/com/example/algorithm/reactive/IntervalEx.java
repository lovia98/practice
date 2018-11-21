package com.example.algorithm.reactive;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class IntervalEx {

  @Test
  public void test() throws InterruptedException {

    Publisher<Integer> pub = sub -> {
      sub.onSubscribe(new Subscription() {

        int no = 0;
        boolean cancelled = false;

        @Override
        public void request(long n) {

          ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
          exec.scheduleAtFixedRate(() -> {

            if(cancelled) {
              exec.shutdown();
              return;
            }
            sub.onNext(no++);

          }, 0, 300, TimeUnit.MILLISECONDS);

        }

        @Override
        public void cancel() {
          cancelled = true;
        }
      });
    };

    Publisher<Integer> takePub = sub -> {
      pub.subscribe(new Subscriber<Integer>() {

        int count = 0;
        Subscription subscription;

        @Override
        public void onSubscribe(Subscription subscription) {
          this.subscription = subscription;
          sub.onSubscribe(subscription);
        }

        @Override
        public void onNext(Integer integer) {
          sub.onNext(integer);
          if (++count > 4) {
            this.subscription.cancel();
          }
        }

        @Override
        public void onError(Throwable throwable) {
          sub.onError(throwable);
        }

        @Override
        public void onComplete() {
          sub.onComplete();
        }
      });
    };

    takePub.subscribe(new Subscriber<Integer>() {
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
    });

    System.out.println("exit");
    TimeUnit.SECONDS.sleep(5);

  }
}
