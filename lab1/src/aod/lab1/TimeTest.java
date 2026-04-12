package aod.lab1;

import java.util.ArrayList;
import java.util.Random;

public class TimeTest {

    public static void main(String[] args) {
        int[] sizes = {20000, 40000, 80000, 160000, 320000, 640000};
        int repetitions = 100;
        Random rand = new Random();

        System.out.println("n\tLinkedFirst\tLinkedMiddle\tLinkedLast\tArrayFirst\tArrayMiddle\tArrayLast");

        for (int n : sizes) {
            long linkedFirst = testLinkedList(n, repetitions, "first", rand);
            long linkedMiddle = testLinkedList(n, repetitions, "middle", rand);
            long linkedLast = testLinkedList(n, repetitions, "last", rand);

            long arrayFirst = testArrayList(n, repetitions, "first", rand);
            long arrayMiddle = testArrayList(n, repetitions, "middle", rand);
            long arrayLast = testArrayList(n, repetitions, "last", rand);

            System.out.println(n + "\t" +
                    linkedFirst + "\t" +
                    linkedMiddle + "\t" +
                    linkedLast + "\t" +
                    arrayFirst + "\t" +
                    arrayMiddle + "\t" +
                    arrayLast);
        }
    }

    public static long testLinkedList(int n, int repetitions, String position, Random rand) {
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            list.add(rand.nextInt(100));
        }

        long startTime = System.nanoTime();

        for (int i = 0; i < repetitions; i++) {
            int value = rand.nextInt(100);

            if (position.equals("first")) {
                list.add(value, 0);
            } else if (position.equals("middle")) {
                list.add(value, list.size() / 2);
            } else if (position.equals("last")) {
                list.add(value);
            }
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static long testArrayList(int n, int repetitions, String position, Random rand) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(rand.nextInt(100));
        }

        long startTime = System.nanoTime();

        for (int i = 0; i < repetitions; i++) {
            int value = rand.nextInt(100);

            if (position.equals("first")) {
                list.add(0, value);
            } else if (position.equals("middle")) {
                list.add(list.size() / 2, value);
            } else if (position.equals("last")) {
                list.add(value);
            }
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}