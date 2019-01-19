package com.example.study.algorithm.sort;

public class QuickSort {

    public static void main(String... args) {

        int[] list = new int[]{4, 6, 9, 0, 8, 7, 1, 2, 5};
        for (int i = 0; i < list.length; i++) {
            System.out.printf("%d ", list[i]);
        }

        quicksort(list, 0, list.length - 1);

        System.out.println("\n");
        for (int i = 0; i < list.length; i++) {
            System.out.printf("%d ", list[i]);
        }
    }

    private static void quicksort(int[] list, int start, int end) {

        int partition = partition(list, start, end);

        if (partition - start - 1 > 0) {
            quicksort(list, start, partition - 1);
        }

        if (end - partition > 0) {
            quicksort(list, partition, end);
        }
    }

    private static int partition(int[] list, int start, int end) {

        int mid = (start + end) / 2;
        int pivot = list[mid];

        int lpoint = start, rpoint = end;
        while (lpoint <= rpoint) {

            while (list[lpoint] < pivot) {
                lpoint++;
            }
            while (list[rpoint] > pivot) {
                rpoint--;
            }

            if (lpoint <= rpoint) {
                swap(list, lpoint, rpoint);
                lpoint++;
                rpoint--;
            }
        }

        return lpoint;
    }

    private static void swap(int[] list, int lpoint, int rpoint) {

        int temp = list[lpoint];
        list[lpoint] = list[rpoint];
        list[rpoint] = temp;
    }
}
