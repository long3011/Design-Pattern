package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class FibonacciIterator implements Iterator<Integer> {
    private final Integer limit;
    private int produced;
    private int previous = 0;
    private int current = 1;

    public FibonacciIterator(Integer limit) {
        this.limit = limit;
    }

    @Override
    public boolean hasNext() {
        return limit == null || produced < limit;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more Fibonacci numbers available");
        }

        int value = current;
        int nextValue = previous + current;
        previous = current;
        current = nextValue;
        produced++;
        return value;
    }
}


