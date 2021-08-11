package xiuqin.leetcode.medium;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/unique-paths-ii/
 * 63. 不同路径 II
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * img(img/doc/UniquePaths.png)
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * <p>
 * 示例 1：
 * img(img/doc/UniquePathsII_1.jpg)
 * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：2
 * 解释：
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 * <p>
 * 示例 2：
 * img(img/doc/UniquePathsII_2.jpg)
 * 输入：obstacleGrid = [[0,1],[0,0]]
 * 输出：1
 * <p>
 * 提示：
 * m ==obstacleGrid.length
 * n ==obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] 为 0 或 1
 */
public class UniquePathsII {
  /**
   * @param obstacleGrid: A list of lists of integers
   * @return: An integer
   */
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
      return 0;
    }

    if (obstacleGrid[0][0] == 1) {
      return 0;
    }

    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;

    // write your code here
    int[][] matrix = new int[m][n];
    for (int i = 0; i < m; i++) {
      if (obstacleGrid[i][0] != 1) {
        matrix[i][0] = 1;
      } else {
        break;
      }
    }

    for (int i = 0; i < n; i++) {
      if (obstacleGrid[0][i] != 1) {
        matrix[0][i] = 1;
      } else {
        break;
      }
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (obstacleGrid[i][j] == 1) {
          matrix[i][j] = 0;
        } else {
          matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
        }
      }
    }

    return matrix[m - 1][n - 1];
  }

  public static void main(String[] args) {
    UniquePathsII obj = new UniquePathsII();

    int[][] obstacleGrid = new int[][]{
      {0, 0, 0},
      {0, 1, 0},
      {0, 0, 0}
    };
    System.out.println(obj.uniquePathsWithObstacles(obstacleGrid));

    obstacleGrid = new int[][]{{0, 1}, {0, 0}};
    System.out.println(obj.uniquePathsWithObstacles(obstacleGrid));
  }

  public static void print(int[][] source) {
    for (int i = 0; i < source.length; i++) {
      System.out.println(Arrays.asList(source[i]));
    }
  }
}
