package xiuqin.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/spiral-matrix-ii/
 * <p>
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * <p>
 * For example,
 * Given n = 3,
 * <p>
 * You should return the following matrix:
 * <p>
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 */
public class SpiralMatrixII {
  public List<List<Integer>> generateMatrix(int n) {
    return generateMatrix(n, n);
  }

  private List<List<Integer>> generateMatrix(int n, int m) {
    List<List<Integer>> matrix = new ArrayList<>();
    if (n <= 0) {
      return matrix;
    }

    for (int i = 0; i < n; i++) {
      List<Integer> v = new ArrayList<>();
      for (int j = 0; j < m; j++) {
        v.add(0);
      }

      matrix.add(v);
    }

    int row = n, col = m;
    int r, c;
    int cnt = 1;
    for (r = 0, c = 0; r < (row + 1) / 2 && c < (col + 1) / 2; r++, c++) {
      //top
      for (int i = c; i < col - c; i++) {
        matrix.get(r).set(i, cnt++);
      }

      //right
      for (int i = r + 1; i < row - r; i++) {
        matrix.get(i).set(col - c - 1, cnt++);
      }

      //bottom
      for (int i = col - c - 2; row - r - 1 > r && i >= c; i--) {
        matrix.get(row - r - 1).set(i, cnt++);
      }

      //left
      for (int i = row - r - 2; col - c - 1 > c && i > r; i--) {
        matrix.get(i).set(c, cnt++);
      }
    }

    return matrix;
  }

  public static void main(String[] args) {
    SpiralMatrixII obj = new SpiralMatrixII();

    System.out.println(obj.generateMatrix(3));
    System.out.println(obj.generateMatrix(4));
  }

}
