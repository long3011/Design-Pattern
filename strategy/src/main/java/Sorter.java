public class Sorter {

    private SortStrategy strategy;

    /**
     * Creates a Sorter with the specified sorting strategy.
     *
     * @param strategy the initial sorting strategy to use
     */
    public Sorter(SortStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Replaces the current sorting strategy with a new one.
     *
     * @param strategy the new sorting strategy
     */
    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Returns the name of the currently active sorting strategy.
     *
     * @return strategy name
     */
    public String getStrategyName() {
        return strategy.getName();
    }

    /**
     * Sorts a copy of the provided array using the current strategy and returns
     * the time taken (in nanoseconds).
     *
     * A copy is sorted so that the original array can be reused unchanged
     * for the next strategy comparison.
     *
     * @param original the array to sort (not modified)
     * @return elapsed time in nanoseconds
     */
    public long sortAndMeasure(int[] original) {
        // Work on a fresh copy so each strategy receives the same unsorted input
        int[] copy = original.clone();

        long start   = System.nanoTime();
        strategy.sort(copy);
        long elapsed = System.nanoTime() - start;

        return elapsed;
    }
}

