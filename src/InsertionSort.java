public class InsertionSort {
    public static <T extends Comparable<T>> void insertionSort(T[] array) {
        int n = array.length;
        for (int j = 1; j < n; j++) {
            T key = array[j];
            int i = j - 1;
            while ((i > -1) && (array[i].compareTo(key) > 0)) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = key;
        }
    }
}
