package xiuqin.leetcode.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://oj.leetcode.com/problems/binary-tree-level-order-traversal/
 * 102. 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层序遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */
public class BinaryTreeLevelOrderTraversal {
  /**
   * @param root: The root of binary tree.
   * @return: Level order a list of lists of integer
   */
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> results = new ArrayList<>();
    if (root == null) {
      return results;
    }

    List<Integer> values = new ArrayList<>();
    Queue<TreeNode> q = new LinkedList<>();

    q.offer(root);
    q.offer(null);

    while (q.size() > 0) {
      TreeNode node = q.poll();

      // null node used as a separator of every level
      if (node == null) {
        results.add(new ArrayList<>(values));
        values.clear();

        if (q.size() == 0) {
          break;
        }
        q.offer(null);
        continue;
      }

      values.add(node.val);
      if (node.left != null) {
        q.offer(node.left);
      }
      if (node.right != null) {
        q.offer(node.right);
      }
    }

    return results;
  }

  public static void main(String[] args) {
    BinaryTreeLevelOrderTraversal obj = new BinaryTreeLevelOrderTraversal();

    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.left.left = new TreeNode(15);
    root.right.right = new TreeNode(7);

    System.out.println(obj.levelOrder(root));
  }
}
