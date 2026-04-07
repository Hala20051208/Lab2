/**
 * 
 */
package aod.lab1;

/**
 * 
 */
public class LinkedList<T> implements List<T> {
	private Node<T> head;
	private int size;

	public LinkedList() {
		head = null;
		size = 0;
	}

	@Override
	public void add(T data) {
		add(data, size);
	}

	@Override
	public void add(T data, int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Ogiltigt index");
		}

		Node<T> newNode = new Node<>(data);

		if (index == 0) {
			newNode.next = head;
			head = newNode;
		} else {
			Node<T> current = head;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			newNode.next = current.next;
			current.next = newNode;
		}

		size++;
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Ogiltigt index");
		}

		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}

		return current.data;
	}

	@Override
	public void set(T data, int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Ogiltigt index");
		}

		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}

		current.data = data;
	}

	@Override
	public void remove() {
		if (size == 0) {
			throw new IndexOutOfBoundsException("Listan är tom");
		}
		remove(size - 1);
	}

	@Override
	public void remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Ogiltigt index");
		}

		if (index == 0) {
			head = head.next;
		} else {
			Node<T> current = head;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			current.next = current.next.next;
		}

		size--;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}
}