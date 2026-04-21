package aod.lab2;

import java.util.Random;

/**
 * Klass för att mäta söktid i BinarySearchTree.
 *
 * Två experiment görs:
 * 1. Träd byggda med slumpade heltal
 * 2. Träd byggda med sorterade heltal.
 *
 * Resultaten skrivs ut så att de kan föras över till tabell och diagram i rapporten.
 */
public class TimeTest {

    public static void main(String[] args) {
        testRandomNumbers();
        System.out.println();
        testSortedNumbers();
    }

    /**
     * Testar söktider i träd byggda med slumpade heltal.
     */
    private static void testRandomNumbers() {
        int[] sizes = {20000, 40000, 80000, 160000};
        int searches = 100000;
        Random random = new Random();

        System.out.println("SLUMPADE HELTAL");
        System.out.println("n\tTid (ms)\tKvot T(2n)/T(n)");

        long previousTime = -1;

        for (int n : sizes) {
            BinarySearchTree<Integer> tree = new BinarySearchTree<>();

            // Bygg trädet med slumpade tal
            for (int i = 0; i < n; i++) {
                tree.add(random.nextInt(1_000_000));
            }

            // Mät endast söktiden
            long start = System.nanoTime();

            for (int i = 0; i < searches; i++) {
                tree.searchFor(random.nextInt(1_000_000));
            }

            long end = System.nanoTime();
            long elapsedMs = (end - start) / 1_000_000;

            if (previousTime == -1) {
                System.out.println(n + "\t" + elapsedMs + "\t\t-");
            } else {
                double quotient = (double) elapsedMs / previousTime;
                System.out.printf("%d\t%d\t\t%.3f%n", n, elapsedMs, quotient);
            }

            previousTime = elapsedMs;
        }
    }

    /**
     * Testar söktider i träd byggda med sorterade heltal.
     */
    private static void testSortedNumbers() {
        int[] sizes = {5000, 10000, 15000, 20000};
        int searches = 100000;
        Random random = new Random();

        System.out.println("SORTERADE HELTAL");
        System.out.println("n\tTid (ms)\tKvot T(2n)/T(n)");

        long previousTime = -1;

        for (int n : sizes) {
            BinarySearchTree<Integer> tree = new BinarySearchTree<>();

            try {
                // Bygg trädet med sorterade tal
                for (int i = 0; i < n; i++) {
                    tree.add(i);
                }

                // Mät endast söktiden
                long start = System.nanoTime();

                for (int i = 0; i < searches; i++) {
                    tree.searchFor(random.nextInt(n));
                }

                long end = System.nanoTime();
                long elapsedMs = (end - start) / 1_000_000;

                if (previousTime == -1) {
                    System.out.println(n + "\t" + elapsedMs + "\t\t-");
                } else {
                    double quotient = (double) elapsedMs / previousTime;
                    System.out.printf("%d\t%d\t\t%.3f%n", n, elapsedMs, quotient);
                }

                previousTime = elapsedMs;

            } catch (StackOverflowError e) {
                System.out.println(n + "\tStackOverflowError\t-");
            }
        }
    }
}