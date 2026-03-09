/**
 * Concrete Strategy: Quick Sort
 *
 * Quick Sort is a divide-and-conquer algorithm that selects a "pivot" element and
 * partitions the array into two sub-arrays: elements less than the pivot and elements
 * greater than the pivot. Each sub-array is then sorted recursively.
 * Average time complexity: O(n log n). Worst case (already sorted / bad pivot): O(n²).
 * Sorting is done in-place, requiring only O(log n) stack space for recursion.
 *
 * This implementation uses the last element as the pivot and a Lomuto partition scheme.
 *
 * Reference: https://www.geeksforgeeks.org/quick-sort/
 * The core quickSort/partition logic is adapted from the GeeksForGeeks Java implementation
 * and integrated into the Strategy pattern.
 */
public class QuickSortStrategy implements SortStrategy {

    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * Recursively sorts sub-arrays using the partition index.
     */
    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low,          pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    /**
     * Lomuto partition: places the pivot at its correct sorted position
     * and ensures all smaller elements are to its left.
     *
     * @return the final index of the pivot element
     */
    private int partition(int[] array, int low, int high) {
        int pivot = array[high];    // choose the last element as pivot
        int i = low - 1;            // index of the smaller element

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                // swap array[i] and array[j]
                int temp  = array[i];
                array[i]  = array[j];
                array[j]  = temp;
            }
        }

        // Place the pivot in its correct position
        int temp      = array[i + 1];
        array[i + 1]  = array[high];
        array[high]   = temp;

        return i + 1;
    }

    @Override
    public String getName() {
        return "Quick Sort";
    }
}

