import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import org.junit.Test;

public class MultithreadedSortTester {
    @Test
    public void testMultiQuickSort() {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        Integer[] array = new Integer[1000];
        for(int i = 0; i < 1000; i++)
            array[i] = new Random().nextInt(1000);

        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String[] chars = new String[1000];
        for(int i = 0; i < 1000; i++)
            chars[i] = "" + alphabet.charAt(new Random().nextInt(alphabet.length()));

        pool.invoke(new MultithreadedQuickSort(0, 999, array));
        pool.invoke(new MultithreadedQuickSort(0, 999, chars));

        for(int i = 1; i < 1000; i++)
            assert(array[i-1] <= array[i]);
        for(int i = 1; i < 1000; i++)
            assert(chars[i-1].compareTo(chars[i]) <= 0);
    }

    @Test
    public void testMultithreadedMergeSort() {
        ForkJoinPool pool = ForkJoinPool.commonPool();

        Integer[] array = new Integer[1000];
        for(int i = 0; i < 1000; i++)
            array[i] = new Random().nextInt(1000);

        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String[] chars = new String[1000];
        for(int i = 0; i < 1000; i++)
            chars[i] = "" + alphabet.charAt(new Random().nextInt(alphabet.length()));

        pool.invoke(new MultithreadedMergeSort(array, 0, 999));
        pool.invoke(new MultithreadedMergeSort(chars, 0, 999));

        for(int i = 1; i < 1000; i++)
            assert(array[i-1] <= array[i]);
        for(int i = 1; i < 1000; i++)
            assert(chars[i-1].compareTo(chars[i]) <= 0);
    }
    public static void main(String[] args) {
        int n = 7;
        Integer[] arr = { 54, 64, 95, 82, 12, 32, 63 };
        String[] strings = { "apple", "cart", "door", "elephant", "banana", "fish", "giant"};
        Integer[] arr2 = new Integer[1000];
        for(int i = 0; i < 1000; i++)
            arr2[i] = new Random().nextInt(1000);
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String[] chars = new String[1000];
        for(int i = 0; i < 1000; i++)
            chars[i] = "" + alphabet.charAt(new Random().nextInt(alphabet.length()));

        // Forkjoin ThreadPool to keep
        // thread creation as per resources
        ForkJoinPool pool = ForkJoinPool.commonPool();

        // Start the first thread in fork
        // join pool for range 0, n-1
        pool.invoke(new MultithreadedQuickSort(0, n - 1, arr));
        pool.invoke(new MultithreadedQuickSort(0, n-1, strings));
        pool.invoke(new MultithreadedQuickSort(0, arr2.length - 1, arr2));
        pool.invoke(new MultithreadedQuickSort(0, chars.length - 1, chars));
        // Print sorted elements
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
        for (int i = 0; i < n; i++)
            System.out.print(strings[i] + " ");
        System.out.println();
        for (Integer integer : arr2) System.out.print(integer + " ");
        System.out.println();
        for(String str : chars) System.out.print(str + " ");

        pool.invoke(new MultithreadedMergeSort(arr, 0, n - 1));
        pool.invoke(new MultithreadedMergeSort(strings,0, n-1));
        pool.invoke(new MultithreadedMergeSort(arr2, 0, arr2.length - 1));
        pool.invoke(new MultithreadedMergeSort(chars, 0, chars.length - 1));
        // Print sorted elements
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
        for (int i = 0; i < n; i++)
            System.out.print(strings[i] + " ");
        System.out.println();
        for (Integer integer : arr2) System.out.print(integer + " ");
        System.out.println();
        for(String str : chars) System.out.print(str + " ");
    }
}
