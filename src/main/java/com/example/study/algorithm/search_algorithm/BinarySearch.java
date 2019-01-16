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

        assertThat(binarySearch(integers.toArray(new Integer[integers.size()]), 10)).isEqualTo(9);

        assertThat(binarySearch(new Integer[]{1, 3, 5, 7, 9}, 3)).isEqualTo(1);
    }

    public int binarySearch(Integer[] list, Integer searchItem) {

        int low = 0;
        int high = list.length - 1;

        while (low <= high) {

            int mid = (high + low) / 2;

            if (list[mid] == searchItem) {
                return mid;
            } else if (list[mid] > searchItem) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return 0;
    }
}
