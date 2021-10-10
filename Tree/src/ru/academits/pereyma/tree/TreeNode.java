package ru.academits.pereyma.tree;

class TreeNode<T> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private final T data;

    TreeNode(T data) {
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
