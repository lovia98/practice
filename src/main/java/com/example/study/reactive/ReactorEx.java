package com.example.study.reactive;

import org.junit.Test;
import reactor.core.publisher.Flux;

public class ReactorEx {

  @Test
  public void test() {

    Flux.create(e -> {
      e.next(1);
      e.next(2);
      e.next(3);
      e.complete();
    })
        .log()
        .subscribe(s -> System.out.println(s));

  }

  @Test
  public void test1() {

    Flux.<Integer>create(e -> {
      e.next(1);
      e.next(2);
      e.next(3);
      e.complete();
    })
        .log()
        .map(s -> s * 10)
        .log()
        .subscribe(System.out::println);

  }

  @Test
  public void test2() {

    Flux.<Integer>create(e -> {
      e.next(1);
      e.next(2);
      e.next(3);
      e.complete();
    })
        .log()
        .map(s -> s * 10)
        .reduce(0, (a, b) -> a + b)
        .log()
        .subscribe(System.out::println);

  }
}
