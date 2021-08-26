package ru.academits.pereyma.list.main;

import ru.academits.pereyma.list.ListItem;
import ru.academits.pereyma.list.SinglyLinkedList;

public class ListMain {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
        list.insertFirstItem(new ListItem<Integer>(3));
        list.insertFirstItem(new ListItem<Integer>(2));
        list.insertFirstItem(new ListItem<Integer>(1));

        System.out.println(list);

        list.invert();

        System.out.println(list);

    }
}
