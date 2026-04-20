/**
 * 
 */
package aod.lab2;

/**
 * @author 24haal14
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements Tree<T> {

    private class BSTNode {
        T item;
        BSTNode left;
        BSTNode right;

        public BSTNode(T item) {
            this.item = item;
            this.left = null;
            this.right = null;
        }
    }

    private BSTNode root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    @Override
    public void add(T item) {
        root = addRecursive(root, item);
    }

    private BSTNode addRecursive(BSTNode node, T item) {
        if (node == null) {
            size++;
            return new BSTNode(item);
        }

        int cmp = item.compareTo(node.item);

        if (cmp < 0) {
            node.left = addRecursive(node.left, item);
        } else if (cmp > 0) {
            node.right = addRecursive(node.right, item);
        }
        // dubbletter ignoreras

        return node;
    }

    @Override
    public boolean searchFor(T itemToSearchFor) {
        return searchRecursive(root, itemToSearchFor);
    }

    private boolean searchRecursive(BSTNode node, T item) {
        if (node == null) {
            return false;
        }

        int cmp = item.compareTo(node.item);

        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return searchRecursive(node.left, item);
        } else {
            return searchRecursive(node.right, item);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean remove(T itemToRemove) {
        if (!searchFor(itemToRemove)) {
            return false;
        }

        root = removeRecursive(root, itemToRemove);
        size--;
        return true;
    }

    private BSTNode removeRecursive(BSTNode node, T item) {
        if (node == null) {
            return null;
        }

        int cmp = item.compareTo(node.item);

        if (cmp < 0) {
            node.left = removeRecursive(node.left, item);
        } else if (cmp > 0) {
            node.right = removeRecursive(node.right, item);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            }

            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            T minValue = findMin(node.right);
            node.item = minValue;
            node.right = removeRecursive(node.right, minValue);
        }

        return node;
    }

    private T findMin(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.item;
    }

    @Override
    public String toString() {
        return inorder(root).trim();
    }

    private String inorder(BSTNode node) {
        if (node == null) {
            return "";
        }

        return inorder(node.left) + node.item + " " + inorder(node.right);
    }
}