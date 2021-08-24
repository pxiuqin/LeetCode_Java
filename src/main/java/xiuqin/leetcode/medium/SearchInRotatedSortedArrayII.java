package xiuqin.leetcode.medium;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * 81. 搜索旋转排序数组 II
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 * <p>
 * 示例1：
 * 输入：nums = [2,5,6,0,0,1,2], target = 0
 * 输出：true
 * <p>
 * 示例2：
 * 输入：nums = [2,5,6,0,0,1,2], target = 3
 * 输出：false
 * <p>
 * 提示：
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 * <p>
 * 进阶：
 * 这是 搜索旋转排序数组的延伸题目，本题中的nums 可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 * <p>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 * <p>
 * You are given a target value to search. If found in the array return true, otherwise return false.
 * <p>
 * Example 1:
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * <p>
 * Example 2:
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 * <p>
 * 这道是之前那道 Search in Rotated Sorted Array 的延伸，现在数组中允许出现重复数字，
 * 这个也会影响我们选择哪半边继续搜索，由于之前那道题不存在相同值，
 * 我们在比较中间值和最右值时就完全符合之前所说的规律：如果中间的数小于最右边的数，则右半段是有序的，若中间数大于最右边数，则左半段是有序的。
 * 而如果可以有重复值，就会出现来面两种情况，[3 1 1] 和 [1 1 3 1]，对于这两种情况中间值等于最右值时，目标值3既可以在左边又可以在右边，那怎么办么，
 * 对于这种情况其实处理非常简单，只要把最右值向左一位即可继续循环，如果还相同则继续移，直到移到不同值为止，然后其他部分还采用 Search in Rotated Sorted Array 中的方法
 */
public class SearchInRotatedSortedArrayII {
  public boolean search(int A[], int key) {
    int n = A.length;
    if (n <= 0) {
      return false;
    }

    if (n == 1) {
      return A[0] == key ? true : false;
    }

    int low = 0, high = n - 1;
    while (low <= high) {
      if (A[low] < A[high] && (key < A[low] || key > A[high])) {
        return false;
      }

      //if dupilicates, remove the duplication
      while (low < high && A[low] == A[high]) {
        low++;
      }

      int mid = low + (high - low) / 2;
      if (A[mid] == key) {
        return true;
      }

      //the target in non-rotated array
      if (A[low] < A[mid] && key >= A[low] && key < A[mid]) {
        high = mid - 1;
        continue;
      }

      //the target in non-rotated array
      if (A[mid] < A[high] && key > A[mid] && key <= A[high]) {
        low = mid + 1;
        continue;
      }

      //the target in rotated array
      if (A[low] > A[mid]) {
        high = mid - 1;
        continue;
      }

      //the target in rotated array
      if (A[mid] > A[high]) {
        low = mid + 1;
        continue;
      }

      //reach here means nothing found.
      low++;
    }

    return false;
  }

  public static void main(String[] args) {
    SearchInRotatedSortedArrayII obj = new SearchInRotatedSortedArrayII();

    System.out.println(obj.search(new int[]{3, 3, 3, 4, 5, 6, 2, 3}, 2));
    System.out.println(obj.search(new int[]{3, 3, 3, 4, 5, 6, 3, 3}, 4));
    System.out.println(obj.search(new int[]{3, 3, 3, 3, 1, 3}, 1));
    System.out.println(obj.search(new int[]{3, 3, 3, 2, 2, 1, 3, 3}, 1));
    System.out.println(obj.search(new int[]{3, 3, 3, 2, 2, 1, 4, 3}, 4));
    System.out.println(obj.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
    System.out.println(obj.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 4));
  }
}
