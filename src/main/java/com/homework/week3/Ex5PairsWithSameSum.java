package com.homework.week3;

/**
 * 5. Write a Java method to find all pairs of elements in an array whose sum is equal to a given number.
 */
public class Ex5PairsWithSameSum {
    public static void main(String[] args) {
        int[] myArray = {7, 7, 14, 1, 3, 5, 0, 10};
        int sum = 14;

        findPairsWithSameSum(myArray, sum);
    }

    private static void findPairsWithSameSum(int[] myArray, int sum) {
        boolean isThereAtLeastOnePair = false;
        for(int i = 0; i < myArray.length - 1; i++) {
            for (int j = i+1; j < myArray.length; j++) {
                if((myArray[i] + myArray[j]) == sum) {
                    System.out.println(myArray[i] + " + " + myArray[j] + " = " + sum);
                    isThereAtLeastOnePair = true;
                }
            }
        }
        if (!isThereAtLeastOnePair) {
            System.out.println("Sorry! No two elements in your array could sum up to " + sum + " :(.");
        }
    }
}
