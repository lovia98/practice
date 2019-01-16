package com.example.study.algorithm.hash;

import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;

public class Spy {

  @Test
  public void test() {
    Assert.assertEquals(5, solution(new String[][]{
        new String[]{"yellow_hat", "headgear"},
        new String[]{"blue_sunglasses", "eyewear"},
        new String[]{"green_turban", "headgear"}
    }));

    Assert.assertEquals(3, solution(new String[][]{
        new String[]{"crow_mask", "face"},
        new String[]{"blue_sunglasses", "face"},
        new String[]{"moky_makeup", "face"}
    }));

    Assert.assertEquals(7, solution(new String[][]{
        new String[]{"crow_mask", "headgear"},
        new String[]{"blue_sunglasses", "eyewear"},
        new String[]{"black_sunglasses", "eyewear"},
        new String[]{"moky_makeup", "face"}
    }));
  }

  public int solution(String[][] clothes) {

    int answer = 0;

    HashMap<String, Integer> clothesMap = new HashMap<>();

    for (String[] clothe : clothes) {
      clothesMap.put(clothe[1], clothesMap.getOrDefault(clothe[1], 0) + 1);
    }

    if(clothesMap.size() == 1) {
      return clothes.length;
    }

    for(String key : clothesMap.keySet()){
      answer += clothesMap.get(key)+1;
    }

    return answer;
  }

}
