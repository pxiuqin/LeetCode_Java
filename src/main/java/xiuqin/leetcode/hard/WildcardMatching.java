package xiuqin.leetcode.hard;

/**
 * https://leetcode.com/problems/wildcard-matching/
 * 44. 通配符匹配
 * 给定一个字符串(s) 和一个字符模式(p) ，实现一个支持'?'和'*'的通配符匹配。
 * <p>
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * <p>
 * 说明:
 * <p>
 * s可能为空，且只包含从a-z的小写字母。
 * p可能为空，且只包含从a-z的小写字母，以及字符?和*。
 * <p>
 * 示例1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * <p>
 * 示例2:
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释:'*' 可以匹配任意字符串。
 * <p>
 * 示例3:
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释:'?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * <p>
 * 示例4:
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释:第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * <p>
 * 示例5:
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 */
public class WildcardMatching {
  public boolean isMatch(String s, String p) {
    String last_s = null;
    String last_p = null;

    while (!s.isEmpty()) {
      if (p.isEmpty()) {
        return false;
      }

      //skip the *
      if (p.charAt(0) == '*') {
        p = p.substring(1);
        if (p.isEmpty()) {
          return true;  // match *
        }

        //use last_s and last_p to store where the "*" match starts.
        last_s = s;
        last_p = p;
      } else if (p.charAt(0) == '?' || p.charAt(0) == s.charAt(0)) {
        p = p.substring(1);
        s = s.substring(1);
      } else if (last_s != null) {
        // check "last_s" to know whether meet "*" before
        // if meet "*" previously, and the *s != *p
        // reset the p, using '*' to match this situation
        p = last_p;
        s = s.substring(1);
      } else {
        // *p is not wildcard char,
        // doesn't match *s,
        // there are no '*' wildcard matched before
        return false;
      }
    }

    //edge case: "s" is done, but "p" still have chars.
    while (!p.isEmpty() && p.charAt(0) == '*') {
      p = p.substring(1);
    }

    return p.isEmpty();
  }

  public static void main(String[] args) {
    WildcardMatching obj = new WildcardMatching();

    System.out.println(obj.isMatch("aa", "a"));
    System.out.println(obj.isMatch("aa", "*"));
    System.out.println(obj.isMatch("cb", "?a"));
    System.out.println(obj.isMatch("adceb", "*a*b"));
    System.out.println(obj.isMatch("acdcb", "a*c?b"));
  }
}
