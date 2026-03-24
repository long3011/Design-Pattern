public class Sorter {

    private SortStrategy strategy;

    public Sorter(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public String getStrategyName() {
        return strategy.getName();
    }

    public long sortAndMeasure(int[] original) {
        // Work on a fresh copy so each strategy receives the same unsorted input
        int[] copy = original.clone();

        long start   = System.nanoTime();
        strategy.sort(copy);
        long elapsed = System.nanoTime() - start;

        return elapsed;
    }
}

