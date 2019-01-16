package com.example.study.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 미세먼지를 위험성을 알리는 data 서비스
 */
public class FineDustData implements Publisher {

  List<Describer> describers;

  int currentLevel;
  int dangersLevel;

  public FineDustData(int dangersLevel) {
    this.dangersLevel = dangersLevel;
    this.describers = new ArrayList<>();
  }

  @Override
  public void registerDescriber(Describer dc) {
    describers.add(dc);
  }

  @Override
  public void removeDescriber(Describer dc) {
    describers.remove(dc);
  }

  @Override
  public void notifyDescribers() {
    for (Describer dc : describers) {
      dc.alert();
    }
  }

  public void changeDustLevel(int level) {
    this.currentLevel = level;

    if(currentLevel >= dangersLevel) {
      notifyDescribers();
    }
  }
}
