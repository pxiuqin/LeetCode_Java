package xiuqin.leetcode.easy;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 * 83. 删除排序链表中的重复元素
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 * 返回同样按升序排列的结果链表。
 * <p>
 * 示例 1：
 * img(doc/img/list1.jpg)
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 * <p>
 * 示例 2：
 * img(doc/img/list1.jpg)
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 * <p>
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 */
public class RemoveDuplicatesFromSortedList {
  ListNode deleteDuplicates(ListNode head) {
    for (ListNode p = head; p != null && p.next != null; ) {
      if (p.val == p.next.val) {
        p.next = p.next.next;
        continue;
      }
      p = p.next;
    }

    return head;
  }

  public static void main(String[] args) {
    RemoveDuplicatesFromSortedList obj = new RemoveDuplicatesFromSortedList();

    ListNode test = ListNode.createList(new int[]{1, 1, 2});
    ListNode.printList(obj.deleteDuplicates(test));

    test = ListNode.createList(new int[]{1, 1, 2, 3, 3});
    ListNode.printList(obj.deleteDuplicates(test));
  }
}

class ListNode {
  int val;
  ListNode next;

  public ListNode() {
  }

  public ListNode(int val) {
    this.val = val;
  }

  public static void printList(ListNode h) {
    while (h != null) {
      System.out.println(h.val);
      h = h.next;
    }
    System.out.println();
  }

  public static ListNode createList(int a[]) {
    int n = a.length;
    ListNode p = new ListNode();
    ListNode head = p;

    for (int i = 0; i < n; i++) {
      p.next = new ListNode(a[i]);
      p = p.next;
    }

    return head.next;
  }
}
