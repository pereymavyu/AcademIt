package ru.academits.pereyma.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
    private int size;
    private Comparator<? super T> comparator;

    public Tree() {
    }

    public Tree(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    private int compareTreeNodesData(T data1, T data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null) {
            if (data2 == null) {
                return 0;
            } else {
                return -1;
            }
        }

        if (data2 == null) {
            return 1;
        }

        //noinspection unchecked
        return ((Comparable<T>) data1).compareTo(data2);
    }

    //функция getRoot нужна для получения ссылки на root для использования в рекурсивном обходе дерева
    private TreeNode<T> getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    public void widthTraversal(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (queue.size() > 0) {
            TreeNode<T> currentNode = queue.remove();

            consumer.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    public void depthTraversal(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        ArrayList<TreeNode<T>> stack = new ArrayList<>(size);
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode<T> currentNode = stack.remove(stack.size() - 1);

            consumer.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.add(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.add(currentNode.getLeft());
            }
        }
    }

    public void recursiveDepthTraversal(Consumer<T> consumer) {
        recursiveVisit(getRoot(), consumer);
    }

    private void recursiveVisit(TreeNode<T> node, Consumer<T> consumer) {
        consumer.accept(node.getData());

        if (node.getLeft() != null) {
            recursiveVisit(node.getLeft(), consumer);
        }

        if (node.getRight() != null) {
            recursiveVisit(node.getRight(), consumer);
        }
    }

    private TreeNode<T> getNode(T data) {
        if (root == null) {
            return null;
        }

        TreeNode<T> currentNode = root;

        for (; ; ) {
            if (compareTreeNodesData(data, currentNode.getData()) == 0) {
                return currentNode;
            } else if (compareTreeNodesData(data, currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return null;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    return null;
                }
            }
        }
    }

    public void add(T data) {
        if (root == null) {
            root = new TreeNode<>(data);

            ++size;
            return;
        }

        TreeNode<T> currentNode = root;

        for (; ; ) {
            if (compareTreeNodesData(data, currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(data));

                    ++size;
                    return;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new TreeNode<>(data));

                    ++size;
                    return;
                }
            }
        }
    }

    public boolean remove(T data) {
        if (root == null) {
            return false;
        }

        TreeNode<T> currentNode = root;
        TreeNode<T> currentNodeParent = null;

        for (; ; ) {
            if (compareTreeNodesData(data, currentNode.getData()) == 0) {
                removeNode(currentNode, currentNodeParent);

                return true;
            } else if (compareTreeNodesData(data, currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNodeParent = currentNode;
                    currentNode = currentNode.getLeft();
                } else {
                    return false;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNodeParent = currentNode;
                    currentNode = currentNode.getRight();
                } else {
                    return false;
                }
            }
        }
    }

    private void removeNode(TreeNode<T> nodeToRemove, TreeNode<T> nodeToRemoveParent) {
        if (nodeToRemoveParent == null) {
            if (nodeToRemove.getLeft() == null && nodeToRemove.getRight() == null) {
                root = null;
            } else if ((nodeToRemove.getRight() == null)) {
                root = nodeToRemove.getLeft();
            } else if ((nodeToRemove.getLeft() == null)) {
                root = nodeToRemove.getRight();
            } else {
                TreeNode<T> nodeToReplaceParent = nodeToRemove;
                TreeNode<T> nodeToReplace = nodeToRemove.getRight();

                while (nodeToReplace.getLeft() != null) {
                    nodeToReplaceParent = nodeToReplace;
                    nodeToReplace = nodeToReplace.getLeft();
                }

                if (nodeToReplace.getRight() != null) {
                    if (nodeToReplaceParent.getLeft() == nodeToReplace) {
                        nodeToReplaceParent.setLeft(nodeToReplace.getRight());
                    } else {
                        nodeToReplaceParent.setRight(nodeToReplace.getRight());
                    }
                } else {
                    nodeToReplaceParent.setLeft(null);
                }

                nodeToReplace.setLeft(nodeToRemove.getLeft());
                nodeToReplace.setRight(nodeToRemove.getRight());

                root = nodeToReplace;
            }
        } else {
            if (nodeToRemove.getLeft() == null && nodeToRemove.getRight() == null) {
                if (nodeToRemove == nodeToRemoveParent.getLeft()) {
                    nodeToRemoveParent.setLeft(null);
                } else {
                    nodeToRemoveParent.setRight(null);
                }
            } else if ((nodeToRemove.getRight() == null)) {
                if (nodeToRemove == nodeToRemoveParent.getLeft()) {
                    nodeToRemoveParent.setLeft(nodeToRemove.getLeft());
                } else {
                    nodeToRemoveParent.setRight(nodeToRemove.getLeft());
                }
            } else if ((nodeToRemove.getLeft() == null)) {
                if (nodeToRemove == nodeToRemoveParent.getLeft()) {
                    nodeToRemoveParent.setLeft(nodeToRemove.getRight());
                } else {
                    nodeToRemoveParent.setRight(nodeToRemove.getRight());
                }
            } else {
                TreeNode<T> nodeToReplaceParent = nodeToRemove;
                TreeNode<T> nodeToReplace = nodeToRemove.getRight();

                while (nodeToReplace.getLeft() != null) {
                    nodeToReplaceParent = nodeToReplace;
                    nodeToReplace = nodeToReplace.getLeft();
                }

                if (nodeToReplace.getRight() != null) {
                    if (nodeToReplaceParent.getLeft() == nodeToReplace) {
                        nodeToReplaceParent.setLeft(nodeToReplace.getRight());
                    } else {
                        nodeToReplaceParent.setRight(nodeToReplace.getRight());
                    }
                } else {
                    nodeToReplaceParent.setLeft(null);
                }

                nodeToReplace.setLeft(nodeToRemove.getLeft());
                nodeToReplace.setRight(nodeToRemove.getRight());

                if (nodeToRemove == nodeToRemoveParent.getLeft()) {
                    nodeToRemoveParent.setLeft(nodeToReplace);
                } else {
                    nodeToRemoveParent.setRight(nodeToReplace);
                }
            }
        }

        --size;
    }

    @Override
    public String toString() {
        if (root == null) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("[");

        widthTraversal(x -> {
            sb.append(x);
            sb.append(", ");
        });

        sb.setLength(sb.length() - 2);

        return sb.append("]").toString();
    }
}