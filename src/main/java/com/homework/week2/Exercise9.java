package com.homework.week2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Exercise 9: Write a Java program to remove the duplicate elements of a given array and return the new length of the array.
 *      Sample array: [20, 20, 30, 40, 50, 50, 50]
 *      After removing the duplicate elements the program should return [20, 30, 40, 50] as the array without duplicates.
 */
public class Exercise9 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("How many elements would you like your array to have?");
        int userLength = input.nextInt();
        int[] userArray = new int[userLength];
        System.out.println("Now please input the int elements in your array:");
        for (int i = 0; i < userArray.length; i++) {
            userArray[i] = input.nextInt();
        }
        createNewArray(userArray);

    }
    public static int findLengthOfNewArray(int[] userArray) {

        int count = 0;
        for(int i = 0; i < userArray.length; i++) {

            for(int j = (i + 1); j < userArray.length; j++) {
                if(userArray[i] == userArray[j]) {
                    if(!isItBehindMe(i, userArray[i], userArray))
                    count= count + 1;
                }

            }
        }
        System.out.println("After removing the duplicates, the new length of the array is: " + count);
        return (userArray.length - count);
    }

    public static void createNewArray(int[] userArray){
        int[] newArray = new int[findLengthOfNewArray(userArray)];
        newArray[0] = userArray[0];
        int i = 1;
        int j = 1;

        while(j < newArray.length) {
            if(!isItBehindMe(j, userArray[i], newArray)) {
                newArray[j] = userArray[i];
                j++;
            }
            i++;
        }

        System.out.println("The array is now: " + Arrays.toString(newArray));
    }

    public static boolean isItBehindMe(int position, int value, int[] array){
        int k = 0;
        while (k < position) {
            if(array[k] == value) {
                return true;
            }
            k++;
        }
        return false;
    }

}



