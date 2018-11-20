package com.example.algorithm.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class Runner {

  /*
    수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.

    participant - 마라톤에 참여한 선수들의 이름이 담긴 배열
    completion - 완주한 선수들의 이름이 담긴 배열

    완주하지 못한 선수들의 이름을 return하도록 함수를 작성해 주세요.

    * 제한사항
        - 마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
        - completion의 길이는 participant의 길이보다 1 작습니다.
        - 참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
        - 참가자 중에는 동명이인이 있을 수 있습니다.
   */
  @Test
  public void test() {

//    Assert.assertEquals("leo",
//        solution(new String[]{"leo", "kiki", "eden"},
//            new String[]{"eden", "kiki"}));
//
//    Assert.assertEquals("vinko",
//        solution(new String[]{"marina", "josipa", "nikola", "vinko", "filipa"},
//            new String[]{"josipa", "filipa", "marina", "nikola"}));

    Assert.assertEquals("mislav",
        solution(new String[]{"mislav", "stanko", "mislav", "ana"},
            new String[]{"stanko", "ana", "mislav"}));

//    Assert.assertEquals("abc",
//        solution(new String[]{"ab", "abc", "ef", "abc", "abc", "abc", "gd", "fg"},
//            new String[]{"abc", "abc", "ef", "abc", "gd", "fg", "ab"}));

  }

  public String solution(String[] participant, String[] completion) {

    String answer = "";

    HashMap<String, Integer> abscentMap = new HashMap<>();

    for (String player : participant) {
      abscentMap.put(player, abscentMap.getOrDefault(player, 0) + 1);
    }
    for (String player : completion) {
      abscentMap.put(player, abscentMap.get(player) - 1);
    }

    for (String key : abscentMap.keySet()) {
      if(abscentMap.get(key) != 0) {
        answer = key;
      }
    }

    return answer;
  }

  public String solution2(String[] participant, String[] completion) {

    Arrays.sort(participant);
    Arrays.sort(completion);

    int i;
    for (i = 0; i < completion.length; i++) {
      if (!participant[i].equals(completion[i])) {
        return participant[i];
      }
    }

    return participant[i];
  }

  public String mySolution(String[] participant, String[] completion) {

    Map<String, Integer> completionMap = new HashMap<>();

    if (completion.length == 0) {
      return "";
    }

    for (int i = 0; i < completion.length; i++) {
      completionMap.put(completion[i], i);
    }

    String answer = "";
    for (int i = 0; i < participant.length; i++) {

      if (!completionMap.containsKey(participant[i])) {
        answer = participant[i];
      } else {
        completionMap.remove(participant[i], completionMap.get(participant[i]));
      }
    }

    return answer;
  }
}
