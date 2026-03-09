/**
 * Concrete Strategy: Merge Sort
 *
 * Merge Sort is a divide-and-conquer algorithm that recursively splits the array
 * in half, sorts each half, and then merges the two sorted halves back together.
 * It guarantees O(n log n) time complexity in all cases (best, average, and worst),
 * but requires O(n) extra space for the temporary arrays used during merging.
 *
 * Reference: https://www.geeksforgeeks.org/merge-sort/
 * The core merge/mergeSort logic is adapted from the GeeksForGeeks Java implementation
 * and integrated into the Strategy pattern.
 */
public class MergeSortStrategy implements SortStrategy {

    @Override
    public void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    /**
     * Recursively divides the array and sorts each half.
     */
    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    /**
     * Merges two sorted sub-arrays array[left..mid] and array[mid+1..right].
     */
    private void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Temporary arrays for the two halves
        int[] leftArr  = new int[n1];
        int[] rightArr = new int[n2];

        System.arraycopy(array, left,       leftArr,  0, n1);
        System.arraycopy(array, mid + 1,    rightArr, 0, n2);

        int i = 0, j = 0, k = left;

        // Merge the temp arrays back into array[left..right]
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                array[k++] = leftArr[i++];
            } else {
                array[k++] = rightArr[j++];
            }
        }

        // Copy remaining elements of leftArr, if any
        while (i < n1) {
            array[k++] = leftArr[i++];
        }

        // Copy remaining elements of rightArr, if any
        while (j < n2) {
            array[k++] = rightArr[j++];
        }
    }

    @Override
    public String getName() {
        return "Merge Sort";
    }
}

