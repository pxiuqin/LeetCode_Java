package xiuqin.leetcode.medium;

/**
 * https://leetcode.com/problems/unique-paths/
 * 62. 不同路径
 * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 * <p>
 * 示例 1：
 * doc/img/0-100/robot_maze.png
 * 输入：m = 3, n = 7
 * 输出：28
 * <p>
 * 示例 2：
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 * <p>
 * 示例 3：
 * 输入：m = 7, n = 3
 * 输出：28
 * <p>
 * 示例 4：
 * 输入：m = 3, n = 3
 * 输出：6
 * <p>
 * 提示：
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 109
 *
 * <p>
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * <p>
 * The robot can only move either down or right at any point in time. The robot is trying to reach
 * the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * <p>
 * <p>
 * start
 * +---------+----+----+----+----+----+
 * |----|    |    |    |    |    |    |
 * |----|    |    |    |    |    |    |
 * +----------------------------------+
 * |    |    |    |    |    |    |    |
 * |    |    |    |    |    |    |    |
 * +----------------------------------+
 * |    |    |    |    |    |    |----|
 * |    |    |    |    |    |    |----|
 * +----+----+----+----+----+---------+
 * finish
 * <p>
 * <p>
 * How many possible unique paths are there?
 * <p>
 * Above is a 3 x 7 grid. How many possible unique paths are there?
 * <p>
 * Note: m and n will be at most 100.
 * <p>
 * Example 1:
 * img(doc/img/UniquePaths.png)
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * <p>
 * Right -> Right -> Down
 * Right -> Down -> Right
 * Down -> Right -> Right
 * Example 2:
 * <p>
 * Input: m = 7, n = 3
 * Output: 28
 * 机器人位于m x n网格的左上角（在下图中标记为“开始”）。
 * <p>
 * 机器人只能在任何时间点向下或向右移动。 机器人正试图到达网格的右下角（在下图中标记为“完成”）。
 * <p>
 * 题目思路
 * <p>
 * 对于这道题，有点类似于典型的路径规划问题，只不过机器人的行驶方向有着一定的限制：每次只能朝右走一格或者朝下走一格。
 * <p>
 * 因此，在经过分析之后我们可以看到，对于这个行驶网格，最上面一层和最左边一列，它们的行走方式都只有一种，也就是向右到达该方格或者向下到达该方格。
 * <p>
 * 而对于除了这些方格之外的其他方格，我们可以得知，到达该方格的方式，为：
 * 从它上方的方格到达该方格的总方式
 * 加上
 * 从它左侧的方格到达该方格的总方式
 * <p>
 * 因此，这个问题也就变成了一道比较经典的动态规划问题了。
 */
public class UniquePaths {
  /**
   * @param n, m: positive integer (1 <= n ,m <= 100)
   * @return an integer
   */
  public int uniquePaths(int m, int n) {
    // write your code here
    int[][] matrix = new int[m][n];

    for (int i = 0; i < m; i++) {
      matrix[i][0] = 1;
    }

    for (int i = 0; i < n; i++) {
      matrix[0][i] = 1;
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
      }
    }

    return matrix[m - 1][n - 1];
  }

  public static void main(String[] args) {
    UniquePaths obj = new UniquePaths();

    System.out.println(obj.uniquePaths(3, 2));
    System.out.println(obj.uniquePaths(3, 7));
  }
}
