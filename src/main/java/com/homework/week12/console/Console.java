package com.homework.week12.console;

import java.util.Scanner;

public class Console {
    private static CommandService commandService = new CommandService();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        printHelp();
        while (true) {
            System.out.print(commandService.getCurrentWorkingPath() + "# ");
            String command = input.nextLine();
            switch(command) {
                case "exit":
                    System.exit(0);
                case "help":
                    printHelp();
                    break;
                default:
                    String result = commandService.call(command);
                    System.out.println(result);
            }
        }
    }

    private static void printHelp() {
        System.out.println("Available commands:" +
                "\nhelp" +
                "\nexit" +
                "\nls" +
                "\ncd" +
                "\ncp" +  //cp a ../a
                "\nmv" +
                "\nwc");
    }
}
