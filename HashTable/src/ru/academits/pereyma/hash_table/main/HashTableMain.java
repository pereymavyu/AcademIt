package ru.academits.pereyma.hash_table.main;

import ru.academits.pereyma.hash_table.HashTable;

import java.util.ArrayList;
import java.util.Arrays;

public class HashTableMain {
    public static void main(String[] args) {
        HashTable<Integer> table1 = new HashTable<>(20);
        HashTable<Integer> table2 = new HashTable<>();

        System.out.println(table1);
        System.out.println(table1.isEmpty());

        table1.add(1);
        table1.add(0);
        table1.add(0);
        table1.add(2);
        table1.add(2);
        table1.add(1);
        table1.add(17);
        table1.add(17);
        table1.add(null);
        table1.add(null);

        System.out.println(table1.size());

        System.out.println(table1);
        System.out.println(table1.isEmpty());

        System.out.println("===");

        System.out.println(table1.contains(5));
        table1.add(5);
        System.out.println(table1.contains(5));

        System.out.println(table1);
        System.out.println(table1.size());

        for (Integer e : table1) {
            System.out.println(e);
        }

        System.out.println(Arrays.toString(table1.toArray(new Integer[]{1, 2, 3})));

        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 5, 17, 17, 17, null));

        System.out.println("===");

        System.out.println(table1.remove(null));
        System.out.println(table1);

        System.out.println(table1.containsAll(list2));

        System.out.println(table1.addAll(list2));
        System.out.println(table1);

        System.out.println("===");

        System.out.println(table1.size());
        System.out.println(table1.retainAll(Arrays.asList(1, 5, 17)));
        System.out.println(table1.size());
        System.out.println(table1);
        System.out.println(table2);
        table2.add(443);
        System.out.println(table2);
    }
}
