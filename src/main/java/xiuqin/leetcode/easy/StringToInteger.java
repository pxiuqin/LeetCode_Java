package xiuqin.leetcode.easy;


/**
 * https://oj.leetcode.com/problems/string-to-integer-atoi/
 *
 * 8.Implement atoi to convert a string to an integer.
 *
 * Hint: Carefully consider all possible input cases. If you want a challenge,
 *       please do not see below and ask yourself what are the possible input cases.
 *
 * Notes:
 *   It is intended for this problem to be specified vaguely (ie, no given input specs).
 *   You are responsible to gather all the input requirements up front.
 *
 *
 * Requirements for atoi:
 *
 * The function first discards as many whitespace characters as necessary until the first
 * non-whitespace character is found. Then, starting from this character, takes an optional
 * initial plus or minus sign followed by as many numerical digits as possible, and interprets
 * them as a numerical value.
 *
 * The string can contain additional characters after those that form the integral number,
 * which are ignored and have no effect on the behavior of this function.
 *
 * If the first sequence of non-whitespace characters in str is not a valid integral number,
 * or if no such sequence exists because either str is empty or it contains only whitespace
 * characters, no conversion is performed.
 *
 * If no valid conversion could be performed, a zero value is returned. If the correct value
 * is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648)
 * is returned.
 *
 */
public class StringToInteger {
    public int atoi(String source) {
        int result = 0;

        source=source.trim();

        if (source == null || source.isEmpty()) {
            return result;
        }

        boolean neg = source.charAt(0) == '-' ? true : false;
        if (neg || source.charAt(0) == '+') {
            source = source.substring(1, source.length());
        }

        for (int i = 0; i < source.length(); i++) {
            int digit = source.charAt(i) - '0';
            if (neg) {
                if (-result < (Integer.MIN_VALUE + digit) / 10) {
                    return Integer.MIN_VALUE;
                }
            } else {
                if (result > (Integer.MAX_VALUE - digit) / 10) {
                    return Integer.MAX_VALUE;
                }
            }

            result = result * 10 + digit;
        }

        return neg ? -result : result;
    }

    public static void main(String[] args) {
        StringToInteger obj = new StringToInteger();
        System.out.println(obj.atoi("123"));
        System.out.println(obj.atoi("   123"));
        System.out.println(obj.atoi("+123"));
        System.out.println(obj.atoi(" -123"));
        System.out.println(obj.atoi("123ABC"));
        System.out.println(obj.atoi(" abc123ABC"));
        System.out.println(obj.atoi("2147483647"));
        System.out.println(obj.atoi("-2147483647"));
        System.out.println(obj.atoi("2147483648"));
        System.out.println(obj.atoi("-2147483649"));
    }
}
