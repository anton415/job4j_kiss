package ru.job4j.algo.newcoll.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
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
}
