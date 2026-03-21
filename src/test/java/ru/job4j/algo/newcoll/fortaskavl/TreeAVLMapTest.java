package ru.job4j.algo.newcoll.fortaskavl;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TreeAVLMapTest {

    @Test
    void whenEmptyTree() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertTrue(tree.values().isEmpty());
        assertTrue(tree.keySet().isEmpty());
    }

    @Test
    void whenAddOneElemThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertTrue(tree.put(1, "11"));
        assertEquals(List.of("11"), List.copyOf(tree.values()));
        assertEquals(List.of(1), List.copyOf(tree.keySet()));
    }

    @Test
    void whenAddSevenElemThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertTrue(tree.put(1, "11"));
        assertTrue(tree.put(2, "22"));
        assertTrue(tree.put(3, "33"));
        assertTrue(tree.put(4, "44"));
        assertTrue(tree.put(5, "55"));
        assertTrue(tree.put(6, "66"));
        assertTrue(tree.put(7, "77"));
        assertEquals(List.of("11", "22", "33", "44", "55", "66", "77"), List.copyOf(tree.values()));
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), List.copyOf(tree.keySet()));
    }

    @Test
    void whenUpdateValueThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(1, "11");
        tree.put(2, "22");
        tree.put(3, "33");
        tree.put(4, "44");
        tree.put(5, "55");
        tree.put(6, "66");
        tree.put(7, "77");
        assertTrue(tree.put(5, "555"));
        assertEquals(List.of("11", "22", "33", "44", "555", "66", "77"), List.copyOf(tree.values()));
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), List.copyOf(tree.keySet()));
    }

    @Test
    void whenDeleteKeyThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(1, "11");
        tree.put(2, "22");
        tree.put(3, "33");
        tree.put(4, "44");
        tree.put(5, "55");
        tree.put(6, "66");
        tree.put(7, "77");
        assertTrue(tree.remove(5));
        assertFalse(tree.remove(0));
        assertEquals(List.of("11", "22", "33", "44", "66", "77"), List.copyOf(tree.values()));
        assertEquals(List.of(1, 2, 3, 4, 6, 7), List.copyOf(tree.keySet()));
    }

    @Test
    void whenGetKeyThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.put(1, "11");
        tree.put(2, "22");
        tree.put(3, "33");
        tree.put(4, "44");
        tree.put(5, "55");
        tree.put(6, "66");
        tree.put(7, "77");
        assertEquals("55", tree.get(5));
        assertNull(tree.get(0));
    }

    @Test
    void whenUseInsertAliasThenMapUpdated() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertTrue(tree.insert(10, "100"));
        assertEquals("100", tree.get(10));
    }

    @Test
    void whenUseNullKeyThenNoChanges() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertFalse(tree.put(null, "x"));
        assertFalse(tree.insert(null, "x"));
        assertFalse(tree.remove(null));
        assertNull(tree.get(null));
        assertTrue(tree.keySet().isEmpty());
        assertTrue(tree.values().isEmpty());
    }
}
