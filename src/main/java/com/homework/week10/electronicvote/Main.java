package com.homework.week10.electronicvote;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String citizenText = "src/main/java/com/homework/week10/electronicvote/citizens.txt";
        String candidatesText = "src/main/java/com/homework/week10/electronicvote/candidates.txt";
        ElectronicVoteService electronicVoteService = new ElectronicVoteService(citizenText, candidatesText);
        runApp(electronicVoteService);
    }

    private static void printMenu() {
        System.out.println("Press " +
                "\n1 --> Vote" +
                "\n2 --> See Poll" +
                "\n3 --> Exit App");
    }

    private static void runApp(ElectronicVoteService electronicVoteService) {
        Scanner input = new Scanner(System.in);
        DECISION_LOOP:
        while (true) {
            printMenu();
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    String cnp = input.next();
                    String candidateName = input.nextLine();
                    System.out.println(electronicVoteService.vote(cnp, candidateName));
                    break;
                case 2:
                    electronicVoteService.getPoll().forEach((k, v) -> System.out.println(k + " : " + v + " votes"));
                    break;
                case 3:
                    break DECISION_LOOP;
            }
        }
    }
}
