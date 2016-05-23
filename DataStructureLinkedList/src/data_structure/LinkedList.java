package data_structure;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * This code is the complete version of the Linked List Data Structure.
 * This class includes: add, remove, make empty, peek, size and 
 * iterator methods.
 */
public class LinkedList<E> implements ListInterface<E> {
	
	/**
	 * Creates the node for the linked list
	 * @param <E> generic type E
	 */
	@SuppressWarnings("hiding")
	class Node<E> {
		E data;
		Node<E> next;
		public Node(E obj) {
			data = obj;
			next = null;
		}
		
	}//Node Class
	
	private Node<E> head, tail;
	private int currentSize;
	
	/**
	 * constructor for Node
	 */
	public LinkedList() {
		head = tail = null;
		currentSize = 0;		
	}

	/**
	 * Iterator class that goes thru each item
	 * in the linked list.
	 */
	class IteratorHelper implements Iterator<E> {
		Node<E> index;
		
		public IteratorHelper() {
			index = head;
		}
		
		public boolean hasNext() {
			return index != null;
		}
		
		public E next() {
			if(!hasNext())
				throw new NoSuchElementException();
			E tmp = index.data;
			index = index.next;
			return tmp;
		}
		
		public void remove() {
			throw new UnsupportedOperationException("Not Enabled");
		}
	}
	
	/** 
	 * Add an object to the front of the list
	 * @param obj the object to be added to the list
	 */
	public void addFirst(E obj) {
		Node<E> newNode = new Node<E>(obj);
		newNode.next = head;
		head = newNode;
		if (currentSize==0)
			tail = newNode;
		currentSize++;
	}
	
	/**
	 * This method will add an object to the end 
	 * of the list.
	 * @param obj the object to be added to the list.
	 */
	public void addLast(E obj) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Node<E> node = new Node(obj);
		if (head==null) {
			head = tail = node;
			currentSize++;
			return;
		}
		tail.next = node;
		tail = node;
		currentSize++;
		return;
	}

	/** 
	 * Removes the first Object in the list and returns it.
	 * Returns null if the list is empty.
	 * @return the object removed.
	 */
	public E removeFirst() {
		if (head==null)
			return null;
		E tmp = head.data;
		head = head.next;
		currentSize--;
		return tmp;
	}
	
	/**
	 * Removes the last Object in the list and returns it.
	 * Returns null if the list is empty.
	 * @return the object removed.
	 */	
	public E removeLast() {
		
		if (head==null)
			return null;
		if (head==tail)
			return removeFirst();
		Node<E> current = head;
		Node<E> previous = null;
		while (current != tail) {
			previous = current;
			current = current.next;
		}
		tail = previous;
		previous.next = null;
		currentSize--;
		return current.data;
	}

	/**
	 * Returns the first Object in the list, but does not remove it.
	 * Returns null if the list is empty.
	 * @return the object at the beginning of the list.
	 */
	public E peekFirst() {
		if (currentSize==0)
			return null;
		return head.data;
	}

	/**
	 * Returns the last Object in the list, but does not remove it. 
	 * Returns null if the list is empty.
	 * @return the object at the end of the list.
	 */
	public E peekLast() {
		if (currentSize==0)
			return null;
		return tail.data;
	}

	/**
	 * Return the list to an empty state.
	 * This should generally be a constant time operation.
	 */
	public void makeEmpty() {
		tail = null;
		head = null;
		currentSize = 0;
	}
		
		
	/** 
	 * return true if list is empty, else return false
	 * @return status of list (empty or not)
	 */
	public boolean isEmpty() {
		if (currentSize==0)
			return true;
		return false;
	}

	/**
	 * Test whether the list is full.
	 * @return true if the list is full, otherwise false
	 */
	public boolean isFull() {
		return false;		//linked list is never full
	}

	/**
	 * Returns the number of Objects in the list.
	 * @return the number of Objects currently in the list.
	 */
	public int size() {
		return currentSize;
	}

	/**
	 * Test whether the list contains an object. This will use the object's
	 * compareTo method to determine whether two objects are the same.
	 * 
	 * @param obj The object to look for in the list
	 * @return true if the object is found in the list, false if it is not found
	 */
	@SuppressWarnings("unchecked")
	public boolean contains(E obj) {
		Node<E> tmp = head;
		
		while (tmp!=null) {
			if (((Comparable<E>)tmp.data).compareTo(obj)==0)
				return true;
			else
				tmp = tmp.next;
		}
		return false;
	}

	/**
	 * Reverse the order of the list.
	 * This will exactly reverse the order of the list, so the first element is 
	 * last, and vice-versa.
	 */
	@SuppressWarnings("unchecked")
	public void reverse() {
		Node<E> temp = head;
		
		E[] container;
		container = (E[]) new Object[currentSize];
		
		for (int x = 0; x < currentSize; x++) {
			container[x] = temp.data;
			temp = temp.next;
		}
		
		temp = head;
		
		for (int i = currentSize-1; i >= 0; i--) {
			temp.data = container[i];
			temp = temp.next;
		}		
	}

	/**
	 * Returns an Iterator of the values in the list, presented in
	 * the same order as the list.
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<E> iterator() {
		return new IteratorHelper();
	}
}

