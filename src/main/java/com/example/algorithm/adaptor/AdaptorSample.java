package com.example.algorithm.adaptor;

import org.junit.Test;

public class AdaptorSample {

  public interface Duck {

    public void quack();
    public void fly();
  }

  public class MallardDuck implements Duck {

    @Override
    public void quack() {
      System.out.println("Quack");
    }

    @Override
    public void fly() {
      System.out.println("Fly");
    }
  }

  public interface Turkey {
    public void gobble();
    public void fly();
  }

  public class WildTurykey implements Turkey {

    @Override
    public void gobble() {
      System.out.println("Gobble");
    }

    @Override
    public void fly() {
      System.out.println("little fly");
    }
  }

  public class TurkeyAdapter implements Duck {

    Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
      this.turkey = turkey;
    }

    @Override
    public void quack() {
      turkey.gobble();
    }

    @Override
    public void fly() {
      for (int i = 0; i < 5; i++) {
      turkey.fly();
      }
    }
  }

  @Test
  public void test() {
    MallardDuck duck = new MallardDuck();

    WildTurykey turkey = new WildTurykey();
    Duck turkeyAdapter = new TurkeyAdapter(turkey);    //터키 타입의 새를 어댑터에 끼어서 오리로 만듬

    System.out.println("The Turkey says...");
    turkey.gobble();
    turkey.fly();

    System.out.println("\nThe Duck says...");
    testDuck(duck);

    System.out.println("\nThe TurkyAdapter says...");
    testDuck(turkeyAdapter);
  }

  private void testDuck(Duck duck) {
    duck.quack();
    duck.fly();
  }

  //question : Duck 을 Tukey로 만들어 주는 어댑터를 만들어 보자.

  public class DuckAdptoer implements Turkey {

    Duck duck;

    public DuckAdptoer(Duck duck) {
      this.duck = duck;
    }

    @Override
    public void gobble() {
      duck.quack();
    }

    @Override
    public void fly() {
      duck.fly();
    }
  }

  @Test
  public void test2() {

    MallardDuck duck = new MallardDuck();

    WildTurykey turkey = new WildTurykey();

    Turkey duckAdapter = new DuckAdptoer(duck);

    duckAdapter.fly();
    duckAdapter.gobble();
  }
}
