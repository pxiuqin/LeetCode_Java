package xiuqin.leetcode.easy;

/**
 * https://leetcode.com/problems/valid-number/
 * 65. 有效数字
 * 有效数字（按顺序）可以分成以下几个部分：
 *
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 小数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * 部分有效数字列举如下：
 *
 * ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
 * 部分无效数字列举如下：
 *
 * ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
 * 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
 *
 * 示例 1：
 * 输入：s = "0"
 * 输出：true
 *
 * 示例 2：
 * 输入：s = "e"
 * 输出：false

 * 示例 3：
 * 输入：s = "."
 * 输出：false

 * 示例 4：
 * 输入：s = ".1"
 * 输出：true
 *
 * 提示：
 *
 * 1 <= s.length <= 20
 * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。
 */
public class ValidNumber {
  private boolean isdigit(char c) {
    return (c >= '0' && c <= '9');
  }

  private boolean isspace(char c) {
    return (c == ' ' || c == '\t' || c == '\n' || c == '\r' || c == '\f');
  }

  public boolean isNumber(String s) {
    boolean point = false;
    boolean hasE = false;

    //trim the space
    while (!s.isEmpty() && isspace(s.charAt(0))) {
      s = s.substring(1);
    }

    //check empty
    if (s.isEmpty()) {
      return false;
    }

    //check sign
    if (s.charAt(0) == '+' || s.charAt(0) == '-') {
      s = s.substring(1);
    }

    for (int i = 0; i < s.length(); i++) {
      //if contain "."
      if (s.charAt(i) == '.') {
        if (hasE == true || point == true) {
          return false;
        }

        int index = i + 1;
        if (index < s.length() && !isdigit(s.charAt(index))) {
          return false;
        }
        point = true;
        continue;
      }

      //if contain "e"
      if (s.charAt(i) == 'e') {
        if (hasE == true) {
          return false;
        }

        int index = i + 1;
        i++;
        if(i<s.length() && (s.charAt(i)=='+'||s.charAt(i)=='-')){
          index = i + 1;
        }

        if (index < s.length()) {
          if(!isdigit(s.charAt(index))){
            return false;
          }
        } else {
          return false;
        }
        hasE = true;
        continue;
      }

      //if contain space, check the rest chars are space or not
      if (isspace(s.charAt(i))) {
        return false;
      }

      if (!isdigit(s.charAt(i))) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    ValidNumber obj = new ValidNumber();

    System.out.println("1.044" + obj.isNumber("1.044"));
    System.out.println(" 1.044 " + obj.isNumber(" 1.044 "));
    System.out.println("1.a" + obj.isNumber("1.a"));
    System.out.println("abc" + obj.isNumber("abc"));
    System.out.println("e" + obj.isNumber("e"));
    System.out.println("1e" + obj.isNumber("1e"));
    System.out.println("1e2" + obj.isNumber("1e2"));
    System.out.println("" + obj.isNumber(""));
    System.out.println(" " + obj.isNumber(" "));
    System.out.println("1. " + obj.isNumber("1."));
    System.out.println(".2" + obj.isNumber(".2"));
    System.out.println(" . " + obj.isNumber(" . "));
    System.out.println("." + obj.isNumber("."));
    System.out.println("1.2.3" + obj.isNumber("1.2.3"));
    System.out.println("1e2e3" + obj.isNumber("1e2e3"));
    System.out.println("1.." + obj.isNumber("1.."));
    System.out.println("+1." + obj.isNumber("+1."));
    System.out.println("-1." + obj.isNumber(" -1."));
    System.out.println("6e6.5" + obj.isNumber("6e6.5"));
    System.out.println("005047e+6" + obj.isNumber("005047e+6"));
  }
}
