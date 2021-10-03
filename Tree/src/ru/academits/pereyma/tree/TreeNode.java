package ru.academits.pereyma.tree;

public class TreeNode<T extends Comparable<T>> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private final T data;

    TreeNode(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data must not be null");
        }

        this.data = data;
    }

    TreeNode<T> getLeft() {
        return left;
    }

    void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    TreeNode<T> getRight() {
        return right;
    }

    void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public T getData() {
        return data;
    }
}
