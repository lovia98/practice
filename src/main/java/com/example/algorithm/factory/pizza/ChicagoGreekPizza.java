package com.example.algorithm.factory.pizza;

import com.example.algorithm.factory.OrderPizzaPractice.PizzaIngredientFactory;

/**
 * @author 한주희
 * @vesion 1.0
 * @since 2018-12-17
 */
public class ChicagoGreekPizza extends Pizza {

  PizzaIngredientFactory ingredientFactory;

  public ChicagoGreekPizza(
      PizzaIngredientFactory ingredientFactory) {
    this.ingredientFactory = ingredientFactory;
  }

  @Override
  public void prepare() {
    System.out.println("Preparing " + name);
    dough = ingredientFactory.createDough();
    sauce = ingredientFactory.createSauce();
    cheese = ingredientFactory.createCheese();
  }
}
