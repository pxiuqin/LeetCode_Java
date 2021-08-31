package xiuqin.leetcode.easy;

/**
 * https://leetcode.com/problems/merge-sorted-array/
 * 88. 合并两个有序数组
 * 给你两个按 非递减顺序 排列的整数数组nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 * <p>
 * 示例 2：
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * 解释：需要合并 [1] 和 [] 。
 * 合并结果是 [1] 。
 * <p>
 * 示例 3：
 * 输入：nums1 = [0], m = 0, nums2 = [1], n = 1
 * 输出：[1]
 * 解释：需要合并的数组是 [] 和 [1] 。
 * 合并结果是 [1] 。
 * 注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
 * <p>
 * 提示：
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -10^9 <= nums1[i], nums2[j] <= 10^9
 * <p>
 * 进阶：你可以设计实现一个时间复杂度为 O(m + n) 的算法解决此问题吗？
 */
public class MergeSortedArray {
  public void merge(int A[], int m, int B[], int n) {
    int ia = m - 1;
    int ib = n - 1;
    for (int i = m + n - 1; i >= 0; i--) {
      if (ia >= 0 && ib < 0) {
        break;  //because of A is result, no need to deal with ia > 0
      }

      if (ia < 0 && ib >= 0) {
        A[i] = B[ib--];   //add the reaming arrays
        continue;
      }

      if (ia >= 0 && ib >= 0) {
        if (A[ia] > B[ib]) {
          A[i] = A[ia--];
        } else {
          A[i] = B[ib--];
        }
      }
    }
  }

  public static void main(String[] args) {
    MergeSortedArray obj = new MergeSortedArray();

    int[] a = new int[]{2, 4, 6, 8, 10, 0, 0, 0};
    int[] b = new int[]{1, 3, 5};
    obj.merge(a, 5, b, 3);
    print(a);
  }

  public static void print(int[] result) {
    for (int i = 0; i < result.length; i++) {
      System.out.println(result[i]);
    }
  }
}
