package xiuqin.leetcode.medium;

/**
 * https://leetcode.com/problems/maximum-subarray/
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * <p>
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 */
public class MaximumSubarray {
  public int maxSubArray(int[] nums) {
    int max = nums[0];
    int index = 0;

    for (int num : nums) {
      //not start negate number
      if (index < 0) {
        index = 0;
      }

      index += num;
      max = Math.max(max, index);
    }

    return max;
  }

  public static void main(String[] args) {
    MaximumSubarray obj = new MaximumSubarray();

    int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
    System.out.println(obj.maxSubArray(nums));
  }
}