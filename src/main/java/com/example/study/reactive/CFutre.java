package com.example.study.reactive;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class CFutre {

  @Test
  public void main() throws ExecutionException, InterruptedException {

    CompletableFuture
        .supplyAsync(() -> {
          log.info("runAsync");

          return 1;
        })
        .thenApply((s) -> { //map과 유사
          log.info("thenApply {}", s);
          return s + 1;
        })
        .thenAccept(s2 -> log.info("thenAccept {}", s2));

    log.info("exit");

    ForkJoinPool.commonPool().shutdown();
    ForkJoinPool.commonPool().awaitTermination(10, TimeUnit.SECONDS);
  }

  @Test
  public void main1() throws ExecutionException, InterruptedException {

    CompletableFuture
        .supplyAsync(() -> {
          log.info("runAsync");
          return 1;
        })
        .thenCompose((s) -> {   //flatmap과 유사
          log.info("thenApply {}", s);
          return CompletableFuture.completedFuture(s + 1);  //새로운 비동기 작업
        })
        .thenAccept(s2 -> log.info("thenAccept {}", s2));

    log.info("exit");

    ForkJoinPool.commonPool().shutdown();
    ForkJoinPool.commonPool().awaitTermination(10, TimeUnit.SECONDS);
  }

  //예외 처리
  @Test
  public void main2() throws ExecutionException, InterruptedException {

    CompletableFuture
        .supplyAsync(() -> {
          log.info("runAsync");
          if (1 == 1) {
            throw new RuntimeException();
          }
          return 1;
        })
        .thenCompose((s) -> {
          log.info("thenApply {}", s);
          return CompletableFuture.completedFuture(s + 1);  //새로운 비동기 작업
        })
        .exceptionally(e -> -10)
        .thenAccept(s2 -> log.info("thenAccept {}", s2));

    log.info("exit");

    ForkJoinPool.commonPool().shutdown();
    ForkJoinPool.commonPool().awaitTermination(10, TimeUnit.SECONDS);
  }

  //서로 다른 쓰레드에서 처리 하고자 할때
  @Test
  public void main3() throws ExecutionException, InterruptedException {

    ExecutorService es = Executors.newFixedThreadPool(10);

    CompletableFuture
        .supplyAsync(() -> {
          log.info("runAsync");
          return 1;
        })
        .thenCompose(s -> {
          log.info("thenApply {}", s);
          return CompletableFuture.completedFuture(s + 1);  //새로운 비동기 작업
        })
        .thenApplyAsync(s2 -> {
          log.info("thenApply {}", s2);
          return s2 * 3;
        }, es)
        .exceptionally(e -> -10)
        .thenAcceptAsync(s3 -> log.info("thenAccept {}", s3), es);

    log.info("exit");

    ForkJoinPool.commonPool().shutdown();
    ForkJoinPool.commonPool().awaitTermination(10, TimeUnit.SECONDS);
  }
}
