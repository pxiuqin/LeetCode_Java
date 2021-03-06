package xiuqin.leetcode.medium;

/**
 * https://leetcode.com/problems/integer-to-roman/
 * 12. 整数转罗马数字
 * 罗马数字包含以下七种字符：I，V，X，L，C，D和M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做II，即为两个并列的 1。12 写做XII，即为X+II。 27 写做XXVII, 即为XX+V+II。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
 * 给你一个整数，将其转为罗马数字。
 * <p>
 * 示例1:
 * 输入:num = 3
 * 输出: "III"
 * <p>
 * 示例2:
 * 输入:num = 4
 * 输出: "IV"
 * <p>
 * 示例3:
 * 输入:num = 9
 * 输出: "IX"
 * <p>
 * 示例4:
 * 输入:num = 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * <p>
 * 示例5:
 * 输入:num = 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * <p>
 * 提示：
 * 1 <= num <= 3999
 */
public class IntegerToRoman {
    //greeding algorithm
    public String intToRoman(int num) {
        String symbol[] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int value[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String result = "";

        for (int i = 0; num != 0; i++) {
            while (num >= value[i]) {
                num -= value[i];
                result += symbol[i];
            }
        }

        return result;
    }

    //doc/img/intToRoman2.jpg
    public String intToRoman2(int num) {

        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[num / 1000] + hundreds[num % 1000 / 100] + tens[num % 100 / 10] + ones[num % 10];
    }

    public static void main(String[] args) {
        IntegerToRoman obj = new IntegerToRoman();

        System.out.println(obj.intToRoman(3));
        System.out.println(obj.intToRoman(4));
        System.out.println(obj.intToRoman(9));
        System.out.println(obj.intToRoman(58));
        System.out.println(obj.intToRoman(1994));

        System.out.println(obj.intToRoman2(3));
        System.out.println(obj.intToRoman2(4));
        System.out.println(obj.intToRoman2(9));
        System.out.println(obj.intToRoman2(58));
        System.out.println(obj.intToRoman2(1994));
    }
}
