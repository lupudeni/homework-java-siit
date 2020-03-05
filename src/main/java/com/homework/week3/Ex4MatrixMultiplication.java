package com.homework.week3;

import java.util.Arrays;

/**
 * 4. Write a Java method to do Matrix multiplication (for simplicity, the input is known as a 3x3 matrix)
 */
public class Ex4MatrixMultiplication {
    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] b = new int[][]{
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };

        int[][] x = multiplyMatrix(a, b);

        System.out.println("Matrix a * Matrix b = ");
        for (int[] ints : x) {
            System.out.println(Arrays.toString(ints));
        }
    }

    private static int[][] multiplyMatrix(int[][] a, int[][] b) {
        int[][] x = new int[3][3];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                for (int k = 0; k < b.length; k++) {
                    x[i][j] = x[i][j] + (a[i][k] * b[k][j]);
                }
            }
        }
        return x;
    }
}

