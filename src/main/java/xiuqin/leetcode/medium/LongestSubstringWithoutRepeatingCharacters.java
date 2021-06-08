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
