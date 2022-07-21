package xiuqin.common.sort;

/**
 * 选择排序
 */
public class SelectionSort {

    public static void sort(Integer[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            // 寻找[i, n)区间里的最小值的索引
            int minIndex = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }

            // 数据交换不同索引位置数据
            SortHelper.swap(arr, i, minIndex);
        }
    }

    public static void main(String[] args) {

        Integer[] arr = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        SelectionSort.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
    }
}
