package aod.lab2;

/**
 * Enkel testklass för att prova BinarySearchTree manuellt.
 */
public class BinarySearchTreeTest {

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        System.out.println("TEST AV BINARY SEARCH TREE");

        // Test: tomt träd
        System.out.println("Storlek från början: " + tree.size()); // 0
        System.out.println("Tomt träd som sträng: '" + tree.toString() + "'");

        // Test: add
        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(1);
        tree.add(4);
        tree.add(6);
        tree.add(8);

        System.out.println("Trädet efter add: " + tree); // 1 3 4 5 6 7 8
        System.out.println("Storlek efter add: " + tree.size()); // 7

        // Test: dubblett ignoreras
        tree.add(7);
        System.out.println("Storlek efter dubblett 7: " + tree.size()); // fortfarande 7

        // Test: searchFor
        System.out.println("Finns 4? " + tree.searchFor(4)); // true
        System.out.println("Finns 10? " + tree.searchFor(10)); // false

        // Test: remove bladnod
        System.out.println("Tar bort 1: " + tree.remove(1)); // true
        System.out.println("Trädet nu: " + tree);

        // Test: remove nod med ett eller två barn
        System.out.println("Tar bort 7: " + tree.remove(7)); // true
        System.out.println("Trädet nu: " + tree);

        // Test: remove värde som inte finns
        System.out.println("Tar bort 99: " + tree.remove(99)); // false
        System.out.println("Trädet nu: " + tree);

        // Test: clear
        tree.clear();
        System.out.println("Storlek efter clear: " + tree.size()); // 0
        System.out.println("Trädet efter clear: '" + tree.toString() + "'");
    }
}