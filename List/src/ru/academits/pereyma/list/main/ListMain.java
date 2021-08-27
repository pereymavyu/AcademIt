package ru.academits.pereyma.list.main;

import ru.academits.pereyma.list.ListItem;
import ru.academits.pereyma.list.SinglyLinkedList;

public class ListMain {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.insertFirstItem(new ListItem<>(5));
        list.insertFirstItem(new ListItem<>(3));
        list.insertFirstItem(new ListItem<>(2));
        list.insertFirstItem(new ListItem<>(1));
        list.insertItem(3, new ListItem<>(4));

        System.out.println(list);

        System.out.println(list.getSize()); // 5
        System.out.println(list.getItemData(4)); // 5
        System.out.println(list.setItemData(4, 6)); // 5
        System.out.println(list.getItemData(4)); // 6
        System.out.println(list.getFirst()); // 1

        System.out.println("===");

        System.out.println(list.removeItem(4)); //6
        System.out.println(list);

        list.insertFirstItem(new ListItem<>(0));
        System.out.println(list);

        list.insertItem(4, new ListItem<>(4));
        System.out.println(list);

        System.out.println("===");

        System.out.println(list.removeItem(Integer.valueOf(7)));
        System.out.println(list.removeItem(Integer.valueOf(4)));
        System.out.println(list);

        System.out.println(list.removeFirstItem());
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