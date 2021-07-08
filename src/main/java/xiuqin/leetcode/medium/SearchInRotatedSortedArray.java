package xiuqin.leetcode.medium;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * 33. 搜索旋转排序数组
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * <p>
 * 示例 1：
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * <p>
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * <p>
 * 示例 3：
 * 输入：nums = [1], target = 0
 * 输出：-1
 * <p>
 * 提示：
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 *  
 * 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？
 * <p>
 * 解题思路：
 * <p>
 * 这道题让在旋转数组中搜索一个给定值，若存在返回坐标，若不存在返回-1。我们还是考虑二分搜索法，但是这道题的难点在于我们不知道原数组在哪旋转了，我们还是用题目中给的例子来分析，对于数组[0 1 2 4 5 6 7] 共有下列七种旋转方法：
 * <p>
 * 0　　1　　2　　 |4　　5　　6　　7
 * 7　　0　　1　　 |2　　4　　5　　6
 * 6　　7　　0　　 |1　　2　　4　　5
 * 5　　6　　7　　 |0　　1　　2　　4
 * 4　　5　　6　　7|　　0　　1　　2
 * 2　　4　　5　　6|　　7　　0　　1
 * 1　　2　　4　　5|　　6　　7　　0
 * <p>
 * 二分搜索法的关键在于获得了中间数后，判断下面要搜索左半段还是右半段，
 * 我们观察上面|加粗的数字都是升序的，由此我们可以观察出规律，如果中间的数小于最右边的数，则右半段是有序的，
 * 若中间数大于最右边数，则左半段是有序的，我们只要在有序的半段里用首尾两个数组来判断目标值是否在这一区域内，
 * 这样就可以确定保留哪半边了
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[start] <= nums[mid]) { // in higher part
                if (nums[start] <= target && target <= nums[mid]) { // target within the start and mid.
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (nums[mid] <= target && target <= nums[nums.length - 1]) { // target within the mid and the end
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }

        if (nums[start] == target) {
            return start;
        }

        if (nums[end] == target) {
            return end;
        }

        return -1;
    }

    int search2(int A[], int target) {
        int n = A.length;
        if (n == 0) {
            return -1;
        }

        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] < A[right]) {
                //如果中间的数小于最右边的数，则右半段是有序的
                if (A[mid] < target && A[right] >= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                //若中间数大于最右边数，则左半段是有序的
                if (A[left] <= target && A[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray obj = new SearchInRotatedSortedArray();

        System.out.println(obj.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(obj.search2(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));

        System.out.println(obj.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        System.out.println(obj.search2(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));

        System.out.println(obj.search(new int[]{1}, 0));
        System.out.println(obj.search2(new int[]{1}, 0));
    }
}
