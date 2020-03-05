package com.homework.week3;

import java.util.Scanner;

/**
 * Write a Java method to find the duplicate characters in a string.
 * (Hint use str.toCharArray() to split the string into an array of characters)
 */
public class Ex3DuplicatedChars {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input a string of words: ");
        String userString = input.nextLine();
        char[] characters = userString.toCharArray();
        System.out.println("The duplicates in your string are: " + findDuplicates(characters));
    }

    private static String findDuplicates(char[] characters) {
        String duplicates = "";
        for (int i = 0; i <= characters.length - 2; i++) {
            if (isDuplicate(i, characters) && !isRepeating(characters[i], duplicates)) {
                duplicates = duplicates + characters[i];
            }
        }
        return duplicates;
    }

    private static boolean isRepeating(char character, String duplicates) {
        if (duplicates.length() == 0) {
            return false;
        }
        for (int j = 0; j <= duplicates.length() - 1; j++) {
            if (character == duplicates.charAt(j)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDuplicate(int i, char[] characters) {
        for (int j = i + 1; j <= characters.length - 1; j++) {
            if (characters[i] == characters[j]) {
                return true;
            }
        }
        return false;
    }

}


