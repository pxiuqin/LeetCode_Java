package xiuqin.leetcode.medium;

import java.util.HashSet;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * 3.Longest Substring Without Repeating Characters
 * Find the length of the longest substring T of a given string (consists of lowercase
 * letters only) such that every character in T appears no less than k times.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * s = "aaabb", k = 3
 * <p>
 * Output:
 * 3
 * <p>
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * <p>
 * Example 2:
 * <p>
 * Input:
 * s = "ababbc", k = 2
 * <p>
 * Output:
 * 5
 * <p>
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3
 * times.
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

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters obj=new LongestSubstringWithoutRepeatingCharacters();

        System.out.println(obj.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(obj.lengthOfLongestSubstring("aaabb"));
        System.out.println(obj.lengthOfLongestSubstring("ababbc"));
    }
}
