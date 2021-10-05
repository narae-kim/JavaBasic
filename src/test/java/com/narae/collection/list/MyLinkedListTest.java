package com.narae.collection.list;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {
    MyLinkedList<String> linkedList;

    @BeforeEach
    void setUp() {
        linkedList = new MyLinkedList<String>();
    }

    @Test
    @DisplayName("Normal addition should work")
    void testAddAndGet() {
        linkedList.add("First");
        linkedList.add("Second");
        linkedList.add("Third");

        assertAll("add and get of the linked list",
                () -> assertEquals(Optional.of("First"), linkedList.get(0)),
                () -> assertEquals(Optional.of("Second"), linkedList.get(1)),
                () -> assertEquals(Optional.of("Third"), linkedList.get(2)));
    }

    @Test
    @DisplayName("Normal addition with an index should work")
    void testAddWithIndexAndGet() {
        linkedList.add("First");
        linkedList.add("Second");
        linkedList.add("Third");
        linkedList.add("NEW", 1);

        assertAll("add with index and get of the linked list",
                () -> assertEquals(Optional.of("First"), linkedList.get(0)),
                () -> assertEquals(Optional.of("NEW"), linkedList.get(1)),
                () -> assertEquals(Optional.of("Second"), linkedList.get(2)),
                () -> assertEquals(Optional.of("Third"), linkedList.get(3)));
    }

    @Test
    @DisplayName("Optimised addition with an index should work")
    void testOptimisedAddWithIndexAndGet() {
        linkedList.optimisedAdd("O First", 0);
        linkedList.optimisedAdd("O Second", 1);
        linkedList.optimisedAdd("O Third", 2);
        linkedList.optimisedAdd("ZERO", 0);
        linkedList.optimisedAdd("NEW", 2);

        assertAll("optimised add with index and get of the linked list",
                () -> assertEquals(Optional.of("ZERO"), linkedList.get(0)),
                () -> assertEquals(Optional.of("O First"), linkedList.get(1)),
                () -> assertEquals(Optional.of("NEW"), linkedList.get(2)),
                () -> assertEquals(Optional.of("O Second"), linkedList.get(3)),
                () -> assertEquals(Optional.of("O Third"), linkedList.get(4)));
    }

    @Test
    @DisplayName("Optimised addition with an index over the size of index should work")
    void testOptimisedAddOverIndexAndGet() {
        linkedList.optimisedAdd("O First", 0);
        linkedList.optimisedAdd("O Second", 1);
        linkedList.optimisedAdd("O Third", 2);
        linkedList.optimisedAdd("NEW", 5);

        assertAll("optimised add with index over the size of index and get of the linked list",
                () -> assertEquals(Optional.of("O First"), linkedList.get(0)),
                () -> assertEquals(Optional.of("O Second"), linkedList.get(1)),
                () -> assertEquals(Optional.of("O Third"), linkedList.get(2)),
                () -> assertEquals(Optional.ofNullable(null), linkedList.get(3)),
                () -> assertEquals(Optional.ofNullable(null), linkedList.get(4)),
                () -> assertEquals(Optional.of("NEW"), linkedList.get(5)));
    }

    @Test
    @DisplayName("Normal addition and optimised getter should work")
    void testAddAndOptimisedGet() {
        linkedList.add("First", 0);
        linkedList.add("Second", 1);
        linkedList.add("Third", 2);
        linkedList.add("Fourth", 3);
        linkedList.add("Fifth", 4);

        assertAll("normal add and optimised get of the linked list",
                () -> assertEquals(Optional.of("First"), linkedList.optimisedGet(0)),
                () -> assertEquals(Optional.of("Second"), linkedList.optimisedGet(1)),
                () -> assertEquals(Optional.of("Third"), linkedList.optimisedGet(2)),
                () -> assertEquals(Optional.of("Fourth"), linkedList.optimisedGet(3)),
                () -> assertEquals(Optional.of("Fifth"), linkedList.optimisedGet(4)));
    }

    @Test
    @DisplayName("Normal addition and optimised removal should work")
    void testAddAndOptimisedRemove() {
        linkedList.add("First", 0);
        linkedList.add("Second", 1);
        linkedList.add("Third", 2);
        linkedList.add("Fourth", 3);
        linkedList.add("Fifth", 4);
        linkedList.optimisedRemove(0);
        linkedList.optimisedRemove(2);

        assertAll("normal add and optimised remove and normal get of the linked list",
                () -> assertEquals(Optional.of("Second"), linkedList.get(0)),
                () -> assertEquals(Optional.of("Third"), linkedList.get(1)),
                () -> assertEquals(Optional.of("Fifth"), linkedList.get(2)),
                () -> assertEquals(Optional.ofNullable(null), linkedList.get(3)),
                () -> assertEquals(Optional.ofNullable(null), linkedList.get(4)));
    }

    @Test
    @DisplayName("Get middle should return the middle element in an odd number of elements")
    void testGetMiddleOdd() {
        linkedList.add("First");
        linkedList.add("Second");
        linkedList.add("Third");
        assertEquals("Second", linkedList.getMiddel().get());
    }

    @Test
    @DisplayName("Get middle should return the right center element in an even number of elements")
    void testGetMiddleEven() {
        linkedList.add("First");
        linkedList.add("Second");
        linkedList.add("Third");
        linkedList.add("Fourth");
        assertEquals("Third", linkedList.getMiddel().get());
    }

    @AfterEach
    @DisplayName("Verify the linked list")
    void printAll() {
        System.out.println(linkedList.toString());
    }
}