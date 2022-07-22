package xiuqin.leetcode.easy;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/longest-common-prefix/
 * 14.最长共同前缀
 * Write a function to find the longest common prefix string amongst an array of
 * strings.
 * 
 * If there is no common prefix, return an empty string "".
 * 
 * Example 1:
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 *
 * Example 2:
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 * 这题有好几种解法，个人认为会1，2的解法就可以了，但这种多方法解题的思路可以好好学习一下。具体可参考：Longest Common Prefix
 * 
 * 1. 一个一个字符串取比较word by word matching（横向扫描）：
 * 先拿前2个，从第一位开始比较，直到发现有不同的字符，此时前面一样的字符串在去和后面的字符串比较，直到结束。可以用递归。
 * Time: O(n*m) (n是字符串个数，m是字符串最长长度) Space: O(m)
 * 解法参考图片：img\0-100\longest-common-prefix-1.png
 * 
 * 2. 一个字符一个字符的比较character by character matching（纵向扫描）：
 * 所有的字符串同时比较第1个，第2个.......，发现有不同的出现，之前一样的就是找到的最长共同前缀。Time: O(n*m)
 * (n是字符串个数，m是字符串最长长度) Space: O(m)
 * 解法参考图片：img\0-100\longest-common-prefix-2.png
 * 
 * 3. divide and conquer（分治法）:
 * 把所有字符串分成两组，分别去比较，最后都剩一个的时候，两组在比较。Time： O(n*m), Space: O(n*logm)
 * 解法参考图片：img\0-100\longest-common-prefix-3.png
 * 
 * 4. Binary Search（二分法）：
 * 先找到最短的字符串，然后把这个最短的字符串二分成前面和后面两部分，前面的和所有剩下字符串比较，如果一样在比较后面的，如果有不一样的，则后面的部分不用比较了，前面的部分在二分比较。Time：
 * O(n*m*logm), Space: O(m)
 * 解法参考图片：img\0-100\longest-common-prefix-4.png
 * 
 * 5. 使用Trie:
 * 首先了解Trie数据结构，然后把所有的字符串都执行一遍插入到Trie，然后读取Trie中最后一个没有分支的node，此时之前这些字符就是答案。
 * Time： O(n*m + m), Space: O(26*m*n) ~ O(m*n)
 * 
 * 6. 先对所有子串排序再取首尾两个字串的前缀
 */
public class LongestCommonPrefix {
  public String longestCommonPrefix(List<String> strs) {
    String word = "";
    if (strs.size() <= 0) {
      return word;
    }
    for (int i = 1; i <= strs.get(0).length(); i++) {
      String w = strs.get(0).substring(0, i);
      boolean match = true;

      for (int j = 1; j < strs.size(); j++) {
        if (i > strs.get(j).length() || !w.equals(strs.get(j).substring(0, i))) {
          match = false;
          break;
        }
      }

      if (!match) {
        return word;
      }
      word = w;
    }

    return word;
  }

  public String longestCommonPrefix2(String[] strs) {
    if (strs == null || strs.length == 0) {
      return "";
    }

    String res = new String();
    for (int j = 0; j < strs[0].length(); ++j) {
      char c = strs[0].charAt(j);
      for (int i = 1; i < strs.length; ++i) {
        if (j >= strs[i].length() || strs[i].charAt(j) != c) {
          return res;
        }
      }
      res += Character.toString(c);
    }

    return res;
  }

  public String longestCommonPrefix3(String[] strs, int start, int end) {
    // 如果无法再分的话就把单串返回
    if (start == end) {
      return strs[start];
    } else {
      int mid = (start + end) / 2;
      String left = longestCommonPrefix3(strs, start, mid);
      String right = longestCommonPrefix3(strs, mid + 1, end);

      // 调用横向扫描方法
      return longestCommonPrefix(Arrays.asList(left, right));
    }
  }

  public String longestCommonPrefix4(String[] strs) {
    if (strs == null || strs.length == 0) {
      return "";
    }

    int minLength = strs[0].length();
    for (int i = 1; i < strs.length; i++) {
      minLength = Math.min(minLength, strs[i].length());
    }

    int start = 0, end = minLength;

    while (start < end) {
      int mid = (end - start + 1) / 2 + start;
      String[] newStrs = new String[strs.length];
      for (int i = 0; i < strs.length; i++) {
        newStrs[i] = strs[i].substring(0, mid);
      }

      Boolean isCP = true;
      String subNewStr = newStrs[0];
      for (int i = 1; i < newStrs.length; i++) {
        // 判断所有子串是否相等
        if (!subNewStr.equals(newStrs[i])) {
          isCP = false;
          break;
        }
      }

      // 判断是否为公共前缀
      if (isCP) {
        start = mid;
      } else {
        end = mid - 1;
      }
    }

    return strs[0].substring(0, start);
  }

  public String longestCommonPrefix6(String[] strs) {
    if (strs == null || strs.length == 0) {
      return "";
    }

    Arrays.sort(strs);
    int i = 0;
    int len = Math.min(strs[0].length(), strs[strs.length - 1].length());

    while (i < len && strs[0].charAt(i) == strs[strs.length - 1].charAt(i)) {
      i++;
    }

    return strs[0].substring(0, i);
  }

  public static void main(String[] args) {
    LongestCommonPrefix obj = new LongestCommonPrefix();

    List<String> strings = Arrays.asList("abc", "ab111", "ab234");
    System.out.println(obj.longestCommonPrefix(strings));
    System.out.println(obj.longestCommonPrefix2(new String[] { "abc", "ab111", "ab234" }));
    System.out.println(obj.longestCommonPrefix3(new String[] { "abc", "ab111", "ab234" }, 0, 2));
    System.out.println(obj.longestCommonPrefix4(new String[] { "abc", "ab111", "ab234" }));
    System.out.println(obj.longestCommonPrefix6(new String[] { "abc", "ab111", "ab234" }));
  }

}
