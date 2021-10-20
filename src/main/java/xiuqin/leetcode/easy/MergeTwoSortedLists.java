package xiuqin.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * <p>
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 * <p>
 * 提示：
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 */
public class MergeTwoSortedLists {
  public List<Integer> mergeTwoLists(List<Integer> l1, List<Integer> l2) {
    List<Integer> result = new ArrayList<>();

    l1.sort(Comparator.naturalOrder());
    l2.sort(Comparator.naturalOrder());

    int count = l1.size() + l2.size();
    for (int i = 0; i < count; i++) {

      if (l1.size() > i && l2.size() > i && l1.get(i) < l2.get(i)) {
        result.add(l1.get(i));
        l2.add(i, 0);  //insert 0 to first index
      } else if (l2.size() > i) {
        result.add(l2.get(i));

        if (l1.size() > i) {
          l1.add(i, 0);
        }
      }
    }

    return result;
  }

  public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }
    ListNode head = new ListNode();
    ListNode out = head;
    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        out.next = l1;
        l1 = l1.next;
      } else {
        out.next = l2;
        l2 = l2.next;
      }

      out = out.next;
    }

    out.next = l1 != null ? l1 : l2;

    return head.next;
  }

  public static void main(String[] args) {
    MergeTwoSortedLists obj = new MergeTwoSortedLists();

    List<Integer> l1 = Arrays.asList(1, 2, 4, 3, 5);
    List<Integer> l2 = Arrays.asList(6, 7, 8, 9, 10);
    System.out.println(obj.mergeTwoLists(new ArrayList<>(l1), new ArrayList<>(l2)));

    ListNode result = obj.mergeTwoLists2(ListNode.createList(new int[]{1, 2, 4, 3, 5}), ListNode.createList(new int[]{6, 7, 8, 9, 10}));
    ListNode.printList(result);

    l1 = Arrays.asList(1, 3, 4, 7, 9);
    l2 = Arrays.asList(2, 4, 5, 8, 10);
    System.out.println(obj.mergeTwoLists(new ArrayList<>(l1), new ArrayList<>(l2)));

    result = obj.mergeTwoLists2(ListNode.createList(new int[]{1, 3, 4, 7, 9}), ListNode.createList(new int[]{2, 4, 5, 8, 10}));
    ListNode.printList(result);

    l1 = Arrays.asList(1, 2, 3, 7, 9);
    l2 = Arrays.asList(4, 5, 6, 8, 10);
    System.out.println(obj.mergeTwoLists(new ArrayList<>(l1), new ArrayList<>(l2)));

    l1 = Arrays.asList(1, 2, 4);
    l2 = Arrays.asList(1, 3, 4);
    System.out.println(obj.mergeTwoLists(new ArrayList<>(l1), new ArrayList<>(l2)));

    l1 = Arrays.asList();
    l2 = Arrays.asList();
    System.out.println(obj.mergeTwoLists(new ArrayList<>(l1), new ArrayList<>(l2)));

    l1 = Arrays.asList();
    l2 = Arrays.asList(0);
    System.out.println(obj.mergeTwoLists(new ArrayList<>(l1), new ArrayList<>(l2)));
  }
}
