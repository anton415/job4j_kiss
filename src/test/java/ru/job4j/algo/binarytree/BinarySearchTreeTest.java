package ru.job4j.algo.binarytree;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    @Test
    void whenAddToEmptyTreeThenListContainsOneElement() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertTrue(tree.put("first"));
        assertEquals(List.of("first"), tree.inSymmetricalOrder());
    }

    @Test
    void whenAddTwoToEmptyTreeThenListContainsTwoElement() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertTrue(tree.put("first"));
        assertTrue(tree.put("second"));
        assertEquals(List.of("first", "second"), tree.inSymmetricalOrder());
    }

    @Test
    void whenAddElementThenContainElementOk() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.put("first");
        tree.put("second");
        tree.put("three");
        assertTrue(tree.contains("second"));
        assertFalse(tree.contains("four"));
    }

    @Test
    void whenAddMaximumNotEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 8, 7}) {
            tree.put(element);
        }
        assertEquals(8, tree.maximum());
    }

    @Test
    void whenAddMaximumIsEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.put(element);
        }
        assertEquals(7, tree.maximum());
    }

    @Test
    void whenAddMinimumIsEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertEquals(1, tree.minimum());
    }

    @Test
    void whenAddMinimumIsNotEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7 }) {
            tree.put(element);
        }
        assertEquals(2, tree.minimum());
    }

    @Test
    void whenSymmetricalOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), tree.inSymmetricalOrder());
    }

    @Test
    void whenPreOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertEquals(List.of(4, 2, 1, 3, 6, 5, 7), tree.inPreOrder());
    }

    @Test
    void whenPostOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertEquals(List.of(1, 3, 2, 5, 7, 6, 4), tree.inPostOrder());
    }
}
