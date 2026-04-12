package aod.lab1;

import java.util.LinkedList;

public class IteratorTest {

    public static void main(String[] args) {
        int[] sizes = {20000, 40000, 80000, 160000};

        System.out.println("n\tForLoop\tForEach");

        for (int n : sizes) {
            LinkedList<Integer> list = new LinkedList<>();

            // fyll listan
            for (int i = 0; i < n; i++) {
                list.add(i);
            }

            long forLoopTime = testForLoop(list);
            long forEachTime = testForEach(list);

            System.out.println(n + "\t" + forLoopTime + "\t" + forEachTime);
        }
    }

    public static long testForLoop(LinkedList<Integer> list) {
        long start = System.nanoTime();

        for (int i = 0; i < list.size(); i++) {
            Integer x = list.get(i);
        }

        long end = System.nanoTime();
        return end - start;
    }

    public static long testForEach(LinkedList<Integer> list) {
        long start = System.nanoTime();

        for (Integer x : list) {
            // gör inget
        }

        long end = System.nanoTime();
        return end - start;
    }
}