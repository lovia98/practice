package com.example.study.factory;


import com.example.study.factory.ingredient.Cheese;
import com.example.study.factory.ingredient.Clams;
import com.example.study.factory.ingredient.Dough;
import com.example.study.factory.ingredient.FreshClams;
import com.example.study.factory.ingredient.Garlic;
import com.example.study.factory.ingredient.MarinaraSauce;
import com.example.study.factory.ingredient.Mushroom;
import com.example.study.factory.ingredient.Onion;
import com.example.study.factory.ingredient.Pepperoni;
import com.example.study.factory.ingredient.RedPepper;
import com.example.study.factory.ingredient.ReggianoCheese;
import com.example.study.factory.ingredient.Sauce;
import com.example.study.factory.ingredient.SlicePepperoni;
import com.example.study.factory.ingredient.ThinCrustDough;
import com.example.study.factory.ingredient.Veggies;
import com.example.algorithm.factory.pizza.*;
import com.example.algorithm.factory.pizzaStore.*;
import com.example.study.factory.pizza.ChicagoCheesePizza;
import com.example.study.factory.pizza.ChicagoGreekPizza;
import com.example.study.factory.pizza.ChicagoNormalPizza;
import com.example.study.factory.pizza.NYCheesePizza;
import com.example.study.factory.pizza.NYGreekPizza;
import com.example.study.factory.pizza.NYNormalPizza;
import com.example.study.factory.pizza.Pizza;
import com.example.study.factory.pizzaStore.PizzaStore;
import org.junit.Test;

public class OrderPizzaPractice {

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

  class NYPizzaStore extends PizzaStore {

    @Override
    public Pizza createPizza(String type) {

      Pizza pizza = null;
      PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

      if (type.equals("cheese")) {
        pizza = new NYCheesePizza(ingredientFactory);
        pizza.setName("New York Style Cheese Pizza");

      } else if (type.equals("greek")) {
        pizza = new NYGreekPizza(ingredientFactory);
        pizza.setName("New York Style Cheese Pizza");

      } else {
        pizza = new NYNormalPizza(ingredientFactory);
        pizza.setName("New York Style Cheese Pizza");
      }

      return pizza;
    }
  }

  class ChicagoPizzaStore extends PizzaStore {

    Pizza pizza = null;
    PizzaIngredientFactory ingredientFactory = new ChicagoPizzaIngredientFactory();

    @Override
    public Pizza createPizza(String type) {

      if (type.equals("cheese")) {
        pizza = new ChicagoCheesePizza(ingredientFactory);
        pizza.setName("Chicago Style Cheese Pizza");

      } else if (type.equals("greek")) {
        pizza = new ChicagoGreekPizza(ingredientFactory);
        pizza.setName("Chicago Style Greek Pizza");

      } else {
        pizza = new ChicagoNormalPizza(ingredientFactory);
        pizza.setName("Chicago Style Normal Pizza");
      }

      return pizza;
    }
  }

  class PizzaMain {

    //생산자 클래스 : abstract PizzaStore >> NYPizzaStore , ChicagoPizzaStore
    //제품 클래스 : NYPizza, ChicagoPizza

    @Test
    public void orderPizza() {

      NYPizzaStore nyPizzaStore1 = new NYPizzaStore();
      PizzaStore nyPizzaStore = nyPizzaStore1;
      PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();

      Pizza orderPizza1 = nyPizzaStore.orderPizza("cheese");
      Pizza orderPizza2 = chicagoPizzaStore.orderPizza("greek");
    }
  }

  public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
      return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
      return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
      return new ReggianoCheese();
    }

    @Override
    public Veggies[] createVeggies() {
      return new Veggies[]{new Garlic(), new Onion(), new Mushroom(), new RedPepper()};
    }

    @Override
    public Pepperoni createPepperoni() {
      return new SlicePepperoni();
    }

    @Override
    public Clams createClam() {
      return new FreshClams();
    }
  }

  public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
      return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
      return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
      return new ReggianoCheese();
    }

    @Override
    public Veggies[] createVeggies() {
      return new Veggies[]{new Garlic(), new Onion(), new Mushroom(), new RedPepper()};
    }

    @Override
    public Pepperoni createPepperoni() {
      return new SlicePepperoni();
    }

    @Override
    public Clams createClam() {
      return new FreshClams();
    }
  }

}

