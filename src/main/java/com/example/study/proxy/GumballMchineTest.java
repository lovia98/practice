package com.example.study.proxy;


import org.junit.Test;

public class GumballMchineTest {

  public class GumballMachine {

    String location;
    int count;

    String state;

    public GumballMachine(String location, int count) {
      this.location = location;
      this.count = count;

      this.state = "ready";

    }


    public String getLocation() {
      return location;
    }

    public int getCount() {
      return count;
    }

    public String getState() {
      return state;
    }
  }

  public class GumballMonitor {

    GumballMachine machine;

    public GumballMonitor(GumballMachine machine) {
      this.machine = machine;
    }

    public void report() {
      System.out.println("location : " + machine.getLocation());
      System.out.println("remain Count : " + machine.getCount());
      System.out.println("state : " + machine.getState());
    }

  }

  @Test
  public void test() {

    GumballMachine machine = new GumballMachine("Seattle", 112);
    GumballMonitor monitor = new GumballMonitor(machine);

    monitor.report();
  }

}
