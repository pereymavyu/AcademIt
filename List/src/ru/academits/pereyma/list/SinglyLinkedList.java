package ru.academits.pereyma.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public int getSize() {
        return size;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }

        return head.getData();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of list index range [0, " + (size - 1) + "]");
        }
    }

    private ListItem<T> getItem(int index) {
        checkIndex(index);

        ListItem<T> currentItem = head;

        for (int i = 0; i < index; ++i) {
            currentItem = currentItem.getNext();
        }

        return currentItem;
    }

    public T get(int index) {
        checkIndex(index);

        return getItem(index).getData();
    }

    public T set(int index, T data) {
        checkIndex(index);

        ListItem<T> currentItem = getItem(index);

        T oldData = currentItem.getData();
        currentItem.setData(data);

        return oldData;
    }

    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }

        T removedData = head.getData();
        head = head.getNext();

        --size;
        return removedData;
    }

    public T remove(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> previousItem = getItem(index - 1);
        T removedData = previousItem.getNext().getData();
        previousItem.setNext(previousItem.getNext().getNext());

        --size;
        return removedData;
    }

    public void insertFirst(T data) {
        head = new ListItem<>(data, head);

        ++size;
    }

    public void insert(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of list index range [0, " + size + "]");
        }

        if (index == 0) {
            insertFirst(data);

            return;
        }

        ListItem<T> previousItem = getItem(index - 1);
        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));

        ++size;
    }

    public boolean remove(T data) {
        for (ListItem<T> currentItem = head, previousItem = null; currentItem != null; previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (Objects.equals(data, currentItem.getData())) {
                if (previousItem != null) {
                    previousItem.setNext(currentItem.getNext());
                } else {
                    head = currentItem.getNext();
                }

                --size;
                return true;
            }
        }

        return false;
    }

    public void invert() {
        if (size <= 1) {
            return;
        }

        ListItem<T> previousItem = null;
        ListItem<T> currentItem = head;
        ListItem<T> nextItem = head.getNext();

        while (nextItem != null) {
            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = nextItem;
            nextItem = currentItem.getNext();
        }

        currentItem.setNext(previousItem);
        head = currentItem;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> resultList = new SinglyLinkedList<>();

        if (size == 0) {
            return resultList;
        }

        resultList.head = new ListItem<>(head.getData());
        resultList.size = size;

        for (ListItem<T> sourceItem = head.getNext(), resultItem = resultList.head; sourceItem != null; sourceItem = sourceItem.getNext()) {
            resultItem.setNext(new ListItem<>(sourceItem.getData()));
            resultItem = resultItem.getNext();
        }

        return resultList;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("[");

        ListItem<T> currentItem = head;

        for (int i = 0; i < size - 1; ++i) {
            sb.append(currentItem.getData()).append(", ");
            currentItem = currentItem.getNext();
        }

        sb.append(currentItem.getData());

        return sb.append("]").toString();
    }
}
