package com.example.algorithm.observer2;

import com.example.algorithm.observer.Describer;
import java.util.Observable;
import java.util.Observer;

/**
 * @Auth 한주희
 * @Vesion 1.0
 * @Since 2018-12-10
 */
public class SMS_ implements Observer, Describer {

  Observable ob;

  public SMS_(Observable ob) {
    this.ob = ob;
    ob.addObserver(this);
  }

  @Override
  public void alert() {
    System.out.println("SMS발송! - 미세먼지가 위험수준에 도달하였습니다.\n 외출하실때 마스크 착용하세요!!");
  }

  @Override
  public void stopDescribe() {
    ob.deleteObserver(this);
  }

  @Override
  public void update(Observable o, Object arg) {
    alert();
  }
}
