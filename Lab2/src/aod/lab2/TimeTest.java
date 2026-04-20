package aod.lab2;

import java.util.Random;

public class TimeTest {

    public static void main(String[] args) {

        int[] sizes = {20000, 40000, 80000, 160000};
        Random rand = new Random();

        for (int n : sizes) {

            BinarySearchTree<Integer> tree = new BinarySearchTree<>();

         // Lägg till slumpade tal
            for (int i = 0; i < n; i++) {
                tree.add(rand.nextInt(1000000));
            }

            // Mät tid för sökningar
            long start = System.currentTimeMillis();

            for (int i = 0; i < 100000; i++) {
                tree.searchFor(rand.nextInt(1000000));
            }

            long end = System.currentTimeMillis();

            System.out.println("n = " + n + " Tid: " + (end - start) + " ms");
        }
    }
}