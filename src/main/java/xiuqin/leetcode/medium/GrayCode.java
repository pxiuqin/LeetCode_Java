package xiuqin.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/gray-code/
 * <p>
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * <p>
 * Given a non-negative integer n representing the total number of bits in the code,
 * print the sequence of gray code. A gray code sequence must begin with 0.
 * <p>
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * <p>
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * <p>
 * Note:
 * For a given n, a gray code sequence is not uniquely defined.
 * <p>
 * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
 * <p>
 * For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 */
public class GrayCode {
  /*
   * I designed the following stupid algorithm base on the blow observation
   *
   * I noticed I can use a `mirror-like` binary tree to figure out the gray code.
   *
   * For example:
   *
   *           0
   *        __/ \__
   *       0       1
   *      / \     / \
   *     0   1   1   0
   * So, the gray code as below: (top-down, from left to right)
   *
   *     0 0 0
   *     0 0 1
   *     0 1 1
   *     0 1 0
   *
   *                  0
   *            _____/ \_____
   *           0             1
   *        __/ \__       __/ \__
   *       0       1     1       0
   *      / \     / \   / \     / \
   *     0   1   1   0 0   1   1   0
   *
   * So, the gray code as below:
   *
   *     0 0 0 0
   *     0 0 0 1
   *     0 0 1 1
   *     0 0 1 0
   *     0 1 1 0
   *     0 1 1 1
   *     0 1 0 1
   *     0 1 0 0
   */
  public List<Integer> grayCode01(int n) {
    List<Integer> v = new ArrayList<>();
    //n = 1<<n;

    int x = 0;
    v.add(x);
    for (int i = 0; i < n; i++) {
      int len = v.size();
      for (int j = 0; j < len; j++) {
        x = v.get(j) << 1;
        if (j % 2 == 0) {
          v.add(x);
          v.add(x + 1);
        } else {
          v.add(x + 1);
          v.add(x);
        }
      }
      v = v.subList(len, v.size());
    }

    return v;
  }

  /*
   * Actually, there is a better way.
   * The mathematical way is: (num >> 1) ^ num;
   * Please refer to http://en.wikipedia.org/wiki/Gray_code
   */
  public List<Integer> grayCode02(int n) {
    List<Integer> ret = new ArrayList<>();
    int size = 1 << n;
    for (int i = 0; i < size; ++i) {
      ret.add((i >> 1) ^ i);
    }
    return ret;
  }

  //random invoker
  public List<Integer> grayCode(int n) {
    if (Math.random() % 2 == 0) {
      return grayCode01(n);
    }

    return grayCode02(n);
  }

  public static void main(String[] args) {
    GrayCode obj = new GrayCode();

    System.out.println(obj.grayCode(2));
    System.out.println(obj.grayCode(3));
    System.out.println(obj.grayCode(4));
  }
}
