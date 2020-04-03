package com.homework.week4.library;

import com.homework.util.ActionStatus;

import java.util.Scanner;

/**
 * This class is the user interface of the Library app.
 */
public class LibraryFrontDesk {
    private Scanner numericalInput;
    private Scanner textInput;
    private LibraryCatalogue catalogue;

    public LibraryFrontDesk() {
        this(new Scanner(System.in), new Scanner(System.in), new LibraryCatalogue());
    }

    public LibraryFrontDesk(Scanner numericalInput, Scanner textInput, LibraryCatalogue catalogue) {
        this.numericalInput = numericalInput;
        this.textInput = textInput;
        this.catalogue = catalogue;
    }

    void populateDefaultLibrary() {
        catalogue.addNovel("Charlie and the Chocolate Factory", 120, "Children's fantasy novel");
        catalogue.addNovel("Nineteen Eighty-Four", 150, "Dystopian Fiction");
        catalogue.addNovel("Frankenstein", 90, "Horror fiction");
        catalogue.addAlbum("Gustav Klimt", 60, "Glossy paper");
        catalogue.addAlbum("Van Gogh", 744, "Silk coated paper");
        catalogue.addAlbum("Dali", 96, "Semi-gloss paper");
    }

    void printMenu() {
        System.out.println("-----------------------------------------" +
                "\nPress the number for one of the following options:" +
                "\n1 --> List the books in the Library" +
                "\n2 --> Add a book to the list" +
                "\n3 --> Delete a book from the list" +
                "\n4 --> Display book details" +
                "\n5 --> Quit");
    }

    void makeChoice() {
        boolean continueOptions = true;

        while (continueOptions) {
            printMenu();
            int choice = numericalInput.nextInt();

            switch (choice) {
                case 1:
                    catalogue.printList();
                    break;
                case 2:
                    String status = addBook();
                    if (ActionStatus.SUCCESS.equals(status)) {
                        System.out.println("The book has been added to the library.");
                    } else if (ActionStatus.FAIL.equals(status)) {
                        System.out.println("Not enough information");
                    } else {
                        System.out.println(status);
                    }
                    break;
                case 3:
                    if (ActionStatus.SUCCESS.equals(deleteBook())) {
                        System.out.println("Book has been deleted successfully");
                    } else {
                        System.out.println(ActionStatus.NOT_FOUND);
                    }
                    break;
                case 4:
                    System.out.println(displayDetails());
                    break;
                case 5:
                    System.out.println("Bye!");
                    continueOptions = false;
                    break;
                default:
                    System.out.println(ActionStatus.BAD_INPUT);
            }
        }
    }

    String addBook() {
        System.out.println("What type of book would you like to add?" +
                "\n1 --> Novel" +
                "\n2 --> Album");
        int choice = numericalInput.nextInt();
        switch (choice) {
            case 1:
                return addNovel();
            case 2:
                return addAlbum();
            default:
                return ActionStatus.BAD_INPUT;
        }
    }

    String addNovel() {
        System.out.println("Novel name: ");
        String novelName = textInput.nextLine();
        System.out.println("Number of pages: ");
        int novelPages = numericalInput.nextInt();
        System.out.println("Novel type (Mystery, Fiction, etc.): ");
        String type = textInput.nextLine();

        return catalogue.addNovel(novelName, novelPages, type);
    }

    String addAlbum() {
        System.out.println("Album name: ");
        String albumName = textInput.nextLine();
        System.out.println("Number of pages: ");
        int albumPages = numericalInput.nextInt();
        System.out.println("Paper quality (Glossy, Matte, etc.): ");
        String paperQuality = textInput.nextLine();

        return catalogue.addAlbum(albumName, albumPages, paperQuality);
    }


    String deleteBook() {
        System.out.println("Here is a list of all the books in the library.");
        catalogue.printList();

        System.out.println("What book would you like to remove? Type in the number: ");
        int choice = numericalInput.nextInt();
        return catalogue.deleteBook(choice);
    }


    String displayDetails() {
        System.out.println("Here is a list of all the books in the library.");
        catalogue.printList();

        System.out.println("For which book would you like to see the details? Type in the number: ");
        int choice = numericalInput.nextInt();

        return catalogue.displayDetails(choice);
    }

}


