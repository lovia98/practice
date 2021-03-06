package com.example.study.factory.pizza;

import com.example.study.factory.ingredient.Cheese;
import com.example.study.factory.ingredient.Clams;
import com.example.study.factory.ingredient.Dough;
import com.example.study.factory.ingredient.Pepperoni;
import com.example.study.factory.ingredient.Sauce;
import com.example.study.factory.ingredient.Veggies;

/**
 * @author 한주희
 * @vesion 1.0
 * @since 2018-12-17
 */
public abstract class Pizza {

  protected String name;
  protected Dough dough;
  protected Sauce sauce;
  protected Veggies veggies[];
  protected Cheese cheese;
  protected Pepperoni pepperoni;
  protected Clams clam;

  public abstract void prepare();

  public void bake() {
    System.out.println("bake 35");
  }

  public void cut() {
    System.out.println("cut 11");
  }

  public void box() {
    System.out.println("place pizza in red box");
  }

  public void setName(String name) {
    this.name = name;
  }

  public String toString() {
    return "[Pizza name = " + name + "]";
  }
}
