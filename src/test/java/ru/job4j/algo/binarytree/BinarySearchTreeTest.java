package ru.job4j.algo.binarytree;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

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

    @Test
    void whenRemoveLeafThenTreeStaysSearchTree() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.put(element);
        }
        assertTrue(tree.remove(1));
        assertFalse(tree.contains(1));
        assertEquals(List.of(2, 3, 4, 5, 6, 7), tree.inSymmetricalOrder());
    }

    @Test
    void whenRemoveNodeWithOneLeftChildThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 5, 7}) {
            tree.put(element);
        }
        assertTrue(tree.remove(2));
        assertFalse(tree.contains(2));
        assertEquals(List.of(1, 4, 5, 6, 7), tree.inSymmetricalOrder());
    }

    @Test
    void whenRemoveNodeWithOneRightChildThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7}) {
            tree.put(element);
        }
        assertTrue(tree.remove(2));
        assertFalse(tree.contains(2));
        assertEquals(List.of(3, 4, 5, 6, 7), tree.inSymmetricalOrder());
    }

    @Test
    void whenRemoveNodeWithTwoChildrenThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{2, 1, 10, 6, 14, 4, 8, 12, 16, 11, 9, 13, 15, 17, 3, 5, 7}) {
            tree.put(element);
        }
        assertTrue(tree.remove(10));
        assertFalse(tree.contains(10));
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17), tree.inSymmetricalOrder());
    }

    @Test
    void whenRemoveRootThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.put(element);
        }
        assertTrue(tree.remove(4));
        assertFalse(tree.contains(4));
        assertEquals(List.of(1, 2, 3, 5, 6, 7), tree.inSymmetricalOrder());
        assertEquals(1, tree.minimum());
        assertEquals(7, tree.maximum());
    }

    @Test
    void whenRemoveMissingThenFalse() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.put(element);
        }
        assertFalse(tree.remove(8));
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), tree.inSymmetricalOrder());
    }

    @Test
    void whenRemoveNullOrFromEmptyThenFalse() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assertFalse(tree.remove(null));
        assertFalse(tree.remove(1));
    }

    @Test
    void whenNodeRemovedThenReferencesAreCleared() throws ReflectiveOperationException {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.put(element);
        }
        Object root = getField(tree, "root");
        Object removedNode = getField(root, "left");
        assertTrue(tree.remove(2));
        assertNull(getField(removedNode, "key"));
        assertNull(getField(removedNode, "left"));
        assertNull(getField(removedNode, "right"));
    }

    @Test
    void whenClearThenTreeBecomesEmpty() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.put(element);
        }
        tree.clear();
        assertEquals(List.of(), tree.inSymmetricalOrder());
        assertNull(tree.minimum());
        assertNull(tree.maximum());
        assertFalse(tree.contains(4));
    }

    @Test
    void whenClearThenReferencesAreCleared() throws ReflectiveOperationException {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.put(element);
        }
        var root = getField(tree, "root");
        var left = getField(root, "left");
        var right = getField(root, "right");
        tree.clear();
        assertNull(getField(tree, "root"));
        assertNull(getField(root, "key"));
        assertNull(getField(root, "left"));
        assertNull(getField(root, "right"));
        assertNull(getField(left, "key"));
        assertNull(getField(left, "left"));
        assertNull(getField(left, "right"));
        assertNull(getField(right, "key"));
        assertNull(getField(right, "left"));
        assertNull(getField(right, "right"));
    }

    private Object getField(Object target, String name) throws ReflectiveOperationException {
        Field field = target.getClass().getDeclaredField(name);
        field.setAccessible(true);
        return field.get(target);
    }
}
