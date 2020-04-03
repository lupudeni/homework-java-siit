package com.homework.week5.strings;

import com.homework.util.MyPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1. Write a method to reverse a given string in place.
 * 2. Write a method to print duplicate characters from a string.
 * NOTE for 2: 'A' and 'a' are not the same char --> method is Case Sensitive and takes the space (ASCII 32) into consideration
 * 3. Write a method to check if two strings are anagrams of each other.
 * NOTE for 3: Anagrams = a word or phrase made by using the letters of another word or phrase in a different order,
 * ^therefore method is NOT Case Sensitive and Space between words in String is ignored as there can be different combinations
 * 4. Write a method to find all the permutations of a string.
 * 5. Write a method to check if a string contains only digits.
 * 6. Write a method to find duplicate characters in a given string.
 * NOTE for 6: 'A' and 'a' are not the same char --> method is Case Sensitive and takes the space (ASCII 32) into consideration
 * 7. Write a method to count a number of vowels and consonants in a given string.
 * 8. Write a method to count the occurrence of a given character in a string.
 * NOTE for 8: 'A' and 'a' are not the same char --> method is Case Sensitive and takes the space (ASCII 32) into consideration
 * 9. Write a method to print the first non-repeated character from a string.
 * NOTE for 9: 'A' and 'a' are not the same char --> method is Case Sensitive and takes the space (ASCII 32) into consideration
 * 10. Write a method to convert a given String into int like the atoi().
 * 11. Write a method to reverse words in a given sentence without using any library method.
 * 12. Write a method to check if two strings are a rotation of each other.
 * NOTE for 12: Method is Case Sensitive and takes blank space into consideration between words
 * 13. Write a method to check if a given string is a palindrome.
 * NOTE for 13: Palindrome: a word or group of words that is the same when you read it forwards from the beginning or backwards from the end.
 * ^therefore method is NOT Case Sensitive and Space between words in String is ignored
 * 14. Write a method to find the length of the longest substring without repeating characters.
 * NOTE for 14: 'A' and 'a' are not the same char --> method is Case Sensitive and takes the space (ASCII 32) into consideration
 * 15. Given string str, write a method to find the longest palindromic substring in str.
 * NOTE for 15: Palindrome: a word or group of words that is the same when you read it forwards from the beginning or backwards from the end.
 * ^therefore method is NOT Case Sensitive and Space between words in String is ignored
 * 16. Write a method to remove the duplicate character from String.
 * NOTE for 16: 'A' and 'a' are not the same char --> method is Case Sensitive and takes the space (ASCII 32) into consideration
 * 17. Write a method to remove a given character from String.
 * NOTE for 17: 'A' and 'a' are not the same char --> method is Case Sensitive
 * 18. Given an array of strings, find the most frequent word in a given array,
 * ^I mean, the string that appears the most in the array. In the case of a tie, ​the string that is the smallest (lexicographically) ​is printed.
 */
public class StringManipulation {
    private MyPrinter printer;

    public StringManipulation() {
        this(new MyPrinter());
    }

    public StringManipulation(MyPrinter printer) {
        this.printer = printer;
    }

