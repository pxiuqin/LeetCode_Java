package xiuqin.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/n-queens-ii/
 * 52. N皇后 II
 * n皇后问题研究的是如何将n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 * <p>
 * 示例 1：
 * img(doc/img/queens2.jpg)
 * 输入：n = 4
 * 输出：2
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * <p>
 * 提示：
 * 1 <= n <= 9
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 */
public class NQueensII {
  int totalNQueens(int n) {
    int result = 0;
    List<Integer> solution = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      solution.add(0);
    }

    result = solveNQueensRecursive(n, 0, solution);

    return result;
  }

  // the solution is same as the "N Queens" problem.
  private int solveNQueensRecursive(int n, int currentRow, List<Integer> solution) {
    int count = 0;

    for (int i = 0; i < n; i++) {
      if (isValid(i, currentRow, solution)) {
        if (currentRow + 1 == n) {
          count++;
          continue;
        }
        solution.set(currentRow, i);
        count += solveNQueensRecursive(n, currentRow + 1, solution);
      }
    }

    return count;
  }

  private boolean isValid(int attemptedColumn, int attemptedRow, List<Integer> queenInColumn) {
    for (int i = 0; i < attemptedRow; i++) {
      if (attemptedColumn == queenInColumn.get(i) ||
        //checked 对角线
        Math.abs(attemptedColumn - queenInColumn.get(i)) == Math.abs(attemptedRow - i)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    NQueensII obj = new NQueensII();

    System.out.println(obj.totalNQueens(4));
    System.out.println(obj.totalNQueens(8));
  }
}
