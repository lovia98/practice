package com.example.study.decorator;

import org.junit.Test;

public class DecoRatorPattern {

    public abstract class Beverage {

        String description = "no subject";

        public String getDescription() {
            return description;
        }

        public abstract double cost();
    }

    public abstract class CondimentDecorator extends Beverage {

        public abstract String getDescription();
    }

    public class Espresso extends Beverage {

        public Espresso() {
            description = "Espresso";
        }

        @Override
        public double cost() {
            return 1.99;
        }
    }

    public class HouseBlend extends Beverage {

        public HouseBlend() {
            description = "House Blend Coffee";
        }

        @Override
        public double cost() {
            return .99;
        }
    }

    public class DarkRoast extends Beverage {

        public DarkRoast() {
            description = "dark roast";
        }

        @Override
        public double cost() {
            return 0.99;
        }
    }

    public class DeCafain extends Beverage {

        public DeCafain() {
            description = "decafain";
        }

        @Override
        public double cost() {
            return 1.05;
        }
    }

    public class Mocha extends CondimentDecorator {

        Beverage beverage;

        public Mocha(Beverage beverage) {
            this.beverage = beverage;
        }

        @Override
        public String getDescription() {
            return beverage.getDescription() + " + Mocha";
        }

        @Override
        public double cost() {
            return .20 + beverage.cost();
        }
    }

    public class SteamMilk extends CondimentDecorator {

        Beverage beverage;

        public SteamMilk(Beverage beverage) {
            this.beverage = beverage;
        }

        @Override
        public double cost() {
            return .10 + beverage.cost();
        }

        @Override
        public String getDescription() {
            return beverage.getDescription() + " + steam milk";
        }
    }

    public class SoyMilk extends CondimentDecorator {

        Beverage beverage;

        public SoyMilk(Beverage beverage) {
            this.beverage = beverage;
        }

        @Override
        public double cost() {
            return .15 + beverage.cost();
        }

        @Override
        public String getDescription() {
            return beverage.getDescription() + " + soy milk";
        }
    }

    public class WhipingCream extends CondimentDecorator {

        Beverage beverage;

        public WhipingCream(Beverage beverage) {
            this.beverage = beverage;
        }

        @Override
        public double cost() {
            return .10 + beverage.cost();
        }

        @Override
        public String getDescription() {
            return beverage.getDescription() + " + whiping scream";
        }
    }

    @Test
    public void patternTest() {

        //에스프레소
        Beverage espresso = new Espresso();
        espresso = new SteamMilk(espresso); //우유를 첨가하여 까페라떼가 되었음.

        System.out.println(espresso.getDescription() + " $" + espresso.cost());

        //다크로스트
        Beverage darkRoast = new DarkRoast();
        darkRoast = new Mocha(darkRoast);
        darkRoast = new Mocha(darkRoast);
        Beverage darkRoastMochaWhip = new WhipingCream(darkRoast);

        //모카를 두번, 휘핑크림을 얻은 darkRoast
        System.out.println(darkRoast.getDescription() + " $" + darkRoast.cost());

    }
}
