package com.example.algorithm;

import com.example.study.observer.AppPush;
import com.example.study.observer.FineDustData;
import com.example.study.observer.SMS;
import com.example.study.observer2.AppPush_;
import com.example.study.observer2.FineDustData_;
import com.example.study.observer2.SMS_;
import org.junit.Test;

/**
 * @Auth 한주희
 * @Vesion 1.0
 * @Since 2018-12-10
 */
public class TestFineDustObserver {

  @Test
  public void test() {

    //publisher정의
    FineDustData dustData = new FineDustData(10);

    //앱푸시 구독자
    AppPush appPush = new AppPush(dustData);
    //SMS 구독자
    SMS sms = new SMS(dustData);


    //미세먼지 농도가 올라간다.
    dustData.changeDustLevel(5);
    //..점점 올라가더니 위험수치에 다다랐다.
    dustData.changeDustLevel(10);
  }

  @Test
  public void test2() {

    FineDustData_ dustData = new FineDustData_(10);

    AppPush_ appPush = new AppPush_(dustData);
    SMS_ sms = new SMS_(dustData);

    dustData.changeDustLevel(5);
    dustData.changeDustLevel(10);
  }
}
