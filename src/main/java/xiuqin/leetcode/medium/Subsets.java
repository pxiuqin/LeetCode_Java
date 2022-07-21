package xiuqin.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets/
 * 78. 子集
 * 给你一个整数数组nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * <p>
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 */
public class Subsets {
  /**
   * Leetcode version
   *
   * @param nums
   * @return
   */
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();

    if (nums == null || nums.length == 0) {
      return result;
    }

    ArrayList<Integer> solution = new ArrayList<>();
    Arrays.sort(nums);
    addSubset(result, solution, nums, 0);

    return result;
  }

  private void addSubset(List<List<Integer>> result, ArrayList<Integer> solution, int[] nums, int pos) {
    result.add(new ArrayList<>(solution));  //add empty set ...

    for (int i = pos; i < nums.length; i++) {
      solution.add(nums[i]);
      addSubset(result, solution, nums, i + 1);
      solution.remove(solution.size() - 1);
    }
  }

  /**
   * Lintcode version
   *
   * @param s: A set of numbers.
   * @return: A list of lists. All valid subsets.
   */
  public ArrayList<ArrayList<Integer>> subsets2(ArrayList<Integer> s) {
    // write your code here
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    if (s == null || s.size() == 0) {
      return result;
    }

    ArrayList<Integer> list = new ArrayList<>();
    Collections.sort(s);
    addSubset(s, result, list, 0);

    return result;
  }

  private void addSubset(
    ArrayList<Integer> s,
    ArrayList<ArrayList<Integer>> result,
    ArrayList<Integer> list,
    int pos) {

    result.add(new ArrayList<>(list));

    for (int i = pos; i < s.size(); i++) {
      list.add(s.get(i));
      addSubset(s, result, list, i + 1);
      list.remove(list.size() - 1);
    }
  }

  public static void main(String[] args) {
    Subsets obj = new Subsets();

    System.out.println(obj.subsets(new int[]{1, 2, 3}));
    System.out.println(obj.subsets2(new ArrayList<>(Arrays.asList(1, 2, 3))));
  }
}
