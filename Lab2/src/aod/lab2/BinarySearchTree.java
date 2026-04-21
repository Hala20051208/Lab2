package aod.lab2;

/**
 * Implementation av ett binärt sökträd...
 *
 * Trädet lagrar element i sorterad ordning enligt compareTo():
 * - mindre värden placeras i vänster delträd
 * - större värden placeras i höger delträd
 *
 * I denna implementation ignoreras dubbletter.
 *
 * @param <T> typen som lagras i trädet.
 *            Måste kunna jämföras med compareTo().
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements Tree<T> {

    /**
     * Inre klass som representerar en nod i trädet.
     */
    private class BSTNode {
        T item;
        BSTNode left;
        BSTNode right;

        /**
         * Skapar en ny nod med ett värde.
         *
         * @param item värdet som ska lagras i noden
         */
        BSTNode(T item) {
            this.item = item;
            this.left = null;
            this.right = null;
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
     * Om elementet redan finns i trädet ignoreras det,
     * alltså lagras inga dubbletter.
     *
     * @param item elementet som ska läggas till
     */
    @Override
    public void add(T item) {
        root = addRecursive(root, item);
    }

    /**
     * Rekursiv hjälpmetod för att lägga till ett element.
     *
     * Basfall:
     * - Om aktuell nod är null skapas en ny nod.
     *
     * Rekursionssteg:
     * - Om item är mindre än nodens värde går vi vänster.
     * - Om item är större än nodens värde går vi höger.
     * - Om item är lika med nodens värde ignoreras det.
     *
     * @param node aktuell nod
     * @param item elementet som ska läggas till
     * @return roten till det uppdaterade delträdet
     */
    private BSTNode addRecursive(BSTNode node, T item) {
        if (node == null) {
            size++;
            return new BSTNode(item);
        }

        int compareValue = item.compareTo(node.item);

        if (compareValue < 0) {
            node.left = addRecursive(node.left, item);
        } else if (compareValue > 0) {
            node.right = addRecursive(node.right, item);
        }
        // Om compareValue == 0 görs inget:
        // dubbletter ignoreras.

        return node;
    }

    /**
     * Söker efter ett element i trädet.
     *
     * @param itemToSearchFor elementet som ska sökas efter
     * @return true om elementet finns, annars false
     */
    @Override
    public boolean searchFor(T itemToSearchFor) {
        return searchRecursive(root, itemToSearchFor);
    }

    /**
     * Rekursiv hjälpmetod för sökning i trädet.
     *
     * Basfall:
     * - Om aktuell nod är null finns elementet inte.
     *
     * Rekursionssteg:
     * - Om item är mindre än nodens värde söker vi vänster.
     * - Om item är större än nodens värde söker vi höger.
     * - Om item är lika med nodens värde har vi hittat det.
     *
     * @param node aktuell nod
     * @param item elementet som söks
     * @return true om elementet finns, annars false
     */
    private boolean searchRecursive(BSTNode node, T item) {
        if (node == null) {
            return false;
        }

        int compareValue = item.compareTo(node.item);

        if (compareValue == 0) {
            return true;
        } else if (compareValue < 0) {
            return searchRecursive(node.left, item);
        } else {
            return searchRecursive(node.right, item);
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
     * Om elementet inte finns returneras false.
     * Om elementet finns tas det bort och metoden returnerar true.
     *
     * @param itemToRemove elementet som ska tas bort
     * @return true om elementet fanns och togs bort, annars false
     */
    @Override
    public boolean remove(T itemToRemove) {
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
     * Basfall:
     * - Om aktuell nod är null finns inget att ta bort.
     *
     * Rekursionssteg:
     * - Om item är mindre än nodens värde går vi vänster.
     * - Om item är större än nodens värde går vi höger.
     * - Om item är lika med nodens värde ska noden tas bort.
     *
     * Vid borttagning finns tre fall:
     * 1. Noden har inga barn -> returnera null
     * 2. Noden har ett barn -> returnera barnnoden
     * 3. Noden har två barn ->
     *    ersätt nodens värde med minsta värdet i höger delträd
     *    och ta sedan bort den noden där minsta värdet fanns
     *
     * @param node aktuell nod
     * @param item elementet som ska tas bort
     * @return roten till det uppdaterade delträdet
     */
    private BSTNode removeRecursive(BSTNode node, T item) {
        if (node == null) {
            return null;
        }

        int compareValue = item.compareTo(node.item);

        if (compareValue < 0) {
            node.left = removeRecursive(node.left, item);
        } else if (compareValue > 0) {
            node.right = removeRecursive(node.right, item);
        } else {
            // Fall 1: noden har inga barn
            if (node.left == null && node.right == null) {
                return null;
            }

            // Fall 2: noden har bara höger barn
            if (node.left == null) {
                return node.right;
            }

            // Fall 2: noden har bara vänster barn
            if (node.right == null) {
                return node.left;
            }

            // Fall 3: noden har två barn
            T minValue = findMin(node.right);
            node.item = minValue;
            node.right = removeRecursive(node.right, minValue);
        }

        return node;
    }

    /**
     * Hittar minsta värdet i ett delträd.
     *
     * Minsta värdet i ett BST finns längst till vänster.
     *
     * @param node roten till delträdet
     * @return minsta värdet i delträdet
     */
    private T findMin(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.item;
    }

    /**
     * Returnerar trädets innehåll som en sträng i sorterad ordning.
     *
     * Traverseringen sker med djupet-först-traversering av typen in-order.
     *
     * @return en sträng med trädets innehåll i sorterad ordning
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        inorder(root, builder);
        return builder.toString().trim();
    }

    /**
     * Rekursiv hjälpmetod för in-order-traversering.
     *
     * Ordningen är:
     * vänster delträd -> rot -> höger delträd
     *
     * @param node aktuell nod
     * @param builder strängbyggare där resultatet samlas
     */
    private void inorder(BSTNode node, StringBuilder builder) {
        if (node == null) {
            return;
        }

        inorder(node.left, builder);
        builder.append(node.item).append(" ");
        inorder(node.right, builder);
    }
}