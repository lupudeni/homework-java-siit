package com.homework.week11.streams.exercise;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class WallStreetService {

    static Trader raoul = new Trader("Raoul", "Cambridge");

    static Trader mario = new Trader("Mario", "Milan");

    static Trader alan = new Trader("Alan", "Cambridge");

    static Trader brian = new Trader("Brian", "Cambridge");

    static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    public static void main(String... args) {

        System.out.println("1. Find all transactions in the year 2011 and sort them by value (small to high).");
        findSortedTransactionsFromYear(2012).forEach(System.out::println);

        System.out.println("2. What are all the unique cities where the traders work?");
        findUniqueCitiesOfTraders().forEach(System.out::println);

        System.out.println("3. Find all traders from Cambridge and sort them by name.");
        findAllTradersFromCity("Cambridge").forEach(trader -> System.out.println(trader.getName()));

        System.out.println("4. Return a string of all traders’ names sorted alphabetically.");
        System.out.println(getAllTradersNamesAlphabetically());

        System.out.println("5. Are any traders based in Milan?");
        System.out.println(areAnyTradersBasedInCity("Milan") ? "Yes" : "No");

        System.out.println("6. Print all transactions’ values from the traders living in Cambridge.");
        getTransactionValuesFromTradersLivingInCity("Cambridge").forEach(System.out::println);

        System.out.println("7. What’s the highest value of all the transactions?");
        System.out.println(getHighestValueOfAllTransactions());

        System.out.println("8. Find the transaction with the smallest value.");
        System.out.println(findTransactionWithSmallestValue());

    }
    /*
    1. Find all transactions in the year 2011 and sort them by value (small to high).
    2. What are all the unique cities where the traders work?
    3. Find all traders from Cambridge and sort them by name.
    4. Return a string of all traders’ names sorted alphabetically.
    5. Are any traders based in Milan?
    6. Print all transactions’ values from the traders living in Cambridge.
    7. What’s the highest value of all the transactions?
    8. Find the transaction with the smallest value.
     */

    //1
    static List<Transaction> findSortedTransactionsFromYear(int year) {
        return transactions.stream()
                .filter(transaction -> transaction.getYear() == year)
//                           .sorted((t1, t2) -> t1.getValue() - t2.getValue())
                .sorted(Comparator.comparingInt(Transaction::getValue))
//                           .collect(Collectors.toCollection(ArrayList::new));
                .collect(toList());
    }

    //2
    static Set<String> findUniqueCitiesOfTraders() {
        return transactions.stream() //Transaction
//                           .map(transaction -> transaction.getTrader().getCity())

//                          .map(transaction -> transaction.getTrader())
                .map(Transaction::getTrader) //String::isEmpty

//                           .map(trader -> trader.getCity()) //String==city apply(Trader t)
                .map(Trader::getCity) //String==city apply(Trader t)

                .collect(Collectors.toSet());
    }

    //Continued exercises from class:
    //3
    static List<Trader> findAllTradersFromCity(String city) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals(city))
                .distinct()
                .sorted((trader1, trader2) -> trader1.getName().compareTo(trader2.getName()))
                .collect(Collectors.toList());
    }

    //4
    static String getAllTradersNamesAlphabetically() {
        return transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted((name1, name2) -> name1.compareTo(name2))
                .collect(Collectors.joining(", "));
    }

    //5
    static boolean areAnyTradersBasedInCity(String city) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .anyMatch(traderCity -> traderCity.equals(city));
    }

    //6
    static List<Integer> getTransactionValuesFromTradersLivingInCity(String city) {
        return transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals(city))
                .map(Transaction::getValue)
                .collect(Collectors.toList());
    }

    //7
    static int getHighestValueOfAllTransactions() {
        return transactions.stream()
                .map(Transaction::getValue).max(new Comparator<Integer>() {

                    @Override
                    public int compare(Integer t1, Integer t2) {
                        return t1 - t2;
                    }
                })
                .get();
    }

    //8
    static Transaction findTransactionWithSmallestValue() {
        return transactions.stream()
                .map(transaction -> transaction).min(new Comparator<Transaction>() {
                    @Override
                    public int compare(Transaction t1, Transaction t2) {
                        return t1.getValue() - t2.getValue();
                    }
                })
                .get();
    }

}
