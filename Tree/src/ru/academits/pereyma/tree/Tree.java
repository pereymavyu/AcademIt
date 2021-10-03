package ru.academits.pereyma.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T extends Comparable<T>> {
    private TreeNode<T> head;
    private int size;

    public Tree() {
    }

    public Tree(T data) {
        head = new TreeNode<>(data);
        ++size;
    }

    //функция getHead используется для получения ссылки на head для использования в рекурсивном обходе дерева
    public TreeNode<T> getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    public void wightTraversal(Consumer<TreeNode<T>> consumer) {
        if (head == null) {
            return;
        }

        LinkedList<TreeNode<T>> queue = new LinkedList<>();
        queue.add(head);

        while (queue.size() > 0) {
            TreeNode<T> currentNode = queue.getFirst();
            queue.removeFirst();

            consumer.accept(currentNode);

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    public void depthTraversal(Consumer<TreeNode<T>> consumer) {
        if (head == null) {
            return;
        }

        ArrayList<TreeNode<T>> stack = new ArrayList<>();
        stack.add(head);

        while (stack.size() > 0) {
            TreeNode<T> currentNode = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);

            consumer.accept(currentNode);

            if (currentNode.getRight() != null) {
                stack.add(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.add(currentNode.getLeft());
            }
        }
    }

    public void recursiveDepthTraversal(TreeNode<T> node) {
        System.out.print(node.getData() + " ");

        if (node.getLeft() != null) {
            recursiveDepthTraversal(node.getLeft());
        }

        if (node.getRight() != null) {
            recursiveDepthTraversal(node.getRight());
        }
    }

    public TreeNode<T> getNode(T data) {
        if (head == null) {
            return null;
        }

        TreeNode<T> currentNode = head;

        for (; ; ) {
            if (Objects.equals(data, currentNode.getData())) {
                return currentNode;
            } else if (data.compareTo(currentNode.getData()) < 0) {
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
        if (head == null) {
            head = new TreeNode<>(data);

            ++size;
            return;
        }

        TreeNode<T> currentNode = head;

        for (; ; ) {
            if (data.compareTo(currentNode.getData()) < 0) {
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
        if (head == null) {
            return false;
        }

        TreeNode<T> currentNode = head;
        TreeNode<T> currentNodeParent = null;

        for (; ; ) {
            if (Objects.equals(data, currentNode.getData())) {
                removeNode(currentNode, currentNodeParent);

                return true;
            } else if (data.compareTo(currentNode.getData()) < 0) {
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
                head = null;
            } else if ((nodeToRemove.getRight() == null)) {
                head = nodeToRemove.getLeft();
            } else if ((nodeToRemove.getLeft() == null)) {
                head = nodeToRemove.getRight();
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

                head = nodeToReplace;
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
        if (head == null) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("[");

        wightTraversal((x) -> {
            sb.append(x.getData());
            sb.append(", ");
        });

        sb.setLength(sb.length() - 2);

        return sb.append("]").toString();
    }
}