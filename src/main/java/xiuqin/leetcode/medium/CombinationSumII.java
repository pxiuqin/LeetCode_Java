package xiuqin.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-ii/
 * 40. 组合总和 II
 * 给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的每个数字在每个组合中只能使用一次。
 * 注意：解集不能包含重复的组合。

 * 示例1:
 * 输入: candidates =[10,1,2,7,6,1,5], target =8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 * 示例2:
 * 输入: candidates =[2,5,2,1,2], target =5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 * 提示:
 * 1 <=candidates.length <= 100
 * 1 <=candidates[i] <= 50
 * 1 <= target <= 30
 *
 */
public class CombinationSumII {
  void combinationSumHelper(List<Integer> candidates, int start, int target, List<Integer> solution, List<List<Integer>> result) {
    if(target<0){
      return;
    }

    if(target==0){
      result.add(new ArrayList<>(solution));
      return;
    }

    for(int i=start;i<candidates.size();i++){
      //skip duplicates
      int n=candidates.get(i);
      if(i>start && n==candidates.get(i-1)){
        continue;
      }

      solution.add(n);
      combinationSumHelper(candidates,i+1, target-n, solution,result);
      solution.remove(solution.size()-1);
    }
  }

  public List<List<Integer>> combinationSum2(List<Integer> candidates, int target) {
    List<List<Integer>> result=new ArrayList<>();
    if (candidates.size()<=0){
      return result;
    }
    candidates.sort(Comparator.naturalOrder());

    combinationSumHelper(candidates, 0, target, new ArrayList<>(), result);

    return result;
  }

  public static void main(String[] args){
    CombinationSumII obj=new CombinationSumII();

    System.out.println(obj.combinationSum2(Arrays.asList(10,1,2,7,6,1,5), 8));
    System.out.println(obj.combinationSum2(Arrays.asList(2,5,2,1,2), 5));
  }
}
