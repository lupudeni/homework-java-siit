package com.homework.week5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringManipulation {
    public static void main(String[] args) {

//1
        System.out.println("1. Write a method to reverse a given string in place.");
        reverseStringInPlace("These violent delights have violent ends...");
        breakPage();
//2
        System.out.println("2. Write a method to print duplicate characters from a string.");
        printDuplicates("These violent delights have violent ends...");
        breakPage();
//3
        System.out.println("3. Write a method to check if two strings are anagrams of each other.");
        System.out.println((checkAnagrams("West World", "Sword Welt")) ? "Anagrams" : "Not Anagrams");
        breakPage();
//4
        System.out.println("4. Write a method to find all the permutations of a string.");
        //write method
        breakPage();
//5
        System.out.println("5. Write a method to check if a string contains only digits.");
        System.out.println((checkOnlyDigits("08907")) ? "Only digits" : "Not only digits");
        breakPage();
//6
        System.out.println("6. Write a method to count a number of vowels and consonants in a given string.");
        numberOfVowelsAndConsonants("These violent delights have violent ends...");
        breakPage();
//7
        System.out.println("7. Write a method to count the occurrence of a given character in a string." +
                "\nNote: Not case sensitive. ");

        String s = "These violent delights have violent ends...";
        char myChar = 't';

        System.out.println("String: " + "\"" + s + "\"" +
                "\nChar: " + "'" + myChar + "'");
        System.out.println("Occurrence: " + countOccurrenceOfChar(myChar, s));
        breakPage();
//8
        System.out.println("8. Write a method to print the first non-repeated character from a string." +
                "\nNote: Not case sensitive. ");
        printFirstNonRepeatChar("These violent delights have violent ends...");
        breakPage();
//9
        System.out.println("9. Write a method to convert a given String into int like the atoi().");
        System.out.println("Number: " + myAtoi("-12345"));
        breakPage();

//12
        System.out.println("12. Write a method to check if a given string is a palindrome.");
        String s1 = "Hannah";
        System.out.println("String: \"" + s + "\"");
        System.out.println("Result: " + (isPalinrome(s1) ? "Palindrome" : "Not a palindrome"));
        breakPage();

//13
        System.out.println("13. Write a method to find the length of the longest substring without repeating characters.");
        printLongestSubstrNoDuplicates("These violent delights have violent ends...");
        breakPage();

//14
        System.out.println("14. Given string str, write a method to find the longest palindromic substring in str.");
        printLongestPalindromicSub("wow, this is a very long homework.");
        breakPage();

//15
        System.out.println("15. Write a method to remove the duplicate character from String.");
        removeDuplicates("These violent delights have violent ends...");
        breakPage();
//16
        System.out.println("16. Write a method to remove a given character from String.");
        removeCharacter('t', "These violent delights have violent ends...");
        breakPage();
//17
        System.out.println("17. Given an array of strings, find the most frequent word in a given array.");
        String[] stringies = new String[]{"java", "struggle", "position", "applaud", "attitude", "java", "position",
                "java", "electron", "bee", "position"};
        findMostFrequentString(stringies);

    }


    private static void breakPage() {
        System.out.println("\n--------------------------\n");
    }

    //1
    private static void reverseStringInPlace(String s) {
        System.out.println("String: \"" + s + "\"." +
                "\nReversed: " + new StringBuilder(s).reverse().toString());
    }

    //2
    private static void printDuplicates(String s) {
        System.out.println("String: \"" + s + "\".");
        System.out.print("Duplicates: ");

        char[] myArray = s.toLowerCase().toCharArray();
        Arrays.sort(myArray);
        char duplicate = 0;
        for (int i = 0; i < myArray.length - 1; i++) {
            if ((myArray[i] == myArray[i + 1]) && (myArray[i] != duplicate)) {
                duplicate = myArray[i];
                System.out.print("'" + duplicate + "'" + " ");
            }
        }

    }


    //3
    private static boolean checkAnagrams(String ex1, String ex2) {
        System.out.println("String 1: " + ex1 +
                "\nString 2: " + ex2);
        System.out.print("Result: ");

        if (ex1.length() == ex2.length()) {
            char[] arrayEx1 = ex1.toLowerCase().replaceAll("\\s+", "").toCharArray();
            char[] arrayEx2 = ex2.toLowerCase().replaceAll("\\s+", "").toCharArray();

            Arrays.sort(arrayEx1);
            Arrays.sort(arrayEx2);

            for (int i = 0; i < arrayEx1.length; i++) {
                if (arrayEx1[i] != arrayEx2[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

//5
    private static boolean checkOnlyDigits(String s) {
        System.out.println("String: \"" + s + "\".");
        System.out.print("Result: ");
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < 48 || s.charAt(i) > 57) {
                return false;
            }
        }
        return true;
    }

//6
    private static void numberOfVowelsAndConsonants(String s) {
        int v = 0;
        int c = 0;
        for (int i = 0; i < s.length(); i++) {
            if (((s.charAt(i) > 64) && (s.charAt(i) < 91))
                    || ((s.charAt(i) > 96) && (s.charAt(i) < 123))) {
                if (isVowel(s.charAt(i))) {
                    v++;
                } else {
                    c++;
                }
            }
        }
        System.out.println("String: \"" + s + "\"" +
                "\nResult: " + v + " vowels and " + c + " consonants.");
    }

    private static boolean isVowel(char myChar) {
        int[] vowels = {65, 69, 73, 79, 85, 97, 101, 105, 111, 117};
        for (int vowel : vowels) {
            if (myChar == vowel) {
                return true;
            }
        }
        return false;
    }

//7
    private static int countOccurrenceOfChar(char myChar, String s) {
        int occurrence = 0;
        if (myChar >= 65 && myChar <= 90) {
            myChar = (char) (myChar + 32);
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.toLowerCase().charAt(i) == myChar) {
                occurrence++;
            }
        }
        return occurrence;
    }

    //8
    private static void printFirstNonRepeatChar(String s) {
        System.out.println("String: \"" + s + "\"");
        int occurrence = 0;
        for (int i = 0; i < s.length(); i++) {
            occurrence = countOccurrenceOfChar(s.charAt(i), s);
            if (occurrence == 1) {
                System.out.println("The first non-repeated character: " + "'" + s.charAt(i) + "'");
                break;
            }
        }
    }
    //9

    private static int myAtoi(String s) {
        System.out.println("String: \"" + s + "\"");
        int number = 0;
        int sign = 0;
        int start = 0;
        if (s.charAt(0) == '-') {
            sign = -1;
            start = 1;
        } else if (s.charAt(0) == '+') {
            sign = 1;
            start = 1;
        }
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                number = number * 10 + (s.charAt(i) - 48);
            } else {
                return 0;
            }
        }
        number = number * sign;
        return number;
    }
    //12
    private static boolean isPalinrome(String s) {
        String reversed = new StringBuilder(s).reverse().toString();
        if (reversed.equalsIgnoreCase(s)) {
            return true;
        }
        return false;
    }

    //13
    private static void printLongestSubstrNoDuplicates(String s) {
        List<String> substrings = getAllSubstrings(s);
        int max = 0;
        String longestSub = "";
        for (String miniString : substrings) {
            if (!hasDuplicateChars(miniString) && miniString.length() > max) {
                longestSub = miniString;
                max = miniString.length();
            }
        }
        System.out.println("String: \"" + s + "\"" +
                "\nLongest substring with no repeating chars: " + longestSub);

    }

    private static List<String> getAllSubstrings(String s) {
        List<String> substrings = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                substrings.add(s.substring(i, j));
            }
        }
        return substrings;
    }

    private static boolean hasDuplicateChars(String s) {
        char[] myArray = s.toLowerCase().toCharArray();
        Arrays.sort(myArray);
        for (int i = 0; i < myArray.length - 1; i++) {
            if (myArray[i] == myArray[i + 1]) {
                return true;
            }
        }
        return false;
    }

    //14
    private static void printLongestPalindromicSub(String s) {
        List<String> substrings = getAllSubstrings(s);
        int max = 0;
        String longestPalindrome = "";
        for (String miniString : substrings) {
            if (isPalinrome(miniString) && miniString.length() > max) {
                longestPalindrome = miniString;
                max = miniString.length();
            }
        }
        System.out.println("String: \"" + s + "\"" +
                "\nThe longest palindromic substring is: " + longestPalindrome);
    }

    //15
    private static void removeDuplicates(String s) {
        List<Character> chars = new ArrayList<>();

        for (char myChar : s.toCharArray()) {
            if (!chars.contains(myChar)) {
                chars.add(myChar);
            }
        }

        StringBuilder noDuplicates = new StringBuilder();
        for (char myChar : chars) {
            noDuplicates.append(myChar);
        }
        System.out.println("String: \"" + s + "\"" +
                "\nString without duplicates: " + noDuplicates.toString());
    }

    //16
    private static void removeCharacter(char removeChar, String s) {
        List<Character> chars = new ArrayList<>();
        for (char myChar : s.toCharArray()) {
            if (myChar != removeChar) {
                chars.add(myChar);
            }
        }
        StringBuilder withoutChar = new StringBuilder();
        for (char myChar : chars) {
            withoutChar.append(myChar);
        }
        System.out.println("String: \"" + s + "\"" +
                "\nChar to remove: " + "'" + removeChar + "'" +
                "\nString without char: " + withoutChar.toString());

    }

    //17
    private static void findMostFrequentString(String[] stringies) {
        String mostFrequent = "";
        int max = 0;
        int occurrence = 0;
        for (String miniString : stringies) {
            occurrence = stringOccurrence(miniString, stringies);
            if (occurrence > max) {
                max = occurrence;
                mostFrequent = miniString;
            } else if (occurrence == max) {
                int comparison = miniString.compareTo(mostFrequent);
                if (comparison < 0) {
                    mostFrequent = miniString;
                }
            }
        }
        System.out.println("Array of Strings: " + Arrays.toString(stringies));
        System.out.println("Most frequent String: " + mostFrequent);

    }

    private static int stringOccurrence(String s, String[] stringies) {
        int occurrence = 0;
        for (int i = 0; i < stringies.length; i++) {
            if (s.equals(stringies[i])) {
                occurrence++;
            }
        }
        return occurrence;
    }


}
