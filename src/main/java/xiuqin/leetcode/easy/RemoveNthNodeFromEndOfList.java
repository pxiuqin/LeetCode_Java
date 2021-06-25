package xiuqin.leetcode.easy;

import java.util.LinkedList;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 进阶：你能尝试使用一趟扫描实现吗？

 输入：head = [1,2,3,4,5], n = 2
 输出：[1,2,3,5]

 示例 2：
 输入：head = [1], n = 1
 输出：[]

 示例 3：
 输入：head = [1,2], n = 1
 输出：[1]
  
 提示：
 链表中结点的数目为 sz
 1 <= sz <= 30
 0 <= Node.val <= 100
 1 <= n <= sz

 */
public class RemoveNthNodeFromEndOfList {
    LinkedList<Integer> removeNthFromEnd(LinkedList<Integer> head, int n) {
        LinkedList<Integer> result = new LinkedList<>();

        if (head.isEmpty() || n <= 0) {
            return result;
        }

        for (int i = 0; i < head.size(); i++) {
            if (i != (head.size() - n)) {
                result.addLast(head.get(i));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        RemoveNthNodeFromEndOfList obj = new RemoveNthNodeFromEndOfList();

        LinkedList<Integer> test= new LinkedList<>();
        test.add(1);
    }
}
