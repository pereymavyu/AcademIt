package ru.academits.pereyma.myarraylist;

import java.util.*;
import java.util.function.UnaryOperator;

public class MyArrayList<E> implements List<E> {
    private Object[] items;
    private int length;

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object e : items) {
            if (e.equals(o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, length);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < length) {
            return (T[]) Arrays.copyOf(items, length);
        }

        for (int i = 0; i < length; ++i) {
            a[i] = (T) items[i];

            if (a.length > length) {
                a[++i] = null;
            }
        }

        return a;
    }

    public void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    @Override
    public boolean add(E e) {
        if (length >= items.length) {
            increaseCapacity();
        }

        items[++length] = e;

        ++length;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < length; ++i) {
            if (items[i].equals(o)) {
                System.arraycopy(items, i + 1, items, i, length - i - 1);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        while (items.length <= length + c.size()) {
            increaseCapacity();
        }

        System.arraycopy(c.toArray(), 0, items, length, c.size());

        length +=c.size();

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {

    }

    @Override
    public void sort(Comparator<? super E> c) {
        Arrays.sort((E[])items, c);
    }

    @Override
    public void clear() {
        length = 0;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }
}
