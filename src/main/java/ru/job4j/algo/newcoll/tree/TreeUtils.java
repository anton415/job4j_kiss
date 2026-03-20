package ru.job4j.algo.newcoll.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException("Root cannot be null");
        }
        int count = 0;
        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            count++;
            for (Node<T> child : current.getChildren()) {
                queue.offer(child);
            }
        }
        return count;
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
       if (root == null) {
           throw new IllegalArgumentException("Root cannot be null");
       }
       List<T> result = new ArrayList<>();
       Queue<Node<T>> queue = new ArrayDeque<>();
       queue.offer(root);
       while (!queue.isEmpty()) {
           Node<T> current = queue.poll();
           result.add(current.getValue());
           for (Node<T> child : current.getChildren()) {
               queue.offer(child);
           }
       }
       return result;
    }

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent 
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     * @param root корень дерева
     * @param parent ключ узла-родителя
     * @param child ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException если root является null
     */
    public boolean add(Node<T> root, T parent, T child) {
        if (root == null) {
            throw new IllegalArgumentException("Root cannot be null");
        }
        if (findByKey(root, child).isPresent()) {
            return false;
        }
        Optional<Node<T>> parentNode = findByKey(root, parent);
        if (parentNode.isEmpty()) {
            return false;
        }
        parentNode.get().getChildren().add(new Node<>(child));
        return true;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> findByKey(Node<T> root, T key) {        
        if (root == null) {
            throw new IllegalArgumentException("Root cannot be null");
        }
        Deque<Node<T>> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> current = stack.pop();
            if (Objects.equals(current.getValue(), key)) {
                return Optional.of(current);
            }
            List<Node<T>> children = current.getChildren();
            for (int index = children.size() - 1; index >= 0; index--) {
                stack.push(children.get(index));
            }
        }
        return Optional.empty();
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key, 
     * при этом из дерева root удаляется все поддерево найденного узла
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("Root cannot be null");
        }
        if (Objects.equals(root.getValue(), key)) {
            return Optional.of(root);
        }
        Deque<Node<T>> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> current = stack.pop();
            List<Node<T>> children = current.getChildren();
            for (int index = 0; index < children.size(); index++) {
                Node<T> child = children.get(index);
                if (Objects.equals(child.getValue(), key)) {
                    children.remove(index);
                    return Optional.of(child);
                }
            }
            for (int index = children.size() - 1; index >= 0; index--) {
                stack.push(children.get(index));
            }
        }
        return Optional.empty();
    }
}
