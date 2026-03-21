package ru.job4j.algo.binarytree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AvlTreeTest {

    @Test
    @DisplayName("Симметричный обход возвращает отсортированные значения")
    void whenInsertSequentialThenSymmetricalOrderIsSorted() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inSymmetricalOrder();
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), list);
    }

    @Test
    @DisplayName("Последовательная вставка балансирует дерево")
    void whenInsertSequentialThenPreOrderMatchesBalancedTree() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        assertEquals(List.of(4, 2, 1, 3, 6, 5, 7), tree.inPreOrder());
        assertEquals(List.of(1, 3, 2, 5, 7, 6, 4), tree.inPostOrder());
    }

    @Test
    @DisplayName("LR-ротация выполняется при вставке")
    void whenInsertLrCaseThenTreeIsBalanced() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(3);
        tree.insert(1);
        tree.insert(2);
        assertEquals(List.of(2, 1, 3), tree.inPreOrder());
    }

    @Test
    @DisplayName("RL-ротация выполняется при вставке")
    void whenInsertRlCaseThenTreeIsBalanced() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(1);
        tree.insert(3);
        tree.insert(2);
        assertEquals(List.of(2, 1, 3), tree.inPreOrder());
    }

    @Test
    @DisplayName("minimum и maximum возвращают крайние значения")
    void whenTreeIsNotEmptyThenMinimumAndMaximumAreCorrect() {
        AvlTree<Integer> tree = new AvlTree<>();
        assertNull(tree.minimum());
        assertNull(tree.maximum());
        for (int element : List.of(4, 2, 6, 1, 3, 5, 7)) {
            tree.insert(element);
        }
        assertEquals(1, tree.minimum());
        assertEquals(7, tree.maximum());
    }

    @Test
    @DisplayName("contains корректно обрабатывает существующие и отсутствующие ключи")
    void whenCheckContainsThenResultMatchesTreeContent() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : List.of(4, 2, 6, 1, 3, 5, 7)) {
            tree.insert(element);
        }
        assertTrue(tree.contains(6));
        assertFalse(tree.contains(8));
        assertFalse(tree.contains(null));
    }

    @Test
    @DisplayName("remove удаляет элемент и сохраняет свойства дерева поиска")
    void whenRemoveExistingElementThenResultIsTrueAndOrderIsCorrect() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : List.of(4, 2, 6, 1, 3, 5, 7)) {
            tree.insert(element);
        }
        assertTrue(tree.remove(4));
        assertEquals(List.of(1, 2, 3, 5, 6, 7), tree.inSymmetricalOrder());
        assertFalse(tree.contains(4));
    }

    @Test
    @DisplayName("remove возвращает false для null и отсутствующего ключа")
    void whenRemoveNullOrMissingThenResultIsFalse() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : List.of(2, 1, 3)) {
            tree.insert(element);
        }
        assertFalse(tree.remove(null));
        assertFalse(tree.remove(42));
        assertEquals(List.of(1, 2, 3), tree.inSymmetricalOrder());
    }
}
