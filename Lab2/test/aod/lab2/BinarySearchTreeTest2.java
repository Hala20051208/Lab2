package aod.lab2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest2 {

	private BinarySearchTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new BinarySearchTree<>();
    }

    @Test
    void testAdd() {
        tree.add(5);
        tree.add(3);
        tree.add(7);

        assertEquals(3, tree.size());
    }

    @Test
    void testSearch() {
        tree.add(10);

        assertTrue(tree.searchFor(10));
        assertFalse(tree.searchFor(99));
    }

    @Test
    void testRemove() {
        tree.add(5);
        tree.add(3);

        assertTrue(tree.remove(3));
        assertFalse(tree.searchFor(3));
    }

    @Test
    void testClear() {
        tree.add(1);
        tree.add(2);

        tree.clear();

        assertEquals(0, tree.size());
    }
    @Test
    void testDuplicate() {
        tree.add(5);
        tree.add(5);

        assertEquals(1, tree.size());
    }
    @Test
    void testEmptyTree() {
        assertFalse(tree.searchFor(10));
        assertEquals(0, tree.size());
    }
    @Test
    void testRemoveNotExisting() {
        tree.add(5);

        assertFalse(tree.remove(99));
        assertEquals(1, tree.size());
    }
    @Test
    void testRemoveRoot() {
        tree.add(5);
        tree.add(3);
        tree.add(7);

        assertTrue(tree.remove(5));
        assertFalse(tree.searchFor(5));
    }
    
}