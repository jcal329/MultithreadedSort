import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MultithreadedQuickSort extends RecursiveAction {
    private final Comparable[] arr;
    private final int start, end;


    /**
     * Finding random pivoted and partition
     * array on a pivot.
     * There are many different
     * partitioning algorithms.
     *
     * @param start
     * @param end
     * @param arr
     * @return
     */
    private <T extends Comparable<T>> int partition(int start, int end, T[] arr) {

        int i = start, j = end;

        // Decide random pivot
        int pivot = new Random().nextInt(j - i) + i;

        // Swap the pivot with end
        // element of array;
        T t = arr[j];
        arr[j] = arr[pivot];
        arr[pivot] = t;
        j--;

        // Start partitioning
        while (i <= j) {

            if (arr[i].compareTo(arr[end]) <= 0) {
                i++;
                continue;
            }

            if (arr[j].compareTo(arr[end]) >= 0) {
                j--;
                continue;
            }

            t = arr[j];
            arr[j] = arr[i];
            arr[i] = t;
            j--;
            i++;
        }

        // Swap pivot to its
        // correct position
        t = arr[j + 1];
        arr[j + 1] = arr[end];
        arr[end] = t;
        return j + 1;
    }

    // Function to implement
    // QuickSort method
    public MultithreadedQuickSort(int start, int end, Comparable[] arr) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        // Base case
        if (start >= end)
            return;
        if (this.arr.length < 100) {
            InsertionSort.insertionSort(arr);
        } else {
            // Find partition
            int p = partition(start, end, arr);

            // Divide array
            MultithreadedQuickSort left
                    = new MultithreadedQuickSort(start,
                    p - 1,
                    arr);

            MultithreadedQuickSort right
                    = new MultithreadedQuickSort(p + 1,
                    end,
                    arr);

            // Left and right as separate threads
            left.fork();
            right.fork();

            // Wait until threads complete
            left.join();
            right.join();
        }
    }
}

