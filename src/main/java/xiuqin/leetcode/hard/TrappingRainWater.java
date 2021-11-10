package xiuqin.leetcode.hard;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 * 42. 接雨水
 * 给定n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 示例 1：
 * (img:doc/img/0-100/rainwatertrap.png)
 * <p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * <p>
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * <p>
 * 提示：
 * n == height.length
 * 0 <= n <= 3 * 10^4
 * 0 <= height[i] <= 10^5
 * <p>
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * <p>
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * <p>
 * ^
 * ..|
 * 3 |                       +--+
 * ..|                       |  |
 * 2 |          +--+xxxxxxxxx|  +--+xx+--+
 * ..|          |  |xxxxxxxxx|  |  |xx|  |
 * 1 |   +--+xxx|  +--+xxx+--+  |  +--+  +--+
 * ..|   |  |xxx|  |  |xxx|  |  |  |  |  |  |
 * 0 +---+--+---+--+--+---+--+--+--+--+--+--+----->
 * ....0  1   0  2  1   0  1  3  2  1  2  1
 * <p>
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case,
 * 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 */
public class TrappingRainWater {
  /*
   * The idea is:
   *    1) find the highest bar.
   *    2) traverse the bar from left the highest bar.
   *       because we have the highest bar in right, so, any bar higher than its right bar(s) can contain the water.
   *    3) traverse the bar from right the highest bar.
   *       because we have the highest bar in left, so, any bar higher than its left bar(s) can contain the water.
   *
   * The code below is quite clear!
   *
   */
  int trap(int a[]) {
    int n = a.length;

    int result = 0;

    //find the highest value/position
    int maxHigh = 0;
    int maxIdx = 0;
    for (int i = 0; i < n; i++) {
      if (a[i] > maxHigh) {
        maxHigh = a[i];
        maxIdx = i;
      }
    }

    //from the left to the highest position
    int prevHigh = 0;
    for (int i = 0; i < maxIdx; i++) {
      if (a[i] > prevHigh) {
        prevHigh = a[i];
      }
      result += prevHigh - a[i];
    }

    //from the right to the highest position
    int postHigh = 0;
    for (int i = n - 1; i > maxIdx; i--) {
      if (a[i] > postHigh) {
        postHigh = a[i];
      }
      result += postHigh - a[i];
    }

    return result;
  }

  public static void main(String[] args) {
    TrappingRainWater obj = new TrappingRainWater();

    System.out.println(obj.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    System.out.println(obj.trap(new int[]{4, 2, 0, 3, 2, 5}));
  }
}
