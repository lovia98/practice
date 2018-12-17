package com.example.algorithm.factory;


import com.example.algorithm.factory.pizza.*;
import com.example.algorithm.factory.pizzaStore.*;
import org.junit.Test;

public class OrderPizzaPractice {

  class NYPizzaStore extends PizzaStore {

    @Override
    public Pizza createPizza(String type) {

      if (type.equals("cheese")) {
        return new NYCheesePizza();
      } else if (type.equals("greek")) {
        return new NYGreekPizza();
      } else {
        return new NYNormalPizza();
      }

    }
  }

  class ChicagoPizzaStore extends PizzaStore {

    @Override
    public Pizza createPizza(String type) {

      if (type.equals("cheese")) {
        return new ChicagoCheesePizza();
      } else if (type.equals("greek")) {
        return new ChicagoGreekPizza();
      } else {
        return new ChicagoNormalPizza();
      }

    }
  }


  class PizzaMain {

    //생산자 클래스 : abstract PizzaStore >> NYPizzaStore , ChicagoPizzaStore
    //제품 클래스 : NYPizza, ChicagoPizza

    @Test
    public void orderPizza() {

      PizzaStore nyPizzaStore = new NYPizzaStore();
      PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();

      Pizza orderPizza1 = nyPizzaStore.orderPizza("cheese");
      Pizza orderPizza2 = chicagoPizzaStore.orderPizza("greek");
    }
  }

  class Dough{}
  class Sauce{}
  class Cheese{}
  class Veggies{}
  class Pepperoni{}
  class Clams{}

  /*
   * 원재료 만드는 공장
   */
  public interface PizzaIngredientFactory {

    public Dough createDough();
    public Sauce createSauce();
    public Cheese createCheese();
    public Veggies[] createVeggies();
    public Pepperoni createPepperoni();
    public Clams createClam();
  }

  public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
      return null;
    }

    @Override
    public Sauce createSauce() {
      return null;
    }

    @Override
    public Cheese createCheese() {
      return null;
    }

    @Override
    public Veggies[] createVeggies() {
      return new Veggies[0];
    }

    @Override
    public Pepperoni createPepperoni() {
      return null;
    }

    @Override
    public Clams createClam() {
      return null;
    }
  }

}

