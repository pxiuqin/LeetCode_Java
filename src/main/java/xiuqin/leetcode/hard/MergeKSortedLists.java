package xiuqin.leetcode.hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * <p>
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[] 
 * <p>
 * 提示：
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 * <p>
 * 第一种方法:思路是先分成两个子任务，然后递归求子任务，最后回溯回来。先把k个list分成两半，然后继续划分，直到剩下两个list就合并起来
 * <p>
 * 第二种方法:这种方法用到了堆的数据结构，思路比较难想到，但是其实原理比较简单。
 * 维护一个大小为k的堆，每次取堆顶的最小元素放到结果中，然后读取该元素的下一个元素放入堆中，重新维护好。
 * 因为每个链表是有序的，每次又是去当前k个元素中最小的，所以当所有链表都读完时结束，这个时候所有元素按从小到大放在结果链表中。
 * 这个算法每个元素要读取一次，即是k*n次，然后每次读取元素要把新元素插入堆中要logk的复杂度，所以总时间复杂度是O(nklogk)。空间复杂度是堆的大小，即为O(k)
 */
public class MergeKSortedLists {

    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        if (lists == null || lists.size() == 0) {
            return null;
        }
        return helper(lists, 0, lists.size() - 1);
    }

    private ListNode helper(ArrayList<ListNode> lists, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            return merge(helper(lists, l, m), helper(lists, m + 1, r));
        }
        return lists.get(l);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        dummy.next = l1;
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                l1 = l1.next;
            } else {
                ListNode next = l2.next;
                cur.next = l2;
                l2.next = l1;
                l2 = next;
            }

            cur = cur.next;
        }
        if (l2 != null) {
            cur.next = l2;
        }

        return dummy.next;
    }

    /*************************************************************************************************************/

    public ListNode mergeKLists2(ArrayList<ListNode> lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(10, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode n1, ListNode n2) {
                return n1.val - n2.val;
            }
        });
        for (int i = 0; i < lists.size(); i++) {
            ListNode node = lists.get(i);
            if (node != null) {
                heap.offer(node);
            }
        }
        ListNode head = null;
        ListNode pre = head;
        while (heap.size() > 0) {
            ListNode cur = heap.poll();
            if (head == null) {
                head = cur;
                pre = head;
            } else {
                pre.next = cur;
            }
            pre = cur;
            if (cur.next != null) {
                heap.offer(cur.next);
            }
        }

        return head;
    }

    public static void main(String[] args) {
        MergeKSortedLists obj = new MergeKSortedLists();

        ArrayList<ListNode> test = new ArrayList<>();
        ListNode a = new ListNode();
        a.val = 6;
        ListNode b = new ListNode();
        b.val = 4;
        b.next = a;

        ListNode a2 = new ListNode();
        a2.val = 7;
        ListNode b2 = new ListNode();
        b2.val = 5;
        b2.next = a2;

        ListNode a3 = new ListNode();
        a3.val = 10;
        ListNode b3 = new ListNode();
        b3.val = 8;
        b3.next = a3;

        test.add(b);
        test.add(b2);
        test.add(b3);

        ListNode result = obj.mergeKLists2(test);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

}

class ListNode {
    int val;
    ListNode next;
}
