package com.example.study.reactive;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class FluxScEx {

  @Test
  public void test() throws InterruptedException {

    //1부터 10까지 publishing
    Flux.range(1, 10)
        //컨슈머 즉, Subscriber가 느린 경우
        .publishOn(Schedulers.newSingle("pub"))
        .log()
//        .subscribeOn(Schedulers.newSingle("sub"))
        .subscribe(
            s -> log.debug("onNext:{}", s));

    System.out.println("exit");
    TimeUnit.SECONDS.sleep(5);
  }

  @Test
  public void test1() throws InterruptedException {

    //유져 쓰레드, 데몬 쓰레드
    Flux.interval(Duration.ofMillis(200))
        .take(10)
        .subscribe(s -> log.debug("onNext:{}", s));

    TimeUnit.SECONDS.sleep(10);
  }
}
