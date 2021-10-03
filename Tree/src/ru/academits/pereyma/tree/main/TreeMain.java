package ru.academits.pereyma.tree.main;

import ru.academits.pereyma.tree.Tree;

public class TreeMain {
    public static void main(String[] args) {
        Tree<Integer> tree1 = new Tree<>(3);
        tree1.add(10);
        tree1.add(7);
        tree1.add(5);
        tree1.add(15);
        tree1.add(2);
        tree1.add(2);
        tree1.add(1);

        System.out.println(tree1);

        Tree<String> tree2 = new Tree<>();

        System.out.println(tree2);
        System.out.println(tree1.getSize());
        System.out.println(tree2.getSize());
        tree2.add("string1");
        tree2.add("string2");
        System.out.println(tree2);

        System.out.println("===");

        System.out.println(tree1.getNode(2));
        System.out.println(tree1.getNode(1));
        System.out.println(tree2.getNode("string3"));

        System.out.println(tree1.remove(0));
        System.out.println(tree1);
        System.out.println(tree1.getSize());

        System.out.println(tree2.remove("1"));
        System.out.println(tree2);

        System.out.println("===");

        tree1.wightTraversal((x) -> System.out.print(x.getData() + " "));
        System.out.println();

        tree1.depthTraversal((x) -> System.out.print(x.getData() + " "));
        System.out.println();

        tree1.recursiveDepthTraversal(tree1.getHead());
    }
}