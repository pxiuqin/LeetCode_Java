package xiuqin.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合。
 * <p>
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 *  
 * 提示：
 * 1 <= n <= 8
 * <p>
 * 思路：
 * 这道题可以用回溯法解决，即穷举出所有可能，再按照规则过滤结果。但是更好的办法是在回溯的过程中，就进行规则的判断，进行剪枝操作：
 * 只有在我们知道序列仍然保持有效时才添加 '(' or ')'，我们可以通过跟踪到目前为止放置的左括号和右括号的数目来做到这一点，
 * 如果我们还剩一个位置，我们可以开始放一个左括号。 如果它不超过左括号的数量，我们可以放一个右括号。
 */
public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();
        String s = "";
        //generator(result, n, n, s);
        generator(result, s, 0, 0, n);
        return result;
    }

    void generator(List<String> result, int left, int right, String s) {
        if (left == 0 && right == 0) {
            result.add(s);
            return;
        }
        if (left > 0) {
            generator(result, left - 1, right, s + '(');
        }
        if (right > 0 && right > left) {
            generator(result, left, right - 1, s + ')');
        }
    }

    /**
     * 功能方法.
     *
     * @param result 有效括号集合
     * @param temp   字符串
     * @param open   左括号个数
     * @param close  右括号个数
     * @param n      括号个数
     */
    private void generator(List<String> result, String temp, int open, int close, int n) {
        if (open == n && close == n) {
            result.add(temp);
            return;
        }
        if (open < n) {
            generator(result, temp + "(", open + 1, close, n);
        }
        if (close < open) {
            generator(result, temp + ")", open, close + 1, n);
        }
    }

    public static void main(String[] args) {
        GenerateParentheses obj = new GenerateParentheses();
        System.out.println(obj.generateParenthesis(3));
        System.out.println(obj.generateParenthesis(1));
    }
}
