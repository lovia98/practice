package com.example.algorithm.basic;


import static org.assertj.core.api.Assertions.assertThat;

import com.sun.tools.javac.util.ArrayUtils;
import java.util.Arrays;
import org.junit.Test;

public class RecursivePractice {

  /*
   * 가로 세로로 주어진 토지 면적에 나올수 있는
   * 가장 큰 정사각형 크기
   */
  @Test
  public void test() {
    assertThat(solution(1680, 640)).isEqualTo(80);
  }

  public int solution(int with, int height) {

    int longer = 0;
    int shorter = 0;

    if (with > height) {
      longer = with;
      shorter = height;

    } else {
      longer = height;
      shorter = with;
    }

    int mod = longer % shorter;

    if (mod == 0) {
      return shorter;
    } else {
      return solution(shorter, mod);
    }

  }


  /*
   * 퀵 정렬
   */
  @Test
  public void quickOrderTest() {

    int[] array = new int[]{2, 3, 4, 1, 5};
    quickSort(array, 0, array.length - 1);

    assertThat(array).isEqualTo(new int[]{1, 2, 3, 4, 5});
  }

  public void quickSort(int[] array, int begin, int end) {

    if (begin < end) {

      int pivot = partition(array, begin, end);

      quickSort(array, begin, pivot - 1);
      quickSort(array, pivot + 1, end);

    }
  }

  private int partition(int[] array, int begin, int end) {

    int pivot = array[(begin + end) / 2];

    int left = begin;
    int right = end;

    while (left < right) {

      while ((array[left] < pivot) && (left < right)) {
        left++;
      }

      while ((array[right] > pivot) && (left < right)) {
        right--;
      }

      if (left < right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
      }
    }

    return left;
  }

  @Test
  public void testSum() {
    assertThat(sum(new int[]{1, 2, 3, 4, 5}, 0, 4)).isEqualTo(15);
  }

  public int sum(int array[], int begin, int end) {

    if (end - begin == 1) {
      return (array[begin] + array[end]);
    } else {

      int pivot = (begin + end) / 2;
      return sum(array, begin, pivot - 1) + array[pivot] + sum(array, pivot + 1, end);
    }
  }

}
