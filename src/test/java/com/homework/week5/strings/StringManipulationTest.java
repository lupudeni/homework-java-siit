package com.homework.week5.strings;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class StringManipulationTest {

    private StringManipulation sut;

    @Mock
    private MyPrinter printer;

    @Before
    public void setup() {
        sut = new StringManipulation(printer);
    }

    // Tests for ex. 1 - reverseStringInPlace(String s)

    @Test
    public void given_Null_When_Reverse_String_In_Place_Then_Return_Null() {
        //Given
        String input = null;

        //When
        String returnedString = sut.reverseStringInPlace(input);

        //Then
        assertThat(returnedString).isNull();

    }

    @Test
    public void given_Empty_When_Reverse_String_In_Place_Then_Return_Empty() {
        //Given
        String input = "";

        //When
        String returnedString = sut.reverseStringInPlace(input);

        //Then
        assertThat(returnedString).isEmpty();
    }

    @Test
    public void given_String_When_Reverse_String_In_Place_Then_Return_Reversed() {
        //Given
        String input = "aBcD";

        //When
        String returnedString = sut.reverseStringInPlace(input);

        //Then
        assertThat(returnedString).isEqualTo("DcBa");
    }

    //----------------------------------------------------------------------
    //Tests for ex. 2 - printDuplicates(String s)

    @Test
    public void given_Null_When_Print_Duplicates_Then_Print_Not_Allowed() {
        //Given
        String input = null;

        //When
        sut.printDuplicates(input);

        //Then
        Mockito.verify(printer, times(1)).print("Null or Empty String not allowed.");
    }

    @Test
    public void given_Empty_When_Print_Duplicates_Then_Print_Not_Allowed() {
        //Given
        String input = "";

        //When
        sut.printDuplicates(input);

        //Then
        Mockito.verify(printer, times(1)).print("Null or Empty String not allowed.");
    }

    @Test
    public void given_String_With_No_Duplicates_When_Print_Duplicates_Then_Print_No_Duplicates() {
        //Given
        String input = "abcd";

        //When
        sut.printDuplicates(input);

        //Then
        Mockito.verify(printer, times(1)).print("No duplicates found.");
    }

    @Test
    public void given_String_With_Duplicates_When_Print_Duplicates_Then_Print_Duplicates() {
        //Given
        String input = "aabb";

        //When
        sut.printDuplicates(input);

        //Then
        Mockito.verify(printer, times(1)).print("'a' 'b' ");
    }

    //----------------------------------------------------------------------
    //Test for ex. 3 - checkAnagrams(String s1, String s2)

    @Test
    public void given_Null_When_Check_If_Anagrams_Then_Return_False() {
        //Given
        String input1 = null;
        String input2 = null;

        //When
        boolean result = sut.checkAnagrams(input1, input2);

        //Then
        assertThat(result).isFalse();
    }

    @Test
    public void given_Empty_When_Check_If_Anagrams_Then_Return_False() {
        //Given
        String input1 = "";
        String input2 = "";

        //When
        boolean result = sut.checkAnagrams(input1, input2);

        //Then
        assertThat(result).isFalse();
    }

    @Test
    public void given_Blank_When_Check_If_Anagrams_Then_Return_False() {
        //Given
        String input1 = "           ";
        String input2 = "  ";

        //When
        boolean result = sut.checkAnagrams(input1, input2);

        //Then
        assertThat(result).isFalse();
    }

    @Test
    public void given_Unequal_Word_Strings_When_Check_If_Anagrams_Then_Return_False() {
        //Given
        String input1 = "Lary   Larry";
        String input2 = "Larry";

        //When
        boolean result = sut.checkAnagrams(input1, input2);

        //Then
        assertThat(result).isFalse();
    }

    @Test
    public void given_Not_Anagram_Strings_When_Check_If_Anagrams_Then_Return_False() {
        //Given
        String input1 = "Harry";
        String input2 = "Larry";

        //When
        boolean result = sut.checkAnagrams(input1, input2);

        //Then
        assertThat(result).isFalse();
    }

    @Test
    public void given_Anagram_Strings_When_Check_If_Anagrams_Then_Return_True() {
        //Given
        String input1 = "Hard Homework";
        String input2 = "Harked Ohm Row";

        //When
        boolean result = sut.checkAnagrams(input1, input2);

        //Then
        assertThat(result).isTrue();
    }

    //----------------------------------------------------------------------
    //Tests for ex. 4
    //getPermutation(String branch, String s, List<String> permutations)

    @Test
    public void given_Null_When_Get_Permutations_Then_List_Is_Empty() {
        //Given
        String s = null;
        String branch = "";
        List<String> permutations = new ArrayList<>();

        //When
        sut.getPermutation(branch, s, permutations);

        //Then
        permutations.isEmpty();
    }

    @Test
    public void given_String_When_Get_Permutations_Then_List_Contains_Permutations() {
        //Given
        String s = "ABC";
        String branch = "";
        List<String> permutations = new ArrayList<>();

        //When
        sut.getPermutation(branch, s, permutations);

        //Then
        permutations.containsAll(Arrays.asList("ABC", "ACB", "BAC", "BCA", "CBA", "CAB"));
    }

    //findPermutations(String s)

    @Test
    public void given_Null_When_Find_Permutations_Then_Return_Empty_List() {
        //Given
        String s = null;

        //When
        List<String> permutations = sut.findPermutations(s);

        //Then
        assertThat(permutations).isEmpty();
    }

    @Test
    public void given_Empty_String_When_Find_Permutations_Then_Return_Empty_List() {
        //Given
        String s = "";

        //When
        List<String> permutations = sut.findPermutations(s);

        //Then
        assertThat(permutations).isEmpty();
    }

    @Test
    public void given_Blank_When_Find_Permutations_Then_Return_Empty_List() {
        //Given
        String s = "         ";

        //When
        List<String> permutations = sut.findPermutations(s);

        //Then
        assertThat(permutations).isEmpty();
    }

    @Test
    public void given_String_When_Find_Permutations_Then_Return_List_Of_Permutations() {
        //Given
        String s = "ABC";

        //When
        List<String> permutations = sut.findPermutations(s);

        //Then
        assertThat(permutations).containsAll(Arrays.asList("ABC", "ACB", "BAC", "BCA", "CBA", "CAB"));
    }

    //----------------------------------------------------------------------
    //Tests for ex. 5 - checkIfOnlyDigits(String s)

    @Test
    public void given_Null_When_Check_If_Only_Digits_Then_Return_False() {
        //Given
        String input = null;

        //When
        boolean result = sut.checkIfOnlyDigits(input);

        //Then
        assertThat(result).isFalse();
    }

    @Test
    public void given_Empty_When_Check_If_Only_Digits_Then_Return_False() {
        //Given
        String input = "";

        //When
        boolean result = sut.checkIfOnlyDigits(input);

        //Then
        assertThat(result).isFalse();
    }

    @Test
    public void given_Blank_When_Check_If_Only_Digits_Then_Return_False() {
        //Given
        String input = "    ";

        //When
        boolean result = sut.checkIfOnlyDigits(input);

        //Then
        assertThat(result).isFalse();
    }

    @Test
    public void given_No_Digit_String_When_Check_If_Only_Digits_Then_Return_False() {
        //Given
        String input = "Access code: Unknown";

        //When
        boolean result = sut.checkIfOnlyDigits(input);

        //Then
        assertThat(result).isFalse();
    }

    @Test
    public void given_Digit_And_Non_Digit_String_When_Check_If_Only_Digits_Then_Return_False() {
        //Given
        String input = "Access code: 8890";

        //When
        boolean result = sut.checkIfOnlyDigits(input);

        //Then
        assertThat(result).isFalse();
    }

    @Test
    public void given_Only_Digit_String_When_Check_If_Only_Digits_Then_Return_True() {
        //Given
        String input = "8890";

        //When
        boolean result = sut.checkIfOnlyDigits(input);

        //Then
        assertThat(result).isTrue();
    }

    //----------------------------------------------------------------------
    //Tests for ex. 6 - findDuplicates(String s)

    @Test
    public void given_Null_When_Find_Duplicates_Then_Return_Empty_List() {
        //Given
        String s = null;

        //When
        List<Character> duplicates = sut.findDuplicates(s);

        //Then
        assertThat(duplicates).isEmpty();
    }

    @Test
    public void given_Empty_When_Find_Duplicates_Then_Return_Empty_List() {
        //Given
        String s = "";

        //When
        List<Character> duplicates = sut.findDuplicates(s);

        //Then
        assertThat(duplicates).isEmpty();
    }

    @Test
    public void given_String_Without_Duplicates_When_Find_Duplicates_Then_Return_Empty_List() {
        //Given
        String s = "ABC";

        //When
        List<Character> duplicates = sut.findDuplicates(s);

        //Then
        assertThat(duplicates).isEmpty();
    }

    @Test
    public void given_String_With_Duplicates_When_Find_Duplicates_Then_Return_List_Of_Duplicates() {
        //Given
        String s = "AABBCC  ";

        //When
        List<Character> duplicates = sut.findDuplicates(s);

        //Then
        assertThat(duplicates).containsAll(Arrays.asList('A', 'B', 'C', ' '));
    }

    //----------------------------------------------------------------------
    //Tests for ex. 7
    // isVowel(char myChar)

    @Test
    public void given_Blank_Char_When_Check_If_Vowel_Then_Return_False() {
        //Given
        char myChar = ' ';

        //When
        boolean isVowel = sut.isVowel(myChar);

        //Then
        assertThat(isVowel).isFalse();
    }

    @Test
    public void given_Special_Char_When_Check_If_Vowel_Then_Return_False() {
        //Given
        char myChar = '%';

        //When
        boolean isVowel = sut.isVowel(myChar);

        //Then
        assertThat(isVowel).isFalse();
    }

    @Test
    public void given_Consonant_When_Check_If_Vowel_Then_Return_False() {
        //Given
        char myChar = 'c';

        //When
        boolean isVowel = sut.isVowel(myChar);

        //Then
        assertThat(isVowel).isFalse();
    }

    @Test
    public void given_Vowel_When_Check_If_Vowel_Then_Return_True() {
        //Given
        char myChar = 'a';

        //When
        boolean isVowel = sut.isVowel(myChar);

        //Then
        assertThat(isVowel).isTrue();
    }

    @Test
    public void given_Uppercase_Vowel_When_Check_If_Vowel_Then_Return_True() {
        //Given
        char myChar = 'A';

        //When
        boolean isVowel = sut.isVowel(myChar);

        //Then
        assertThat(isVowel).isTrue();
    }

    // printNumberOfVowelsAndConsonants(String s)

    @Test
    public void given_Null_When_Print_Nr_Of_Vowels_And_Consonants_Then_Print_Not_Allowed() {
        //Given
        String input = null;

        //When
        sut.printNumberOfVowelsAndConsonants(input);

        //Then
        Mockito.verify(printer, times(1)).print("Null, Empty or Blank String not allowed.");
    }

    @Test
    public void given_Empty_When_Print_Nr_Of_Vowels_And_Consonants_Then_Print_Not_Allowed() {
        //Given
        String input = "";

        //When
        sut.printNumberOfVowelsAndConsonants(input);

        //Then
        Mockito.verify(printer, times(1)).print("Null, Empty or Blank String not allowed.");
    }

    @Test
    public void given_Blank_When_Print_Nr_Of_Vowels_And_Consonants_Then_Print_Not_Allowed() {
        //Given
        String input = "        ";

        //When
        sut.printNumberOfVowelsAndConsonants(input);

        //Then
        Mockito.verify(printer, times(1)).print("Null, Empty or Blank String not allowed.");
    }

    @Test
    public void given_String_When_Print_Nr_Of_Vowels_And_Consonants_Then_Print_Nr_Of_Vowels_And_Consonants_String() {
        //Given
        String input = "abcde";

        //When
        sut.printNumberOfVowelsAndConsonants(input);

        //Then
        Mockito.verify(printer, times(1)).print("2 vowels and 3 consonants");
    }

    //----------------------------------------------------------------------
    //Tests for ex. 8 - countOccurrenceOfChar(char myChar, String s)

    @Test
    public void given_Null_String_When_Count_Occurrence_Of_Char_Then_Return_Negative_1() {
        //Given
        String input = null;
        char myChar = 'a';

        //When
        int occurrence = sut.countOccurrenceOfChar(myChar, input);

        //Then
        assertThat(occurrence).isEqualTo(-1);
    }

    @Test
    public void given_Empty_String_When_Count_Occurrence_Of_Char_Then_Return_Negative_1() {
        //Given
        String input = "";
        char myChar = 'a';

        //When
        int occurrence = sut.countOccurrenceOfChar(myChar, input);

        //Then
        assertThat(occurrence).isEqualTo(-1);
    }

    @Test
    public void given_String_Without_Char_When_Count_Occurrence_Of_Char_Then_Return_0() {
        //Given
        String input = "  A Cowboy";
        char myChar = 'a';

        //When
        int occurrence = sut.countOccurrenceOfChar(myChar, input);

        //Then
        assertThat(occurrence).isEqualTo(0);
    }

    @Test
    public void given_String_With_Char_When_Count_Occurrence_Of_Char_Then_Return_Occurrence_Of_Char() {
        //Given
        String input = "A Cowboy";
        char myChar = 'o';

        //When
        int occurrence = sut.countOccurrenceOfChar(myChar, input);

        //Then
        assertThat(occurrence).isEqualTo(2);
    }

    //----------------------------------------------------------------------
    //Tests for ex. 9 - printFirstNonRepeatChar(String s)

    @Test
    public void given_Null_When_Print_First_Non_Repeat_Char_Then_Print_Not_Allowed() {
        //Given
        String input = null;

        //When
        sut.printFirstNonRepeatChar(input);

        //Then
        Mockito.verify(printer, times(1)).print("Null or Empty String not allowed.");
    }

    @Test
    public void given_Empty_When_Print_First_Non_Repeat_Char_Then_Print_Not_Allowed() {
        //Given
        String input = null;

        //When
        sut.printFirstNonRepeatChar(input);

        //Then
        Mockito.verify(printer, times(1)).print("Null or Empty String not allowed.");
    }

    @Test
    public void given_String_Only_Repeating_Chars_When_Print_First_Non_Repeat_Char_Then_Print_Char() {
        //Given
        String input = "aabbcc";

        //When
        sut.printFirstNonRepeatChar(input);

        //Then
        Mockito.verify(printer, times(1)).print("String has no unique chars.");
    }

    @Test
    public void given_String_When_Print_First_Non_Repeat_Char_Then_Print_Char() {
        //Given
        String input = "aabccd";

        //When
        sut.printFirstNonRepeatChar(input);

        //Then
        Mockito.verify(printer, times(1)).print("The first non-repeated character: 'b'");
    }

    //----------------------------------------------------------------------
    //Tests for ex. 10  - myAtoi(String s)

    @Test
    public void given_Null_When_Convert_String_To_Int_Atoi_Then_Return_0() {
        //Given
        String input = null;

        //When
        int myAtoiNumber = sut.myAtoi(input);

        //Then
        assertThat(myAtoiNumber).isEqualTo(0);
    }

    @Test
    public void given_Empty_When_Convert_String_To_Int_Atoi_Then_Return_0() {
        //Given
        String input = "";

        //When
        int myAtoiNumber = sut.myAtoi(input);

        //Then
        assertThat(myAtoiNumber).isEqualTo(0);
    }

    @Test
    public void given_Blank_When_Convert_String_To_Int_Atoi_Then_Return_0() {
        //Given
        String input = "                              ";

        //When
        int myAtoiNumber = sut.myAtoi(input);

        //Then
        assertThat(myAtoiNumber).isEqualTo(0);
    }

    @Test
    public void given_Alphabetic_String_When_Convert_String_To_Int_Atom_Then_Return_0() {
        //Given
        String input = "abcd";

        //When
        int myAtoiNumber = sut.myAtoi(input);

        //Then
        assertThat(myAtoiNumber).isEqualTo(0);
    }

    @Test
    public void given_Alpha_Numeric_String_When_Convert_String_To_Int_Atom_Then_Return_0() {
        //Given
        String input = "3abcd0872";

        //When
        int myAtoiNumber = sut.myAtoi(input);

        //Then
        assertThat(myAtoiNumber).isEqualTo(0);
    }

    @Test
    public void given_Numeric_String_When_Convert_String_To_Int_Atom_Then_Return_Number() {
        //Given
        String input = "123";

        //When
        int myAtoiNumber = sut.myAtoi(input);

        //Then
        assertThat(myAtoiNumber).isEqualTo(123);
    }

    @Test
    public void given_Positive_Numeric_String_When_Convert_String_To_Int_Atom_Then_Return_Number() {
        //Given
        String input = "+123";

        //When
        int myAtoiNumber = sut.myAtoi(input);

        //Then
        assertThat(myAtoiNumber).isEqualTo(123);
    }

    @Test
    public void given_Negative_Numeric_String_When_Convert_String_To_Int_Atom_Then_Return_Negative_Number() {
        //Given
        String input = "-123";

        //When
        int myAtoiNumber = sut.myAtoi(input);

        //Then
        assertThat(myAtoiNumber).isEqualTo(-123);
    }

    //----------------------------------------------------------------------
    //Tests for ex. 11 - reverseWordInSentence(String s)

    @Test
    public void given_Null_When_Reverse_Words_In_Sentence_Then_Return_Null() {
        //Given
        String input = null;

        //When
        String reversedSentence = sut.reverseWordInSentence(input);

        //Then
        assertThat(reversedSentence).isNull();
    }

    @Test
    public void given_Empty_When_Reverse_Words_In_Sentence_Then_Return_Empty() {
        //Given
        String input = "";

        //When
        String reversedSentence = sut.reverseWordInSentence(input);

        //Then
        assertThat(reversedSentence).isEmpty();
    }

    @Test
    public void given_Blank_When_Reverse_Words_In_Sentence_Then_Return_Empty() {
        //Given
        String input = "         ";

        //When
        String reversedSentence = sut.reverseWordInSentence(input);

        //Then
        assertThat(reversedSentence).isEmpty();
    }

    @Test
    public void given_Sentence_When_Reverse_Words_In_Sentence_Then_Return_Reversed_Sentence() {
        //Given
        String input = "I am blue";

        //When
        String reversedSentence = sut.reverseWordInSentence(input);

        //Then
        assertThat(reversedSentence).isEqualTo("blue am I");
    }

    //----------------------------------------------------------------------
    //Tests for ex. 12 - areStringsRotation(String s1, String s2)

    @Test
    public void given_Null_When_Check_If_Strings_Are_Rotation_Then_Return_False() {
        //Given
        String s1 = null;
        String s2 = null;

        //When
        boolean areRotating = sut.areStringsRotation(s1, s2);

        //Then
        assertThat(areRotating).isFalse();
    }

    @Test
    public void given_Empty_Strings_When_Check_If_Strings_Are_Rotation_Then_Return_False() {
        //Given
        String s1 = "";
        String s2 = "";

        //When
        boolean areRotating = sut.areStringsRotation(s1, s2);

        //Then
        assertThat(areRotating).isFalse();
    }

    @Test
    public void given_Blank_String_When_Check_If_Strings_Are_Rotation_Then_Return_False() {
        //Given
        String s1 = "   ";
        String s2 = " ab";

        //When
        boolean areRotating = sut.areStringsRotation(s1, s2);

        //Then
        assertThat(areRotating).isFalse();
    }

    @Test
    public void given_Non_Rotating_Strings_When_Check_If_Strings_Are_Rotation_Then_Return_False() {
        //Given
        String s1 = "ABCD";
        String s2 = "BADC";

        //When
        boolean areRotating = sut.areStringsRotation(s1, s2);

        //Then
        assertThat(areRotating).isFalse();
    }

    @Test
    public void given_Rotating_Strings_When_Check_If_Strings_Are_Rotation_Then_Return_True() {
        //Given
        String s1 = "ABCD";
        String s2 = "CDAB";

        //When
        boolean areRotating = sut.areStringsRotation(s1, s2);

        //Then
        assertThat(areRotating).isTrue();
    }

    //----------------------------------------------------------------------
    //Tests for ex. 13 - isPalindrome(String s)

    @Test
    public void given_Null_When_Check_If_Palindrome_Then_Return_False() {
        //Given
        String input = null;

        //When
        boolean isPalindrome = sut.isPalindrome(input);

        //Then
        assertThat(isPalindrome).isFalse();
    }

    @Test
    public void given_Empty_String_When_Check_If_Palindrome_Then_Return_False() {
        //Given
        String input = "";

        //When
        boolean isPalindrome = sut.isPalindrome(input);

        //Then
        assertThat(isPalindrome).isFalse();
    }

    @Test
    public void given_Blank_When_Check_If_Palindrome_Then_Return_False() {
        //Given
        String input = "               ";

        //When
        boolean isPalindrome = sut.isPalindrome(input);

        //Then
        assertThat(isPalindrome).isFalse();
    }

    @Test
    public void given_String_With_Length_Of_1_When_Check_If_Palindrome_Then_Return_False() {
        //Given
        String input = "A";

        //When
        boolean isPalindrome = sut.isPalindrome(input);

        //Then
        assertThat(isPalindrome).isFalse();
    }


    @Test
    public void given_Non_Palindrome_When_Check_If_Palindrome_Then_Return_False() {
        //Given
        String input = "West World";

        //When
        boolean isPalindrome = sut.isPalindrome(input);

        //Then
        assertThat(isPalindrome).isFalse();
    }

    @Test
    public void given_Palindrome_When_Check_If_Palindrome_Then_Return_False() {
        //Given
        String input = "Top spot";

        //When
        boolean isPalindrome = sut.isPalindrome(input);

        //Then
        assertThat(isPalindrome).isTrue();
    }

    //----------------------------------------------------------------------
    //Tests for ex. 14
    // getAllSubstrings(String s)

    @Test
    public void given_Null_When_Get_All_Substrings_Then_Return_Empty_List() {
        //Given
        String s = null;

        //When
        List<String> substrings = sut.getAllSubstrings(s);

        //Then
        assertThat(substrings).isEmpty();
    }

    @Test
    public void given_Empty_String_When_Get_All_Substrings_Then_Return_Empty_List() {
        //Given
        String s = "";

        //When
        List<String> substrings = sut.getAllSubstrings(s);

        //Then
        assertThat(substrings).isEmpty();
    }

    @Test
    public void given_String_When_Get_All_Substrings_Then_Return_List_Of_Substrings() {
        //Given
        String s = "abc";

        //When
        List<String> substrings = sut.getAllSubstrings(s);

        //Then
        assertThat(substrings).containsAll(Arrays.asList("abc", "ab", "bc", "a", "b", "c"));
    }

    // hasDuplicateChars(String s)

    @Test
    public void given_Null_When_Has_Duplicate_Chars_Then_Return_False() {
        //Given
        String s = null;

        //When
        boolean hasDuplicatedChar = sut.hasDuplicateChars(s);

        //Then
        assertThat(hasDuplicatedChar).isFalse();
    }

    @Test
    public void given_Empty_When_Has_Duplicate_Chars_Then_Return_False() {
        //Given
        String s = "";

        //When
        boolean hasDuplicatedChar = sut.hasDuplicateChars(s);

        //Then
        assertThat(hasDuplicatedChar).isFalse();
    }

    @Test
    public void given_String_Without_Duplicate_Chars_When_Has_Duplicate_Chars_Then_Return_False() {
        //Given
        String s = "abc";

        //When
        boolean hasDuplicatedChar = sut.hasDuplicateChars(s);

        //Then
        assertThat(hasDuplicatedChar).isFalse();
    }


    @Test
    public void given_String_With_Duplicate_Chars_When_Has_Duplicate_Chars_Then_Return_True() {
        //Given
        String s = "aabbcc";

        //When
        boolean hasDuplicatedChar = sut.hasDuplicateChars(s);

        //Then
        assertThat(hasDuplicatedChar).isTrue();
    }

    // findLongestSubstrNoDuplicates(String s)

    @Test
    public void given_Null_When_Find_Longest_Substring_Then_Return_Null() {
        //Given
        String input = null;

        //When
        String longestSubstr = sut.findLongestSubstrNoDuplicates(input);

        //Then
        assertThat(longestSubstr).isNull();
    }

    @Test
    public void given_Empty_When_Find_Longest_Substring_Then_Return_Null() {
        //Given
        String input = "";

        //When
        String longestSubstr = sut.findLongestSubstrNoDuplicates(input);

        //Then
        assertThat(longestSubstr).isNull();
    }

    @Test
    public void given_String_When_Find_Longest_Substring_Then_Return_Longest_Substring_No_Duplicates() {
        //Given
        String input = "abcd ef abcdeefghhiijj";

        //When
        String longestSubstr = sut.findLongestSubstrNoDuplicates(input);

        //Then
        assertThat(longestSubstr).isEqualTo("abcd ef");
    }

    //----------------------------------------------------------------------
    //Tests for ex. 15 - findLongestPalindromicSub(String s)

    @Test
    public void given_Null_When_Find_Longest_Palindromic_Sub_Then_Return_Null() {
        //Given
        String s = null;

        //When
        String longestPalindrome = sut.findLongestPalindromicSub(s);

        //Then
        assertThat(longestPalindrome).isNull();
    }

    @Test
    public void given_Empty_When_Find_Longest_Palindromic_Sub_Then_Return_Empty() {
        //Given
        String s = "";

        //When
        String longestPalindrome = sut.findLongestPalindromicSub(s);

        //Then
        assertThat(longestPalindrome).isEmpty();
    }

    @Test
    public void given_Blank_When_Find_Longest_Palindromic_Sub_Then_Return_Empty() {
        //Given
        String s = "   ";

        //When
        String longestPalindrome = sut.findLongestPalindromicSub(s);

        //Then
        assertThat(longestPalindrome).isEmpty();
    }

    @Test
    public void given_String_Without_Palindrome_When_Find_Longest_Palindromic_Sub_Then_Return_Empty() {
        //Given
        String s = "Homework.";

        //When
        String longestPalindrome = sut.findLongestPalindromicSub(s);

        //Then
        assertThat(longestPalindrome).isEmpty();
    }

    @Test
    public void given_String_With_Palindrome_When_Find_Longest_Palindromic_Sub_Then_Return_Longest_Palindromic_Sub() {
        //Given
        String s = "Wow, this is a long homework.";

        //When
        String longestPalindrome = sut.findLongestPalindromicSub(s);

        //Then
        assertThat(longestPalindrome).isEqualTo("Wow");
    }

    //----------------------------------------------------------------------
    //Tests for ex. 16 - removeDuplicates(String s)

    @Test
    public void given_Null_When_Remove_Duplicate_Chars_From_String_Then_Return_Null() {
        //Given
        String s = null;

        //When
        String withoutDuplicates = sut.removeDuplicates(s);

        //Then
        assertThat(withoutDuplicates).isNull();
    }

    @Test
    public void given_Empty_When_Remove_Duplicate_Chars_From_String_Then_Return_Empty() {
        //Given
        String s = "";

        //When
        String withoutDuplicates = sut.removeDuplicates(s);

        //Then
        assertThat(withoutDuplicates).isEmpty();
    }

    @Test
    public void given_String_Without_Duplicates_When_Remove_Duplicate_Chars_From_String_Then_Return_String() {
        //Given
        String s = "abc";

        //When
        String withoutDuplicates = sut.removeDuplicates(s);

        //Then
        assertThat(withoutDuplicates).isEqualTo(s);
    }


    @Test
    public void given_String_With_Duplicates_When_Remove_Duplicate_Chars_From_String_Then_Return_String_Without_Duplicates() {
        //Given
        String s = "abbcc  ";

        //When
        String withoutDuplicates = sut.removeDuplicates(s);

        //Then
        assertThat(withoutDuplicates).isEqualTo("abc ");
    }

    //----------------------------------------------------------------------
    //Tests for ex. 17 - removeCharacter(char removeChar, String s)

    @Test
    public void given_Null_String_And_Char_When_Remove_Char_Then_Return_Null() {
        //Given
        String s = null;
        char myChar = 'A';

        //When
        String withoutChar = sut.removeCharacter(myChar, s);

        //Then
        assertThat(withoutChar).isNull();
    }

    @Test
    public void given_Empty_String_And_Char_When_Remove_Char_Then_Return_Empty() {
        //Given
        String s = "";
        char myChar = 'A';

        //When
        String withoutChar = sut.removeCharacter(myChar, s);

        //Then
        assertThat(withoutChar).isEmpty();
    }

    @Test
    public void given_String_Without_Char_And_Char_When_Remove_Char_Then_Return_String() {
        //Given
        String s = "BCD";
        char myChar = 'A';

        //When
        String withoutChar = sut.removeCharacter(myChar, s);

        //Then
        assertThat(withoutChar).isEqualTo(s);
    }

    @Test
    public void given_Blank_String_And_Char_When_Remove_Char_Then_Return_Blank() {
        //Given
        String s = "    ";
        char myChar = 'A';

        //When
        String withoutChar = sut.removeCharacter(myChar, s);

        //Then
        assertThat(withoutChar).isBlank();
    }

    @Test
    public void given_String_And_Char_When_Remove_Char_Then_Return_String_Without_Char() {
        //Given
        String s = "ABCaA";
        char myChar = 'A';

        //When
        String withoutChar = sut.removeCharacter(myChar, s);

        //Then
        assertThat(withoutChar).isEqualTo("BCa");
    }

    //----------------------------------------------------------------------
    //Tests for ex. 18

    //getStringOccurrence(String s, String[] strings)

    @Test
    public void given_String_And_Null_Array_When_Getting_String_Occurrence_Then_Return_0() {
        //Given
        String s = "Java";
        String[] strings = null;

        //When
        int occurrence = sut.getStringOccurrence(s, strings);

        //Then
        assertThat(occurrence).isEqualTo(0);
    }

    @Test
    public void given_Null_String_And_Array_Of_Strings_When_Getting_String_Occurrence_Then_Return_0() {
        //Given
        String s = null;
        String[] strings = {"Java", "Ruby", "Java", "Python", "Ruby", "SQL", "Java", "Ruby"};

        //When
        int occurrence = sut.getStringOccurrence(s, strings);

        //Then
        assertThat(occurrence).isEqualTo(0);
    }

    @Test
    public void given_Empty_String_And_Array_Of_Strings_When_Getting_String_Occurrence_Then_Return_0() {
        //Given
        String s = "";
        String[] strings = {"Java", "Ruby", "Java", "Python", "Ruby", "SQL", "Java", "Ruby"};

        //When
        int occurrence = sut.getStringOccurrence(s, strings);

        //Then
        assertThat(occurrence).isEqualTo(0);
    }

    @Test
    public void given_String_And_Array_Of_Strings_When_Getting_String_Occurrence_Then_Return_Occurrence() {
        //Given
        String s = "Java";
        String[] strings = {"Java", "Ruby", "Java", "Python", "Ruby", "SQL", "Java", "Ruby"};

        //When
        int occurrence = sut.getStringOccurrence(s, strings);

        //Then
        assertThat(occurrence).isEqualTo(3);
    }

    //findMostFrequentString(String[] strings)

    @Test
    public void given_Null_Array_When_Finding_Most_Frequent_String_Then_Return_Null() {
        //Given
        String[] strings = null;

        //When
        String mostFrequent = sut.findMostFrequentString(strings);

        //Then
        assertThat(mostFrequent).isNull();
    }

    @Test
    public void given_Empty_Array_When_Finding_Most_Frequent_String_Then_Return_Empty_String() {
        //Given
        String[] strings = {};

        //When
        String mostFrequent = sut.findMostFrequentString(strings);

        //Then
        assertThat(mostFrequent).isEmpty();
    }

    @Test
    public void given_Array_Of_Strings_When_Finding_Most_Frequent_String_Then_Return_Most_Frequent_String() {
        //Given
        String[] strings = {"Ruby", "Java", "Python", "Java", "Ruby", "SQL", "Java", "Ruby"};

        //When
        String mostFrequent = sut.findMostFrequentString(strings);

        //Then
        assertThat(mostFrequent).isEqualTo("Java");
    }
}
