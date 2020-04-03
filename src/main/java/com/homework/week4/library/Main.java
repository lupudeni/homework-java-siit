package com.homework.week4.library;

public class Main {
    public static void main(String[] args) {
        LibraryFrontDesk libraryFrontDesk = new LibraryFrontDesk();
        libraryFrontDesk.populateDefaultLibrary();
        System.out.println("Hello! Welcome to the Library Catalogue!");
        libraryFrontDesk.makeChoice();
    }
}
