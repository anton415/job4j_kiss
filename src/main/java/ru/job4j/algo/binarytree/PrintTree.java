package ru.job4j.algo.binarytree;

public class PrintTree {
    private PrintTree() {
    }

    public static String getTreeDisplay(VisualNode root) {
        StringBuilder result = new StringBuilder();
        build(root, 0, result);
        return result.toString();
    }

    private static void build(VisualNode node, int depth, StringBuilder result) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < depth; i++) {
            result.append("  ");
        }
        result.append(node.getText()).append(System.lineSeparator());
        build(node.getLeft(), depth + 1, result);
        build(node.getRight(), depth + 1, result);
    }
}
