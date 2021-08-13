package xiuqin.leetcode.easy;

import java.util.*;

/**
 * https://leetcode.com/problems/plus-one/
 * 66. 加一
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例1：
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * <p>
 * 示例2：
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * <p>
 * 示例 3：
 * 输入：digits = [0]
 * 输出：[1]
 * <p>
 * 提示：
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 */
public class PlusOne {
  List<Integer> plusOne(List<Integer> digits) {
    int carry = 1;
    List<Integer> v = new ArrayList<>();
    while (digits.size() > 0) {
      int x = digits.get(digits.size() - 1);
      digits = digits.subList(0, digits.size() - 1);
      x = x + carry;
      v.add(x % 10);
      carry = x / 10;
    }

    if (carry > 0) {
      v.add(carry);
    }

    Collections.reverse(v);
    return v;
  }

  public static void main(String[] args) {
    PlusOne obj = new PlusOne();

    System.out.println(obj.plusOne(Arrays.asList(1, 2, 3)));
    System.out.println(obj.plusOne(Arrays.asList(4, 3, 2, 1)));
    System.out.println(obj.plusOne(Arrays.asList(0)));
    System.out.println(obj.plusOne(Arrays.asList(1, 8, 9)));
    System.out.println(obj.plusOne(Arrays.asList(1, 9, 9)));
    System.out.println(obj.plusOne(Arrays.asList(1, 9, 9, 9)));
  }
}
