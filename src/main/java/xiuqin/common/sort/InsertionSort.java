package xiuqin.common.sort;

/**
 * 插入排序
 * 和冒泡排序比较：插入排序因为是和已经排好序的数据比较，所以比较次数会更少
 */
public class InsertionSort {

    public static void sort1(Integer[] arr) {

        int n = arr.length;

        // 默认第一个元素不用比较
        for (int i = 1; i < n; i++) {
            int temp = arr[i];
            int j = i;

            // 寻找从j-1索引的元素比j索引大的元素
            for (; j > 0 && arr[j - 1] < temp; j--) {
                arr[j] = arr[j - 1];
            }

            arr[j] = temp;
        }
    }

    public static void sort2(Integer[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--)
                if (arr[j - 1] > arr[j]) {
                    SortHelper.swap(arr, j, j - 1);
                } else {
                    break;
                }
        }
    }

    public static void main(String[] args) {

        Integer[] arr = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        InsertionSort.sort1(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }

        System.out.println();

        int N = 200;
        arr = SortHelper.generateRandomArray(N, 0, 100);
        InsertionSort.sort2(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
    }
}
