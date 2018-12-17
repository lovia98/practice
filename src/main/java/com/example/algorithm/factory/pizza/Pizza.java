package com.example.algorithm.factory.pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 한주희
 * @vesion 1.0
 * @since 2018-12-17
 */
public abstract class Pizza {

  protected String name;
  protected String dough;
  protected String sauce;
  protected List toppings = new ArrayList();

  public void prepare() {

    System.out.println("preparing " + name);
    System.out.println("dough " + dough);
    System.out.println("preparing " + sauce);
    toppings.forEach(System.out::println);

  }

  public void bake() {
    System.out.println("bake 35");
  }

  public void cut() {
    System.out.println("cut 11");
  }

  public void box() {
    System.out.println("place pizza in red box");
  }

  public String getName() {
    return name;
  }
}
