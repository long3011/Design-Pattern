package iterator;

import java.util.Iterator;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        Sequence sequence = new FibonacciSequence(10);
        Iterator<Integer> iterator = sequence.iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next());
            if (iterator.hasNext()) {
                System.out.print(" ");
            }
        }
    }
}

