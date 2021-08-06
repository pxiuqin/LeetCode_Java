package xiuqin.leetcode.easy;

/**
 * https://leetcode.com/problems/length-of-last-word/
 * 58. 最后一个单词的长度
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
 * <p>
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 * <p>
 * 示例 1：
 * 输入：s = "Hello World"
 * 输出：5
 * <p>
 * 示例 2：
 * 输入：s = "   fly me   to   the moon  "
 * 输出：4
 * <p>
 * 示例 3：
 * 输入：s = "luffy is still joyboy"
 * 输出：6
 * <p>
 * 提示：
 * 1 <= s.length <= 10^4
 * s 仅有英文字母和空格 ' ' 组成
 * s 中至少存在一个单词
 */
public class LengthOfLastWord {
  public int lengthOfLastWord(String s) {
    // don't forget rangeCheck
    if (s == null || s.length() == 0) {
      return 0;
    }

    int len = s.length();
    int i = len - 1;
    while (i >= 0 && s.charAt(i) == ' ') {
      i--;
    }

    if (i == -1) {
      return 0;
    }

    int count = 0;
    while (i >= 0 && s.charAt(i) != ' ') {
      count++;
      i--;
    }

    return count;
  }

  public static void main(String[] args) {
    LengthOfLastWord obj = new LengthOfLastWord();

    System.out.println(obj.lengthOfLastWord("Hello World"));
    System.out.println(obj.lengthOfLastWord("   fly me   to   the moon  "));
    System.out.println(obj.lengthOfLastWord("luffy is still joyboy"));
  }
}
