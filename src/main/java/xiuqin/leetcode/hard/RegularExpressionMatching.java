package xiuqin.leetcode.hard;

/**
 * https://oj.leetcode.com/problems/regular-expression-matching/
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * 示例 1：
 * 输入：s = "aa" p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * <p>
 * 示例 2:
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * <p>
 * 示例 3：
 * 输入：s = "ab" p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * <p>
 * 示例 4：
 * 输入：s = "aab" p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * <p>
 * 示例 5：
 * 输入：s = "mississippi" p = "mis*is*p*."
 * 输出：false
 *  
 * 提示：
 * 0 <= s.length <= 20
 * 0 <= p.length <= 30
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 * <p>
 * <p>
 * Implement regular expression matching with support for '.' and '*'.
 * <p>
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 * <p>
 * 大概思路如下：
 * - 若p为空，若s也为空，返回 true，反之返回 false。
 * - 若p的长度为1，若s长度也为1，且相同或是p为 '.' 则返回 true，反之返回 false。
 * - 若p的第二个字符不为*，若此时s为空返回 false，否则判断首字符是否匹配，且从各自的第二个字符开始调用递归函数匹配。
 * - 若p的第二个字符为*，进行下列循环，条件是若s不为空且首字符匹配（包括 p[0] 为点），调用递归函数匹配s和去掉前两个字符的p（这样做的原因是假设此时的星号的作用是让前面的字符出现0次，验证是否匹配），若匹配返回 true，否则s去掉首字母（因为此时首字母匹配了，我们可以去掉s的首字母，而p由于星号的作用，可以有任意个首字母，所以不需要去掉），继续进行循环。
 * - 返回调用递归函数匹配s和去掉前两个字符的p的结果（这么做的原因是处理星号无法匹配的内容，比如 s="ab", p="a*b"，直接进入 while 循环后，我们发现 "ab" 和 "b" 不匹配，所以s变成 "b"，那么此时跳出循环后，就到最后的 return 来比较 "b" 和 "b" 了，返回 true。再举个例子，比如 s="", p="a*"，由于s为空，不会进入任何的 if 和 while，只能到最后的 return 来比较了，返回 true，正确）。
 */
public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        //若p为空，若s也为空，返回 true，反之返回 false。
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        //p's length 1 is special case
        //若p的长度为1，若s长度也为1，且相同或是p为 '.' 则返回 true，反之返回 false。
        if (p.length() == 1) {
            return s.length() == 1 && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0));
        }

        //若p的第二个字符不为*，若此时s为空返回 false，否则判断首字符是否匹配，且从各自的第二个字符开始调用递归函数匹配。
        if (p.charAt(1) != '*') {
            if (s.isEmpty() || (p.charAt(0) != '.' && s.charAt(0) != p.charAt(0))) {
                return false;
            }
            return isMatch(s.substring(1), p.substring(1));
        } else {
            int len = s.length();
            int i = 0;

            //若p的第二个字符为*，进行下列循环，条件是若s不为空且首字符匹配（包括 p[0] 为点），调用递归函数匹配s和去掉前两个字符的p（这样做的原因是假设此时的星号的作用是让前面的字符出现0次，验证是否匹配），若匹配返回 true，否则s去掉首字母（因为此时首字母匹配了，我们可以去掉s的首字母，而p由于星号的作用，可以有任意个首字母，所以不需要去掉），继续进行循环。
            while (i < len && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))) {
                if (isMatch(s.substring(i), p.substring(2))) {
                    return true;
                }
                i++;
            }

            return isMatch(s.substring(i), p.substring(2));
        }
    }

    public boolean isMatch_Sample(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        if (p.length() > 1 && p.charAt(1) == '*') {
            return isMatch_Sample(s, p.substring(2)) || (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch_Sample(s.substring(1), p));
        } else {
            return !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch_Sample(s.substring(1), p.substring(1));
        }
    }

    /**
     * 我们也可以用 DP 来解，定义一个二维的 DP 数组，其中 dp[i][j] 表示 s[0,i) 和 p[0,j) 是否 match，然后有下面三种情况
     * (下面部分摘自这个帖子)：https://discuss.leetcode.com/topic/17852/9-lines-16ms-c-dp-solutions-with-explanations
     * <p>
     * 1.  P[i][j] = P[i - 1][j - 1], if p[j - 1] != '*' && (s[i - 1] == p[j - 1] || p[j - 1] == '.');
     * 2.  P[i][j] = P[i][j - 2], if p[j - 1] == '*' and the pattern repeats for 0 times;
     * 3.  P[i][j] = P[i - 1][j] && (s[i - 1] == p[j - 2] || p[j - 2] == '.'), if p[j - 1] == '*' and the pattern repeats for at least 1 times.
     */
    public boolean isMatch_DP(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (j > 1 && p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2] || (i > 0 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[i - 1][j]);
                } else {
                    dp[i][j] = i > 0 && dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        RegularExpressionMatching obj = new RegularExpressionMatching();
        System.out.println("'',a->" + obj.isMatch("", "a"));
        System.out.println("'',*->" + obj.isMatch("", "*"));
        System.out.println("a,''->" + obj.isMatch("a", ""));
        System.out.println("a,*->" + obj.isMatch("a", "*"));
        System.out.println("a,.->" + obj.isMatch("a", "."));
        System.out.println("aa,.->" + obj.isMatch("aa", "."));
        System.out.println("'',''->" + obj.isMatch("", ""));
        System.out.println("aa,a->" + obj.isMatch("aa", "a"));
        System.out.println("aa,aa->" + obj.isMatch("aa", "aa"));
        System.out.println("aaa,aa->" + obj.isMatch("aaa", "aa"));
        System.out.println("aa,a*->" + obj.isMatch("", "a*"));
        System.out.println("aa,a*->" + obj.isMatch("aa", "a*"));
        System.out.println("aa,.*->" + obj.isMatch("aa", ".*"));
        System.out.println("ab,.*->" + obj.isMatch("ab", ".*"));
        System.out.println("acb,a*b->" + obj.isMatch("acb", "a*b"));
        System.out.println("aab,c*a*b->" + obj.isMatch("aab", "c*a*b"));
        System.out.println("acb,c*a*b->" + obj.isMatch("acb", "c*a*b"));
        System.out.println("acb,ca*b->" + obj.isMatch("acb", "ca*b"));
        System.out.println("acb,c*a*cb->" + obj.isMatch("acb", "c*a*cb"));
        System.out.println("aab,c.a.b->" + obj.isMatch("aab", "c.a.b"));
        System.out.println("mississippi,mis*is*p*.->" + obj.isMatch_Sample("mississippi", "mis*is*p*."));
        System.out.println("acb,c*a*cb->" + obj.isMatch_DP("acb", "c*a*cb"));
        System.out.println("aab,c.a.b->" + obj.isMatch_DP("aab", "c.a.b"));
    }
}
