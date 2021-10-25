package xiuqin.leetcode.medium;

/**
 * https://leetcode.com/problems/divide-two-integers/
 * 29.两数相除
 * 给定两个整数，被除数dividend和除数divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数dividend除以除数divisor得到的商。
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * <p>
 * 示例1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * <p>
 * 示例2:
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 * <p>
 * 提示：
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231− 1]。本题中，如果除法结果溢出，则返回 231− 1。
 * 对于这道题，要求计算两个数字的商。
 * 在计算两个数字的商时，关键在于：
 * 使得被除数减去除数、且不让被除数变为0的次数。
 * <p>
 * 举个例子，假设被除数为15，除数为4，总的次数为m
 * 15 - 4 = 11 > 0，将除数扩大为原来的两倍，m = 1
 * 15 - 4*2 = 7 > 0，将除数扩大为原来的两倍， m = 2
 * 15 - 4*2^2 = -1 < 0，不可行，此时，我们得到新的被除数为：15 - 4*2 = 7，除数为4，因为被除数当前还大于除数，因此还需要继续进行；
 * 7 - 4 = 3 > 0，将除数扩大为原来的两倍，m = 3
 * 此时，被除数为7，除数为4*2 = 8，被除数小于除数，自然结束了。
 * 因为m = 3，我们有：15 = 3*4 + 3
 * 即，15/4 = 3
 * 同时，需要注意的是，当INT_MIN除以-1时，会造成溢出，因此，当被除数和除数分别是上述二者时，直接返回INT_MAX即可。
 */
public class DivideTwoIntegers {
  public int divide(int dividend, int divisor) {
    int sign = (float) dividend / divisor > 0 ? 1 : -1;
    int dvd = dividend > 0 ? dividend : -dividend;
    int dvs = divisor > 0 ? divisor : -divisor;

    long bit_num[] = new long[33];
    int i = 0;
    long d = dvs;
    bit_num[i] = d;
    while (d <= dvd) {
      bit_num[++i] = d = d << 1;
    }
    i--;

    int result = 0;
    while (dvd >= dvs) {
      if (dvd >= bit_num[i]) {
        dvd -= bit_num[i];
        result += (1 << i);
      } else {
        i--;
      }
    }

    //becasue need to return `int`, so we need to check it is overflowed or not.
    if (result > Integer.MAX_VALUE && sign > 0) {
      return Integer.MAX_VALUE;
    }

    return result * sign;
  }

  public int divide2(int dividend, int divisor) {
    if (dividend == Integer.MAX_VALUE && divisor == -1) {
      return Integer.MAX_VALUE; //对移除的情况进行判断
    }

    int sign = dividend > 0 ^ divisor > 0 ? -1 : 1;
    int dvd = Math.abs(dividend);  //被除数
    int dvs = Math.abs(divisor);  //除数
    int res = 0;

    //当被除数大于等于除数时
    while (dvd >= dvs) {
      long tmp = dvs, m = 1;
      while ((tmp << 1) <= dvd) { //当前够除
        tmp <<= 1;   //除数×2
        m <<= 1;     //除数构成个数×2
      }

      dvd -= tmp; //剩余的被除数
      res += m;
    }

    return sign * res;
  }


  public static void main(String[] args) {
    DivideTwoIntegers obj = new DivideTwoIntegers();

    System.out.println(obj.divide(0, 2));
    System.out.println(obj.divide(10, 2));
    System.out.println(obj.divide(-2, 2));
    System.out.println(obj.divide(-2, -2));
    System.out.println(obj.divide2(Integer.MAX_VALUE, -1));
    System.out.println(obj.divide(Integer.MAX_VALUE, 1));
    System.out.println(obj.divide(Integer.MIN_VALUE, 1));


    System.out.println(obj.divide2(0, 2));
    System.out.println(obj.divide2(10, 2));
    System.out.println(obj.divide2(-2, 2));
    System.out.println(obj.divide2(-2, -2));
    System.out.println(obj.divide2(Integer.MAX_VALUE, -1));
    System.out.println(obj.divide2(Integer.MAX_VALUE, 1));
    System.out.println(obj.divide2(Integer.MIN_VALUE, 1));
  }
}
