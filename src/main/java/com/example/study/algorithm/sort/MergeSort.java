package com.example.study.algorithm.sort;

import org.junit.Test;

public class MergeSort {

    @Test
    public void test() {

        int[] list = {4, 2, 6, 3, 7, 8, 5, 1};
        mergeSort(list, 0, list.length - 1);

        for (int i = 0; i < list.length; i++) {
            System.out.printf("%d ,", list[i]);
        }

    }

    private void mergeSort(int list[], int start, int end) {

        if (end - start > 0) {
            int mid = (start + end) / 2;

            mergeSort(list, start, mid);
            mergeSort(list, mid + 1, end);
            merge(list, start, mid, end);
        }
    }

    private void merge(int[] list, int start, int mid, int end) {

        int[] mergeList = new int[end - start + 1];

        int lPoint = start;
        int rPoint = mid + 1;

        int min;

        for (int i = 0; i < mergeList.length; i++) {

            if(rPoint > end)
                min = list[lPoint];
            else if(lPoint > mid)
                min = list[rPoint];
            else
                min = Math.min(list[lPoint], list[rPoint]);

            if(min == list[lPoint])
                lPoint++;
            else
                rPoint++;

            mergeList[i] = min;

        }

        for (int i = 0; i < mergeList.length; i++) {
            list[start] = mergeList[i];
            start++;
        }
    }
}
