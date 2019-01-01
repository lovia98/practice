package com.example.study.factory.pizzaStore;

import com.example.study.factory.pizza.Pizza;

/**
 * @author 한주희
 * @vesion 1.0
 * @since 2018-12-17
 */
public abstract class PizzaStore {

  public Pizza orderPizza(String type) {

    Pizza pizza;

    pizza = createPizza(type);

    pizza.prepare();
    pizza.bake();
    pizza.cut();
    pizza.box();

    return pizza;
  }

  public abstract Pizza createPizza(String type);
}
