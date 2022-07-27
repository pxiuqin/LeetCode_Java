package xiuqin.common.sort;

/**
 * 双路快速排序
 */
public class QuickSort {
    // 随机化的快速排序，选择基点过程
    private static int partition1(Integer[] arr, int l, int r) {
        Integer v = arr[l];

        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(v) < 0) {
                j++;

                if (i == j)
                    continue;

                // 数组元素位置交换
                SortHelper.swap(arr, j, i);
            }
        }

        SortHelper.swap(arr, l, j);

        return j;
    }

    // 改进版，双路快速选择基点
    private static int partition2(Integer[] arr, int l, int r) {
        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        SortHelper.swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);
        Integer v = arr[l];
        // arr[l+1...i) <= v; arr(j...r] >= v
        int i = l + 1, j = r;
        while (true) {
            while (i <= r && arr[i].compareTo(v) < 0)
                i++;
            while (j >= l + 1 && arr[j].compareTo(v) > 0)
                j--;
            if (i > j)
                break;
            SortHelper.swap(arr, i, j);
            i++;
            j--;
        }
        SortHelper.swap(arr, l, j);
        return j;
    }

    // 递归使用快速排序,对arr[l...r]的范围进行排序
    private static void sort(Integer[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition1(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    public static void sort(Integer[] arr) {

        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    // 测试 QuickSort
    public static void main(String[] args) {

        // 双路快速排序算法也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据

        // Quick Sort也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        int N = 100;
        Integer[] arr = SortHelper.generateRandomArray(N, 0, 10);
        sort(arr);
        SortHelper.printArray(arr);
    }
}
