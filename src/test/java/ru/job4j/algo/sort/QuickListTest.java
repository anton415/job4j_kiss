package ru.job4j.algo.sort;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class QuickListTest {
    @Test
    @DisplayName("Сортирует список пользователей по возрастанию id")
    void whenSortThenOk() {
        User user1 = new User(1, "11");
        User user2 = new User(1, "22");
        User user3 = new User(3, "33");
        User user4 = new User(-4, "44");
        User user5 = new User(4, "55");
        User user6 = new User(6, "66");
        Comparator<User> comparator = Comparator.comparingInt(User::getId);
        List<User> listUser = new ArrayList<>();
        listUser.add(user3);
        listUser.add(user5);
        listUser.add(user1);
        listUser.add(user4);
        listUser.add(user6);
        listUser.add(user2);
        QuickList.quickSort(listUser, comparator);
        List<Integer> result = listUser.stream().map(User::getId).toList();
        assertIterableEquals(List.of(-4, 1, 1, 3, 4, 6), result);
    }

    @Test
    @DisplayName("Сортирует список в обратном порядке по пользовательскому компаратору")
    void whenReverseOrderThenOk() {
        Comparator<Integer> comparator = (a, b) -> b - a;
        List<Integer> list = new ArrayList<>();
        list.add(-2);
        list.add(-8);
        list.add(1);
        list.add(0);
        list.add(10);
        list.add(1);
        list.add(8);
        QuickList.quickSort(list, comparator);
        assertIterableEquals(List.of(10, 8, 1, 1, 0, -2, -8), list);
    }

    private static class User {
        private Integer id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }
    }
}
