package xiuqin.leetcode.easy;

/**
 * https://oj.leetcode.com/problems/palindrome-number/
 * 9. 回文数
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 * <p>
 * 示例 1：
 * 输入：x = 121
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * <p>
 * 示例 3：
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 * <p>
 * 示例 4：
 * 输入：x = -101
 * 输出：false
 *  
 * 提示：
 * -2^31 <= x <= 2^31 - 1
 *  
 * 进阶：你能不将整数转为字符串来解决这个问题吗？
 */
public class PalindromeNumber {
    /**
     * The simple way is :
     * Reverse x to reverseX and judge whether reverseX is equal to x
     * For example:
     * x is 1234321, then reverseX is 1234321, they are equal, so 1234321 is palindrome
     * x is 1234123, then reverseX is 3214321, they are not equal, so 1234123 is not palindrome
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        if (x < 10) {
            return true;
        }

        int n = x;
        int reverseX = 0;
        while (n > 0) {
            reverseX = 10 * reverseX + n % 10;
            n /= 10;
        }

        if (reverseX == x) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }

        if (s.length() == 1) {
            return true;
        }

        for (int i = 0; i < s.length() / 2; i++) {
            char left = s.charAt(i);
            char right = s.charAt(s.length() - 1 - i);
            if (left != right) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        PalindromeNumber obj = new PalindromeNumber();
        System.out.println(obj.isPalindrome(1));
        System.out.println(obj.isPalindrome(1234321));
        System.out.println(obj.isPalindrome(-1234321));
        System.out.println(obj.isPalindrome(1234123));
        System.out.println(obj.isPalindrome(123321));

        System.out.println("----------------------------");
        System.out.println(obj.isPalindrome("1"));
        System.out.println(obj.isPalindrome("1234321"));
        System.out.println(obj.isPalindrome("-1234321"));
        System.out.println(obj.isPalindrome("1234123"));
        System.out.println(obj.isPalindrome("123321"));
    }
}
