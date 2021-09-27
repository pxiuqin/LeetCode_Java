package xiuqin.leetcode.medium;

import java.util.Arrays;
import java.util.HashSet;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 *
 * 示例1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
 *     请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。

 * 示例 4:
 * 输入: s = ""
 * 输出: 0
 *
 * 提示：
 * 0 <= s.length <= 5 * 10^4
 * s由英文字母、数字、符号和空格组成
 *
 * 介绍一种线性的算法，也是这类题目最常见的方法。
 * 基本思路是维护一个窗口，每次关注窗口中的字符串，在每次判断中，左窗口和右窗口选择其一向前移动。
 * 同样是维护一个HashSet, 正常情况下移动右窗口，如果没有出现重复则继续移动右窗口，
 * 如果发现重复字符，则说明当前窗口中的串已经不满足要求，继续移动右窗口不可能得到更好的结果，
 * 此时移动左窗口，直到不再有重复字符为止，中间跳过的这些串中不会有更好的结果，
 * 因为他们不是重复就是更短。因为左窗口和右窗口都只向前，所以两个窗口都对每个元素访问不超过一遍，
 * 因此时间复杂度为O(2*n)=O(n),是线性算法。
 * 空间复杂度为HashSet的size,也是O(n).
 */
public class LongestSubstringWithoutRepeatingCharacters {
  public int lengthOfLongestSubstring(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }

    HashSet<Character> set = new HashSet<Character>();
    int max = 0;
    int walker = 0;  //left windows
    int runner = 0;  //right windows

    while (runner < s.length()) {
      if (set.contains(s.charAt(runner))) {
        if (max < runner - walker) {
          max = runner - walker;
        }
        while (s.charAt(walker) != s.charAt(runner)) {
          set.remove(s.charAt(walker));
          walker++;
        }

        walker++;
      } else {
        set.add(s.charAt(runner));
      }

      runner++;
    }

    max = Math.max(max, runner - walker);

    return max;
  }

  public int lengthOfLongestSubstring2(String s) {
    int[] m = new int[256];
    Arrays.fill(m, -1);
    int res = 0, left = -1;
    for (int i = 0; i < s.length(); ++i) {
      left = Math.max(left, m[s.charAt(i)]);
      m[s.charAt(i)] = i;
      res = Math.max(res, i - left);
    }
    return res;
  }

  public int lengthOfLongestSubstring3(String s) {
    int res = 0, left = 0, right = 0;
    HashSet<Character> t = new HashSet<>();
    while (right < s.length()) {
      if (!t.contains(s.charAt(right))) {
        t.add(s.charAt(right++));
        res = Math.max(res, t.size());
      } else {
        t.remove(s.charAt(left++));
      }
    }
    return res;
  }

  public static void main(String[] args) {
    LongestSubstringWithoutRepeatingCharacters obj = new LongestSubstringWithoutRepeatingCharacters();

    System.out.println(obj.lengthOfLongestSubstring("abcabcbb"));
    System.out.println(obj.lengthOfLongestSubstring("aaabb"));
    System.out.println(obj.lengthOfLongestSubstring("ababbc"));

    System.out.println(obj.lengthOfLongestSubstring2("abcabcbb"));
    System.out.println(obj.lengthOfLongestSubstring2("aaabb"));
    System.out.println(obj.lengthOfLongestSubstring2("ababbc"));

    System.out.println(obj.lengthOfLongestSubstring3("abcabcbb"));
    System.out.println(obj.lengthOfLongestSubstring3("aaabb"));
    System.out.println(obj.lengthOfLongestSubstring3("ababbc"));
  }
}
