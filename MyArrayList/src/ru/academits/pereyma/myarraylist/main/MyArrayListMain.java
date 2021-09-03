package ru.academits.pereyma.myarraylist.main;

import ru.academits.pereyma.myarraylist.MyArrayList;

import java.util.Arrays;

public class MyArrayListMain {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();

        System.out.println(list);
        System.out.println(list.isEmpty());
        list.add(7);
        System.out.println(list);
        list.add((int) 7.0d);
        System.out.println(list);
        System.out.println(list.isEmpty());
        System.out.println(list.size());
        System.out.println(list.contains(0));
        System.out.println(list.contains(7));
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(Arrays.toString(list.toArray(new Integer[4])));
        System.out.println(list.remove(Integer.valueOf(7)));
        System.out.println(list);

    }
}
