package xiuqin.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 * 1. 两数之和
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 * <p>
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 */

public class TwoSum {

  public int[] twoSum(int[] nums, int target) {
    int[] result = new int[2];

    Map<Integer, Integer> numMap = new HashMap();
    for (int i = 0; i < nums.length; i++) {
      if (numMap.containsKey(target - nums[i])) {
        int index = numMap.get(target - nums[i]);
        result[0] = index;
        result[1] = i;

        break;
      }
      numMap.put(nums[i], i);
    }

    return result;
  }

  public static void main(String[] args) {
    TwoSum twoSum = new TwoSum();

    int[] nums = new int[]{2, 7, 11, 15};
    int target = 9;
    int[] result = twoSum.twoSum(nums, target);

    System.out.println(String.format("{%d,%d}", result[0], result[1]));

    nums = new int[]{3,2,4};
    target = 6;
    result = twoSum.twoSum(nums, target);

    System.out.println(String.format("{%d,%d}", result[0], result[1]));
  }
}
