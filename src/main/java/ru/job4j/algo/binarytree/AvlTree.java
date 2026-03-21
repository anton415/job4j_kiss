package ru.job4j.algo.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AvlTree<T extends Comparable<T>> {

    public Node root;

    public boolean contains(T value) {
        // Null-ключ не может находиться в дереве.
        return Objects.nonNull(value) && contains(root, value);
    }

    private boolean contains(Node node, T value) {
        if (Objects.isNull(node)) {
            return false;
        }
        int compare = value.compareTo(node.key);
        if (compare < 0) {
            return contains(node.left, value);
        }
        if (compare > 0) {
            return contains(node.right, value);
        }
        return true;
    }

    public boolean insert(T value) {
        boolean result = false;
        if (Objects.nonNull(value) && !contains(root, value)) {
            root = insert(root, value);
            result = true;
        }
        return result;
    }

    private Node insert(Node node, T value) {
        if (Objects.isNull(node)) {
            return new Node(value);
        }
        int comparisonResult = value.compareTo(node.key);
        if (comparisonResult < 0) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }
        // После вставки пересчитываем высоту и балансируем поддерево.
        updateHeight(node);
        return balance(node);
    }

    public boolean remove(T element) {
        boolean result = false;
        if (Objects.nonNull(element) && Objects.nonNull(root) && contains(root, element)) {
            root = remove(root, element);
            result = true;
        }
        return result;
    }

    private Node remove(Node node, T element) {
        if (node == null) {
            return null;
        }
        int comparisonResult = element.compareTo(node.key);
        if (comparisonResult < 0) {
            node.left = remove(node.left, element);
        } else if (comparisonResult > 0) {
            node.right = remove(node.right, element);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                if (node.left.height > node.right.height) {
                    T heir = maximum(node.left).key;
                    node.key = heir;
                    node.left = remove(node.left, heir);
                } else {
                    T heir = minimum(node.right).key;
                    node.key = heir;
                    node.right = remove(node.right, heir);
                }
            }
        }
        updateHeight(node);
        return balance(node);
    }

    private void updateHeight(Node node) {
        int leftNodeHeight = Objects.isNull(node.left) ? -1 : node.left.height;
        int rightNodeHeight = Objects.isNull(node.right) ? -1 : node.right.height;
        // Высота и фактор баланса считаются от дочерних поддеревьев.
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.balanceFactor = rightNodeHeight - leftNodeHeight;
    }

    private Node balance(Node node) {
        Node result = node;
        if (node.balanceFactor < -1) {
            // Левое поддерево "тяжелее": выбираем LL или LR случай.
            if (node.left.balanceFactor > 0) {
                result = leftRightCase(node);
            } else {
                result = rightRotation(node);
            }
        } else if (node.balanceFactor > 1) {
            // Правое поддерево "тяжелее": выбираем RR или RL случай.
            if (node.right.balanceFactor < 0) {
                result = rightLeftCase(node);
            } else {
                result = leftRotation(node);
            }
        }
        return result;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node leftRotation(Node node) {
        // Поднимаем правого потомка вверх и пересобираем связи.
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        // Пересчитываем параметры снизу вверх после ротации.
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node rightRotation(Node node) {
        // Поднимаем левого потомка вверх и пересобираем связи.
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        // Пересчитываем параметры снизу вверх после ротации.
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    public T minimum() {
        return Objects.nonNull(root) ? minimum(root).key : null;
    }

    private Node minimum(Node node) {
        Node result = node;
        // Минимальный ключ всегда в самом левом узле.
        while (Objects.nonNull(result.left)) {
            result = result.left;
        }
        return result;
    }

    public T maximum() {
        return Objects.nonNull(root) ? maximum(root).key : null;
    }

    private Node maximum(Node node) {
        Node result = node;
        // Максимальный ключ всегда в самом правом узле.
        while (Objects.nonNull(result.right)) {
            result = result.right;
        }
        return result;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        inSymmetricalOrder(root, result);
        return result;
    }

    private void inSymmetricalOrder(Node node, List<T> result) {
        if (Objects.nonNull(node)) {
            inSymmetricalOrder(node.left, result);
            result.add(node.key);
            inSymmetricalOrder(node.right, result);
        }
    }

    public List<T> inPreOrder() {
        List<T> result = new ArrayList<>();
        inPreOrder(root, result);
        return result;
    }

    private void inPreOrder(Node node, List<T> result) {
        if (Objects.nonNull(node)) {
            result.add(node.key);
            inPreOrder(node.left, result);
            inPreOrder(node.right, result);
        }
    }

    public List<T> inPostOrder() {
        List<T> result = new ArrayList<>();
        inPostOrder(root, result);
        return result;
    }

    private void inPostOrder(Node node, List<T> result) {
        if (Objects.nonNull(node)) {
            inPostOrder(node.left, result);
            inPostOrder(node.right, result);
            result.add(node.key);
        }
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private int balanceFactor;
        private T key;
        private int height;
        private Node left;
        private Node right;

        public Node(T key) {
            this.key = key;
        }

        @Override
        public VisualNode getLeft() {
            return left;
        }

        @Override
        public VisualNode getRight() {
            return right;
        }

        @Override
        public String getText() {
            return String.valueOf(key);
        }
    }
}
