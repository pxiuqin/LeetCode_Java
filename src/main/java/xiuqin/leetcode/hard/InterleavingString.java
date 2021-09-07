package xiuqin.leetcode.hard;

/**
 * https://leetcode.com/problems/interleaving-string/
 * 97. 交错字符串
 * 给定三个字符串s1、s2、s3，请你帮忙验证s3是否是由s1和s2 交错 组成的。
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 * <p>
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 提示：a + b 意味着字符串 a 和 b 连接。
 * <p>
 * 示例 1：
 * img(doc/img/interleave.jpg)
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 * <p>
 * 提示：
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1、s2、和 s3 都由小写英文字母组成
 */
public class InterleavingString {
  /*
  Considering:

      s1 = a1, a2 ........a(i-1), ai
      s2 = b1, b2, .......b(j-1), bj
      s3 = c1, c3, .......c(i+j-1), c(i+j)


  Defined

      match[i][j]  means   s1[0..i] and s2[0..j] is matched S3[0..i+j]

      So, if ai == c(i+j), then match[i][j] = match[i-1][j], which means

          s1 = a1, a2 ........a(i-1)
          s2 = b1, b2, .......b(j-1), bj
          s3 = c1, c3, .......c(i+j-1)

      Same, if bj = c(i+j), then match[i][j] = match[i][j-1];

  Formula:

      Match[i][j] =
          (s3[i+j-1] == s1[i]) && match[i-1][j] ||
          (s3[i+j-1] == s2[j]) && match[i][j-1]

  Initialization:

      i=0 && j=0, match[0][0] = true;

      i=0,   s3[j] == s2[j], match[0][j] |= match[0][j-1]
             s3[j] != s2[j], match[0][j] = false;

      j=0,   s3[i] == s1[i], match[i][0] |= match[i-1][0]
             s3[i] != s1[i], Match[i][0] = false;

  */


  //Dynamic Programming
  public boolean isInterleave(String s1, String s2, String s3) {

    if (s1.length() + s2.length() != s3.length()) {
      return false;
    }

    boolean[][] match = new boolean[s1.length() + 1][s2.length() + 1];

    match[0][0] = true;
    for (int i = 1; i <= s1.length(); i++) {
      if (s1.charAt(i - 1) == s3.charAt(i - 1)) {
        match[i][0] = true;
      } else {
        break;  //because the char must be continuous
      }
    }

    for (int i = 1; i < s2.length(); i++) {
      if (s2.charAt(i - 1) == s3.charAt(i - 1)) {
        match[0][i] = true;
      } else {
        break;
      }
    }

    for (int i = 1; i <= s1.length(); i++) {
      for (int j = 1; j <= s2.length(); j++) {
        if (s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
          match[i][j] = match[i - 1][j] || match[i][j];
        }
        if (s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
          match[i][j] = match[i][j - 1] || match[i][j];
        }
      }
    }

    return match[s1.length()][s2.length()];
  }

  //Time Limit Exceeded
  public boolean isInterleave_dfs(String s1, String s2, String s3) {
    if (s1.length() + s2.length() != s3.length()) {
      return false;
    }

    for (int i = 0; i < s3.length(); i++) {
      //determine whether the matching string is empty
      if (s1.isEmpty()) {
        return s2.equals(s3.substring(i));
      } else if (s2.isEmpty()) {
        return s1.equals(s3.substring(i));
      }

      char p1 = s1.charAt(0);
      char p2 = s2.charAt(0);
      char p3 = s3.charAt(i);
      if (p3 == p1 && p3 != p2) {
        s1 = s1.substring(1);
      } else if (p3 == p2 && p3 != p1) {
        s2 = s2.substring(1);
      } else if (p3 == p2 && p3 == p1) {
        if (isInterleave_dfs(s1.substring(1), s2, s3.substring(i + 1)) == false) {
          return isInterleave_dfs(s1, s2.substring(1), s3.substring(i + 1));
        }

        return true;
      } else {
        return false;
      }
    }

    return s1.isEmpty() && s2.isEmpty() && s3.isEmpty();
  }

  public static void main(String[] args) {
    InterleavingString obj = new InterleavingString();

    System.out.println(obj.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    System.out.println(obj.isInterleave_dfs("aabcc", "dbbca", "aadbbcbcac"));

    System.out.println(obj.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    System.out.println(obj.isInterleave_dfs("aabcc", "dbbca", "aadbbbaccc"));

    System.out.println(obj.isInterleave("", "", ""));
    System.out.println(obj.isInterleave_dfs("", "", ""));
  }

}
