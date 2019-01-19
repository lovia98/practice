package com.example.study.algorithm.search_algorithm;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;


public class BinarySearch {

    @Test
    public void test() {

        List<Integer> integers = Stream.iterate(1, it -> it + 1)
            .limit(100)
            .collect(Collectors.toList());

        //assertThat(binarySearch(integers.toArray(new Integer[integers.size()]), 10)).isEqualTo(9);

        assertThat(binarySearch(new int[]{1, 3, 5, 7, 9}, 3)).isEqualTo(1);
    }

    public int binarySearch(int[] list, int number) {

        int find = 0;
        int start = 0, end = list.length - 1;

        while (start <= end) {

            int mid = (start + end) / 2;

            if (list[mid] == number) {
                find = mid;
                break;
            }

            if (list[mid] > number) {
                //search start to mid-1
                end = mid - 1;

            } else if (list[mid] < number) {
                //search mid to end
                start = mid + 1;
            }
        }

        return find;
    }

    @Test
    public void recursive_test() {
        assertThat(binarySearchR(new int[]{1, 3, 5, 7, 9}, 3)).isEqualTo(1);
        assertThat(binarySearchR(new int[]{1, 3, 5, 7, 9}, 8)).isEqualTo(-1);
    }

    public int binarySearchR(int[] list, int number) {

        return binarySearchR(list, 0, list.length - 1, number);
    }

    private int binarySearchR(int[] list, int start, int end, int number) {

        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;

        if (list[mid] < number) {
            return binarySearchR(list, mid + 1, end, number);
        } else if (list[mid] > number) {
            return binarySearchR(list, start, mid - 1, number);
        } else {
            return mid;
        }
    }
}
