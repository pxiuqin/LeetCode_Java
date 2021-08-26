package xiuqin.leetcode.hard;

import java.util.*;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 示例 1:
 * img(doc/img/histogram.jpg)
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 *
 * 示例 2：
 * img(doc/img/histogram-1.jpg)
 * 输入： heights = [2,4]
 * 输出： 4
 *
 * 提示：
 *
 * 1 <= heights.length <=105
 * 0 <= heights[i] <= 104
 *
 *
 * img(doc/img/Histogram.png)
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 *
 *                    6
 *                  +---+
 *               5  |   |
 *              +---+   |
 *              |   |   |
 *              |   |   |
 *              |   |   |     3
 *              |   |   |   +---+
 *        2     |   |   | 2 |   |
 *      +---+   |   |   +---+   |
 *      |   | 1 |   |   |   |   |
 *      |   +---+   |   |   |   |
 *      |   |   |   |   |   |   |
 *      +---+---+---+---+---+---+
 *
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 *
 *
 *                    6
 *                  +---+
 *               5  |   |
 *              +-------|
 *              |-------|
 *              |-------|
 *              |-------|     3
 *              |-------|   +---+
 *        2     |-------| 2 |   |
 *      +---+   |-------|---+   |
 *      |   | 1 |-------|   |   |
 *      |   +---|-------|   |   |
 *      |   |   |-------|   |   |
 *      +---+---+---+---+---+---+
 *
 *
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 *
 * For example,
 * Given height = [2,1,5,6,2,3],
 * return 10.
 */
public class LargestRectangleInHistogram {
  //Time Limit Exceeded
  public int largestRectangleArea_01(int[] heights) {
    if (heights.length == 0) {
      return 0;
    }

    // idx of the first bar in the left or right that is lower than current bar
    int[] left=new int[heights.length];
    int[] right=new int[heights.length];

    right[heights.length - 1] = heights.length;
    left[0] = -1;

    for (int i = 1; i < heights.length; i++) {
      int l = i - 1;
      while (l >= 0 && heights[l] >= heights[i]) {
        l--;
      }
      left[i] = l;
    }

    for (int i = heights.length - 2; i >= 0; i--) {
      int r = i + 1;
      while (r < heights.length && heights[r] >= heights[i]) {
        r++;
      }
      right[i] = r;
    }

    int maxArea = 0;
    for (int i = 0; i < heights.length; i++) {
      maxArea = Math.max(maxArea, heights[i] * (right[i] - left[i] - 1));
    }

    return maxArea;
  }

  //遍历数组，每找到一个局部峰值（只要当前的数字大于后面的一个数字，那么当前数字就看作一个局部峰值，跟前面的数字大小无关），
  //然后向前遍历所有的值，算出共同的矩形面积，每次对比保留最大值
  public int largestRectangleArea_02(int[] height) {
    int res = 0;

    for (int i = 0; i < height.length; ++i) {
      if (i + 1 < height.length && height[i] <= height[i + 1]) {
        continue;
      }

      int minH = height[i];
      for (int j = i; j >= 0; --j) {
        minH = Math.min(minH, height[j]);
        int area = minH * (i - j + 1);
        res = Math.max(res, area);
      }
    }

    return res;
  }

