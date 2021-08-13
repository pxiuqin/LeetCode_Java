package xiuqin.leetcode.easy;

/**
 * https://leetcode.com/problems/add-binary/
 * 67. 二进制求和
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字1和0。
 * <p>
 * 示例1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * <p>
 * 示例2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * <p>
 * 提示：
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 */
public class AddBinary {
  public String addBinary(String a, String b) {
    int alen = a.length();
    int blen = b.length();
    boolean carry = false;
    StringBuffer result = new StringBuffer();

    while (alen > 0 || blen > 0) {
      int abit = alen <= 0 ? 0 : a.charAt(alen - 1) - '0';
      int bbit = blen <= 0 ? 0 : b.charAt(blen - 1) - '0';
      int cbit = carry ? 1 : 0;
      result.append((char)('0' + ((abit + bbit + cbit) & 1)));
      carry = (abit + bbit + cbit > 1);

      alen--;
      blen--;
    }

    if (carry) {
      result.append('1');
    }

    return result.reverse().toString();
  }

  public static void main(String[] args){
    AddBinary obj =new AddBinary();

    System.out.println(obj.addBinary("11","1"));
    System.out.println(obj.addBinary("1010","1011"));
  }
}
