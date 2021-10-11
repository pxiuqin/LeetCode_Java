package xiuqin.leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/zigzag-conversion/
 * 6. Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行Z 字形排列。
 * <p>
 * 比如输入字符串为 "PAYPALISHIRING"行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * <p>
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 *
 * <p>
 * 示例 1：
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * <p>
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * <p>
 * 示例 3：
 * 输入：s = "A", numRows = 1
 * 输出："A"
 *
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 */
public class ZigZagConversion {
  public String convert(String s, int numRows) {
    //The cases no need to do anything
    if (numRows <= 1 || numRows >= s.length()) {
      return s;
    }

    List<String> rowStr = new ArrayList<>(Collections.nCopies(numRows, ""));
    int row = 0;
    int step = 1;
    for (int i = 0; i < s.length(); i++) {
      if (row == numRows - 1) {
        step = -1;
      }

      if (row == 0) {
        step = 1;
      }

      rowStr.set(row, rowStr.get(row) + s.charAt(i));
      row += step;
    }

    String result = "";
    for (int i = 0; i < numRows; i++) {
      result += rowStr.get(i);
    }

    return result;
  }

  public static void main(String[] args) {
    ZigZagConversion obj = new ZigZagConversion();

    String s = "PAYPALISHIRING";
    int numRows = 3;
    System.out.println(obj.convert(s, numRows));

    numRows = 4;
    System.out.println(obj.convert(s, numRows));

    s = "A";
    numRows = 1;
    System.out.println(obj.convert(s, numRows));
  }
}
