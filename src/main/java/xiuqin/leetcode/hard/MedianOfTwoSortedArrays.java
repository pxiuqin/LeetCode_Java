package xiuqin.leetcode.hard;

import java.util.Arrays;
import java.util.List;

/**
 * https://oj.leetcode.com/problems/median-of-two-sorted-arrays/
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * <p>
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * <p>
 * 示例 3：
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * <p>
 * 示例 4：
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * <p>
 * 示例 5：
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *  
 * <p>
 * 提示：
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 *  
 * <p>
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * Cite:Hao Chen
 */
public class MedianOfTwoSortedArrays {

  public double findMedianSortedArrays(List<Integer> A, List<Integer> B) {
    //checking the edge cases
    if (A.isEmpty() && B.isEmpty()) {
      return 0.0;
    }

    int m = A.size();
    int n = B.size();

    //if the length of array is odd, return the middle one
    //if the length of array is even, return the average of the middle two numbers
    if (A.isEmpty()) {
      return n % 2 == 1 ? B.get(n / 2) : (B.get(n / 2 - 1) + B.get(n / 2)) / 2.0;
    }
    if (B.isEmpty()) {
      return m % 2 == 1 ? A.get(n / 2) : (A.get(n / 2 - 1) + B.get(n / 2)) / 2.0;
    }

    //let the longer array be A, and the shoter array be B
    if (m > n) {
      return findMedianSortedArrayHelper(A, B, 0, m - 1, 0, n - 1);
    } else {
      return findMedianSortedArrayHelper(B, A, 0, n - 1, 0, m - 1);
    }
  }

  private double findMedianSortedArrayHelper(List<Integer> A, List<Integer> B, int lowA, int highA, int lowB, int highB) {
    int m = A.size();
    int n = B.size();

    // Take the A[middle], search its position in B array
    int mid = lowA + (highA - lowA) / 2;
    int pos = binarySearch(B, lowB, highB, A.get(mid));
    int num = mid + pos;

    // If the A[middle] in B is B's middle place, then we can have the result
    if (num == (m + n) / 2) {
      // If two arrays total length is odd, just simply return the A[mid]
      // Why not return the B[pos] instead ?
      //   suppose A={ 1,3,5 } B={ 2,4 }, then mid=1, pos=1
      //   suppose A={ 3,5 }   B={1,2,4}, then mid=0, pos=2
      //   suppose A={ 1,3,4,5 }   B={2}, then mid=1, pos=1
      // You can see, the `pos` is the place A[mid] can be inserted, so return A[mid]
      if ((m + n) % 2 == 1) {
        return A.get(mid);
      } else {

        // If tow arrays total length is even, then we have to find the next one.
        int next = 0;

        // If both `mid` and `pos` are not the first postion.
        // Then, find max(A[mid-1], B[pos-1]).
        // Because the `mid` is the second middle number, we need to find the first middle number
        // Be careful about the edge case
        if (mid > 0 && pos > 0) {
          next = A.get(mid - 1) > B.get(pos - 1) ? A.get(mid - 1) : B.get(pos - 1);
        } else if (pos > 0) {
          next = B.get(pos - 1);
        } else if (mid > 0) {
          next = A.get(mid - 1);
        }

        return (A.get(mid) + next) / 2.0;
      }
    }

    // if A[mid] is in the left middle place of the whole two arrays
    //
    //         A(len=16)        B(len=10)
    //     [................] [...........]
    //            ^             ^
    //           mid=7         pos=1
    //
    //  move the `low` pointer to the "middle" position, do next iteration.
    if (num < (m + n) / 2) {
      lowA = mid + 1;
      lowB = pos;
      if (highA - lowA > highB - lowB) {
        return findMedianSortedArrayHelper(A, B, lowA, highA, lowB, highB);
      }

      return findMedianSortedArrayHelper(B, A, lowB, highB, lowA, highA);
    }

    // if A[mid] is in the right middle place of the whole two arrays
    if (num > (m + n) / 2) {
      highA = mid - 1;
      highB = pos - 1;
      if (highA - lowA > highB - lowB) {
        return findMedianSortedArrayHelper(A, B, lowA, highA, lowB, highB);
      }

      return findMedianSortedArrayHelper(B, A, lowB, highB, lowA, highA);
    }

    return 0;
  }

  // Classical binary search algorithm, but slightly different
  // if cannot find the key, return the position where can insert the key
  private int binarySearch(List<Integer> A, int low, int high, int key) {
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (key == A.get(mid)) {
        return mid;
      }

      if (key > A.get(mid)) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return low;
  }

  public static void main(String[] args) {
    MedianOfTwoSortedArrays obj = new MedianOfTwoSortedArrays();
    List<Integer> nums1 = Arrays.asList(3);
    List<Integer> nums2 = Arrays.asList(2);

    System.out.println(obj.findMedianSortedArrays(nums1, nums2));

    nums1 = Arrays.asList(1, 3);
    nums2 = Arrays.asList(2);

    System.out.println(obj.findMedianSortedArrays(nums1, nums2));

    nums1 = Arrays.asList(1, 2);
    nums2 = Arrays.asList(3, 4);

    System.out.println(obj.findMedianSortedArrays(nums1, nums2));

    nums1 = Arrays.asList(1, 2, 3);
    nums2 = Arrays.asList(3, 4);

    System.out.println(obj.findMedianSortedArrays(nums1, nums2));

    nums1 = Arrays.asList(0, 0);
    nums2 = Arrays.asList(0, 0);

    System.out.println(obj.findMedianSortedArrays(nums1, nums2));

    nums1 = Arrays.asList(1);
    nums2 = Arrays.asList();

    System.out.println(obj.findMedianSortedArrays(nums1, nums2));

    nums1 = Arrays.asList();
    nums2 = Arrays.asList(2);

    System.out.println(obj.findMedianSortedArrays(nums1, nums2));
  }
}
