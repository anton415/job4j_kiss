package ru.job4j.algo.binarytree;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;

    public boolean put(T key) {
        boolean result;
        if (Objects.isNull(root)) {
            root = new Node(key);
            result = true;
        } else {
            result = put(root, key);
        }
        return result;
    }

    private boolean put(Node node, T key) {
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            // Ключ меньше текущего: идем в левое поддерево.
            if (Objects.isNull(node.left)) {
                node.left = new Node(key);
                return true;
            }
            return put(node.left, key);
        }
        if (compare > 0) {
            // Ключ больше текущего: идем в правое поддерево.
            if (Objects.isNull(node.right)) {
                node.right = new Node(key);
                return true;
            }
            return put(node.right, key);
        }
        // Дубликаты в дерево не добавляем.
        return false;
    }

    public boolean contains(T key) {
        // Ищем узел от корня и проверяем, что результат не null.
        return Objects.nonNull(find(root, key));
    }

    private Node find(Node node, T key) {
        if (Objects.isNull(node)) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            return find(node.left, key);
        }
        if (compare > 0) {
            return find(node.right, key);
        }
        return node;
    }

    public boolean remove(T key) {
        // Метод будет реализован в следующих уроках.
        return false;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inSymmetricalOrder(node, result);
    }

    private List<T> inSymmetricalOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, list);
            list.add(localRoot.key);
            inSymmetricalOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPreOrder() {
        List<T> result = new ArrayList<>();
        return inPreOrder(root, result);
    }

    private List<T> inPreOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            // Прямой обход: корень -> левое поддерево -> правое поддерево.
            list.add(localRoot.key);
            inPreOrder(localRoot.left, list);
            inPreOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPostOrder() {
        List<T> result = new ArrayList<>();
        return inPostOrder(root, result);
    }

    private List<T> inPostOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            // Обратный обход: левое поддерево -> правое поддерево -> корень.
            inPostOrder(localRoot.left, list);
            inPostOrder(localRoot.right, list);
            list.add(localRoot.key);
        }
        return list;
    }

    public T minimum() {
        return Objects.nonNull(root) ? minimum(root).key : null;
    }

    private Node minimum(Node node) {
        Node local = node;
        // Минимум находится в самом левом узле.
        while (Objects.nonNull(local.left)) {
            local = local.left;
        }
        return local;
    }

    public T maximum() {
        return Objects.nonNull(root) ? maximum(root).key : null;
    }

    private Node maximum(Node node) {
        Node local = node;
        // Максимум находится в самом правом узле.
        while (Objects.nonNull(local.right)) {
            local = local.right;
        }
        return local;
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private T key;
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
            return key.toString();
        }
    }
}
