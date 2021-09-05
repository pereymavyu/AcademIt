package ru.academits.pereyma.list.main;

import ru.academits.pereyma.list.SinglyLinkedList;

public class ListMain {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.insertFirst(5);
        list.insertFirst(3);
        list.insertFirst(2);
        list.insertFirst(1);
        list.insert(3, 4);

        System.out.println(list);

        System.out.println(list.getSize()); // 5
        System.out.println(list.get(4)); // 5
        System.out.println(list.set(4, 6)); // 5
        System.out.println(list.get(4)); // 6
        System.out.println(list.getFirst()); // 1

        System.out.println("===");

        System.out.println(list.remove(4)); //6
        System.out.println(list);

        list.insertFirst(0);
        System.out.println(list);

        list.insert(5, 5);
        System.out.println(list);

        System.out.println("===");

        System.out.println(list.remove(Integer.valueOf(7)));
        System.out.println(list.remove(Integer.valueOf(4)));
        System.out.println(list);

        System.out.println(list.removeFirst());
        System.out.println(list);

        System.out.println("===");

        System.out.println(list);
        list.invert();
        System.out.println(list);
        list.invert();
        System.out.println(list);

        System.out.println("===");


        System.out.println(list);

        System.out.println(list.copy());
    }
}