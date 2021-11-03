package xiuqin.leetcode.hard;


import java.util.Stack;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/
 * <p>
 * 32. 最长有效括号
 * 给你一个只包含 '('和 ')'的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * <p>
 * 示例 1：
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * <p>
 * 示例 2：
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * <p>
 * 示例 3：
 * 输入：s = ""
 * 输出：0
 * <p>
 * 提示：
 * 0 <= s.length <= 3 * 10^4
 * s[i] 为 '(' 或 ')'
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        int lastError = -1;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                if (stack.size() > 0) {
                    stack.pop();
                    int len;
                    if (stack.size() == 0) {
                        len = i - lastError;
                    } else {
                        len = i - stack.peek();   //返回栈顶的元素
                    }

                    if (len > maxLen) {
                        maxLen = len;
                    }
                } else {
                    lastError = i;
                }
            }
        }

        return maxLen;
    }

    public static void main(String[] args){
        LongestValidParentheses obj=new LongestValidParentheses();

        System.out.println(obj.longestValidParentheses("(()"));
        System.out.println(obj.longestValidParentheses(")()())"));
        System.out.println(obj.longestValidParentheses(""));
    }

}
