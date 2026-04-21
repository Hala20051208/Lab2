package aod.lab2;

import java.util.Random;

/**
 * Klass för att mäta söktid i BinarySearchTree.
 */
public final class TimeTest {

    /** Antal sökningar som görs per test. */
    private static final int NUMBER_OF_SEARCHES = 100000;

    /** Övre gräns för slumpade tal. */
    private static final int RANDOM_BOUND = 1000000;

    /** Omvandling från nanosekunder till millisekunder. */
    private static final int NANO_TO_MILLI = 1000000;

    /** Storlekar för test med slumpade heltal. */
    private static final int[] RANDOM_SIZES = {
        20000, 40000, 80000, 160000
    };

    /** Storlekar för test med sorterade heltal. */
    private static final int[] SORTED_SIZES = {
        5000, 10000, 15000, 20000
    };

    /**
     * Privat konstruktor eftersom klassen bara innehåller statiska metoder.
     */
    private TimeTest() {
    }

    /**
     * Kör båda tidsmätningarna.
     *
     * @param args kommandoradsargument
     */
    public static void main(final String[] args) {
        testRandomNumbers();
        System.out.println();
        testSortedNumbers();
    }

    /**
     * Testar söktider i träd byggda med slumpade heltal.
     */
    private static void testRandomNumbers() {
        final Random random = new Random();

        System.out.println("SLUMPADE HELTAL");
        System.out.println("n\tTid (ms)\tKvot T(2n)/T(n)");

        long previousTime = -1;

        for (final int n : RANDOM_SIZES) {
            final BinarySearchTree<Integer> tree =
                new BinarySearchTree<Integer>();

            for (int i = 0; i < n; i++) {
                tree.add(random.nextInt(RANDOM_BOUND));
            }

            final long start = System.nanoTime();

            for (int i = 0; i < NUMBER_OF_SEARCHES; i++) {
                tree.searchFor(random.nextInt(RANDOM_BOUND));
            }

            final long end = System.nanoTime();
            final long elapsedMs = (end - start) / NANO_TO_MILLI;

            if (previousTime == -1) {
                System.out.println(n + "\t" + elapsedMs + "\t\t-");
            } else {
                final double quotient =
                    (double) elapsedMs / previousTime;
                System.out.printf(
                    "%d\t%d\t\t%.3f%n",
                    n,
                    elapsedMs,
                    quotient
                );
            }

            previousTime = elapsedMs;
        }
    }

    /**
     * Testar söktider i träd byggda med sorterade heltal.
     */
    private static void testSortedNumbers() {
        final Random random = new Random();

        System.out.println("SORTERADE HELTAL");
        System.out.println("n\tTid (ms)\tKvot T(2n)/T(n)");

        long previousTime = -1;

        for (final int n : SORTED_SIZES) {
            final BinarySearchTree<Integer> tree =
                new BinarySearchTree<Integer>();

            try {
                for (int i = 0; i < n; i++) {
                    tree.add(i);
                }

                final long start = System.nanoTime();

                for (int i = 0; i < NUMBER_OF_SEARCHES; i++) {
                    tree.searchFor(random.nextInt(n));
                }

                final long end = System.nanoTime();
                final long elapsedMs = (end - start) / NANO_TO_MILLI;

                if (previousTime == -1) {
                    System.out.println(n + "\t" + elapsedMs + "\t\t-");
                } else {
                    final double quotient =
                        (double) elapsedMs / previousTime;
                    System.out.printf(
                        "%d\t%d\t\t%.3f%n",
                        n,
                        elapsedMs,
                        quotient
                    );
                }

                previousTime = elapsedMs;

            } catch (StackOverflowError error) {
                System.out.println(n + "\tStackOverflowError\t-");
            }
        }
    }
}