package xiuqin.leetcode.medium;

/**
 * https://leetcode.com/problems/multiply-strings/
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数num1和num2，返回num1和num2的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 *
 * num1和num2的长度小于110。
 * num1 和num2 只包含数字0-9。
 * num1 和num2均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 *
 */
public class MultiplyStrings {
  String multiply(String num1, String num2) {
    int n = num1.length();
    int m = num2.length();
    int[] res = new int[m + n];

    for (int i = n - 1; i >= 0; i--) {
      for (int j = m - 1; j >= 0; j--) {
        res[i + j + 1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
      }
    }

    int carry = 0;
    for (int i = res.length - 1; i >= 0; i--) {
      res[i] += carry;
      carry = res[i] / 10;
      res[i] %= 10;
    }

    //去掉最前的若干个0
    int i = 0;
    for (; i < res.length - 1; i++)
    {
      if (res[i] != 0) {
        break;
      }
    }

    String multi = "";
    for (int j = i; j < res.length; j++) {
      multi += res[j];
    }

    return multi;
  }

  public static void main(String[] args) {
    MultiplyStrings obj = new MultiplyStrings();

    System.out.println(obj.multiply("2", "3"));
    System.out.println(obj.multiply("123", "456"));
  }
}
