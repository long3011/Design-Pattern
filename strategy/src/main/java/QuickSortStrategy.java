public class QuickSortStrategy implements SortStrategy {

    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low,          pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

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

