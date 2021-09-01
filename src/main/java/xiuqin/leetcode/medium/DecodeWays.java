package xiuqin.leetcode.medium;

/**
 * https://leetcode.com/problems/decode-ways/
 * 91. 解码方法
 * 一条包含字母A-Z 的消息通过以下映射进行了 编码 ：
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 * <p>
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为 (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 * <p>
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 * <p>
 * 题目数据保证答案肯定是一个 32 位 的整数。
 * <p>
 * 示例 1：
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 * <p>
 * 示例 2：
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * <p>
 * 示例 3：
 * 输入：s = "0"
 * 输出：0
 * 解释：没有字符映射到以 0 开头的数字。
 * 含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
 * 由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
 * <p>
 * 示例 4：
 * 输入：s = "06"
 * 输出：0
 * 解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。
 * <p>
 * 提示：
 * 1 <= s.length <= 100
 * s 只包含数字，并且可能包含前导零。
 * <p>
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * <p>
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * <p>
 * For example,
 * Given encoded message "12",
 * it could be decoded as "AB" (1 2) or "L" (12).
 * <p>
 * The number of ways decoding "12" is 2.
 */
public class DecodeWays {
  //Count[i] = Count[i-1]              if S[i-1] is a valid char (not '0')
  //         = Count[i-1]+ Count[i-2]  if S[i-1] and S[i-2] together is still a valid char (10 to 26).
  private int check(char ch) {
    //check 0 or not
    return (!Character.isDigit(ch) || ch == '0') ? 0 : 1;  //0 unable match char
  }

  private int check(char ch1, char ch2) {
    //check it's between 10 and 26
    return (ch1 == '1' || (ch1 == '2' && ch2 <= '6')) ? 1 : 0;
  }

  public int numDecodings(String s) {
    if (s.length() <= 0) {
      return 0;
    }

    if (s.length() == 1) {
      return check(s.charAt(0));
    }

    int[] dp = new int[s.length()];

    dp[0] = check(s.charAt(0));
    dp[1] = check(s.charAt(0)) * check(s.charAt(1)) + check(s.charAt(0), s.charAt(1));

    for(int i=2;i<s.length();i++){
      if(!Character.isDigit(s.charAt(i))){
        break;
      }

      if(check(s.charAt(i))>0){
        dp[i]=dp[i-1];  //keep previous value
      }

      if(check(s.charAt(i-1), s.charAt(i))>0){
        dp[i]+=dp[i-2];  //add one to the original value
      }
    }

    int result = dp[s.length() - 1];

    return result;
  }

  public static void main(String[] args) {
    DecodeWays obj = new DecodeWays();

    System.out.println(obj.numDecodings("12"));
    System.out.println(obj.numDecodings("226"));
    System.out.println(obj.numDecodings("0"));
    System.out.println(obj.numDecodings("06"));
  }
}
