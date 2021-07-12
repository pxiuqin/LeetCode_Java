package xiuqin.leetcode.easy;

/**
 * https://oj.leetcode.com/problems/search-insert-position/
 * 35. 搜索插入位置
 * <p>
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * <p>
 * 示例 1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * <p>
 * 示例 3:
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * <p>
 * 示例 4:
 * 输入: [1,3,5,6], 0
 * 输出: 0
 */
public class SearchInsertPosition {
  public int searchInsert(int[] nums, int target) {
    int i = searchInsert2(nums, target);
    int j = searchInsert3(nums, target);
    if (i == j) {
      return i;
    }

    return -1;
  }

  // Find the first position >= target
  public int searchInsert2(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int start = 0;
    int end = nums.length - 1;
    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] < target) {
        start = mid;
      } else {
        end = mid;
      }
    }

    if (target <= nums[start]) {
      return start;
    }
    if (target <= nums[end]) {
      return end;
    }

    return end + 1;
  }

  // Find the last position < target, return + 1
  // 1,2,3,5,6
  public int searchInsert3(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    if (target < nums[0]) {
      return 0;
    }

    int start = 0;
    int end = nums.length - 1;
    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (target > nums[mid]) {
        start = mid;
      } else {
        end = mid;
      }
    }

    if (target == nums[end]) {
      return end;
    } else if (target > nums[end]) {
      return end + 1;
    } else if (target == start) {
      return start;
    } else {
      return start + 1;
    }

  }

  public static void main(String[] args) {
    SearchInsertPosition obj = new SearchInsertPosition();

    int[] test = new int[]{1, 3, 5, 6};
    System.out.println(obj.searchInsert(test, 5));
    System.out.println(obj.searchInsert2(test, 5));
    System.out.println(obj.searchInsert3(test, 5));

    test = new int[]{1, 3, 5, 6};
    System.out.println(obj.searchInsert(test, 2));
    System.out.println(obj.searchInsert2(test, 2));
    System.out.println(obj.searchInsert3(test, 2));


    test = new int[]{1, 3, 5, 6};
    System.out.println(obj.searchInsert(test, 7));
    System.out.println(obj.searchInsert2(test, 7));
    System.out.println(obj.searchInsert3(test, 7));

    test = new int[]{1, 3, 5, 6};
    System.out.println(obj.searchInsert(test, 0));
    System.out.println(obj.searchInsert2(test, 0));
    System.out.println(obj.searchInsert3(test, 0));
  }
}
