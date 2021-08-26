package xiuqin.leetcode.hard;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/maximal-rectangle/
 * 85. 最大矩形
 * <p>
 * 示例 1：
 * img(doc/img/maximal.jpg)
 * 输入：matrix = [['1','0','1','0','0'],['1','0','1','1','1'],['1','1','1','1','1'],['1','0','0','1','0']]
 * 输出：6
 * 解释：最大矩形如上图所示。
 * <p>
 * 示例 2：
 * 输入：matrix = []
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：matrix = [['0']]
 * 输出：0
 * <p>
 * 示例 4：
 * 输入：matrix = [['1']]
 * 输出：1
 * <p>
 * 示例 5：
 * 输入：matrix = [['0','0']]
 * 输出：0
 * <p>
 * 提示：
 * rows == matrix.length
 * cols == matrix[0].length
 * 0 <= row, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 */
public class MaximalRectangle {
  // The problem can be convert to the problem - 'Largest Rectangle in Histogram'
  //   1) we can take each row to calculate each row's histogram.
  //   2) using the algorithm of 'Largest Rectangle in Histogram' to find the largest area histogram.
  //   3) tracking the maximal area.
  //
  // For the 1), it's easy.
  //     heights[i][j] = 1,                     if (i==0)
  //     heights[i][j] = heights[i-1][j] + 1;,  if (i>0)
  //
  // For the 2), please referr to 'Largest Rectangle in Histogram'
  //
  private int largestRectangleArea(List<Integer> height) {
    if (height.size() <= 0) {
      return 0;
    }

    height.add(0);
    Stack<Integer> stack = new Stack<>();
    int maxArea = 0;

    for (int i = 0; i < height.size(); ) {
      if (stack.size() == 0 || height.get(i) >= height.get(stack.peek())) {
        stack.push(i);
        i++;
      } else {
        int topIdx = stack.peek();
        stack.pop();
        int area = height.get(topIdx) * (stack.size() == 0 ? i : i - stack.peek() - 1);
        if (area > maxArea) {
          maxArea = area;
        }
      }
    }

    return maxArea;
  }

  public int maximalRectangle(char[][] matrix) {
    if (matrix.length <= 0 || matrix[0].length <= 0) {
      return 0;
    }

    int row = matrix.length;
    int col = matrix[0].length;
    int[][] heights = new int[row][col];

    int maxArea = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (matrix[i][j] == '1') {
          heights[i][j] = (i == 0 ? 1 : heights[i - 1][j] + 1);
        }
      }

      int area = largestRectangleArea(Arrays.stream(heights[i]).boxed().collect(Collectors.toList()));
      if (area > maxArea) {
        maxArea = area;
      }
    }

    return maxArea;
  }

  public static void main(String[] args) {
    MaximalRectangle obj = new MaximalRectangle();

    char[][] test = new char[][]{
      {'1', '0', '1', '0', '0'},
      {'1', '0', '1', '1', '1'},
      {'1', '1', '1', '1', '1'},
      {'1', '0', '0', '1', '0'}
    };
    System.out.println(obj.maximalRectangle(test));
    System.out.println(obj.maximalRectangle(new char[][]{{'0'}}));
    System.out.println(obj.maximalRectangle(new char[][]{{'1'}}));
    System.out.println(obj.maximalRectangle(new char[][]{{'0', '0'}}));
  }
}
