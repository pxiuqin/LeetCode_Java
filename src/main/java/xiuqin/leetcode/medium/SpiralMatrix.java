package xiuqin.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/spiral-matrix/
 * 54. 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * <p>
 * 示例 1：
 * img(doc/img/spiral1.jpg)
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * <p>
 * 示例 2：
 * img(doc/img/spiral.jpg
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class SpiralMatrix {
  List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> v = new ArrayList<>();
    int row = matrix.length;
    if (row <= 0) {
      return v;
    }

    int col = matrix[0].length;
    if (col <= 0) {
      return v;
    }

    int r, c;
    for (r = 0, c = 0; r < (row + 1) / 2 && c < (col + 1) / 2; r++, c++) {
      //top
      for (int i = c; i < col - c; i++) {
        v.add(matrix[r][i]);
      }

      //right
      for (int i = r + 1; i < row - r; i++) {
        v.add(matrix[i][col - c - 1]);
      }

      //bottom
      for (int i = col - c - 2; row - r - 1 > r && i >= c; i--) {
        v.add(matrix[row - r - 1][i]);
      }

      //left
      for (int i = row - r - 2; col - c - 1 > c && i > r; i--) {
        v.add(matrix[i][c]);
      }
    }

    return v;
  }

  public static void main(String[] args) {
    SpiralMatrix obj = new SpiralMatrix();

    int[][] test = new int[][]{
      {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
    };

    System.out.println(obj.spiralOrder(test));

    test = new int[][]{
      {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}
    };
    System.out.println(obj.spiralOrder(test));

    test = new int[][]{
      {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}
    };
    System.out.println(obj.spiralOrder(test));
  }
}
