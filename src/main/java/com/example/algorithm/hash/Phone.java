package com.example.algorithm.hash;

import java.util.Arrays;
import java.util.HashSet;
import org.junit.Assert;
import org.junit.Test;

public class Phone {

  @Test
  public void test() {

    Assert.assertEquals(false, solution(new String[]{
        "119", "97674223", "1195524421"
    }));

    Assert.assertEquals(true, solution(new String[]{
        "123", "456", "789"
    }));

    Assert.assertEquals(false, solution(new String[]{
        "12", "123", "1235", "567", "88"
    }));

    Assert.assertEquals(false, solution(new String[]{
        "88", "123", "1235", "567", "12"
    }));
  }

  public boolean solution(String[] phone_book) {

    HashSet<String> prefix = new HashSet<>();

    Arrays.sort(phone_book);

    boolean answer = true;
    for (String phone : phone_book) {

      for (String key : prefix) {
        if (phone.indexOf(key) == 0) {
          answer = false;
          break;
        }
      }

      if (answer) {
        prefix.add(phone);
      } else {
        break;
      }
    }

    return answer;
  }
}
