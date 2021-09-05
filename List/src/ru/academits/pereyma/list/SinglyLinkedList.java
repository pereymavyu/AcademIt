package ru.academits.pereyma.list;

import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public int getSize() {
        return size;
    }

    public T getFirst() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }

        return head.getData();
    }

    private void checkIndex(int index) {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of list index range [" + 0 + ", " + (size - 1) + "]");
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
            throw new IllegalStateException("List is empty");
        }

        T data = head.getData();
        head = head.getNext();

        --size;
        return data;
    }

    public T remove(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> previousItem = getItem(index - 1);
        T data = previousItem.getNext().getData();
        previousItem.setNext(previousItem.getNext().getNext());

        --size;
        return data;
    }

    public void insertFirst(T data) {
        head = new ListItem<>(data, head);

        ++size;
    }

    public void insert(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of list index range [" + 0 + ", " + (size - 1) + "]");
        }

        if (index == 0) {
            insertFirst(data);

            return;
        }

        ListItem<T> currentItem = getItem(index - 1);
        currentItem.setNext(new ListItem<>(data, currentItem.getNext()));

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
        if (head == null) {
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

        if (size > 0) {
            resultList.head = new ListItem<>(head.getData());
            resultList.size = 1;

            for (ListItem<T> sourceItem = head.getNext(), resultItem = resultList.head; sourceItem != null; sourceItem = sourceItem.getNext()) {
                resultItem.setNext(new ListItem<>(sourceItem.getData()));
                resultItem = resultItem.getNext();

                ++resultList.size;
            }
        }

        return resultList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[");

        if (head != null) {
            ListItem<T> currentItem = head;

            for (int i = 0; i < size - 1; ++i) {
                sb.append(currentItem.getData()).append(", ");
                currentItem = currentItem.getNext();
            }

            sb.append(currentItem.getData());
        }

        return sb.append("]").toString();
    }
}
