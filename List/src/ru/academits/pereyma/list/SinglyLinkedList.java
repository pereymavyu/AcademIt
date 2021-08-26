package ru.academits.pereyma.list;

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

        --count;
        return temp.getData();
    }

    public void insertFirstItem(ListItem<T> listItem) {
        listItem.setNext(head);
        head = listItem;

        ++count;
    }

    public void insertItem(int index, ListItem<T> listItem) {
        ListItem<T> temp = head;
        ListItem<T> prev = null;

        for (int i = 0; i < index; ++i) {
            prev = temp;
            temp = temp.getNext();
        }

        prev.setNext(listItem);
        listItem.setNext(temp);

        ++count;
    }

    public boolean removeItem(T data) {
        ListItem<T> temp = head;

        for (int i = 0; i < count; ++i) {
            if (temp.getData().equals(data)) {
                removeItem(i);

                return true;
            }

            temp = temp.getNext();
        }

        return false;
    }

    public T removeFirstItem() {
        T data = head.getData();
        head = head.getNext();

        --count;

        return data;
    }

    public void invert() {
        ListItem<T> prev = head;
        ListItem<T> temp = head.getNext();

        prev.setNext(null);

        ListItem<T> next = temp.getNext();

        for (int i = 0; i < count; ++i) {
            temp.setNext(prev);
            prev = temp;
            temp = next;
            next = temp.getNext();
            }
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> result = new SinglyLinkedList<T>();


        return result;
    }

    @Override
    public String toString() {
        return "SinglyLinkedList{" +
                "head=" + head +
                ", count=" + count +
                '}';
    }
}
