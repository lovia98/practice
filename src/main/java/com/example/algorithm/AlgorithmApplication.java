package com.example.algorithm;

import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Slf4j
@EnableAsync
public class AlgorithmApplication {

  @RestController
  public static class MyController {

    @GetMapping("/async")
    public String async() {

    }
  }

  @Component
  public static class MyService {

    @Async(value = "tp")
    //public Future<String> hello() throws InterruptedException {
    public ListenableFuture<String> hello() throws InterruptedException {
      log.info("hello()");
      Thread.sleep(2000);
      return new AsyncResult<>("Hello");
    }
  }

//  @Bean
//  ThreadPoolTaskExecutor tp() {
//
//    ThreadPoolTaskExecutor te = new ThreadPoolTaskExecutor();
//    te.setCorePoolSize(10);
//    te.setMaxPoolSize(100);
//    te.setQueueCapacity(200);
//    te.setThreadNamePrefix("myThread");
//    te.initialize();
//
//    return te;
//  }

  public static void main(String[] args) {

    try(ConfigurableApplicationContext c = SpringApplication.run(AlgorithmApplication.class, args)) {

    }
  }

//  @Autowired
//  MyService myService;
//
//  @Bean
//  ApplicationRunner run() {
//    return args -> {
//      log.info("run()");
//
//      ListenableFuture<String> f = myService.hello();
//      f.addCallback(s-> System.out.println(s)
//        , e-> System.out.println(e.getMessage()));
//
//      log.info("exit");
//
////      Future<String> f = myService.hello();
////      log.info("exit: " + f.isDone());
//    };
//  }


//  @RestController
//  public static class Controller {
//
//    @RequestMapping("/hello")
//    public Publisher<String> hello(String name) {
//      return new Publisher<String>() {
//        @Override
//        public void subscribe(Subscriber<? super String> s) {
//          s.onSubscribe(new Subscription() {
//            @Override
//            public void request(long n) {
//              s.onNext("Hello " + name);
//              s.onComplete();
//            }
//
//            @Override
//            public void cancel() {
//
//            }
//          });
//        }
//      };
//    }
//  }
}
