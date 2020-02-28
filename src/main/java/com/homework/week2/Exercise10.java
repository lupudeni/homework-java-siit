package com.homework.week2;

import java.util.Arrays;

/**
 * Exercise 10: Write a method that checks if the array is square (i.e. every row has the same length as a itself).
 * a. for input:
 * <p>
 * {
 * {4, 4, 3},
 * {5, 7, 4},
 * {7, 8, 9}
 * }
 * should return true
 * b. for input:
 * {
 * {4},
 * {5, 7, 4},
 * {7, 8}
 * }
 * should return false
 * method definition should be:
 * public static boolean isSquare(int[][] array)
 */
public class Exercise10 {
    public static void main(String[] args) {
        int[][] matrix1 = new int[3][3];
        matrix1[0] = new int[]{4, 4, 3};
        matrix1[1] = new int[]{5, 7, 4};
        matrix1[2] = new int[]{7, 8, 9};

        int[][] matrix2 = new int[3][];
        matrix2[0] = new int[]{4};
        matrix2[1] = new int[]{5, 7, 4};
        matrix2[2] = new int[]{7, 8};

        System.out.println("matrix1 = " + Arrays.deepToString(matrix1));
        System.out.println("matrix1 is square status: " + isSquare(matrix1));
        System.out.println("matrix2 = " + Arrays.deepToString(matrix2));
        System.out.println("matrix2 is square status: " + isSquare(matrix2));


    }

    public static boolean isSquare(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != array.length) {
                return false;
            }
        }
        return true;
    }
}
