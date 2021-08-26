package ru.academits.pereyma.mylist;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public int getCount() {
        return count;
    }

    public T getFirst() {
        return head.getData();
    }

    public T getItemData(int index) {
        ListItem<T> temp = head;

        for (int i = 0; i < index; ++i) {
            temp = temp.getNext();
        }

        return temp.getData();
    }

    public T setItemData(int index, T data) {
        ListItem<T> temp = head;

        for (int i = 0; i < index; ++i) {
            temp = temp.getNext();

        }

        T tempData = temp.getData();
        temp.setData(data);

        return tempData;
    }

    public T removeItem(int index) {
        ListItem<T> temp = head;
        ListItem<T> prev = null;

        for (int i = 0; i < index; ++i) {
            prev = temp;
            temp = temp.getNext();
        }

        prev.setNext(temp.getNext());

        return temp.getData();
    }

    public void insertFirstItem(ListItem <T> listItem) {
        listItem.setNext(head);
        head = listItem;
    }

    public void insertItem(int index, ListItem <T> listItem) {
        ListItem<T> temp = head;
        ListItem<T> prev = null;

        for (int i = 0; i < index; ++i) {
            prev = temp;
            temp = temp.getNext();
        }

        prev.setNext(listItem);
        listItem.setNext(temp);
    }

    public boolean removeItem(T data) {
        return false;
    }

    public SinglyLinkedList<T> copy() {

    }



}
