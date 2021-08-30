package xiuqin.leetcode.hard;

import java.util.Arrays;
import java.util.Collections;

/**
 * https://leetcode.com/problems/scramble-string/
 * 87. 扰乱字符串
 * 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
 * 如果字符串的长度为 1 ，算法停止
 * 如果字符串的长度 > 1 ，执行下述步骤：
 * 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
 * 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
 * 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
 * 给你两个 长度相等 的字符串 s1 和s2，判断s2是否是s1的扰乱字符串。如果是，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：s1 = "great", s2 = "rgeat"
 * 输出：true
 * 解释：s1 上可能发生的一种情形是：
 * "great" --> "gr/eat" // 在一个随机下标处分割得到两个子字符串
 * "gr/eat" --> "gr/eat" // 随机决定：「保持这两个子字符串的顺序不变」
 * "gr/eat" --> "g/r / e/at" // 在子字符串上递归执行此算法。两个子字符串分别在随机下标处进行一轮分割
 * "g/r / e/at" --> "r/g / e/at" // 随机决定：第一组「交换两个子字符串」，第二组「保持这两个子字符串的顺序不变」
 * "r/g / e/at" --> "r/g / e/ a/t" // 继续递归执行此算法，将 "at" 分割得到 "a/t"
 * "r/g / e/ a/t" --> "r/g / e/ a/t" // 随机决定：「保持这两个子字符串的顺序不变」
 * 算法终止，结果字符串和 s2 相同，都是 "rgeat"
 * 这是一种能够扰乱 s1 得到 s2 的情形，可以认为 s2 是 s1 的扰乱字符串，返回 true

 * 示例 2：
 * 输入：s1 = "abcde", s2 = "caebd"
 * 输出：false

 * 示例 3：
 * 输入：s1 = "a", s2 = "a"
 * 输出：true
 *
 * 提示：
 * s1.length == s2.length
 * 1 <= s1.length <= 30
 * s1 和 s2 由小写英文字母组成
 * <p>
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 * <p>
 * Below is one possible representation of s1 = "great":
 * <p>
 * great
 * /    \
 * gr    eat
 * / \    /  \
 * g   r  e   at
 * / \
 * a   t
 * <p>
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 * <p>
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 * <p>
 * rgeat
 * /    \
 * rg    eat
 * / \    /  \
 * r   g  e   at
 * / \
 * a   t
 * <p>
 * We say that "rgeat" is a scrambled string of "great".
 * <p>
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 * <p>
 * rgtae
 * /    \
 * rg    tae
 * / \    /  \
 * r   g  ta  e
 * / \
 * t   a
 * <p>
 * We say that "rgtae" is a scrambled string of "great".
 * <p>
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 */
public class ScrambleString {
  // The recursive way is quite simple.
  //    1) break the string to two parts:
  //          s1[0..j]   s1[j+1..n]
  //          s2[0..j]   s2[j+1..n]
  //    2) then
  //          isScramble(s1[0..j], s2[0..j]) &&  isScramble(s1[j+1..n], s2[j+1..n])
  //        OR
  //          isScramble(s1[0..j], s2[j+1, n]) &&  isScramble(s1[j+1..n], s2[0..j])
  public boolean isScramble_recursion(String s1, String s2) {

    if (s1.length() != s2.length() || s1.length() == 0 || s2.length() == 0) {
      return false;
    }
    if (s1 == s2) {
      return true;
    }

    char[] ss1 = s1.toCharArray();
    char[] ss2 = s2.toCharArray();
    Arrays.sort(ss1);
    Arrays.sort(ss2);
    if (ss1 != ss2) {
      return false;
    }

    for (int i = 1; i < s1.length(); i++) {
      if (isScramble_recursion(s1.substring(0, i), s2.substring(0, i)) &&
        isScramble_recursion(s1.substring(i, s1.length() - i), s2.substring(i, s2.length() - i))) {
        return true;
      }
      if (isScramble_recursion(s1.substring(0, i), s2.substring(s2.length() - i, i)) &&
        isScramble_recursion(s1.substring(i, s1.length() - i), s2.substring(0, s2.length() - i))) {
        return true;
      }
    }

    return false;
  }

  /*
   *  Definition
   *
   *      dp[k][i][j] means:
   *
   *         a) s1[i] start from 'i'
   *         b) s2[j] start from 'j'
   *         c) 'k' is the length of substring
   *
   *  Initialization
   *
   *      dp[1][i][j] = (s1[i] == s2[j] ? true : false)
   *
   *  Formula
   *
   *      same as the above recursive method idea
   *
   *      dp[k][i][j] =
   *          dp[divk][i][j] && dp[k-divk][i+divk][j+divk] ||
   *          dp[divk][i][j+k-divk] && dp[k-divk][i+divk][j]
   *
   *      `divk` mean split the k to two parts, so 0 <= divk <= k;
   */
  boolean isScramble_dp(String s1, String s2) {

    if (s1.length() != s2.length() || s1.length() == 0 || s2.length() == 0) {
      return false;
    }
    if (s1 == s2) {
      return true;
    }

    int len = s1.length();

    // dp[len+1][len][len]
    boolean[][][] dp = new boolean[len + 1][len][len];

    // ignor the k=0, just for readable code.

    // initialization k=1
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < len; j++) {
        dp[1][i][j] = (s1.charAt(i) == s2.charAt(j));
      }
    }

    // start from k=2 to len, O(n^4) loop.
    for (int k = 2; k <= len; k++) {
      for (int i = 0; i < len - k + 1; i++) {
        for (int j = 0; j < len - k + 1; j++) {
          dp[k][i][j] = false;
          for (int divk = 1; divk < k && dp[k][i][j] == false; divk++) {
            dp[k][i][j] = (dp[divk][i][j] && dp[k - divk][i + divk][j + divk]) ||
              (dp[divk][i][j + k - divk] && dp[k - divk][i + divk][j]);
          }
        }
      }
    }

    return dp[len][0][0];
  }

  public boolean isScramble(String s1, String s2) {

    if (Math.random() % 2 == 0) {
      System.out.println("---- recursion ---");
      return isScramble_recursion(s1, s2);
    }
    System.out.println("---- dynamic programming ---");
    return isScramble_dp(s1, s2);
  }

  public static void main(String[] args) {
    ScrambleString obj = new ScrambleString();

    System.out.println(obj.isScramble("great", "rgeat"));
    System.out.println(obj.isScramble("abcde", "caebd"));
  }
}
