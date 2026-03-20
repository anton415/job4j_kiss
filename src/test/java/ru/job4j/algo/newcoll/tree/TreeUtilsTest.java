package ru.job4j.algo.newcoll.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TreeUtilsTest {

    private TreeUtils<Integer> treeUtils;
    private Node<Integer> tree = new Node<>(1,
            new Node<>(2,
                    new Node<>(4,
                            new Node<>(8, new Node<>(16), new Node<>(17)),
                            new Node<>(9, new Node<>(18), new Node<>(19))),
                    new Node<>(5,
                            new Node<>(10, new Node<>(20), new Node<>(21)),
                            new Node<>(11, new Node<>(22), new Node<>(23))
                    )
            ),
            new Node<>(3,
                    new Node<>(6,
                            new Node<>(12, new Node<>(24), new Node<>(25)),
                            new Node<>(13, new Node<>(26), new Node<>(27))),
                    new Node<>(7,
                            new Node<>(14, new Node<>(28), new Node<>(29)),
                            new Node<>(15, new Node<>(30), new Node<>(31)))
            )
    );

    @BeforeEach
    void init() {
        treeUtils = new TreeUtils<>();
    }

    @Test
    void checkCount() {
        assertEquals(31, treeUtils.countNode(tree));
        assertEquals(2, treeUtils.countNode(new Node<>(10, new Node<>(20))));
        assertThrows(IllegalArgumentException.class, () -> treeUtils.countNode(null));
    }

    @Test
    void checkFindAll() {
        List<Integer> actual = new ArrayList<>();
        treeUtils.findAll(tree).forEach(actual::add);
        actual.sort(Integer::compareTo);
        assertEquals(List.of(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31), actual);

        List<Integer> single = new ArrayList<>();
        treeUtils.findAll(new Node<>(10)).forEach(single::add);
        assertEquals(List.of(10), single);

        assertThrows(IllegalArgumentException.class, () -> treeUtils.findAll(null));
    }
}
