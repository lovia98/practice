package com.example.study.algorithm.string;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.junit.Test;

public class Matrix {

    @Test
    public void matrix_test() {

        assertThat(solution(
            new int[][]{
                new int[]{1, 0, 0},
                new int[]{1, 0, 8},
                new int[]{0, 0, 4}
            }

        )).isEqualTo(
            new int[][]{
                new int[]{0, 0, 0},
                new int[]{0, 0, 0},
                new int[]{0, 0, 0}
            }
        );

        assertThat(solution(
            new int[][]{
                new int[]{1},
                new int[]{1}
            }

        )).isEqualTo(
            new int[][]{
                new int[]{1},
                new int[]{1}
            }
        );

        assertThat(solution(
            new int[][]{
                new int[]{1, 1, 0},
                new int[]{1, 1, 1},
                new int[]{1, 1, 1}
            }

        )).isEqualTo(
            new int[][]{
                new int[]{0, 0, 0},
                new int[]{1, 1, 0},
                new int[]{1, 1, 0}
            }
        );
    }

    private int[][] solution(int[][] matrix) {

        if (matrix == null || matrix.length == 0) {
            return matrix;
        }

        int colums = matrix.length;
        int rows = matrix[0].length;

        boolean columChecked[] = new boolean[colums];
        boolean rowChecked[] = new boolean[rows];

        for (int i = 0; i < colums; i++) {

            for (int j = 0; j < rows; j++) {

                if (matrix[i][j] == 0) {
                    columChecked[i] = true;
                    rowChecked[j] = true;
                }
            }
        }

        for (int i = 0; i < colums; i++) {

            for (int j = 0; j < rows; j++) {

                if (columChecked[i] || rowChecked[j]) {
                    matrix[i][j] = 0;
                }
            }
        }

        return matrix;
    }


}
