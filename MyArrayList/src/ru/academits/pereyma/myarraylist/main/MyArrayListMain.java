package ru.academits.pereyma.myarraylist.main;

import ru.academits.pereyma.myarraylist.MyArrayList;

import java.util.Arrays;

public class MyArrayListMain {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list);

        Integer[] i = new Integer[]{1, 1};
        System.out.println(Arrays.toString(i));

        Integer[] i1 = list.toArray(i);

        System.out.println(Arrays.toString(i));
        System.out.println(Arrays.toString(i1));

        System.out.println("===");

        String[] s = new String[]{"abs", "abs"};
        System.out.println(Arrays.toString(s));

        String[] s1 = list.toArray(s);

        System.out.println(Arrays.toString(s));
        System.out.println(Arrays.toString(s1));

    }
}
