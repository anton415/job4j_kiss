package ru.job4j.algo.newcoll.fortaskavl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class TreeAVLMap<T extends Comparable<T>, V> {

    private Node root;

    public boolean put(T key, V value) {
        // Null-ключ в map не добавляем.
        if (Objects.isNull(key)) {
            return false;
        }
        root = insert(root, key, value);
        return true;
    }

    public boolean insert(T key, V value) {
        // Поддерживаем API задания через put().
        return put(key, value);
    }

    public boolean remove(T key) {
        // Удаление возможно только для непустого дерева и непустого ключа.
        if (Objects.isNull(key) || Objects.isNull(root) || Objects.isNull(find(root, key))) {
            return false;
        }
        root = remove(root, key);
        return true;
    }

    public V get(T key) {
        // Null-ключ не может быть найден в map.
        if (Objects.isNull(key)) {
            return null;
        }
        Node node = find(root, key);
        return Objects.nonNull(node) ? node.value : null;
    }

    public Set<T> keySet() {
        Set<T> keys = new LinkedHashSet<>();
        // Симметричный обход гарантирует сортировку ключей.
        fillKeysInOrder(root, keys);
        return keys;
    }

    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        // Симметричный обход собирает значения в порядке возрастания ключей.
        fillValuesInOrder(root, values);
        return values;
    }

    private Node insert(Node node, T key, V value) {
        if (Objects.isNull(node)) {
            return new Node(key, value);
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            // Ключ меньше текущего: вставляем в левое поддерево.
            node.left = insert(node.left, key, value);
        } else if (compare > 0) {
            // Ключ больше текущего: вставляем в правое поддерево.
            node.right = insert(node.right, key, value);
        } else {
            // Для существующего ключа просто обновляем значение.
            node.value = value;
            return node;
        }
        updateHeight(node);
        return balance(node);
    }

    private Node remove(Node node, T key) {
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = remove(node.left, key);
        } else if (compare > 0) {
            node.right = remove(node.right, key);
        } else {
            // Если у узла один потомок или ни одного, возвращаем этот потомок.
            if (Objects.isNull(node.left)) {
                return node.right;
            }
            if (Objects.isNull(node.right)) {
                return node.left;
            }
            // Узел с двумя потомками заменяем следующим по порядку ключом.
            Node heir = minimum(node.right);
            node.key = heir.key;
            node.value = heir.value;
            node.right = remove(node.right, heir.key);
        }
        updateHeight(node);
        return balance(node);
    }

    private Node minimum(Node node) {
        Node current = node;
        // Минимальный ключ находится в самом левом узле поддерева.
        while (Objects.nonNull(current.left)) {
            current = current.left;
        }
        return current;
    }

    private Node find(Node node, T key) {
        Node current = node;
        while (Objects.nonNull(current)) {
            int compare = key.compareTo(current.key);
            if (compare < 0) {
                current = current.left;
            } else if (compare > 0) {
                current = current.right;
            } else {
                return current;
            }
        }
        return null;
    }

    private void fillKeysInOrder(Node node, Set<T> keys) {
        if (Objects.isNull(node)) {
            return;
        }
        fillKeysInOrder(node.left, keys);
        keys.add(node.key);
        fillKeysInOrder(node.right, keys);
    }

    private void fillValuesInOrder(Node node, List<V> values) {
        if (Objects.isNull(node)) {
            return;
        }
        fillValuesInOrder(node.left, values);
        values.add(node.value);
        fillValuesInOrder(node.right, values);
    }

    private Node balance(Node node) {
        if (node.balanceFactor < -1) {
            // Левое поддерево тяжелее: выбираем LL или LR-ротацию.
            if (node.left.balanceFactor > 0) {
                node.left = leftRotation(node.left);
            }
            return rightRotation(node);
        }
        if (node.balanceFactor > 1) {
            // Правое поддерево тяжелее: выбираем RR или RL-ротацию.
            if (node.right.balanceFactor < 0) {
                node.right = rightRotation(node.right);
            }
            return leftRotation(node);
        }
        return node;
    }

    private Node leftRotation(Node node) {
        // Поднимаем правого потомка и переносим его левое поддерево.
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node rightRotation(Node node) {
        // Поднимаем левого потомка и переносим его правое поддерево.
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private void updateHeight(Node node) {
        int leftHeight = Objects.isNull(node.left) ? -1 : node.left.height;
        int rightHeight = Objects.isNull(node.right) ? -1 : node.right.height;
        // Пересчитываем высоту и фактор баланса из высот дочерних узлов.
        node.height = Math.max(leftHeight, rightHeight) + 1;
        node.balanceFactor = rightHeight - leftHeight;
    }

    private class Node {
        private int balanceFactor;
        private T key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
