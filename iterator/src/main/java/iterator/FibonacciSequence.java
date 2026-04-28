package iterator;

import java.util.Iterator;

public final class FibonacciSequence implements Sequence {
    private final Integer limit;

    public FibonacciSequence() {
        this.limit = null;
    }

    public FibonacciSequence(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit must be non-negative");
        }
        this.limit = limit;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new FibonacciIterator(limit);
    }
}

