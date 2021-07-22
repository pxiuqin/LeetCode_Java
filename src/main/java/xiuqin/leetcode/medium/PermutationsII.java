package xiuqin.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/permutations-ii
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */
public class PermutationsII {
  // To deal with the duplication number, we need do those modifications:
  //    1) sort the array [pos..n].
  //    2) skip the same number.
  public List<int[]> permute(int[] num) {
    List<int[]> result = new ArrayList<>();
    result.add(num);

    if (num.length < 2) {
      return result;
    }

    int pos = 0;
    while (pos < num.length - 1) {
      int size = result.size();
      for (int i = 0; i < size; i++) {
        //sort the array, so that the same number will be together
        Arrays.sort(result.get(i), pos, result.get(i).length);

        //take each number to the first
        for (int j = pos + 1; j < result.get(i).length; j++) {
          int[] v = result.get(i).clone();

          //skip the same number
          if (j > 0 && v[j] == v[j - 1]) {
            continue;
          }
          int temp = v[j];
          v[j] = v[pos];
          v[pos] = temp;

          result.add(v);
        }
      }
      pos++;
    }

    return result;
  }

  public static void main(String[] args) {
    PermutationsII obj = new PermutationsII();

    System.out.println(toString(obj.permute(new int[]{1, 1, 2})));
    System.out.println(toString(obj.permute(new int[]{1, 2, 3})));
  }

  public static String toString(List<int[]> source) {
    return source.stream().map(e -> Arrays.stream(e).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString()).collect(Collectors.toList()).toString();
  }
}
