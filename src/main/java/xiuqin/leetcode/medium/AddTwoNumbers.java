package xiuqin.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/add-two-numbers/
 * 2. 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * You are given two linked lists representing two non-negative numbers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * <p>
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * <p>
 * 示例 2：
 * <p>
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * <p>
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *  
 * 提示：
 * <p>
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 * <p>
 **/

public class AddTwoNumbers {
    public List<Integer> addTwoNumbers(List<Integer> l1, List<Integer> l2) {
        List<Integer> result = new ArrayList<Integer>();

        int x = 0, y = 0, carry = 0, sum = 0;
        int len = l1.size() > l2.size() ? l1.size() : l2.size();
        for (int i = 0; i < len; i++) {
            x = l1.size() > i ? l1.get(i) : 0;
            y = l2.size() > i ? l2.get(i) : 0;

            sum = carry + x + y;
            result.add(sum % 10);

            carry = sum / 10;
        }

        if (carry > 0) {
            result.add(carry % 10);
        }

        return result;
    }

    public static void main(String[] args) {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();

        List<Integer> l1 = Arrays.asList(2, 4, 3);
        List<Integer> l2 = Arrays.asList(5, 6, 4);

        System.out.println(addTwoNumbers.addTwoNumbers(l1, l2));

        l1 = Arrays.asList(0);
        l2 = Arrays.asList(0);

        System.out.println(addTwoNumbers.addTwoNumbers(l1, l2));

        l1 = Arrays.asList(9, 9, 9, 9, 9, 9, 9);
        l2 = Arrays.asList(9, 9, 9, 9);

        System.out.println(addTwoNumbers.addTwoNumbers(l1, l2));
    }
}
