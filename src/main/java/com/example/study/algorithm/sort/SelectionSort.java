package com.example.study.algorithm.sort;

public class SelectionSort {

    public static void main(String ...args) {

        int[] list = new int[] { 3, 4, 6, 1, 0};
        for (int i = 0; i < list.length; i++) {
            System.out.printf("%d ", list[i]);
        }

        selectionSort(list);

        System.out.println();
        for (int i = 0; i < list.length; i++) {
            System.out.printf("%d ", list[i]);
        }
    }

    private static void selectionSort(int[] list) {

        for (int start = 0; start < list.length; start++) {

            int min = list[start];
            int minPoint = start;

            for (int j = start+1; j < list.length; j++) {
                if(min > list[j]) {
                    min = list[j];
                    minPoint = j;
                }
            }

            int temp = list[start];
            list[start] = list[minPoint];
            list[minPoint] = temp;
        }
    }
}
