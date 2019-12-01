package hr.base;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Interfaz que define el contrato para los iteradores que producen valores
 * "int".
 */
public interface IntIterator extends Iterator<Integer>, Iterable<Integer> {

	public static final IntIterator EMPTY_ITERATOR = new EmptyInterator();

	@Override
	public abstract boolean hasNext();

	@Override
	default public IntIterator iterator() {
		return this;
	}

	@Override
	default public Integer next() {
		return nextInt();
	}

	public abstract int nextInt();

	public static class EmptyInterator implements IntIterator {

		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public Integer next() {
			throw new NoSuchElementException();
		}

		@Override
		public int nextInt() {
			throw new NoSuchElementException();
		}
	}
}