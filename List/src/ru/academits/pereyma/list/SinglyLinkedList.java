package ru.academits.pereyma.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public int getSize() {
        return count;
    }

    public T getFirst() {
        return head.getData();
    }

    public T getItemData(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index is out of list range");
        }

        ListItem<T> temp = head;

        for (int i = 0; i < index; ++i) {
            temp = temp.getNext();
        }

        return temp.getData();
    }

    public T setItemData(int index, T data) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index is out of list range");
        }

        ListItem<T> temp = head;

        for (int i = 0; i < index; ++i) {
            temp = temp.getNext();
        }

        T initialData = temp.getData();
        temp.setData(data);

        return initialData;
    }

    public T removeItem(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index is out of list range");
        }

        ListItem<T> temp = head;

        if (index == 0) {
            head = head.getNext();
        } else {
            ListItem<T> prev = null;

            for (int i = 0; i < index; ++i) {
                prev = temp;
                temp = temp.getNext();
            }

            prev.setNext(temp.getNext());
        }

        --count;
        return temp.getData();
    }

    public void insertFirstItem(ListItem<T> listItem) {
        listItem.setNext(head);
        head = listItem;

        ++count;
    }

    public void insertItem(int index, ListItem<T> listItem) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index is out of list range");
        }

        ListItem<T> temp = head;

        if (index == 0) {
            listItem.setNext(head);
            head = listItem;
        } else {
            ListItem<T> prev = null;

            for (int i = 0; i < index; ++i) {
                prev = temp;
                temp = temp.getNext();
            }

            prev.setNext(listItem);
            listItem.setNext(temp);
        }

        ++count;
    }

    public boolean removeItem(T data) {
        for (ListItem<T> temp = head, prev = null; temp != null; prev = temp, temp = temp.getNext()) {
            if (temp.getData().equals(data)) {
                if (prev != null) {
                    prev.setNext(temp.getNext());
                } else {
                    head = temp.getNext();
                }

                --count;
                return true;
            }
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
        ListItem<T> prev = null;
        ListItem<T> temp = head;
        ListItem<T> next = head.getNext();

        while (next != null) {
            temp.setNext(prev);
            prev = temp;
            temp = next;
            next = temp.getNext();
        }

        temp.setNext(prev);
        head = temp;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> resultList = new SinglyLinkedList<>();

        if (count > 0) {
            resultList.head = new ListItem<>(head.getData());
            resultList.count = 1;

            for (ListItem<T> sourceItem = head.getNext(), resultItem = resultList.head; sourceItem != null; sourceItem = sourceItem.getNext()) {
                resultItem.setNext(new ListItem<>(sourceItem.getData()));
                resultItem = resultItem.getNext();

                ++resultList.count;
            }
        }

        return resultList;
    }

    @Override
    public String toString() {
        return "SinglyLinkedList{" +
                "head=" + head +
                ", count=" + count +
                '}';
    }
}