  // As we know, the area = width * height
  // For every bar, the 'height' is determined by the loweset bar.
  //
  // 1) We traverse all bars from left to right, maintain a stack of bars. Every bar is pushed to stack once.
  // 2) A bar is popped from stack when a bar of smaller height is seen.
  // 3) When a bar is popped, we calculate the area with the popped bar as smallest bar.
  // 4) How do we get left and right indexes of the popped bar –
  //    the current index tells us the ‘right index’ and index of previous item in stack is the ‘left index’.
  //
  //
  // In other word, the stack only stores the incresing bars, let's see some example
  //
  // Example 1
  // ---------
  // height = [1,2,3,4]
  //
  //    stack[] = [ 0, 1, 2, 3 ], i=4
  //
  //    1) pop 3,  area = height[3] * 1 = 4
  //    2) pop 2,  area = height[2] * 2 = 4
  //    3) pop 1,  area = height[1] * 3 = 6
  //    4) pop 0,  area = height[0] * 4 = 4
  //
  //
  // Example 2
  // ---------
  // height = [2,1,2]
  //
  //    stack[] = [ 0 ], i=1
  //    1) pop 0,  area = height[0] * 1 = 2
  //
  //    stack[] = [ 1,2 ], i=3, meet the end
  //    1) pop 2,  area = height[2] * 1 = 2
  //    2) pop 1,  area = height[1] * 3 = 3
  //
  //
  // Example 3
  // ---------
  // height =  [4,2,0,3,2,5]
  //
  //    stack[] = [ 0 ], i=1, height[1] goes down
  //    1) pop 0,  area = height[0] * 1 = 4
  //
  //    stack[] = [ 1 ], i=2, height[2] goes down
  //    1) pop 1,  area = height[1] * 2 = 4 // <- how do we know the left?
  //                                              start from the 0 ??
  //
  //    stack[] = [ 2, 3 ], i=4, height[4] goes down
  //    1) pop 3,  area = height[3] * 1 = 3
  //    2) pop 2,  area = height[2] * ? = 0 // <- how do we know the left?
  //                                              start from the 0 ??
  //
  //    stack[] = [ 2,4,5 ], i=6,  meet the end
  //    1) pop 5,  area = height[5] * 1 = 5
  //    2) pop 4,  area = height[4] * 3 = 6 // <- how do we know the left?
  //                                              need check the previous item.
  //    3) pop 2,  area = height[2] * ? = 4 // <- how do we know the left?
  //                                              start from the 0 ??
  //
  //    so, we can see, when the stack pop the top, the area formular is
  //
  //          height[stack_pop] *  i - stack[current_top] - 1,   if stack is not empty
  //          height[stack_pop] *  i,                            if stack is empty
  //
  public int largestRectangleArea_03(List<Integer> height) {
    if (height.size()<=0){
      return 0;
    }

    //Create an empty stack.
    Stack<Integer> stack=new Stack<>();

    //add a flag as a trigger if the end bar is met, and need to check the stack is empty of not .
    height.add(0);
    int maxArea = 0;
    for(int i=0; i<height.size(); i++){
      //If stack is empty or height[i] is higher than the bar at top of stack, then push ‘i’ to stack.
      if ( stack.size()<=0 || height.get(i) >= height.get(stack.peek()) ) {
        stack.push(i);
        continue;
      }

      //If this bar is smaller than the top of stack, then keep removing the top of stack while top of the stack is greater.
      //Let the removed bar be height[top]. Calculate area of rectangle with height[top] as smallest bar.
      //For height[top], the ‘left index’ is previous (previous to top) item in stack and ‘right index’ is ‘i’ (current index).
      int topIdx = stack.peek();
      stack.pop();
      int area = height.get(topIdx) * (stack.size()==0 ? i : i - stack.peek() - 1 );
      if ( area > maxArea ) {
        maxArea = area;
      }
      //one more time. Because the stack might still have item.
      i--;
    }

    return maxArea;
  }

  public int largestRectangleArea_04(List<Integer> height) {
    int res = 0;
    Stack<Integer> st=new Stack<>();
    height.add(0);
    for (int i = 0; i < height.size(); ++i) {
      while (!st.empty() && height.get(st.peek()) >= height.get(i)) {
        int cur = st.peek();
        st.pop();
        res = Math.max(res, height.get(cur) * (st.empty() ? i : (i - st.peek() - 1)));
      }

      st.push(i);
    }

    return res;
  }

  public static void main(String[] args){
    LargestRectangleInHistogram obj =new LargestRectangleInHistogram();

    System.out.println(obj.largestRectangleArea_01(new int[]{2,1,5,6,2,3}));
    System.out.println(obj.largestRectangleArea_02(new int[]{2,1,5,6,2,3}));
    System.out.println(obj.largestRectangleArea_03(new ArrayList<>(Arrays.asList(2,1,5,6,2,3))));
    System.out.println(obj.largestRectangleArea_04(new ArrayList<>(Arrays.asList(2,1,5,6,2,3))));

    System.out.println(obj.largestRectangleArea_01(new int[]{2,1,5,6,3,3}));
    System.out.println(obj.largestRectangleArea_02(new int[]{2,1,5,6,3,3}));
    System.out.println(obj.largestRectangleArea_03(new ArrayList<>(Arrays.asList(2,1,5,6,3,3))));
    System.out.println(obj.largestRectangleArea_04(new ArrayList<>(Arrays.asList(2,1,5,6,3,3))));
  }
}
