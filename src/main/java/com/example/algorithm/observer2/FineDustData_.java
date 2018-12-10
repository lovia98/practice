package com.example.algorithm.observer2;

import java.util.Observable;

/**
 * java api를 이용한 구현
 */
public class FineDustData_ extends Observable {

  int currentLevel;
  int dangersLevel;

  public FineDustData_(int dangersLevel) {
    this.dangersLevel = dangersLevel;
  }

  public void changeDustLevel(int level) {
    this.currentLevel = level;

    if(currentLevel >= dangersLevel) {
      setChanged(); //Observable의 함수를 호출
      notifyObservers();
    }
  }
}
