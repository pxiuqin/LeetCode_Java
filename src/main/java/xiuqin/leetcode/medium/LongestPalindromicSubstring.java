package xiuqin.leetcode.medium;

/**
 * https://oj.leetcode.com/problems/longest-palindromic-substring/
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 * <p>
 * 示例 3：
 * 输入：s = "a"
 * 输出："a"
 * <p>
 * 示例 4：
 * 输入：s = "ac"
 * 输出："a"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 */
public class LongestPalindromicSubstring {
  private String findPalindrome(String s, int left, int right) {
    int n = s.length();
    while (left >= 0 && right <= n - 1 && s.charAt(left) == s.charAt(right)) {
      left--;
      right++;
    }

    if (left + 1 <= right - left - 1) {
      return s.substring(left + 1, right - left - 1);
    } else {
      return "";
    }
  }

  // This is the common solution.
  // Actuatlly it's faster than DP solution under Leetcode's test
  // the below optimized DP solution need 700ms+, this needs around 250ms.
  public String longestPalindrome_recursive_way(String s) {
    int n = s.length();
    if (n <= 1) {
      return s;
    }

    String longest = "";

    String str;
    for (int i = 0; i < n - 1; i++) {
      str = findPalindrome(s, i, i);
      if (str.length() > longest.length()) {
        longest = str;
      }
      str = findPalindrome(s, i, i + 1);
      if (str.length() > longest.length()) {
        longest = str;
      }
    }

    return longest;
  }


  /************************************************************************/

  private int[] findPalindrome(String s, int left, int right, int start, int len) {
    int n = s.length();

    while (left >= 0 && right <= n - 1 && s.charAt(left) == s.charAt(right)) {
      left--;
      right++;
    }
    if (right - left - 1 > len) {
      len = right - left - 1;
      start = left + 1;
    }

    return new int[]{start, len};
  }

  //The following solution is better than previous solution.
  //Because it remove the sub-string return in findPalindrome().
  public String longestPalindrome_recursive_way2(String s) {
    int n = s.length();
    if (n <= 1) {
      return s;
    }

    int start = 0, len = 0;

    for (int i = 0; i < n - 1; i++) {
      int[] start_len = findPalindrome(s, i, i, start, len);
      start = start_len[0];
      len = start_len[1];
      start_len = findPalindrome(s, i, i + 1, start, len);
      start = start_len[0];
      len = start_len[1];
    }

    return s.substring(start, len);
  }

  /************************************************************************/

  // Time/Memory Limit Exceeded
  public String longestPalindrome_dp_way(String s) {

    String longest = "";

    int n = s.length();
    if (n <= 1) {
      return s;
    }

    //Construct a matrix, and consdier matrix[i][j] as s[i] -> s[j] is Palindrome or not.

    //using char or int could cause the `Memory Limit Error`
    //vector< vector<char> > matrix (n, vector<char>(n));

    //using bool type could cause the `Time Limit Error`
    boolean[][] matrix = new boolean[n][n];

    // Dynamic Programming
    //   1) if i == j, then matrix[i][j] = true;
    //   2) if i != j, then matrix[i][j] = (s[i]==s[j] && matrix[i+1][j-1])
    for (int i = n - 1; i >= 0; i--) {
      for (int j = i; j < n; j++) {
        // The following if statement can be broken to
        //   1) i==j, matrix[i][j] = true
        //   2) the length from i to j is 2 or 3, then, check s[i] == s[j]
        //   3) the length from i to j > 3, then, check s[i]==s[j] && matrix[i+1][j-1]
        if (i == j || (s.charAt(i) == s.charAt(j) && (j - i < 2 || matrix[i + 1][j - 1]))) {
          matrix[i][j] = true;
          if (longest.length() < j - i + 1) {
            if (i < j - i + 1) {
              longest = s.substring(i, j - i + 1);
            }
          }
        }
      }
    }

    return longest;
  }

  /************************************************************************/

  // Optimized DP soltuion can be accepted by LeetCode.
  public String longestPalindrome_dp_opt_way(String s) {

    int n = s.length();
    if (n <= 1) {
      return s;
    }

    //Construct a matrix, and consdier matrix[j][i] as s[i] -> s[j] is Palindrome or not.
    //                                 ------^^^^^^
    //                                 NOTE: it's [j][i] not [i][j]

    //Using vector  could cause the `Time Limit Error`
    //So, use the native array
    boolean[][] matrix = new boolean[n][n];
    int start = 0, len = 0;
    // Dynamic Programming
    //   1) if i == j, then matrix[i][j] = true;
    //   2) if i != j, then matrix[i][j] = (s[i]==s[j] && matrix[i-1][j+1])
    for (int i = 0; i < n; i++) {
      matrix[i][i] = true;
      for (int j = 0; j <= i; j++) {
        // The following if statement can be broken to
        //   1) j==i, matrix[i][j] = true
        //   2) the length from j to i is 2 or 3, then, check s[i] == s[j]
        //   3) the length from j to i > 3, then, check s[i]==s[j] && matrix[i-1][j+1]
        if (i == j || (s.charAt(j) == s.charAt(i) && (i - j < 2 || matrix[i - 1][j + 1]))) {
          matrix[i][j] = true;
          if (len < i - j + 1) {
            start = j;
            len = i - j + 1;
          }
        }
      }
    }

    return s.substring(start, len);
  }

  /************************************************************************/

  public String longestPalindrome(String s) {
    if (s.length() < 2) {
      return s;
    }
    int n = s.length();
    int maxLen = 0;
    int startIndex = 0;
    int i = 0;
    while (i < n) {
      int left = i;
      int right = i;

      //找到当前所要查找的回文串的中间位置
      //但是需要跳过连续的重复的字符
      while (right < n - 1 && s.charAt(right) == s.charAt(right + 1)) {
        right++;
      }
      i = right + 1;

      //从中间开始，向两端探测
      while (right < n - 1 && left > 0 && s.charAt(left - 1) == s.charAt(right + 1)) {
        left--;
        right++;
      }

      //判断找到的回文串是否更长
      int tempLen = right - left + 1;
      if (tempLen > maxLen) {
        startIndex = left;
        maxLen = tempLen;
      }
    }

    return s.substring(startIndex, maxLen);
  }

  public static void main(String[] args) {
    LongestPalindromicSubstring obj = new LongestPalindromicSubstring();

    System.out.println(obj.longestPalindrome("babab"));
    System.out.println(obj.longestPalindrome("cbbd"));
    System.out.println(obj.longestPalindrome("a"));
    System.out.println(obj.longestPalindrome("ac"));
  }
}
