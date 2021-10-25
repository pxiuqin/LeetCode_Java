package xiuqin.leetcode.hard;

/**
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * 25. K 个一组翻转链表
 * 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
 * k是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 进阶：
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * <p>
 * 示例 2：
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * <p>
 * 示例 3：
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 * <p>
 * 示例 4：
 * 输入：head = [1], k = 1
 * 输出：[1]
 * <p>
 * 提示：
 * 列表中节点的数量在范围 sz 内
 * 1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= sz
 */
public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 0) {
            return head;
        }
        ListNode fake = new ListNode();
        fake.next = head;
        ListNode p = fake;

        while (p != null) {
            p.next = reverseList(p.next, k);
            for (int i = 0; p != null && i < k; i++) {
                p = p.next;
            }
        }

        return fake.next;
    }

    ListNode reverseList(ListNode head, int k) {
        ListNode pEnd = head;
        while (pEnd != null && k > 0) {
            pEnd = pEnd.next;
            k--;
        }

        //如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序
        if (k > 0) {
            return head;
        }

        ListNode pHead = pEnd, p = head;
        while (p != pEnd) {
            ListNode q = p.next;
            p.next = pHead;
            pHead = p;
            p = q;  //next
        }

        return pHead;
    }

    // 反转以 a 为头结点的链表
    ListNode reverse(ListNode a) {
        ListNode pre, cur, nxt;
        pre = null;
        cur = a;
        nxt = a;
        while (cur != null) {
            nxt = cur.next;
            // 逐个结点反转
            cur.next = pre;
            // 更新指针位置
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }

    /**
     * 反转区间 [a, b) 的元素，注意是左闭右开
     */
    ListNode reverse(ListNode a, ListNode b) {
        ListNode pre, cur, nxt;
        pre = null;
        cur = a;
        nxt = a;
        // while 终止的条件改一下就行了
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }


    //链表是一种兼具递归和迭代性质的数据结构，认真思考一下可以发现这个问题具有递归性质。
    ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        // 区间 [a, b) 包含 k 个待反转元素
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            // 不足 k 个，不需要反转，base case
            if (b == null) {
                return head;
            }
            b = b.next;
        }

        // 反转前 k 个元素
        ListNode newHead = reverse(a, b);

        // 递归反转后续链表并连接起来
        a.next = reverseKGroup2(b, k);

        return newHead;
    }

    public static void main(String[] args) {
        ReverseNodesInKGroup obj = new ReverseNodesInKGroup();

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

        ListNode result = obj.reverseKGroup2(d, 2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

}
