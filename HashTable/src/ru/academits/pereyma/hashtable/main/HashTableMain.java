package ru.academits.pereyma.hashtable.main;

import ru.academits.pereyma.hashtable.HashTable;

import java.util.ArrayList;
import java.util.Arrays;

public class HashTableMain {
    public static void main(String[] args) {
        HashTable<Integer> table = new HashTable<>(20);
        HashTable<Integer> table2 = new HashTable<>();

        System.out.println(table);
        System.out.println(table.isEmpty());

        table.add(1);
        table.add(17);
        table.add(17);

        System.out.println(table.size());

        System.out.println(table);
        System.out.println(table.isEmpty());

        System.out.println("===");

        System.out.println(table.contains(5));
        table.add(5);
        System.out.println(table.contains(5));

        for (Integer e : table) {
            System.out.println(e);
        }

        System.out.println(Arrays.toString(table.toArray((new Integer[]{1}))));

        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 5, 17, 17, 17));

        System.out.println("===");

        System.out.println(table.remove(4));
        System.out.println(table);

        System.out.println(table.containsAll(list2));

        System.out.println(table.addAll(list2));
        System.out.println(table);

        System.out.println("===");

        System.out.println(table.retainAll(Arrays.asList(1, 5, 17)));
        System.out.println(table);
        System.out.println(table2);
        table2.add(443);
        System.out.println(table2);
    }
}
