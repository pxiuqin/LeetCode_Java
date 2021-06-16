package xiuqin.leetcode.easy;

/**
 * https://leetcode.com/problems/reverse-integer/
 * 7. 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * 示例 1：
 * 输入：x = 123
 * 输出：321
 * <p>
 * 示例 2：
 * 输入：x = -123
 * 输出：-321
 * <p>
 * 示例 3：
 * 输入：x = 120
 * 输出：21
 * <p>
 * 示例 4：
 * 输入：x = 0
 * 输出：0
 *  
 * 提示：
 * -231 <= x <= 231 - 1
 */
public class ReverseInteger {
    //Why need the INT_MIN be defined like that?
    //Please take a look: http://stackoverflow.com/questions/14695118/2147483648-0-returns-true-in-c
    //static int INT_MAX = 2147483647;
    //static int INT_MIN = -INT_MAX - 1;

    public int reverse(int x) {
        int y = 0;
        int n;
        while (x != 0) {
            n = x % 10;

            //Checking the over/underflow.
            //Actually, it should be y>(INT_MAX-n)/10, but n/10 is 0, so omit it.
            if (y > Integer.MAX_VALUE / 10 || y < Integer.MIN_VALUE / 10) {
                return 0;
            }

            y = y * 10 + n;
            x /= 10;
        }

        return y;
    }

    public static void main(String[] args) {
        ReverseInteger obj = new ReverseInteger();
        System.out.println(obj.reverse(123));
        System.out.println(obj.reverse(-123));
        System.out.println(obj.reverse(120));
        System.out.println(obj.reverse(0));
    }
}
