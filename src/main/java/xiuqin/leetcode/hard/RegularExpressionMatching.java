package xiuqin.leetcode.hard;

/**
 * https://oj.leetcode.com/problems/regular-expression-matching/
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

    public boolean isMatch_1(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        if (p.length() > 1 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p));
        } else {
            return !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1));
        }
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
    }
}
