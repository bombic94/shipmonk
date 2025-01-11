package com.shipmonk;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A sorted linked list implementation that maintains the order of elements
 * based on the specified sorting direction (ascending or descending).
 *
 * @param <T> the type of elements in this list, which must implement {@link Comparable}.
 */
public class SortedLinkedList<T extends Comparable<T>> extends LinkedList<T> {

    /**
     * Enumeration representing the sorting order.
     */
    public enum Sort {
        ASCENDING,
        DESCENDING
    }

    private final Sort sort;

    /**
     * Constructs a new {@code SortedLinkedList} with the specified sorting order.
     *
     * @param sort the sorting order to be used (either {@code Sort.ASCENDING} or {@code Sort.DESCENDING})
     */
    public SortedLinkedList(Sort sort) {
        this.sort = sort;
    }

    /**
     * Adds an element to the list while maintaining the sorted order.
     *
     * @param t the element to be added
     * @return {@code true} if the element was successfully added, {@code false} otherwise
     */
    @Override
    public boolean add(T t) {
        if (t == null) {
            return false;
        }
        if (this.size() == 0) {
            super.add(t);
            return true;
        }

        Iterator<T> iterator = this.iterator();
        int index = 0;

        while (iterator.hasNext()) {
            T current = iterator.next();
            if (shouldAddOnThisPosition(current, t)) {
                super.add(index, t);
                return true;
            }
            index++;
        }

        return super.add(t);
    }

    /**
     * Adds an element to the list at the specified position. This method redirects
     * to {@link #add(Object)} to maintain sorted order.
     *
     * @param index   the position at which to add the element (ignored for sorting)
     * @param element the element to be added
     */
    @Override
    public void add(int index, T element) {
        this.add(element);
    }

    /**
     * Adds an element to the start of the list. This method redirects to {@link #add(Object)}
     * to maintain sorted order.
     *
     * @param element the element to be added
     */
    @Override
    public void addFirst(T element) {
        this.add(element);
    }

    /**
     * Adds an element to the end of the list. This method redirects to {@link #add(Object)}
     * to maintain sorted order.
     *
     * @param element the element to be added
     */
    @Override
    public void addLast(T element) {
        this.add(element);
    }

    /**
     * Adds all elements from the specified collection to the list while maintaining
     * the sorted order.
     *
     * @param c the collection of elements to add
     * @return {@code true} if the elements were successfully added
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        c.forEach(this::add);
        return true;
    }

    /**
     * Adds all elements from the specified collection to the list at the given position.
     * This method redirects to {@link #addAll(Collection)} to maintain sorted order.
     *
     * @param index the position at which to add elements (ignored for sorting)
     * @param c     the collection of elements to add
     * @return {@code true} if the elements were successfully added
     */
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return addAll(c);
    }

    /**
     * Determines if the element should be added at the current position based on the sorting order.
     *
     * @param current the current element in the list
     * @param t       the element to be added
     * @return {@code true} if the element should be added at the current position, {@code false} otherwise
     */
    private boolean shouldAddOnThisPosition(T current, T t) {
        if (sort == Sort.DESCENDING) {
            return current.compareTo(t) <= 0;
        } else {
            return current.compareTo(t) >= 0;
        }
    }
}
