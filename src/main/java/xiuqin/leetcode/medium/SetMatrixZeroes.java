package xiuqin.leetcode.medium;

import java.util.Arrays;
import java.util.Set;

/**
 * https://leetcode.com/problems/set-matrix-zeroes/
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 *
 * click to show follow up.
 *
 * Follow up:
 *
 * Did you use extra space?
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */
public class SetMatrixZeroes {
  public void setZeroes(int[][] matrix) {
    if (Math.random() % 2 == 0) {
      setZeroes1(matrix);
    }
    setZeroes2(matrix);
  }

  void setZeroes1(int[][] matrix) {

    boolean bRow = false, bCol = false;

    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[row].length; col++) {
        if (matrix[row][col] == 0) {
          if (row == 0) {
            bRow = true;
          }
          if (col == 0) {
            bCol = true;
          }

          matrix[0][col] = matrix[row][0] = 0;
        }
      }
    }

    for (int row = 1; row < matrix.length; row++) {
      for (int col = 1; col < matrix[row].length; col++) {
        if (matrix[0][col] == 0 || matrix[row][0] == 0) {
          matrix[row][col] = 0;
        }
      }
    }

    if (bRow) {
      for (int col = 0; col < matrix[0].length; col++) {
        matrix[0][col] = 0;
      }
    }
    if (bCol) {
      for (int row = 0; row < matrix.length; row++) {
        matrix[row][0] = 0;
      }
    }
  }

  void setZeroes2(int[][] matrix) {
    boolean rows[] = new boolean[matrix.length];
    boolean cols[] = new boolean[matrix[0].length];

    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[row].length; col++) {
        if (matrix[row][col] == 0) {
          rows[row] = true;
          cols[col] = true;
        }
      }
    }

    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[row].length; col++) {
        if (rows[row] || cols[col]) {
          matrix[row][col] = 0;
        }
      }
    }
  }

  public static void main(String[] args) {
    SetMatrixZeroes obj = new SetMatrixZeroes();

    int[][] test = new int[][]{
      {1, 2, 3, 4, 5},
      {6, 7, 8, 9, 10},
      {1, 2, 3, 0, 3}
    };
    obj.setZeroes(test);
    print(test);
  }

  public static void print(int[][] source) {
    for(int i=0;i<source.length;i++){
      int[] row=source[i];
      String rowStr= Arrays.stream(row).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
      System.out.println(rowStr);
    }
    System.out.println();
  }
}
