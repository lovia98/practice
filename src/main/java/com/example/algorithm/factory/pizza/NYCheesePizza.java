package com.example.algorithm.factory.pizza;

/**
 * @author 한주희
 * @vesion 1.0
 * @since 2018-12-17
 */
public class NYCheesePizza extends Pizza {

  public NYCheesePizza() {
    name = "nypizza";
    dough = "nydouh";
    sauce = "sauce";

    toppings.add("cheese");
  }
}
