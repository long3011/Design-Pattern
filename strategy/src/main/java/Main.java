import java.util.Random;
public class Main {

    // -----------------------------------------------------------------------
    // Dataset sizes
    // -----------------------------------------------------------------------
    private static final int SMALL_SIZE = 30;
    private static final int LARGE_SIZE = 100_000;

    // Value range for random integers
    private static final int VALUE_BOUND = 1_000_000;

    public static void main(String[] args) {
        Random rng = new Random(42);   // fixed seed → reproducible results

        // ----- Generate datasets -----
        int[] smallArray = generateArray(SMALL_SIZE, VALUE_BOUND, rng);
        int[] largeArray = generateArray(LARGE_SIZE, VALUE_BOUND, rng);

        // ----- Define the three strategies -----
        SortStrategy[] strategies = {
            new MergeSortStrategy(),
            new QuickSortStrategy(),
            new HeapSortStrategy()
        };

        // ----- Run benchmarks -----
        System.out.println("=".repeat(65));
        System.out.println("       SORTING ALGORITHM PERFORMANCE COMPARISON");
        System.out.println("=".repeat(65));

        runBenchmark("SMALL dataset (" + SMALL_SIZE + " elements)", smallArray, strategies);
        runBenchmark("LARGE dataset (" + LARGE_SIZE + " elements)", largeArray, strategies);

        System.out.println("=".repeat(65));
    }
    private static void runBenchmark(String label, int[] dataset, SortStrategy[] strategies) {
        System.out.println("\n--- " + label + " ---");
        System.out.printf("%-20s %15s %15s%n", "Algorithm", "Time (ns)", "Time (ms)");
        System.out.println("-".repeat(52));

        Sorter sorter = new Sorter(strategies[0]);   // initial strategy (will be changed)

        for (SortStrategy strategy : strategies) {
            sorter.setStrategy(strategy);
            long nanoseconds = sorter.sortAndMeasure(dataset);
            double milliseconds = nanoseconds / 1_000_000.0;

            System.out.printf("%-20s %15d %15.3f%n",
                    strategy.getName(), nanoseconds, milliseconds);
        }
    }

    private static int[] generateArray(int size, int bound, Random rng) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rng.nextInt(bound);
        }
        return array;
    }
}

