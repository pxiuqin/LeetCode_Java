package xiuqin.leetcode.medium;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 * 107. 二叉树的层序遍历 II
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其自底向上的层序遍历为：
 * <p>
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 */
public class BinaryTreeLevelOrderTraversalII {
  List<List<Integer>> levelOrderBottom(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    List<List<Integer>> vv = new ArrayList<>();

    if (root != null) {
      List<Integer> v = new ArrayList<>();
      v.add(root.val);
      vv.add(v);
    }
    q.offer(root);

    int i = 0;
    List<TreeNode> vt = new ArrayList<>();
    while (q.size() > 0) {
      TreeNode p = q.poll();
      vt.add(p);
      if (p == null) {
        continue;
      }
      q.offer(p.left);
      q.offer(p.right);
    }

    int step = 2;
    int j = 0;
    for (i = 1; i < vt.size(); i = j) {
      List<Integer> v = new ArrayList<>();
      int count = 0;

      for (j = i; j < i + step && j < vt.size(); j++) {
        if (vt.get(j) != null) {
          v.add(vt.get(j).val);
          count += 2;
        }
      }
      step = count;

      if (v.size() > 0) {
        vv.add(v);
      }
    }

    //reverse the order
    Collections.reverse(vv);
    return vv;
  }

  public static void main(String[] args) {
    BinaryTreeLevelOrderTraversalII obj = new BinaryTreeLevelOrderTraversalII();

    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.left.left = new TreeNode(15);
    root.right.right = new TreeNode(7);

    System.out.println(obj.levelOrderBottom(root));
  }
}
