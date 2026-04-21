package aod.lab2;

/**
 * Implementation av ett binärt sökträd.
 *
 * Trädet lagrar element i sorterad ordning enligt compareTo():
 * mindre värden placeras i vänster delträd och större värden
 * placeras i höger delträd.
 *
 * Dubbletter ignoreras i denna implementation..
 *
 * @param <T> typen som lagras i trädet
 */
public class BinarySearchTree<T extends Comparable<? super T>>
    implements Tree<T> {

    /**
     * Inre klass som representerar en nod i trädet.
     */
    private final class BSTNode {

        /** Värdet som lagras i noden. */
        private T item;

        /** Referens till vänster barn. */
        private BSTNode left;

        /** Referens till höger barn. */
        private BSTNode right;

        /**
         * Skapar en ny nod med ett värde.
         *
         * @param newItem värdet som ska lagras i noden
         */
        BSTNode(final T newItem) {
            this.item = newItem;
            this.left = null;
            this.right = null;
        }

        /**
         * Returnerar nodens värde.
         *
         * @return nodens värde
         */
        private T getItem() {
            return item;
        }

        /**
         * Sätter nodens värde.
         *
         * @param newItem nytt värde
         */
        private void setItem(final T newItem) {
            this.item = newItem;
        }

        /**
         * Returnerar vänster barn.
         *
         * @return vänster barn
         */
        private BSTNode getLeft() {
            return left;
        }

        /**
         * Sätter vänster barn.
         *
         * @param newLeft vänster barn
         */
        private void setLeft(final BSTNode newLeft) {
            this.left = newLeft;
        }

        /**
         * Returnerar höger barn.
         *
         * @return höger barn
         */
        private BSTNode getRight() {
            return right;
        }

        /**
         * Sätter höger barn.
         *
         * @param newRight höger barn
         */
        private void setRight(final BSTNode newRight) {
            this.right = newRight;
        }
    }

    /** Referens till trädets rot. */
    private BSTNode root;

    /** Antal element i trädet. */
    private int size;

    /**
     * Skapar ett tomt binärt sökträd.
     */
    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    /**
     * Lägger till ett element i trädet.
     *
     * Om elementet redan finns ignoreras det.
     *
     * @param itemToAdd elementet som ska läggas till
     */
    @Override
    public void add(final T itemToAdd) {
        root = addRecursive(root, itemToAdd);
    }

    /**
     * Rekursiv hjälpmetod för att lägga till ett element.
     *
     * @param currentNode aktuell nod
     * @param itemToAdd elementet som ska läggas till
     * @return roten till det uppdaterade delträdet
     */
    private BSTNode addRecursive(final BSTNode currentNode,
        final T itemToAdd) {

        if (currentNode == null) {
            size++;
            return new BSTNode(itemToAdd);
        }

        final int compareValue = itemToAdd.compareTo(currentNode.getItem());

        if (compareValue < 0) {
            currentNode.setLeft(
                addRecursive(currentNode.getLeft(), itemToAdd)
            );
        } else if (compareValue > 0) {
            currentNode.setRight(
                addRecursive(currentNode.getRight(), itemToAdd)
            );
        }

        return currentNode;
    }

    /**
     * Söker efter ett element i trädet.
     *
     * @param itemToSearchFor elementet som ska sökas efter
     * @return true om elementet finns, annars false
     */
    @Override
    public boolean searchFor(final T itemToSearchFor) {
        return searchRecursive(root, itemToSearchFor);
    }

    /**
     * Rekursiv hjälpmetod för sökning i trädet.
     *
     * @param currentNode aktuell nod
     * @param itemToSearchFor elementet som söks
     * @return true om elementet finns, annars false
     */
    private boolean searchRecursive(final BSTNode currentNode,
        final T itemToSearchFor) {

        if (currentNode == null) {
            return false;
        }

        final int compareValue =
            itemToSearchFor.compareTo(currentNode.getItem());

        if (compareValue == 0) {
            return true;
        } else if (compareValue < 0) {
            return searchRecursive(
                currentNode.getLeft(),
                itemToSearchFor
            );
        } else {
            return searchRecursive(
                currentNode.getRight(),
                itemToSearchFor
            );
        }
    }

    /**
     * Returnerar antalet element i trädet.
     *
     * @return antal element
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Tömmer trädet på alla element.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Tar bort ett element ur trädet.
     *
     * @param itemToRemove elementet som ska tas bort
     * @return true om elementet fanns och togs bort, annars false
     */
    @Override
    public boolean remove(final T itemToRemove) {
        if (!searchFor(itemToRemove)) {
            return false;
        }

        root = removeRecursive(root, itemToRemove);
        size--;
        return true;
    }

    /**
     * Rekursiv hjälpmetod för att ta bort ett element.
     *
     * @param currentNode aktuell nod
     * @param itemToRemove elementet som ska tas bort
     * @return roten till det uppdaterade delträdet
     */
    private BSTNode removeRecursive(final BSTNode currentNode,
        final T itemToRemove) {

        if (currentNode == null) {
            return null;
        }

        final int compareValue =
            itemToRemove.compareTo(currentNode.getItem());

        if (compareValue < 0) {
            currentNode.setLeft(
                removeRecursive(currentNode.getLeft(), itemToRemove)
            );
        } else if (compareValue > 0) {
            currentNode.setRight(
                removeRecursive(currentNode.getRight(), itemToRemove)
            );
        } else {
            if (currentNode.getLeft() == null
                && currentNode.getRight() == null) {
                return null;
            }

            if (currentNode.getLeft() == null) {
                return currentNode.getRight();
            }

            if (currentNode.getRight() == null) {
                return currentNode.getLeft();
            }

            final T minValue = findMin(currentNode.getRight());
            currentNode.setItem(minValue);
            currentNode.setRight(
                removeRecursive(currentNode.getRight(), minValue)
            );
        }

        return currentNode;
    }

    /**
     * Hittar minsta värdet i ett delträd.
     *
     * @param currentNode roten till delträdet
     * @return minsta värdet i delträdet
     */
    private T findMin(final BSTNode currentNode) {
        BSTNode tempNode = currentNode;

        while (tempNode.getLeft() != null) {
            tempNode = tempNode.getLeft();
        }

        return tempNode.getItem();
    }

    /**
     * Returnerar trädets innehåll i sorterad ordning.
     *
     * @return en sträng med trädets innehåll
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        inorder(root, builder);
        return builder.toString().trim();
    }

    /**
     * Rekursiv hjälpmetod för in-order-traversering.
     *
     * @param currentNode aktuell nod
     * @param builder strängbyggare där resultatet samlas
     */
    private void inorder(final BSTNode currentNode,
        final StringBuilder builder) {

        if (currentNode == null) {
            return;
        }

        inorder(currentNode.getLeft(), builder);
        builder.append(currentNode.getItem()).append(" ");
        inorder(currentNode.getRight(), builder);
    }
}