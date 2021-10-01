package ru.academits.pereyma.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class LambdasMain {
    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>();

        persons.add(new Person("Ivan", 30));
        persons.add(new Person("Ivan", 38));
        persons.add(new Person("Ivan", 50));
        persons.add(new Person("Dmitrii", 13));
        persons.add(new Person("Svetlana", 32));
        persons.add(new Person("Elena", 34));
        persons.add(new Person("Egor", 29));
        persons.add(new Person("Egor", 15));
        persons.add(new Person("Petr", 13));


        System.out.println(persons);

        List<String> uniqueNames = persons.stream().map(Person::getName).distinct().collect(Collectors.toList());

        System.out.println(Arrays.toString(uniqueNames.toArray()));

        List<String> youngPeople = persons.stream().filter(x -> x.getAge() <= 18).map(Person::getName).distinct().collect(Collectors.toList());

        System.out.println(Arrays.toString(youngPeople.toArray()));

        OptionalDouble meanAge = persons.stream().filter(x -> x.getAge() <= 18).mapToDouble(Person::getAge).average();
        System.out.println(meanAge);
    }
}
