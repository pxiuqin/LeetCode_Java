package xiuqin.leetcode.medium;

/**
 * https://leetcode.com/problems/powx-n/
 * 50. Pow(x, n)
 * 实现pow(x, n)，即计算 x 的 n 次幂函数（即，xn）。
 * <p>
 * 示例 1：
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * <p>
 * 示例 2：
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * <p>
 * 示例 3：
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * 提示：
 * -100.0 <x< 100.0
 * -2^31<= n <=2^31-1
 * -10^4 <= x^n <= 10^4
 */
public class Pow {
  /**
   * Divide-and-Conquer method
   * For example:
   * <p>
   * 3^9=(3^4)^2*3
   * ↓
   * 3^4=(3^2)^2
   * ↓
   * 3^2=3*3
   * ↓
   * 3=3
   * <p>
   * So, both Space and Time are O(logN)
   */
  public double recursion(double x, long n) {
    if (n == 1) {
      return x;
    }

    //We'd better use unsigned right shift
    double half = recursion(x, n >>> 1);

    if ((n & 1) == 0) {
      return half * half;
    } else {
      return half * half * x;
    }
  }

  public double myPow01(double x, int n) {
    if (n == 0 || x == 1) {
      return 1;
    }
    // Avoid being out of bounds, we should cast int to long
    long m = n;
    double result = recursion(x, Math.abs(m));

    if (n > 0) {
      return result;
    } else {
      return 1 / result;
    }
  }

  public double myPow02(double x, int n) {
    if (n == 0 || x == 1) {
      return 1;
    }
    // Avoid being out of bounds, we should cast int to long
    long m = n;
    double result = bitFunction(x, Math.abs(m));

    if (n > 0) {
      return result;
    } else {
      return 1 / result;
    }
  }

  /**
   * Solution with bit-manipulation
   * For example:
   * 9=1001
   * 3^9=(3^1)^1*(3^2)^0*(3^4)^0*(3^8)^1
   * Space is O(1), Time is O(logN)
   */
  public double bitFunction(double x, long n) {
    double multy = 1;
    double base = x;
    for (long i = n; i >= 1; i >>>= 1) {
      if ((i & 1) > 0) {
        multy *= base;   //if i&1=1 then multiply
      }
      base *= base;  //base^i
    }

    return multy;
  }

  public static void main(String[] args) {
    Pow obj = new Pow();

    System.out.println(obj.myPow01(2, 10));
    System.out.println(obj.myPow02(2, 10));

    System.out.println(obj.myPow01(2.1, 3));
    System.out.println(obj.myPow02(2.1, 3));

    System.out.println(obj.myPow01(2, -2));
    System.out.println(obj.myPow02(2, -2));
  }
}
