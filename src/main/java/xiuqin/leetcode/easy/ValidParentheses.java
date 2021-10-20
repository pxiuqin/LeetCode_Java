package xiuqin.leetcode.easy;

import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 * <p>
 * 示例2：
 * 输入：s = "()[]{}"
 * 输出：true
 * <p>
 * 示例3：
 * 输入：s = "(]"
 * 输出：false
 * <p>
 * 示例4：
 * 输入：s = "([)]"
 * 输出：false
 * <p>
 * 示例5：
 * 输入：s = "{[]}"
 * 输出：true
 * <p>
 * 提示：
 * 1 <= s.length <= 10^4
 * s 仅由括号 '()[]{}' 组成
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '{' || ch == '[' || ch == '(') {
                st.push(ch);
            } else if (ch == '}' || ch == ']' || ch == ')') {
                if (st.empty()) {
                    return false;
                }

                char sch = st.peek();
                if ((sch == '{' && ch == '}') || (sch == '[' && ch == ']') || (sch == '(' && ch == ')')) {
                    st.pop();
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        return st.empty();
    }

    public static void main(String[] args) {
        ValidParentheses obj = new ValidParentheses();

        System.out.println(obj.isValid("()"));
        System.out.println(obj.isValid("()[]{}"));
        System.out.println(obj.isValid("(]"));
        System.out.println(obj.isValid("([)]"));
        System.out.println(obj.isValid("{[]}"));
    }
}
