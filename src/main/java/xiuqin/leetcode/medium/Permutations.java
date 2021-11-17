package xiuqin.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations/
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 * <p>
 * Given a collection of numbers, return all possible permutations.
 * <p>
 * For example,
 * [1,2,3] have the following permutations:
 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 */
public class Permutations {
  /*
{ 1 2 3 }
{ 2 1 3 }
{ 3 2 1 }
{ 1 3 2 }
{ 2 3 1 }
{ 3 1 2 }
*/

  /*
   *    The algorithm - Take each element in array to the first place.
   *
   *    For example:
   *
   *         0) initalization
   *
   *             pos = 0
   *             [1, 2, 3]
   *
   *         1) take each element into the first place,
   *
   *             pos = 1
   *             [1, 2, 3]  ==>  [2, 1, 3] , [3, 1, 2]
   *
   *             then we have total 3 answers
   *             [1, 2, 3],  [2, 1, 3] , [3, 1, 2]
   *
   *         2) take each element into the "first place" -- pos
   *
   *             pos = 2
   *             [1, 2, 3]  ==>  [1, 3, 2]
   *             [2, 1, 3]  ==>  [2, 3, 1]
   *             [3, 1, 2]  ==>  [3, 2, 1]
   *
   *             then we have total 6 answers
   *             [1, 2, 3],  [2, 1, 3] , [3, 1, 2], [1, 3, 2], [2, 3, 1], [3, 2, 1]
   *
   *          3) pos = 3 which greater than length of array, return.
   *
   */
  public List<List<Integer>> permute(List<Integer> num) {

    List<List<Integer>> result = new ArrayList<>();
    result.add(num);   //source is one result

    if (num.size() < 2) {
      return result;
    }

    int pos = 0;
    while (pos < num.size() - 1) {
      int size = result.size();

      for (int i = 0; i < size; i++) {
        //take each number to the first place (from pos to end)
        for (int j = pos + 1; j < result.get(i).size(); j++) {
          List<Integer> v = new ArrayList<>(result.get(i)); //new a list

          //swap(j,pos) for v
          int t = v.get(j);
          v.set(j, v.get(pos));
          v.set(pos, t);

          result.add(v);
        }
      }
      pos++;
    }

    return result;
  }

  public static void main(String[] args) {
    Permutations obj = new Permutations();

    System.out.println(obj.permute(Arrays.asList(1, 2, 3)));
  }
}
