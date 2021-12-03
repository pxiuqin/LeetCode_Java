package xiuqin.leetcode.medium;

/**
 * https://leetcode.com/problems/rotate-list/
 * 61. 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动k个位置。
 * 示例 1：
 * img(doc/img/0-100/rotate1.jpg)
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * <p>
 * 示例 2：
 * img(doc/img/0-100/rotate2.jpg)
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 * <p>
 * 提示：
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 10^9
 */
public class RotateList {
  ListNode rotateRight(ListNode head, int k) {
    if (head == null || k <= 0) {
      return head;
    }

    //find the length of List
    int len = 1;
    ListNode p = head;
    while (p.next != null) {
      p = p.next;
      len++;
    }

    //connect the tail to head
    p.next = head;

    //find the left place (take care the case - k > len)
    k = len - k % len;

    //find the place
    for (int i = 0; i < k; i++) {
      p = p.next;
    }

    //break the list
    head = p.next;
    p.next = null;

    return head;
  }

  public static void main(String[] args) {
    RotateList obj = new RotateList();

    ListNode a0 = new ListNode();
    a0.val = 5;

    ListNode a = new ListNode();
    a.val = 4;
    a.next = a0;

    ListNode b = new ListNode();
    b.val = 3;
    b.next = a;

    ListNode c = new ListNode();
    c.val = 2;
    c.next = b;

    ListNode d = new ListNode();
    d.val = 1;
    d.next = c;

    print(obj.rotateRight(d, 2));

    ListNode n2 = new ListNode();
    n2.val = 2;

    ListNode n1 = new ListNode();
    n1.val = 1;
    n1.next = n2;

    ListNode n0 = new ListNode();
    n0.val = 0;
    n0.next = n1;

    print(obj.rotateRight(n0, 4));

  }

  public static void print(ListNode result) {
    while (result != null) {
      System.out.println(result.val);
      result = result.next;
    }
    System.out.println();
  }
}


