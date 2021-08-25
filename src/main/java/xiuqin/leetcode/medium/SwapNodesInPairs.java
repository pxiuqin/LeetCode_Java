package xiuqin.leetcode.medium;

/**
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * <p>
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：head = [1]
 * 输出：[1]
 * <p>
 * 提示：
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 * <p>
 * 进阶：你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。）
 */
public class SwapNodesInPairs {
  /*just swap the node's value instead of node*/
  ListNode swapPairs1(ListNode head) {
    for (ListNode p = head; p != null && p.next != null; p = p.next.next) {
      int n = p.val;
      p.val = p.next.val;
      p.next.val = n;
    }
    return head;
  }

  /*swap the list nodes physically*/
  ListNode swapPairs2(ListNode head) {
    ListNode h = null;

    //using `p` to traverse the linked list
    for (ListNode p = head; p != null && p.next != null; p = p.next) {
      //`n` is `p`'s next node, and swap `p` and `n` physcially
      ListNode n = p.next;
      p.next = n.next;
      n.next = p;

      //using `h` as `p`'s previous node
      if (h != null) {
        h.next = n;
      }
      h = p;

      //determin the really 'head' pointer
      if (head == p) {
        head = n;
      }
    }

    return head;
  }

  ListNode swapPairs3(ListNode head) {
    // Three pointers point current, previous and next node.
    ListNode curr = head, prev = null, next = null;

    while (curr != null && curr.next != null) {
      next = curr.next;

      //swap nodes
      curr.next = next.next;
      if (null == prev) {
        head = next;
        prev = next;
      } else {
        prev.next = next;
      }

      next.next = curr;

      //set the pointers to next place.
      prev = curr;
      curr = curr.next;
    }

    return head;
  }

  public static void main(String[] args) {
    SwapNodesInPairs obj = new SwapNodesInPairs();

    ListNode d= ListNode.createList(new int[]{1,2,3,4});
    ListNode result = obj.swapPairs3(d);
    ListNode.printList(result);
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
