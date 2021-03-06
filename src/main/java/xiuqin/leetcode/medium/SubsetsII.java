package xiuqin.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets-ii/
 * <p>
 * Given a collection of integers that might contain duplicates, S, return all possible subsets.
 * <p>
 * Note:
 * <p>
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * <p>
 * For example,
 * If S = [1,2,2], a solution is:
 * <p>
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 */
public class SubsetsII {
  /**
   * Leetcode version
   *
   * @param nums
   * @return
   */
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    if (nums == null || nums.length == 0) {
      return result;
    }

    ArrayList<Integer> list = new ArrayList<>();
    Arrays.sort(nums);
    addSubset(result, list, nums, 0);

    return result;
  }

  private void addSubset(List<List<Integer>> result, ArrayList<Integer> list, int[] nums, int pos) {
    result.add(new ArrayList<>(list));

    for (int i = pos; i < nums.length; i++) {
      // in this level, if current loop is not the start element, check if it's duplicated with the previous element
      if (i != pos && nums[i - 1] == nums[i]) {
        continue;
      }

      list.add(nums[i]);
      addSubset(result, list, nums, i + 1);
      list.remove(list.size() - 1);
    }
  }

  /**
   * Lintcode version
   *
   * @param S: A set of numbers.
   * @return: A list of lists. All valid subsets.
   */
  public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> s) {
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
      if (i != pos && s.get(i - 1) == s.get(i)) {
        continue;
      }
      list.add(s.get(i));
      addSubset(s, result, list, i + 1);
      list.remove(list.size() - 1);
    }
  }

  public static void main(String[] args) {
    SubsetsII obj = new SubsetsII();

    System.out.println(obj.subsetsWithDup(new int[]{1, 2, 2}));
    System.out.println(obj.subsetsWithDup(new ArrayList<>(Arrays.asList(1, 2, 2))));
  }
}
