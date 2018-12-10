package com.example.algorithm.observer;

/**
 * @Auth 한주희
 * @Vesion 1.0
 * @Since 2018-12-10
 */
public class AppPush implements Describer {

  Publisher pb;

  public AppPush(Publisher pb) {
    pb.registerDescriber(this);
  }

  @Override
  public void alert() {
    System.out.println("앱푸시! - 미세먼지가 위험수준에 도달하였습니다.");
  }

  @Override
  public void stopDescribe(){
    pb.removeDescriber(this);
  }
}
