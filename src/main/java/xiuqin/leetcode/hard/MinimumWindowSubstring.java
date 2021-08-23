package xiuqin.leetcode.hard;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"

 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"

 * 示例 3:
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 *
 * 提示：
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 *
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 *
 * <p>
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).
 * <p>
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * <p>
 * Minimum window is "BANC".
 * <p>
 * Note:
 * <p>
 * > If there is no such window in S that covers all characters in T,
 * return the emtpy string "".
 * <p>
 * > If there are multiple such windows, you are guaranteed that there
 * will always be only one unique minimum window in S.
 * 解题思路其实就是通过双指针维持一个Window，窗口右指针向右扩张用来找到包含子串为目的，窗口左指针向右收缩以使子串最小。
 * 典型的滑动窗口方法的实现。
 */
public class MinimumWindowSubstring {
  static int MAX_CHARS = 256;
  static int NOT_EXISTED = -1;
  static int NOT_FOUND = 0;

  public String minWindow(String s, String t) {
    String win = "";
    if (s.length() <= 0 || t.length() <= 0 || t.length() > s.length()) {
      return win;
    }

    /*
     * Declare two "hash map" for ASCII chars
     *   window[]: represents the char found in string S
     *   dict[]: stores the chars in string T
     */
    int window[] = new int[MAX_CHARS];
    int dict[] = new int[MAX_CHARS];

    for (int i = 0; i < MAX_CHARS; i++) {
      dict[i] = NOT_EXISTED;
      window[i] = NOT_EXISTED;
    }

    /*
     *  Go through the T, and inital the dict[] and window[]
     *  Notes: a same char can be appeared multiple times.
     */
    for (int i = 0; i < t.length(); i++) {
      int index = t.charAt(i);
      if (dict[index] == NOT_EXISTED) {
        dict[index] = 1;
      } else {
        dict[index]++;
      }

      window[index] = NOT_FOUND;
    }

    int start = -1;
    int winSize = Integer.MAX_VALUE;
    int letterFound = 0;
    int left = 0;

    for (int right = 0; right < s.length(); right++) {
      if (dict[s.charAt(right)] == NOT_EXISTED) {
        continue;
      }

      /* if s[i] is existed in `t` */
      char chr = s.charAt(right);
      window[chr]++;

      /* if one char has been found enough times, then do not do letterFound++ */
      if (window[chr] <= dict[chr]) {
        letterFound++;
      }

      if (letterFound >= t.length()) {
        /*
         * Find the left of the window - try to make the window smaller
         * 1) windows[S[left]] == NOT_EXISTED  ===> the char at the `left` is not in T
         * 2) window[S[left]] > dict[S[left]]   ===> a same char appeared more than excepted.
         */
        char chl = s.charAt(left);
        while (window[chl] == NOT_EXISTED || window[chl] > dict[chl]) {
          if (dict[chl] != NOT_EXISTED) {
            //move the left of window
            window[chl]--;
            // reduce the number of letters found
            if (window[chl] < dict[chl]) {
              letterFound--;
            }
          }
          chl = s.charAt(++left);
        }

        /* Calculate the minimized window size */
        if (winSize > right - left + 1) {
          start = left;
          winSize = right - left + 1;
        }

      }
    }

    if (start >= 0 && winSize > 0) {
      win = s.substring(start, start + winSize);
    }

    return win;
  }

  public String minWindow2(String S, String T) {
    int[] srcHash = new int[MAX_CHARS];

    // 记录目标字符串每个字母出现次数
    for (int i = 0; i < T.length(); i++) {
      srcHash[T.charAt(i)]++;
    }

    int start = 0, i = 0;

    // 用于记录窗口内每个字母出现次数
    int[] destHash = new int[MAX_CHARS];

    int found = 0;
    int begin = -1, end = S.length(), minLength = S.length();

    for (start = i = 0; i < S.length(); i++) {
      // 每来一个字符给它的出现次数加1
      destHash[S.charAt(i)]++;

      // 如果加1后这个字符的数量不超过目标串中该字符的数量，则找到了一个匹配字符
      if (destHash[S.charAt(i)] <= srcHash[S.charAt(i)]) {
        found++;
      }

      // 如果找到的匹配字符数等于目标串长度，说明找到了一个符合要求的子串
      if (found == T.length()) {
        // 将开头没用的都跳过，没用是指该字符出现次数超过了目标串中出现的次数，并把它们出现次数都减1
        while (start < i && destHash[S.charAt(start)] > srcHash[S.charAt(start)]) {
          destHash[S.charAt(start)]--;
          start++;
        }

        // 这时候start指向该子串开头的字母，判断该子串长度
        if (i - start < minLength) {
          minLength = i - start;
          begin = start;
          end = i;
        }

        // 把开头的这个匹配字符跳过，并将匹配字符数减1
        destHash[S.charAt(start)]--;
        found--;

        // 子串起始位置加1，我们开始看下一个子串了
        start++;
      }
    }

    // 如果begin没有修改过，返回空
    return begin == -1 ? "" : S.substring(begin, end + 1);
  }

  public static void main(String[] args) {
    MinimumWindowSubstring obj = new MinimumWindowSubstring();

    System.out.println(obj.minWindow("ADOBECODEBANC", "ABC"));
    System.out.println(obj.minWindow2("ADOBECODEBANC", "ABC"));
    System.out.println(obj.minWindow("ADOBECODEBANC", "ABCD"));
    System.out.println(obj.minWindow2("ADOBECODEBANC", "ABCD"));
  }
}
