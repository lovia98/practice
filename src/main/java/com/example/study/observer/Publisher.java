package com.example.study.observer;

/**
 * @Auth 한주희
 * @Vesion 1.0
 * @Since 2018-12-10
 */
public interface Publisher {

  void registerDescriber(Describer db);
  void removeDescriber(Describer db);
  void notifyDescribers();
}
