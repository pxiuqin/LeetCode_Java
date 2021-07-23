package xiuqin.leetcode.medium;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/rotate-image/
 * <p>
 * Example 1:
 * Given input matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * rotate the input matrix in-place such that it becomes:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * <p>
 * Example 2:
 * Given input matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * rotate the input matrix in-place such that it becomes:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 * <p>
 * 一个n x n的二维矩阵表示一个图像，将图像顺时针旋转90度。要求in-place，所以就不能用额外的空间了。
 * <p>
 * 解法1: 先以对角线为轴翻转得到其转置矩阵，再以中间竖轴翻转。
 * 1  2  3　　　 　 1  4  7　　　　  7  4  1
 * 4  5  6　-->　  2  5  8　 -->   8  5  2
 * 7  8  9 　　　  3  6  9　　   　 9  6  3
 * <p>
 * 解法2: 先以反对角线翻转，在以中间水平轴翻转。
 * 1  2  3　　　 　9  6  3　　　　  7  4  1
 * 4  5  6　-->　 8  5  2　 -->   8  5  2
 * 7  8  9 　　　 7  4  1　　　　  9  6  3
 */
public class RotateImage {
  public int[][] rotate(int[][] matrix) {
    int n = matrix.length;

    for (int i = 0; i < n / 2; i++) {
      int low = i, high = n - i - 1;
      for (int j = low; j < high; j++) {
        int tmp = matrix[i][j];
        // left to top
        matrix[i][j] = matrix[n - j - 1][i];

        // bottom to left
        matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];

        // right to bottom
        matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];

        // top to right
        matrix[j][n - i - 1] = tmp;
      }
    }

    return matrix;
  }

  public int[][] rotate2(int[][] matrix) {
    int n = matrix.length;

    //先以对角线为轴翻转得到其转置矩阵
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < i; ++j) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
      }
    }

    //中间竖轴翻转
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n / 2; ++j) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[i][n - j - 1];
        matrix[i][n - j - 1] = temp;
      }
    }

    return matrix;
  }

  public static void main(String[] args) {
    RotateImage obj = new RotateImage();

    int[][] test = new int[][]{
      {1, 2, 3},
      {4, 5, 6},
      {7, 8, 9}
    };
    print(test);
    print(obj.rotate2(test));
    print(obj.rotate(test));
    print(obj.rotate(test));
    print(obj.rotate2(test));
  }

  public static void print(int[][] source) {
    for(int i=0;i<source.length;i++){
      int[] row=source[i];
      String rowStr=Arrays.stream(row).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
      System.out.println(rowStr);
    }
    System.out.println();
  }
}
