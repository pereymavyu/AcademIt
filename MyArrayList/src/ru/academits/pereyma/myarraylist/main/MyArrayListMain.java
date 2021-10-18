package ru.academits.pereyma.myarraylist.main;

import ru.academits.pereyma.myarraylist.MyArrayList;

import java.util.Arrays;

public class MyArrayListMain {
    public static void main(String[] args) {
        MyArrayList<Integer> list1 = new MyArrayList<>(1);

        list1.add(1);
        list1.add(2);
        list1.add(null);
        list1.add(3);
        list1.add(4);
        list1.add(5);

        System.out.println(list1);
        System.out.println(list1.size());
        System.out.println(list1.isEmpty());
        System.out.println(list1.contains(null));
        System.out.println(Arrays.toString(list1.toArray()));
        System.out.println(Arrays.toString(list1.toArray(new Integer[]{0, 0, 0, 0, 0, 0, 0, 0})));
        list1.trimToSize();
        System.out.println(list1);

        System.out.println("=====");

        System.out.println(list1.remove(Integer.valueOf(-1)));
        System.out.println(list1.remove(2));
        System.out.println(list1);
        System.out.println(list1.size());

        MyArrayList<Integer> list2 = new MyArrayList<>();

        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);

        System.out.println(list1.containsAll(list2));
        System.out.println(list1.retainAll(list2));
        System.out.println(list1);

        System.out.println("=====");

        System.out.println(list2.equals(list1));
        System.out.println(list1.hashCode());
        System.out.println(list2.hashCode());

        list1.add(0, null);

        System.out.println(list1.get(0));
        System.out.println(list1);
        System.out.println(list1.set(4, 0));
        System.out.println(list1);
        list1.add(0, 5);
        System.out.println(list1);

        System.out.println("=====");

        list1.add(null);
        list1.add(null);
        list1.remove(0);
        System.out.println(list1);

        System.out.println(list1.lastIndexOf(1));
    }
}
