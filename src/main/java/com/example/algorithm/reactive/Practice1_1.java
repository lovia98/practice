package com.example.algorithm.reactive;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.Test;

public class Practice1_1 {

  // ReactiveX  > FRP (functional reactive programming)
  // 리액티브란 ? 이벤트에 대해 반응하는 형식의 프로그래밍
  // Observer Pattern
  // Reactive Streams - 표준
  // Java9 API : JAVA8 JDK안에 들어갔음.
  @Test
  public void practice1() {

    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    Iterable<Integer> iter = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    for (Integer i : list) { // for-each 콜론 사용 방식
      System.out.println(i);
    }

    for (Integer i : iter) {
      System.out.println(i);
    }

    //java5이전(Iterator이전)에 쓰던 방식
    for (Iterator<Integer> it = iter.iterator(); it.hasNext(); ) {
      System.out.println(it.next());
    }
  }


  @Test
  public void practice2() {

//    Iterable<Integer> iter = new Iterable<Integer>() {
//
//      @Override
//      public Iterator<Integer> iterator() {
//        return null;
//      }
//
//    };

    Iterable<Integer> iter = () ->

        new Iterator<Integer>() {
          final static int MAX = 10;
          int i = 0;

          @Override
          public boolean hasNext() {
            return i < MAX;
          }

          @Override
          public Integer next() {
            return ++i;
          }
        };

    for (Integer i : iter) {
      System.out.println(i);
    }
  }

  /*
   * 쌍대성
   * Iterable <---> Observable (duality - 애릭마이어)
   *
   * Iterable
   *  사용하는 쪽에서 데이터를 가져 온다. : pull
   *
   * Observable
   *  사용하는 쪽에 데이터를 밀어 넣어 준다. : push
   */

  //Source(publisher) -> Event/Data -> Observer(target)

  //Source : 데이터를 만드는 쪽
  static class IntObservable extends Observable implements Runnable {

    @Override
    public void run() {
      for (int i = 1; i <= 10; i++) {
        setChanged();
        notifyObservers(i);   //push
//        it.next()           //pull
      }
    }
  }

  // DATA method() <-> method(DATA)
  @Test
  public void practice3() {

    //데이터를 받는 쪽
    Observer ob = new Observer() {
      @Override
      public void update(Observable o, Object arg) {
        System.out.println(arg);
      }
    };

    IntObservable io = new IntObservable();
    io.addObserver(ob);

    io.run();
  }

  @Test
  public void ansyncProcess() {

    //데이터를 받는 쪽
    Observer ob = new Observer() {
      @Override
      public void update(Observable o, Object arg) {
        System.out.println(Thread.currentThread().getName() + " " + arg);
      }
    };

    IntObservable io = new IntObservable();
    io.addObserver(ob);

    ExecutorService es = Executors.newSingleThreadExecutor();
    es.execute(io);

    System.out.println(Thread.currentThread().getName() + " EXIT");
    es.shutdown();
  }
}
