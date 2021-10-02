package ru.academits.pereyma.lambdas.main;

import ru.academits.pereyma.lambdas.Person;

import java.util.*;
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

        //А Получение списка уникальных имен

        List<String> uniqueNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        //Б Вывод уникальных имен

        System.out.println(uniqueNames.stream()
                .collect(Collectors.joining(", ", "Имена: ", ".")));

        System.out.println();

        //В Получение списка людей младше 18, расчет среднего возраста

        List<String> under18People = persons.stream()
                .filter(x -> x.getAge() < 18)
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.println(under18People.stream()
                .collect(Collectors.joining(", ", "Имена людей младше 18: ", ".")));

        double under18PeopleAverageAge = persons.stream()
                .filter(x -> x.getAge() < 18)
                .mapToDouble(Person::getAge)
                .average().orElse(0);

        System.out.println("Средний возраст людей младше 18: " + under18PeopleAverageAge);

        System.out.println();

        //Г Получение Map, ключи - имена, значения - средний возраст

        Map<String, Double> namesAndAverageAges = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));

        System.out.println(namesAndAverageAges);

        System.out.println();

        //Д Получить людей от 20 до 45 и вывести в консоль имена в порядке убывания возраста

        System.out.println("Список людей от 20 до 45 в порядке убывания возраста: ");
        persons.stream().filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted((p1, p2) -> -Integer.compare(p1.getAge(), p2.getAge()))
                .forEach(p -> System.out.println(p.getName()));
    }
}
