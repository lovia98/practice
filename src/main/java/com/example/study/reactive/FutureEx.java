package com.example.study.reactive;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class FutureEx {

  @Test
  public void test() throws InterruptedException {

    ExecutorService es = Executors.newCachedThreadPool();

    //Runnable
    es.execute(() -> {
      try {
        Thread.sleep(1000);
        log.info("Async");

      } catch (InterruptedException e) {
        log.error(e.getMessage());
      }

    });
    log.info("Exit");

    //TimeUnit.SECONDS.sleep(2);
  }

  @Test
  public void test1() throws InterruptedException {

    ExecutorService es = Executors.newCachedThreadPool();

    //Callable
    es.submit(() -> {
      Thread.sleep(2000);
      log.info("Async");
      return "Hello";
    });

    log.info("Exit");

    //TimeUnit.SECONDS.sleep(2);
  }

  @Test
  public void test2() throws InterruptedException, ExecutionException {

    ExecutorService es = Executors.newCachedThreadPool();

    //Callable
    Future<String> f = es.submit(() -> {
      Thread.sleep(2000);
      log.info("Async");
      return "Hello";
    });

    //f.get() 문장은 f의 비동기처리가 완료 될때까지 기다린다. : Blocking
    System.out.println(f.get());
    log.info("Exit");
  }

  @Test
  public void test3() throws InterruptedException, ExecutionException {

    ExecutorService es = Executors.newCachedThreadPool();

    //Callable
    Future<String> f = es.submit(() -> {
      Thread.sleep(2000);
      log.info("Async");
      return "Hello";
    });

    System.out.println(f.isDone());
    Thread.sleep(2100);
    log.info("Exit");
    System.out.println(f.isDone());
    System.out.println(f.get());
  }

  //비동기 처리의 기본
  //Future - 결과를 전달 받을 수 있는데 Blocking함
  //CallBack - callback을 만듬 (FutureTask를 구현한 클래스 구현..)

  //FutureTask를 이용한 콜백
  @Test
  public void test4() throws InterruptedException, ExecutionException {

    ExecutorService es = Executors.newCachedThreadPool();

    //Callable
    FutureTask<String> f = new FutureTask<String>(() -> {
      Thread.sleep(2000);
      log.info("Async");
      return "Hello";
    }) {
      @Override
      protected void done() {
        try {
          System.out.println(get());

        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    };

    es.execute(f);
    es.shutdown();

    TimeUnit.SECONDS.sleep(5);
  }

  @Test
  public void test5() throws InterruptedException {

    ExecutorService es = Executors.newCachedThreadPool();

    CallbackFutreTask f = new CallbackFutreTask(() -> {
      Thread.sleep(2000);
      if(1==1) throw new RuntimeException("Async ERROR!!!");
      log.info("Async");
      return "Hello";
    }
    ,s-> System.out.println("Result: " + s)
    ,e -> System.out.println("Error: " + e.getMessage()));

    es.execute(f);
    es.shutdown();

    TimeUnit.SECONDS.sleep(3);
  }

  interface SuccessCallback {

    void onSuccess(String result);
  }

  interface ExceptionCallback {

    void onError(Throwable t);
  }

  public static class CallbackFutreTask extends FutureTask<String> {

    SuccessCallback sc;
    ExceptionCallback ec;

    public CallbackFutreTask(Callable<String> callable, SuccessCallback sc, ExceptionCallback ec) {
      super(callable);
      this.sc = Objects.requireNonNull(sc);
      this.ec = Objects.requireNonNull(ec);
    }

    @Override
    protected void done() {
      try {
        sc.onSuccess(get());
      } catch (InterruptedException e) { //수행 하지 말고 종료 해라 (오류는 아님)
//        e.printStackTrace();
        Thread.currentThread().interrupt(); //이걸로 처리 충분
      } catch (ExecutionException e) {
        ec.onError(e.getCause());
      }
    }


  }
}
