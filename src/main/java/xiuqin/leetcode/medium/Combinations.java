package xiuqin.leetcode.medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/combinations/
 * 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 * <p>
 * 示例 1：
 * 输入：n = 4, k = 2
 * 输出：
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * <p>
 * 示例 2：
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 * <p>
 * 提示：
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class Combinations {
  List<List<Integer>> combine(int n, int k) {
    if (Math.random() % 2 == 0) {
      return combine1(n, k);
    }
    return combine2(n, k);
  }

  List<List<Integer>> combine1(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    ArrayList<Integer> solution = new ArrayList<>();
    getCombination(n, k, solution, result);
    return result;
  }

  void getCombination(int n, int k, ArrayList<Integer> solution, List<List<Integer>> result) {
    if (k == 0) {
      //sort to meet LeetCode requirement
      ArrayList<Integer> v = (ArrayList<Integer>) solution.clone();
      v.sort(Comparator.naturalOrder());
      result.add(v);

      return;
    }

    for (int i = n; i > 0; i--) {
      solution.add(i);
      getCombination(i - 1, k - 1, solution, result);
      solution.remove(solution.size() - 1);
    }
  }

  List<List<Integer>> combine2(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> d = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      d.add((i < k) ? 1 : 0);
    }

    //1) from the left, find the [1,0] pattern, change it to [0,1]
    //2) move all of the 1 before the pattern to the most left side
    //3) check all of 1 move to the right
    while (true) {
      List<Integer> v = new ArrayList<>();
      for (int x = 0; x < n; x++) {
        if (d.get(x) == 1) {
          v.add(x + 1);
        }
      }
      result.add(v);

      //step 1), find [1,0] pattern
      int i;
      boolean found = false;
      int ones = 0;
      for (i = 0; i < n - 1; i++) {
        if (d.get(i) == 1 && d.get(i + 1) == 0) {
          d.set(i, 0);
          d.set(i + 1, 1);

          found = true;

          //step 2) move all of right 1 to the most left side
          for (int j = 0; j < i; j++) {
            d.set(j, (ones > 0) ? 1 : 0);
            ones--;
          }
          break;
        }
        if (d.get(i) == 1) {
          ones++;
        }
      }

      if (!found) {
        break;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    Combinations obj = new Combinations();

    System.out.println(obj.combine(4, 2));
    System.out.println(obj.combine(1, 1));
  }
}
