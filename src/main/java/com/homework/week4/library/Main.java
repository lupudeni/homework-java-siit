package com.homework.week4.library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryFrontDesk libraryFrontDesk = new LibraryFrontDesk(new Scanner(System.in), new Scanner(System.in), new LibraryCatalogue());
        libraryFrontDesk.populateDefaultLibrary();
        System.out.println("Hello! Welcome to the Library Catalogue!");
        libraryFrontDesk.makeChoice();
    }
}
