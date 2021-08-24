package xiuqin.leetcode.medium;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 * 82. 删除排序链表中的重复元素 II
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中没有重复出现的数字。
 * 返回同样按升序排列的结果链表。
 * <p>
 * 示例 1：
 * img(doc/img/linkedlist1.jpg)
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 * <p>
 * 示例 2：
 * img(doc/img/linkedlist2.jpg)
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 * <p>
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 */
public class RemoveDuplicatesFromSortedListII {
  ListNode deleteDuplicates(ListNode head) {
    ListNode fake = new ListNode();
    fake.val = -1;
    fake.next = head;
    head = fake;

    ListNode tail = head;

    boolean dup = false;
    for (ListNode p = head.next; p != null && p.next != null; p = p.next) {
      if (dup == false && p.val == p.next.val) {
        dup = true;
        continue;
      }
      if (dup == true && p.val != p.next.val) {
        dup = false;
        tail.next = p.next;
        continue;
      }
      if (dup == false) {
        tail = p;
      }
    }

    if (dup == true) {
      tail.next = null;
    }

    return head.next;
  }

  public static void main(String args[]) {
    RemoveDuplicatesFromSortedListII obj = new RemoveDuplicatesFromSortedListII();

    ListNode result = obj.deleteDuplicates(createLinkNode2());
    while (result != null) {
      System.out.println(result.val);
      result = result.next;
    }
  }

  public static ListNode createLinkNode() {
    //1,2,3,3,4,4,5
    ListNode a5 = new ListNode();
    a5.val = 5;

    ListNode a4 = new ListNode();
    a4.val = 4;
    a4.next = a5;

    ListNode a4_0 = new ListNode();
    a4_0.val = 4;
    a4_0.next = a4;

    ListNode a3 = new ListNode();
    a3.val = 3;
    a3.next = a4_0;

    ListNode a3_0 = new ListNode();
    a3_0.val = 3;
    a3_0.next = a3;

    ListNode a2 = new ListNode();
    a2.val = 2;
    a2.next = a3_0;

    ListNode a1 = new ListNode();
    a1.val = 1;
    a1.next = a2;

    return a1;
  }

  public static ListNode createLinkNode2() {
    //1,1,1,2,3
    ListNode a3 = new ListNode();
    a3.val = 3;

    ListNode a2 = new ListNode();
    a2.val = 2;
    a2.next = a3;

    ListNode a1_2 = new ListNode();
    a1_2.val = 1;
    a1_2.next = a2;

    ListNode a1_1 = new ListNode();
    a1_1.val = 1;
    a1_1.next = a1_2;

    ListNode a1 = new ListNode();
    a1.val = 1;
    a1.next = a1_1;

    return a1;
  }
}
