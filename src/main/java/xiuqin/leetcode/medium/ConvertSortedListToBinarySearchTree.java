package xiuqin.leetcode.medium;

/**
 * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 * 109. 有序链表转换二叉搜索树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * <p>
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 */
public class ConvertSortedListToBinarySearchTree {
  TreeNode sortedListToBST(ListNode head) {
    int len = 0;

    for (ListNode p = head; p != null; p = p.next) {
      len++;
    }

    return sortedListToBST(0, len - 1, head);
  }

  TreeNode sortedListToBST(int low, int high, ListNode head) {
    if (low > high || head == null) {
      return null;
    }

    int mid = low + (high - low) / 2;

    TreeNode leftNode = sortedListToBST(low, mid - 1, head);

    ListNode midNode = head;
    for (int i = 0; i < mid; i++) {
      midNode = midNode.next;
    }

    TreeNode node = new TreeNode(midNode.val);
    node.left = leftNode;

    TreeNode rightNode = sortedListToBST(mid + 1, high, head);
    node.right = rightNode;

    return node;
  }

  public static void main(String[] args) {
    ConvertSortedListToBinarySearchTree obj = new ConvertSortedListToBinarySearchTree();

    ListNode root = ListNode.createList(new int[]{-10, -3, 0, 5, 9});
    TreeNode result = obj.sortedListToBST(root);
    TreeNode.printTree_in_order(result);
  }
}
