package com.example.algorithm.reactive;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class Practice1_2 {

  //옵져버 패턴의 부족한 점
  /*
   * 1. Complete : 끝나는 개념이 없다. 던지고 받고..한 프로세스가 완료 됐다는걸 어떻게 알지?
   * 2. Error : 던지고 받고 그 사이에 벌어지는 예외에 대한 처리가 빠져 있다.
   *
   * ==> Reactive Streams : 리액티브 프로그래밍이라는 개념을 만들었고, 몇개의 회사들이 모여 표준을 만들었음.
   *   - 두개의 큰 그룹이 있음
   *     --> reactiveX : 마이크로소프트 진영
   *     --> reactive java : java 진영
   */

  @Test
  public void pubSub1() {
    //Publisher <- Observable
    //Subscriber <- Observer
    Iterable<Integer> itr = Arrays.asList(1, 2, 3, 4, 5);

    Publisher p = new Publisher() {

      Iterator<Integer> it = itr.iterator();

      @Override
      public void subscribe(Subscriber subscriber) {

        subscriber.onSubscribe(new Subscription() {
          @Override
          public void request(long n) {

            try {

              while (n-- > 0) {
                if (it.hasNext()) {
                  subscriber.onNext(it.next());

                } else {
                  subscriber.onComplete();
                  break;
                }
              }

            } catch (RuntimeException e) {
              subscriber.onError(e);
            }

          }

          @Override
          public void cancel() {

          }
        });
      }
    };

    Subscriber<Integer> s = new Subscriber<Integer>() {

      Subscription subscription;

      //데이터를 받는 쪽
      @Override
      public void onSubscribe(Subscription subscription) {
        System.out.println("onSubscribe");
        this.subscription = subscription;
        this.subscription.request(1);
      }

      @Override
      public void onNext(Integer item) {
        System.out.println("integer = " + item);
        this.subscription.request(1);
      }

      @Override
      public void onError(Throwable throwable) {
        System.out.println("onError" + throwable.getStackTrace());
      }

      @Override
      public void onComplete() {
        System.out.println("onComplete");
      }
    };

    p.subscribe(s);

  }

  //비동기로 처리
  @Test
  public void pubSub2() throws InterruptedException {

    //Publisher <- Observable
    //Subscriber <- Observer
    Iterable<Integer> itr = Arrays.asList(1, 2, 3, 4, 5);
    ExecutorService es = Executors.newCachedThreadPool();

    Publisher p = new Publisher() {

      Iterator<Integer> it = itr.iterator();

      @Override
      public void subscribe(Subscriber subscriber) {

        subscriber.onSubscribe(new Subscription() {
          @Override
          public void request(long n) {

            es.execute(() -> {

              int i = 0;
              try {

                while (i++ < n) {
                  if (it.hasNext()) {
                    subscriber.onNext(it.next());

                  } else {
                    subscriber.onComplete();
                    break;
                  }
                }

              } catch (RuntimeException e) {
                subscriber.onError(e);
              }

            });
          }

          @Override
          public void cancel() {
          }

        });
      }
    };

    Subscriber<Integer> s = new Subscriber<Integer>() {

      Subscription subscription;

      //데이터를 받는 쪽
      @Override
      public void onSubscribe(Subscription subscription) {
        System.out.println("onSubscribe");
        this.subscription = subscription;
        this.subscription.request(1);
      }

      @Override
      public void onNext(Integer item) {
        System.out.println(Thread.currentThread().getName() +  " onNext = " + item);
        this.subscription.request(1);
      }

      @Override
      public void onError(Throwable throwable) {
        System.out.println("onError" + throwable.getMessage());
      }

      @Override
      public void onComplete() {
        System.out.println("onComplete");
      }
    };

    p.subscribe(s);

    es.awaitTermination(10, TimeUnit.HOURS);
    es.shutdown();

  }
}
