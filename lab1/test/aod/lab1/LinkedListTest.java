package aod.lab1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LinkedListTest {

    private LinkedList<Integer> emptyList;

    @BeforeEach
    void setUp() throws Exception {
        emptyList = new LinkedList<Integer>();
    }

    @AfterEach
    void tearDown() throws Exception {
        emptyList = null;
    }

    @Test
    void testSizeOnEmptyList() {
        assertEquals(0, emptyList.size());
    }

    @Test
    @DisplayName("Testa size() på tom lista.")
    void testAddOneDataToEmptyList() {
        int data = 42;
        assertEquals(0, emptyList.size());
        emptyList.add(data);

        assertEquals(1, emptyList.size());
    }

    @Test
    void testOnNegativePosToAdd() {
        int data = 42;
        assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.add(data, -1));
    }

    @Test
    void testAddTwoElements() {
        emptyList.add(10);
        emptyList.add(20);

        assertEquals(2, emptyList.size());
        assertEquals(10, emptyList.get(0));
        assertEquals(20, emptyList.get(1));
    }

    @Test
    void testAddAtIndexZero() {
        emptyList.add(20);
        emptyList.add(10, 0);

        assertEquals(2, emptyList.size());
        assertEquals(10, emptyList.get(0));
        assertEquals(20, emptyList.get(1));
    }

    @Test
    void testAddAtMiddle() {
        emptyList.add(10);
        emptyList.add(30);
        emptyList.add(20, 1);

        assertEquals(3, emptyList.size());
        assertEquals(10, emptyList.get(0));
        assertEquals(20, emptyList.get(1));
        assertEquals(30, emptyList.get(2));
    }

    @Test
    void testGetValid() {
        emptyList.add(99);

        assertEquals(99, emptyList.get(0));
    }

    @Test
    void testGetInvalid() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.get(0));
    }

    @Test
    void testSetValid() {
        emptyList.add(5);
        emptyList.set(99, 0);

        assertEquals(99, emptyList.get(0));
    }

    @Test
    void testSetInvalid() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.set(10, 0));
    }

    @Test
    void testRemoveLast() {
        emptyList.add(1);
        emptyList.add(2);
        emptyList.remove();

        assertEquals(1, emptyList.size());
        assertEquals(1, emptyList.get(0));
    }

    @Test
    void testRemoveAtIndex() {
        emptyList.add(1);
        emptyList.add(2);
        emptyList.add(3);
        emptyList.remove(1);

        assertEquals(2, emptyList.size());
        assertEquals(1, emptyList.get(0));
        assertEquals(3, emptyList.get(1));
    }

    @Test
    void testRemoveInvalid() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.remove());
    }

    @Test
    void testRemoveInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.remove(0));
    }

    @Test
    void testClear() {
        emptyList.add(1);
        emptyList.add(2);
        emptyList.clear();

        assertEquals(0, emptyList.size());
    }

    @Test
    void testClearThenAddAgain() {
        emptyList.add(1);
        emptyList.clear();
        emptyList.add(5);

        assertEquals(1, emptyList.size());
        assertEquals(5, emptyList.get(0));
    }
}