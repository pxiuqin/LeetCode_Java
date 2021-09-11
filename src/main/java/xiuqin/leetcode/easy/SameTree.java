package xiuqin.leetcode.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * https://leetcode.com/problems/same-tree/
 * 100. 相同的树
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * <p>
 * 示例 1：
 * img(doc/img/ex1.jpg)
 * 输入：p = [1,2,3], q = [1,2,3]
 * 输出：true
 * <p>
 * 示例 2：
 * img(doc/img/ex2.jpg)
 * 输入：p = [1,2], q = [1,null,2]
 * 输出：false
 * <p>
 * 示例 3：
 * img(doc/img/ex3.jpg)
 * 输入：p = [1,2,1], q = [1,1,2]
 * 输出：false
 * <p>
 * 提示：
 * 两棵树上的节点数目都在范围 [0, 100] 内
 * -10^4 <= Node.val <= 10^4
 * <p>
 * Given two binary trees, write a function to check if they are equal or not.
 * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 */
public class SameTree {
  public boolean isSameTree(TreeNode p, TreeNode q) {
    if (Math.random() % 2 == 0) {
      return isSameTree1(p, q);
    }
    return isSameTree2(p, q);
  }

  private boolean isSameTree1(TreeNode p, TreeNode q) {
    if (p == null && q == null) {
      return true;
    }

    if (p == null || q == null) {
      return false;
    }

    return (p.val == q.val) &&
      isSameTree1(p.left, q.left) &&
      isSameTree1(p.right, q.right);
  }

  private boolean isSameTree2(TreeNode p, TreeNode q) {
    List<TreeNode> q1 = new ArrayList<>();
    List<TreeNode> q2 = new ArrayList<>();
    q1.add(p);
    q2.add(q);

    while (q1.size() > 0 && q2.size() > 0) {
      TreeNode p1 = q1.remove(0);
      TreeNode p2 = q2.remove(0);

      if (p1 == null && p2 == null) {
        continue;
      }

      if (p1 == null || p2 == null) {
        return false;
      }

      if (p1.val != p2.val) {
        return false;
      }

      q1.add(p1.left);
      q2.add(p2.left);

      q1.add(p1.right);
      q2.add(p2.right);
    }

    return true;
  }

  public static void main(String[] args) {
    SameTree obj = new SameTree();

    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(1);
    root.right = new TreeNode(4);
    root.right.left = new TreeNode(3);
    root.right.right = new TreeNode(6);

    TreeNode root2 = new TreeNode(5);
    root2.left = new TreeNode(1);
    root2.right = new TreeNode(3);
    root2.right.left = new TreeNode(3);
    root2.right.right = new TreeNode(6);
    System.out.println(obj.isSameTree1(root, root2));
    System.out.println(obj.isSameTree2(root, root2));
    System.out.println(obj.isSameTree1(root, root));
    System.out.println(obj.isSameTree2(root, root));
  }
}

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  public TreeNode() {
  }

  public TreeNode(int x) {
    val = x;
    left = null;
    right = null;
  }

  public static void printList(TreeNode h) {
    if (h != null) {
      System.out.println(h.val);
      if (h.left != null) {
        System.out.print("node:" + h.val + " left:");
        printList(h.left);
      }

      if (h.right != null) {
        System.out.print("node:" + h.val + " right:");
        printList(h.right);
      }
    }
  }
}
