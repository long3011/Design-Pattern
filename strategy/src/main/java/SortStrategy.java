/**
 * Strategy interface for sorting algorithms.
 * Each concrete sorting algorithm implements this interface.
 */
public interface SortStrategy {

    /**
     * Sorts the given array of integers in-place.
     *
     * @param array the array to be sorted
     */
    void sort(int[] array);

    /**
     * Returns a human-readable name for this sorting strategy.
     *
     * @return the name of the sorting algorithm
     */
    String getName();
}

