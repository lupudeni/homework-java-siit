package com.ibm.streams.tutorial;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Streams {

    public static class NotImplementedException extends RuntimeException {
        public NotImplementedException() {
            super("This method hasn't been implemented yet!");
        }
    }

    public static List<Integer> returnSquareRoot(List<Integer> numbers) {
        return numbers.stream()
                .map(Math::sqrt)
                .map(Double::intValue)
                .collect(toList());
    }

    public static List<Integer> getAgeFromUsers(List<User> user) {
        return user.stream()
                .map(User::getAge)
                .collect(toList());
    }

    public static List<Integer> getDistinctAges(List<User> users) {
        return users.stream()
                .map(User::getAge)
                .distinct()
                .collect(toList());
    }

    public static List<User> getLimitedUserList(List<User> users, int limit) {
        return users.stream()
                .limit(limit)
                .collect(toList());
    }

    public static Integer countUsersOlderThen25(List<User> users) {
        return (int) users.stream()
                .filter(user -> user.getAge() > 25)
                .count();
    }

    public static List<String> mapToUpperCase(List<String> strings) {
        return strings.stream()
                .map(String::toUpperCase)
                .collect(toList());
    }

    public static Integer sum(List<Integer> integers) {
        return integers.stream()
                .reduce(0, Integer::sum);
    }

    public static List<Integer> skip(List<Integer> integers, Integer toSkip) {
        return integers.stream()
                .skip(toSkip)
                .collect(toList());
    }

    public static List<String> getFirstNames(List<String> names) {
        return names.stream()
                .map(name -> name.split("\\s+"))
                .map(name -> name[0])
                .collect(toList());
    }

    public static List<String> getDistinctLetters(List<String> names) {
        return names.stream()
                .map(name -> name.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
    }

    public static String separateNamesByComma(List<User> users) {
        return users.stream()
                .map(User::getName)
                .collect(joining(", "));
    }

    public static double getAverageAge(List<User> users) {
        return users.stream()
                .map(User::getAge)
                .mapToDouble(Integer::doubleValue)
                .average()
                .getAsDouble();
    }

    public static Integer getMaxAge(List<User> users) {
        return users.stream()
                .map(User::getAge)
                .max(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                })
                .get();
    }

    public static Integer getMinAge(List<User> users) {
        return users.stream()
                .map(User::getAge)
                .min(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                })
                .get();
    }

    public static Map<Boolean, List<User>> partionUsersByGender(List<User> users) {
        return users.stream()
                .collect(partitioningBy(User::isMale));
    }

    public static Map<Integer, List<User>> groupByAge(List<User> users) {
        return users.stream()
                .collect(groupingBy(User::getAge));
    }

    public static Map<Boolean, Map<Integer, List<User>>> groupByGenderAndAge(List<User> users) {
        return users.stream()
                .collect(groupingBy(User::isMale, groupingBy(User::getAge)));
    }

    public static Map<Boolean, Long> countGender(List<User> users) {
        return users.stream()
                .collect(partitioningBy(User::isMale, counting()));
    }

    public static boolean anyMatch(List<User> users, int age) {
        return users.stream()
                .anyMatch(user -> user.getAge() == age);
    }

    public static boolean noneMatch(List<User> users, int age) {
        return users.stream()
                .noneMatch(user -> user.getAge() == age);
    }

    public static Optional<User> findAny(List<User> users, String name) {
        return users.stream()
                .findAny();
    }

    public static List<User> sortByAge(List<User> users) {
        return users.stream()
                .sorted(new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return o1.getAge() - o2.getAge();
                    }
                })
                .collect(Collectors.toList());
    }

    public static Stream<Integer> getBoxedStream(IntStream stream) {
        return stream.boxed();
    }

    public static List<Integer> generateFirst10PrimeNumbers() {
        return IntStream.iterate(2, i -> i + 1)
                .filter(Streams::isPrime)
                .limit(10)
                .boxed()
                .collect(Collectors.toList());
    }

    public static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, number / 2).noneMatch(i -> number % i == 0);
    }

    public static List<Integer> generate10RandomNumbers() {
        return IntStream.iterate(1, i -> (int) (Math.random() * 10))
                .limit(10)
                .boxed()
                .collect(Collectors.toList());
    }

    public static User findOldest(List<User> users) {
        return users.stream()
                .max(new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return o1.getAge() - o2.getAge();
                    }
                })
                .get();
    }

    public static int sumAge(List<User> users) {
        return users.stream()
                .map(User::getAge)
                .reduce(Integer::sum)
                .get();
    }

}
