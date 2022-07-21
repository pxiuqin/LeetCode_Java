package xiuqin.common.sort;

/**
 * 冒泡排序
 * 和插入排序比较：因为冒泡排序每次迭代都是和无序数据比较，所以和插入排序比较，元素比较次数会更多
 */
public class BubbleSort {

    public static void sort1(Integer[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    SortHelper.swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void sort2(Integer[] arr) {
        int n = arr.length;

        // 注意索引是length-1
        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    SortHelper.swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void main(String[] args) {

        int N = 200;
        Integer[] arr = SortHelper.generateRandomArray(N, 0, 100);
        BubbleSort.sort1(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }

        System.out.println();

        BubbleSort.sort2(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
    }
}
