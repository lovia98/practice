package com.example.study.singleton;

import org.junit.Test;

public class SingleTest {

//    public static void main(String[] args) {
//
//        System.out.println(FirstSingleton.name);
//        System.out.println("============1");
//        FirstSingleton a = FirstSingleton.getInstance();
//        System.out.println("============2");
//    }

    public static void main(String[] args) {

        System.out.println("1=================");

        SecondSingleton a = SecondSingleton.getInstance();

        System.out.println("2=================");

        SecondSingleton b = SecondSingleton.getInstance();
    }
}

class LazySingleton {


}

class SecondSingleton {

    private static final SecondSingleton instace = new SecondSingleton();

    private SecondSingleton() {
        System.out.println("contructor");
    }

    public static SecondSingleton getInstance() {
        System.out.println("getInstance");

        return instace;
    }
}

class FirstSingleton {

//    public static final String name= "singleton";
    public static String name= "singleton";

    private static final FirstSingleton instance = new FirstSingleton();

    private FirstSingleton() {
        System.out.println("Hi");
    }

    public static FirstSingleton getInstance() {
        return instance;
    }
}





