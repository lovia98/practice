package com.example.study.algorithm.sort;

import org.junit.Test;

public class BubbleSort {

    //Buble Sort
    @Test
    public void test() {

        int[] list = new int[]{
            3, 4, 0, 6, 2, 1, 5
        };

        bubbleSort(list, list.length);

        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }
    }

    public void bubbleSort(int[] list, int last) {

        for (int i = 0; i < last-1; i++) {
            if(list[i] > list[i+1]) {
                swap(list, i, i+1);
            }
        }

        if(last > 1) {
            bubbleSort(list, last-1);
        }
    }

    private void swap(int[] list, int left, int right) {
        int temp = list[left];
        list[left] = list[right];
        list[right] = temp;
    }


}
