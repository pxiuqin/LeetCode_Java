package xiuqin.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/
 * 39. 组合总和
 * 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * <p>
 * candidates中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * 所有数字（包括target）都是正整数。
 * 解集不能包含重复的组合。
 * <p>
 * 示例1：
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 * <p>
 * 示例2：
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 * <p>
 * 提示：
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 */
public class CombinationSum {
  public void combinationSumHelper(List<Integer> candidates, int start, int target, List<Integer> solution, List<List<Integer>> result) {
    if (target < 0) {
      return;
    }

    if (target == 0) {
      result.add(new ArrayList<>(solution));
      return;
    }

    for (int i = start; i < candidates.size(); i++) {
      //skip duplicates
      if (i > start && candidates.get(i) == candidates.get(i - 1)) {
        continue;
      }

      int cand = candidates.get(i);
      solution.add(cand);
      combinationSumHelper(candidates, i, target - cand, solution, result);
      solution.remove(solution.size() - 1);
    }
  }

  List<List<Integer>> combinationSum(List<Integer> candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    if (candidates.size() <= 0) {
      return result;
    }
    candidates.sort(Comparator.naturalOrder());

    combinationSumHelper(candidates, 0, target, new ArrayList<>(), result);

    return result;
  }

  public static void main(String[] args) {
    CombinationSum obj = new CombinationSum();

    System.out.println(obj.combinationSum(Arrays.asList(2, 3, 6, 7), 7));
    System.out.println(obj.combinationSum(Arrays.asList(2, 3, 5), 8));
  }
}
