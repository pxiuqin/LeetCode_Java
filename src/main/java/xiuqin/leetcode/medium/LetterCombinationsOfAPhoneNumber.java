package xiuqin.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * ./doc/img/17_telephone_keypad.png
 * <p>
 * 示例 1：
 * <p>
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * 示例 2：
 * 输入：digits = ""
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 * 提示：
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 * <p>
 * <p>
 * Given a digit string, return all possible letter combinations that the number could represent.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * <p>
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * <p>
 * Note:
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
public class LetterCombinationsOfAPhoneNumber {
    public List<String> letterCombinations(String digits) {
        char[][] phone = {{' ', '\0', '\0', '\0'}, //0
                {'\0', '\0', '\0', '\0'}, //1
                {'a', 'b', 'c', '\0'}, //2
                {'d', 'e', 'f', '\0'}, //3
                {'g', 'h', 'i', '\0'}, //4
                {'j', 'k', 'l', '\0'}, //5
                {'m', 'n', 'o', '\0'}, //6
                {'p', 'q', 'r', 's'}, //7
                {'t', 'u', 'v', '\0'}, //8
                {'w', 'x', 'y', 'z'}  //9
        };

        List<String> result = new ArrayList<>();
        if (digits.length() <= 0) {
            return result;
        }

        for (int i = 0; i < digits.length(); i++) {
            List<String> r = new ArrayList<>();
            if (!Character.isDigit(digits.charAt(i))) {
                return r;
            }
            int d = digits.charAt(i) - '0';
            if (result.size() <= 0) {
                for (int j = 0; j < 4 && phone[d][j] != '\0'; j++) {
                    String s = phone[d][j] + "";
                    result.add(s);
                }
                continue;
            }

            for (int j = 0; j < result.size(); j++) {
                for (int k = 0; k < 4 && phone[d][k] != '\0'; k++) {
                    String s = result.get(j) + phone[d][k];
                    r.add(s);
                }
            }

            result = r;
        }

        return result;
    }

    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber obj = new LetterCombinationsOfAPhoneNumber();

        System.out.println(obj.letterCombinations("23"));
        System.out.println(obj.letterCombinations(""));
        System.out.println(obj.letterCombinations("2"));
    }
}
