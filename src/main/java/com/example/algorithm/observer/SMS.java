package com.example.algorithm.observer;

/**
 * @Auth 한주희
 * @Vesion 1.0
 * @Since 2018-12-10
 */
public class SMS implements Describer {

  Publisher pb;

  public SMS(Publisher pb) {
    this.pb = pb;
    pb.registerDescriber(this);
  }

  @Override
  public void alert() {
    System.out.println("SMS발송! - 미세먼지가 위험수준에 도달하였습니다.\n 외출하실때 마스크 착용하세요!!");
  }

  @Override
  public void stopDescribe() {
    pb.removeDescriber(this);
  }
}
