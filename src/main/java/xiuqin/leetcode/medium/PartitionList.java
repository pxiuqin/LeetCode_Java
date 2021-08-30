package xiuqin.leetcode.medium;

import java.util.List;

/**
 * https://leetcode.com/problems/partition-list/
 * 86. 分隔链表
 * <p>
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * <p>
 * 示例 1：
 * img(doc/img/partition.jpg)
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * <p>
 * 示例 2：
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 * <p>
 * 提示：
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 */
public class PartitionList {
  ListNode partition(ListNode head, int x) {
    ListNode fakeHead = new ListNode(0);
    fakeHead.next = head;
    head = fakeHead;

    ListNode pos = null;
    for (ListNode p = head; p != null && p.next != null; ) {
      if (pos == null && p.next.val >= x) {
        pos = p;
        p = p.next;
        continue;
      }

      if (pos != null && p.next.val < x) {
        ListNode pNext = p.next;  //current value: < x
        p.next = pNext.next;   //next value
        pNext.next = pos.next;  //insert value to pos
        pos.next = pNext;  //update next pos
        //pos=pNext;
        continue;
      }

      p = p.next;
    }

    return head.next;
  }

  public static void main(String[] args) {
    PartitionList obj = new PartitionList();

    ListNode.printList(obj.partition(ListNode.createList(new int[]{1, 4, 3, 2, 5, 2}), 3));
    ListNode.printList(obj.partition(ListNode.createList(new int[]{2, 1}), 2));
  }
}
