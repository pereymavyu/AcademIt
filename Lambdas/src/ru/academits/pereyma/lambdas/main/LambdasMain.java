package ru.academits.pereyma.lambdas.main;

import ru.academits.pereyma.lambdas.Person;

import java.util.*;
import java.util.stream.Collectors;

public class LambdasMain {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Ivan", 30),
                new Person("Ivan", 38),
                new Person("Ivan", 50),
                new Person("Dmitrii", 13),
                new Person("Svetlana", 32),
                new Person("Elena", 34),
                new Person("Egor", 29),
                new Person("Egor", 15),
                new Person("Petr", 13)
        );

        // А Получение списка уникальных имен

        List<String> uniqueNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        // Б Вывод уникальных имен

        System.out.println(uniqueNames.stream()
                .collect(Collectors.joining(", ", "Имена: ", ".")));

        System.out.println();

        // В Получение списка людей младше 18, расчет среднего возраста

        List<Person> under18People = persons.stream()
                .filter(x -> x.getAge() < 18)
                .collect(Collectors.toList());

        System.out.println(under18People.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", ", "Имена людей младше 18: ", ".")));

        double under18PeopleAverageAge = under18People.stream()
                .mapToDouble(Person::getAge)
                .average()
                .orElse(0);

        System.out.println("Средний возраст людей младше 18: " + under18PeopleAverageAge);

        System.out.println();

        // Г Получение Map, ключи - имена, значения - средний возраст

        Map<String, Double> namesAndAverageAges = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));

        System.out.println(namesAndAverageAges);

        System.out.println();

        // Д Получить людей от 20 до 45 и вывести в консоль имена в порядке убывания возраста

        System.out.println("Список людей от 20 до 45 в порядке убывания возраста: ");
        persons.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted((p1, p2) -> Integer.compare(p2.getAge(), p1.getAge()))
                .forEach(p -> System.out.println(p.getName()));
    }
}
