package com.shipmonk;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.shipmonk.SortedLinkedList.Sort.ASCENDING;
import static com.shipmonk.SortedLinkedList.Sort.DESCENDING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SortedLinkedListTest {

    @ParameterizedTest(name = "Inserting Integers into SortedLinkedList with sort {0} should return result {2}")
    @MethodSource("integerAddTest")
    void integerAddTest(SortedLinkedList.Sort sort, List<Integer> elements, String expected) {
        Collections.shuffle(elements);
        SortedLinkedList<Integer> list = new SortedLinkedList<>(sort);
        elements.forEach(list::add);

        assertEquals(expected, list.toString());
    }

    static Stream<Arguments> integerAddTest() {
        return Stream.of(
                Arguments.of(ASCENDING, new ArrayList<>(Arrays.asList(10, 5, 15, 7)), "[5, 7, 10, 15]"),
                Arguments.of(DESCENDING, new ArrayList<>(Arrays.asList(10, 5, 15, 7)), "[15, 10, 7, 5]"),
                Arguments.of(ASCENDING, new ArrayList<>(Arrays.asList(5, 10, 5, 10)), "[5, 5, 10, 10]"),
                Arguments.of(DESCENDING, new ArrayList<>(Arrays.asList(5, 10, 5, 10)), "[10, 10, 5, 5]"),
                Arguments.of(ASCENDING, new ArrayList<>(Arrays.asList(-10, -5, -15, -7)), "[-15, -10, -7, -5]"),
                Arguments.of(DESCENDING, new ArrayList<>(Arrays.asList(-10, -5, -15, -7)), "[-5, -7, -10, -15]"),
                Arguments.of(ASCENDING, new ArrayList<>(Arrays.asList(10, -10, 5, 0)), "[-10, 0, 5, 10]"),
                Arguments.of(DESCENDING, new ArrayList<>(Arrays.asList(10, -10, 5, 0)), "[10, 5, 0, -10]")
        );
    }

    @ParameterizedTest(name = "Inserting Strings into SortedLinkedList with sort {0} should return result {2}")
    @MethodSource("stringAddTest")
    void stringAddTest(SortedLinkedList.Sort sort, List<String> elements, String expected) {
        Collections.shuffle(elements);
        SortedLinkedList<String> list = new SortedLinkedList<>(sort);
        elements.forEach(list::add);

        assertEquals(expected, list.toString());
    }

    static Stream<Arguments> stringAddTest() {
        return Stream.of(
                Arguments.of(ASCENDING, new ArrayList<>(Arrays.asList("banana", "apple", "cherry", "apple")), "[apple, apple, banana, cherry]"),
                Arguments.of(DESCENDING, new ArrayList<>(Arrays.asList("banana", "apple", "cherry", "apple")), "[cherry, banana, apple, apple]"),
                Arguments.of(ASCENDING, new ArrayList<>(Arrays.asList("banana", "apple", "", " ")), "[,  , apple, banana]")
        );
    }

    @Test
    void addNullString() {
        SortedLinkedList<String> list = new SortedLinkedList<>(ASCENDING);
        list.add("apple");
        list.add(null);
        list.add("banana");

        assertEquals("[apple, banana]", list.toString());
    }

    @Test
    void addFirst() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>(ASCENDING);
        list.add(10);
        list.add(5);
        list.add(0);
        assertEquals("[0, 5, 10]", list.toString());

        list.addFirst(15);
        list.addFirst(7);
        assertEquals("[0, 5, 7, 10, 15]", list.toString());
    }

    @Test
    void addLast() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>(ASCENDING);
        list.add(10);
        list.add(5);
        list.add(0);
        assertEquals("[0, 5, 10]", list.toString());

        list.addLast(7);
        list.addLast(1);
        assertEquals("[0, 1, 5, 7, 10]", list.toString());
    }

    @Test
    void addOnIndex() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>(ASCENDING);
        list.add(10);
        list.add(5);
        list.add(0);
        assertEquals("[0, 5, 10]", list.toString());

        list.add(1, 7);
        list.add(1, 15);
        assertEquals("[0, 5, 7, 10, 15]", list.toString());
    }

    @Test
    void addAll() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>(ASCENDING);
        list.add(10);
        list.add(5);
        list.add(0);
        assertEquals("[0, 5, 10]", list.toString());

        list.addAll(List.of(15, 7, 1));
        assertEquals("[0, 1, 5, 7, 10, 15]", list.toString());
    }

    @Test
    void addAllOnIndex() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>(ASCENDING);
        list.add(10);
        list.add(5);
        list.add(0);
        assertEquals("[0, 5, 10]", list.toString());

        list.addAll(1, List.of(15, 7, 1));
        assertEquals("[0, 1, 5, 7, 10, 15]", list.toString());
    }

    @Test
    void mixStringAndIntThrowsException() {
        SortedLinkedList list = new SortedLinkedList<>(ASCENDING);
        list.add("apple");
        assertThrows(ClassCastException.class, () -> list.add(1));
    }

    @Test
    void mixIntAndStringThrowsException() {
        SortedLinkedList list = new SortedLinkedList<>(ASCENDING);
        list.add(1);
        assertThrows(ClassCastException.class, () -> list.add("apple"));
    }
}
