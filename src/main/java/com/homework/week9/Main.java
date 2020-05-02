package com.homework.week9;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();

        populateList(persons);

        printPersonsByAge(persons);
        printPersonsByName(persons);

        Hobby dancing = Hobby.builder()
                .hobbyName("Dancing")
                .frequency(2)
                .addresses(Arrays.asList(new Address("Verona", "Italy"),
                        new Address("Versailles", "France")))
                .build();

        Hobby swordFighting = Hobby.builder()
                .hobbyName("Sword fighting")
                .frequency(3)
                .addresses(Arrays.asList(new Address("Verona", "Italy"),
                        new Address("Helsignor", "Denmark"),
                        new Address("London", "England")))
                .build();

        Hobby travelling = Hobby.builder()
                .hobbyName("Travelling")
                .frequency(1)
                .addresses(Arrays.asList(new Address("Navarre", "Spain"),
                        new Address("Vienna", "Austria"),
                        new Address("Mantua", "Italy")))
                .build();

        Hobby playing = Hobby.builder()
                .hobbyName("Playing Hide and Seek")
                .frequency(3)
                .addresses(Arrays.asList(new Address("Verona", "Italy"),
                        new Address("Vienna", "Austria"),
                        new Address("Inverness", "Scotland")))
                .build();

        Hobby drinking = Hobby.builder()
                .hobbyName("Drinking and getting drunk")
                .frequency(6)
                .addresses(Arrays.asList(new Address("Verona", "Italy"),
                        new Address("Versailles", "France"),
                        new Address("Windsor", "England")))
                .build();

        Map<Person, List<Hobby>> personToHobbies = new HashMap<>();

        personToHobbies.put(persons.get(0), Arrays.asList(swordFighting, drinking, travelling, playing));
        personToHobbies.put(persons.get(1), Arrays.asList(dancing, playing));
        personToHobbies.put(persons.get(2), Arrays.asList(drinking, dancing));

        System.out.println("Hobbies of " + persons.get(0).getName() + ":");
        printHobbies(personToHobbies.get(persons.get(0)));
    }

    private static void populateList(List<Person> persons) {
        Person romeo = new Person("Romeo", 16);
        Person juliet = new Person("Juliet", 14);
        Person ladyCapulet = new Person("Lady Capulet", 30);
        Person benvolio = new Person("Benvolio", 20);
        Person tybalt = new Person("Tybalt", 22);
        Person mercutio = new Person("Mercutio", 18);
        Person countParis = new Person("Count Paris", 25);

        persons.add(romeo);
        persons.add(juliet);
        persons.add(ladyCapulet);
        persons.add(benvolio);
        persons.add(tybalt);
        persons.add(mercutio);
        persons.add(countParis);
    }

    private static void printPersonsByName(List<Person> persons) {
        Set<Person> personsByName = new TreeSet<>(new PersonNameComparator());
        personsByName.addAll(persons);
        System.out.println("Persons by name: ");
        personsByName.forEach(person -> System.out.println(person.toString()));
        System.out.println("--------------------------");
    }

    private static void printPersonsByAge(List<Person> persons) {
        Set<Person> personsByAge = new TreeSet<>(new PersonAgeComparator());
        personsByAge.addAll(persons);
        System.out.println("Persons by age: ");
        personsByAge.forEach(person -> System.out.println(person.toString()));
        System.out.println("--------------------------");
    }

    private static void printHobbies(List<Hobby> hobbies) {
        for (Hobby hobby : hobbies) {
            System.out.println("Hobby: " + hobby.getHobbyName());
            for (Address address : hobby.getAddresses()) {
                System.out.println("- " + address.getCountry());
            }
        }
    }
}





