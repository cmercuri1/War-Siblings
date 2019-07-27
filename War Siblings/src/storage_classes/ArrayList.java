package storage_classes;

import java.util.Collection;

public class ArrayList<E> extends java.util.ArrayList<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArrayList() {
	}

	public ArrayList(int initialCapacity) {
		super(initialCapacity);
	}

	public ArrayList(Collection<? extends E> c) {
		super(c);
	}
	
	public E get(E i) {
		return this.get(this.indexOf(i));
	}

}
