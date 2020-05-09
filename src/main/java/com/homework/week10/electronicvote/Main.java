package com.homework.week10.electronicvote;

import com.homework.week10.electronicvote.service.ElectronicVoteService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ElectronicVoteService electronicVoteService = new ElectronicVoteService();
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
                    Scanner textInput = new Scanner(System.in);
                    System.out.println("Enter citizen CNP:");
                    String cnp = textInput.nextLine();
                    System.out.println("Enter candidate name:");
                    String candidateName = textInput.nextLine();
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