    //1
    String reverseStringInPlace(String s) {
        if (s == null) {
            return null;
        }
        int j = s.length() - 1;
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length() / 2; i++) {
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, temp);
            j--;
        }
        return sb.toString();
    }

    //2
    void printDuplicates(String s) {
        if (s == null || s.isEmpty()) {
            printer.print("Null or Empty String not allowed.");
        } else {
            char[] myArray = s.toLowerCase().toCharArray();
            Arrays.sort(myArray);
            char duplicate = 0;
            StringBuilder duplicates = new StringBuilder();

            for (int i = 0; i < myArray.length - 1; i++) {
                if ((myArray[i] == myArray[i + 1]) && (myArray[i] != duplicate)) {
                    duplicate = myArray[i];
                    duplicates.append("'")
                            .append(duplicate)
                            .append("' ");
                }
            }
            if (duplicates.length() == 0) {
                printer.print("No duplicates found.");
            }
            printer.print(duplicates.toString());
        }
    }

    //3
    boolean checkAnagrams(String s1, String s2) {
        if (s1 == null || s2 == null || s1.trim().isEmpty() || s2.trim().isEmpty()) {
            return false;
        }
        char[] arrayS1 = s1.toLowerCase()
                .replaceAll("\\s+", "")
                .toCharArray();
        char[] arrayS2 = s2.toLowerCase()
                .replaceAll("\\s+", "")
                .toCharArray();

        if (arrayS1.length == arrayS2.length) {
            Arrays.sort(arrayS1);
            Arrays.sort(arrayS2);

            for (int i = 0; i < arrayS1.length; i++) {
                if (arrayS1[i] != arrayS2[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    //4
    List<String> findPermutations(String s) {
        List<String> permutations = new ArrayList<>();
        if (s == null || s.trim().isEmpty()) {
            return permutations;
        }
        getPermutation("", s, permutations);
        return permutations;
    }

    void getPermutation(String branch, String s, List<String> permutations) {
        if (s == null) {
            return;
        }

        int length = s.length();
        if (length == 0) {
            permutations.add(branch);
        } else {
            for (int i = 0; i < length; i++) {
                getPermutation(branch + s.charAt(i), s.substring(0, i) + s.substring(i + 1, length), permutations);
            }
        }
    }


    //5 (I made this public as I believe i can use the method in other places as well (outside of this package)
    public boolean checkIfOnlyDigits(String s) {
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < 48 || s.charAt(i) > 57) {
                return false;
            }
        }
        return true;
    }

    //6
    List<Character> findDuplicates(String s) {
        List<Character> duplicateChars = new ArrayList<>();
        if (s != null && !s.isEmpty()) {
            char[] myArray = s.toCharArray();
            Arrays.sort(myArray);
            char duplicate = 0;
            StringBuilder duplicates = new StringBuilder();

            for (int i = 0; i < myArray.length - 1; i++) {
                if ((myArray[i] == myArray[i + 1]) && (myArray[i] != duplicate)) {
                    duplicate = myArray[i];
                    duplicateChars.add(duplicate);
                }
            }
        }
        return duplicateChars;
    }


    //7
    void printNumberOfVowelsAndConsonants(String s) {
        if (s == null || s.trim().isEmpty()) {
            printer.print("Null, Empty or Blank String not allowed.");
        } else {
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
            printer.print(v + " vowels and " + c + " consonants");
        }
    }

    boolean isVowel(char myChar) {
        int[] vowels = {65, 69, 73, 79, 85, 97, 101, 105, 111, 117};
        for (int vowel : vowels) {
            if (myChar == vowel) {
                return true;
            }
        }
        return false;
    }

    //8
    int countOccurrenceOfChar(char myChar, String s) {
        if (s == null || s.isEmpty()) {
            return -1;
        }
        int occurrence = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == myChar) {
                occurrence++;
            }
        }
        return occurrence;

    }

    //9
    void printFirstNonRepeatChar(String s) {
        if (s == null || s.isEmpty()) {
            printer.print("Null or Empty String not allowed.");
        } else {
            boolean isNonUnique = true;
            int occurrence = 0;
            for (int i = 0; i < s.length(); i++) {
                occurrence = countOccurrenceOfChar(s.charAt(i), s);
                if (occurrence == 1) {
                    printer.print("The first non-repeated character: " + "'" + s.charAt(i) + "'");
                    isNonUnique = false;
                }
            }
            if (isNonUnique) {
                printer.print("String has no unique chars.");
            }
        }
    }

    //10
    int myAtoi(String s) {
        if (s == null || s.trim().isEmpty()) {
            return 0;
        }

        int number = 0;
        int sign = 1;
        int start = 0;
        if (s.charAt(0) == '-') {
            sign = -1;
            start = 1;
        } else if (s.charAt(0) == '+') {
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

    //11

    String reverseWordInSentence(String s) {
        if (s == null) {
            return null;
        }
        if (s.trim().isEmpty()) {
            return s.trim();
        }

        String[] myArray = s.split("\\s+");
        String temp = "";
        int length = myArray.length - 1;

        for (int i = 0; i < (length / 2); i++) {
            temp = myArray[i];
            myArray[i] = myArray[length - i];
            myArray[length - i] = temp;
        }

        StringBuilder reversed = new StringBuilder();
        for (String word : myArray) {
            reversed.append(word);
            if (!word.equals(myArray[length])) {
                reversed.append(" ");
            }
        }
        return reversed.toString();
    }

    //12

    boolean areStringsRotation(String s1, String s2) {
        if ((s1 == null || s2 == null) || (s1.length() != s2.length())) {
            return false;
        }

        String longS1 = s1 + s1;
        List<String> s1Subs = getAllSubstrings(longS1);
        for (String substring : s1Subs) {
            if (s2.equals(substring)) {
                return true;
            }
        }
        return false;
    }

    //13
    boolean isPalindrome(String s) {
        if (s == null || s.trim().isEmpty() || s.length() == 1) {
            return false;
        }
        String reversed = reverseStringInPlace(s).replaceAll("\\s+", "");
        return reversed.equalsIgnoreCase(s.replaceAll("\\s+", ""));
    }

    //14
    String findLongestSubstrNoDuplicates(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        List<String> substrings = getAllSubstrings(s);
        int max = 0;
        String longestSub = "";
        for (String miniString : substrings) {
            if (!hasDuplicateChars(miniString) && miniString.length() > max) {
                longestSub = miniString;
                max = miniString.length();
            }
        }
        return longestSub;
    }

    List<String> getAllSubstrings(String s) {
        List<String> substrings = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return substrings;
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length() + 1; j++) {
                substrings.add(s.substring(i, j));
            }
        }
        return substrings;
    }

    boolean hasDuplicateChars(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        char[] myArray = s.toLowerCase().toCharArray();
        Arrays.sort(myArray);
        for (int i = 0; i < myArray.length - 1; i++) {
            if (myArray[i] == myArray[i + 1]) {
                return true;
            }
        }
        return false;
    }

    //15
    String findLongestPalindromicSub(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        List<String> substrings = getAllSubstrings(s);
        int max = 0;
        String longestPalindrome = "";
        for (String miniString : substrings) {
            if (isPalindrome(miniString) && miniString.replaceAll("\\s+", "").length() > max) {
                longestPalindrome = miniString.trim();
                max = miniString.replaceAll("\\s+", "").length();
            }
        }
        return longestPalindrome;
    }

    //16
    String removeDuplicates(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

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
        return noDuplicates.toString();
    }

    //17
    String removeCharacter(char removeChar, String s) {
        if (s == null || s.trim().isEmpty()) {
            return s;
        }

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
        return withoutChar.toString();
    }

    //18
    String findMostFrequentString(String[] strings) {
        if (strings == null) {
            return null;
        }
        if (strings.length == 0) {
            return "";
        }

        String mostFrequent = "";
        int max = 0;
        int occurrence = 0;
        for (String miniString : strings) {
            occurrence = getStringOccurrence(miniString, strings);
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
        return mostFrequent;
    }

    int getStringOccurrence(String s, String[] strings) {
        if (s == null || s.isEmpty() || strings == null) {
            return 0;
        }
        int occurrence = 0;
        for (String string : strings) {
            if (s.equals(string)) {
                occurrence++;
            }
        }
        return occurrence;
    }
}
