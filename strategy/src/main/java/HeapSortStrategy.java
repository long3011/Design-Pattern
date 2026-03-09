
public class HeapSortStrategy implements SortStrategy {

    @Override
    public void sort(int[] array) {
        int n = array.length;

        // Phase 1: Build a max-heap (rearrange the array)
        // Start from the last non-leaf node and heapify each node up to the root
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Phase 2: Extract elements from the heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move the current root (maximum) to the end
            int temp   = array[0];
            array[0]   = array[i];
            array[i]   = temp;

            // Restore the heap property on the reduced heap
            heapify(array, i, 0);
        }
    }

    /**
     * Ensures the sub-tree rooted at index {@code i} satisfies the max-heap property.
     *
     * @param array the heap array
     * @param n     the size of the heap (portion of array considered)
     * @param i     the root index of the sub-tree to heapify
     */
    private void heapify(int[] array, int n, int i) {
        int largest = i;        // Assume the root is the largest
        int left    = 2 * i + 1;
        int right   = 2 * i + 2;

        // If left child is larger than root
        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        // If right child is larger than the current largest
        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        // If the largest is not the root, swap and continue heapifying
        if (largest != i) {
            int temp       = array[i];
            array[i]       = array[largest];
            array[largest] = temp;

            heapify(array, n, largest);
        }
    }

    @Override
    public String getName() {
        return "Heap Sort";
    }
}

