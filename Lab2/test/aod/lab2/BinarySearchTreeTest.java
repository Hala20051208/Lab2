package aod.lab2;

//Test
public class BinarySearchTreeTest {

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(1);
        tree.add(4);

        System.out.println("Trädet: " + tree);
        System.out.println("Finns 3? " + tree.searchFor(3));
        System.out.println("Finns 9? " + tree.searchFor(9));
        System.out.println("Size: " + tree.size());

        System.out.println("Tar bort 3: " + tree.remove(3));
        System.out.println("Trädet efter remove: " + tree);
        System.out.println("Size efter remove: " + tree.size());
    }
}