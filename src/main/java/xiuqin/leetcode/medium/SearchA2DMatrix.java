package xiuqin.leetcode.medium;

/**
 * https://leetcode.com/problems/search-a-2d-matrix/
 * 74. 搜索二维矩阵
 * 编写一个高效的算法来判断m x n矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * <p>
 * 示例 1：
 * img(img/doc/mat.jpg)
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 * <p>
 * 示例 2：
 * img(doc/img/0-100/mat.jpg)
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 * <p>
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -10^4 <= matrix[i][j], target <= 10^4
 */
public class SearchA2DMatrix {
  int rows = 0;
  int cols = 0;
  int[][] m;

  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
      return false;
    }

    m = matrix;
    rows = matrix.length;
    cols = matrix[0].length;

    int start = 0;
    int end = rows * cols - 1;

    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      int midValue = getValue(mid);
      if (midValue == target) {
        return true;
      } else if (midValue < target) {
        start = mid;
      } else {
        end = mid;
      }
    }

    if (getValue(start) == target) {
      return true;
    }

    if (getValue(end) == target) {
      return true;
    }

    return false;
  }

  /**
   * Get the value of the 2D matrix from the index
   *
   * @param index
   * @return
   */
  private int getValue(int index) {
    return m[index / cols][index % cols];
  }

  public static void main(String[] args) {
    SearchA2DMatrix obj = new SearchA2DMatrix();

    int[][] test = new int[][]{
      {1, 3, 5, 7},
      {10, 11, 16, 20},
      {23, 30, 34, 60}
    };
    System.out.println(obj.searchMatrix(test, 3));
    System.out.println(obj.searchMatrix(test, 13));
  }
}
