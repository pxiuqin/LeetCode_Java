package xiuqin.leetcode.easy;

/**
 * https://leetcode.com/problems/sqrtx/
 * 69. x 的平方根
 * 实现int sqrt(int x)函数。
 * 计算并返回x的平方根，其中x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * 输入: 4
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 *
 * 牛顿法逼近解析： img(doc/img/Newton4Sqrt.png)
 */
public class Sqrt {
  int sqrt(int x) {
    if (x <= 0) return 0;

    //the sqrt is not greater than x/2+1
    int e = x / 2 + 1;
    int s = 0;

    // binary search
    while (s <= e) {
      int mid = s + (e - s) / 2;
      long sq = (long) mid * (long) mid;

      if (sq == x) {
        return mid;
      }

      if (sq < x) {
        s = mid + 1;
      } else {
        e = mid - 1;
      }
    }

    return e;
  }

  // http://en.wikipedia.org/wiki/Newton%27s_method
  int sqrt_nt(int x) {
    if (x == 0) {
      return 0;
    }

    double last = 0;
    double res = 1;

    while (res != last) {
      last = res;
      res = (res + x / res) / 2;   //牛顿法逼近
    }

    return (int) res;
  }

  public static void main(String[] args) {
    Sqrt obj = new Sqrt();

    System.out.println(obj.sqrt(4));
    System.out.println(obj.sqrt_nt(4));
    System.out.println(obj.sqrt(8));
    System.out.println(obj.sqrt_nt(8));
    System.out.println(obj.sqrt(9));
    System.out.println(obj.sqrt_nt(9));
  }
}
