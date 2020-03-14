package com.homework.week4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryCatalogue {
    static List<Book> bookList = new ArrayList<>();
    static Scanner numericalInput = new Scanner(System.in);
    static int choice;
    static Scanner textInput = new Scanner(System.in);

    public static void main(String[] args) {
        populateDefaultLibrary();
        System.out.println("Hello! Welcome to the Library Catalogue!");
        makeChoice();
    }

    private static void populateDefaultLibrary() {
        Book charlieChocolateFactory = new Novel("Charlie and the Chocolate Factory", 120, "Children's fantasy novel");
        bookList.add(charlieChocolateFactory);
        Book nineteenEightyFour = new Novel("Nineteen Eighty-Four", 150, "Dystopian Fiction");
        bookList.add(nineteenEightyFour);
        Book frankenstein = new Novel("Frankenstein", 90, "Horror fiction");
        bookList.add(frankenstein);
        Book gustavKlimt = new Album("Gustav Klimt", 60, "Glossy paper");
        bookList.add(gustavKlimt);
        Book vanGogh = new Album("Van Gogh", 744, "Silk coated paper");
        bookList.add(vanGogh);
        Book dali = new Album("Dali", 96, "Semi-gloss paper");
        bookList.add(dali);
    }

    private static void printMenu() {
        System.out.println("-----------------------------------------" +
                "\nPress the number for one of the following options:" +
                "\n1 --> List the books in the Library" +
                "\n2 --> Add a book to the list" +
                "\n3 --> Delete a book from the list" +
                "\n4 --> Display book details" +
                "\n5 --> Quit");
    }

    private static void makeChoice() {
        boolean continueOptions = true;

        while (continueOptions) {
            printMenu();
            choice = numericalInput.nextInt();

            switch (choice) {
                case 1:
                    printList();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    displayDetails();
                    break;
                case 5:
                    System.out.println("Bye!");
                    continueOptions = false;
                    break;
                default:
                    System.out.println("Bad input!");
            }
        }
    }


    private static void printList() {
        int i = 1;
        for (Book book : bookList) {
            System.out.println(i + ". " + book.getName());
            i++;
        }
    }

    private static void addBook() {
        System.out.println("What type of book would you like to add?" +
                "\n1 --> Novel" +
                "\n2 --> Album");
        boolean goodChoice = false;
        while (!goodChoice) {
            choice = numericalInput.nextInt();
            switch (choice) {
                case 1:
                    addNovel();
                    goodChoice = true;
                    break;
                case 2:
                    addAlbum();
                    goodChoice = true;
                    break;
                default:
                    System.out.println("Bad input! " +
                            "\nPlease try again:" +
                            "\n1 --> Novel" +
                            "\n2 --> Album");
            }
        }
    }

    private static void addNovel() {
        System.out.println("Novel name: ");
        String name = textInput.nextLine();
        System.out.println("Number of pages: ");
        int pages = numericalInput.nextInt();
        System.out.println("Novel type (Mystery, Fiction, etc.): ");
        String type = textInput.nextLine();

        bookList.add(new Novel(name, pages, type));
        System.out.println("" +
                "\nThe novel " + name + " has been added to the library." +
                "\n ");
    }

    private static void addAlbum() {
        System.out.println("Album name: ");
        String name = textInput.nextLine();
        System.out.println("Number of pages: ");
        int pages = numericalInput.nextInt();
        System.out.println("Paper quality (Glossy, Matte, etc.): ");
        String paperQuality = textInput.nextLine();

        bookList.add(new Album(name, pages, paperQuality));
        System.out.println("" +
                "\nThe album " + name + " has been added to the library." +
                "\n ");
    }

    private static void deleteBook() {
        System.out.println("Here is a list of all the books in the library.");
        printList();

        boolean goodChoice = false;
        while (!goodChoice) {
            System.out.println("What book would you like to remove? Type in the number: ");
            choice = numericalInput.nextInt();
            if ((choice > 0) && (choice <= (bookList.size()))) {
                bookList.remove((choice - 1));
                goodChoice = true;
            } else {
                System.out.println("Bad number!");
            }
        }
    }

    private static void displayDetails() {
        System.out.println("Here is a list of all the books in the library.");
        printList();

        boolean goodChoice = false;
        while (!goodChoice) {
            System.out.println("For which book would you like to see the details? Type in the number: ");
            choice = numericalInput.nextInt();
            if ((choice > 0) && (choice <= (bookList.size()))) {
                printDetailsByType();
                goodChoice = true;
            } else {
                System.out.println("Bad number!");
            }
        }
    }

    private static void printDetailsByType() {
        Book book = bookList.get(choice - 1);
        System.out.println(book.getDetails());
    }
}
