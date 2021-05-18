import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MultithreadedMergeSort extends RecursiveAction {
    private final Comparable[] array;
    private final Comparable[] helper;
    private final int low;
    private final int high;

    private <T extends Comparable<T>> void merge(T[] a, T[] b, int from, int mid, int to){
        int n = to - from + 1;
        // Merge both halves into a temporary array b

        int i1 = from;
        // Next element to consider in the first range
        int i2 = mid + 1;
        // Next element to consider in the second range
        int j = 0;
        // Next open position in b

        // As long as neither i1 nor i2 past the end, move
        // the smaller element into b
        while (i1 <= mid && i2 <= to) {
            if (a[i1].compareTo(a[i2]) < 0) {
                b[j] = a[i1];
                i1++;
            } else {
                b[j] = a[i2];
                i2++;
            }
            j++;
        }

        // Note that only one of the two while loops
        // below is executed
        // Copy any remaining entries of the first half
        while (i1 <= mid) {
            b[j] = a[i1];
            i1++;
            j++;
        }

        // Copy any remaining entries of the second half
        while (i2 <= to) {
            b[j] = a[i2];
            i2++;
            j++;
        }

        // Copy back from the temporary array
        for (j = 0; j < n; j++) {
            a[from + j] = b[j];
        }
    }

    public MultithreadedMergeSort(final Comparable[] array, final int low, final int high) {
        this.array = array;
        helper = new Comparable[array.length];
        this.low = low;
        this.high = high;
    }

    @Override
    protected void compute() {
        if (low < high) {
            if(array.length < 100)
                InsertionSort.insertionSort(array);
            else {
                int middle = (low + high) / 2;
                MultithreadedMergeSort left = new MultithreadedMergeSort(array, low, middle);
                MultithreadedMergeSort right = new MultithreadedMergeSort(array, middle + 1, high);

                left.fork();
                right.fork();

                left.join();
                right.join();

                merge(array, helper, low, middle, high);
            }
        }
    }

}

