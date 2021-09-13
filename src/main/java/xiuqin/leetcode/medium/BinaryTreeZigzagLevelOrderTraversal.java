package xiuqin.leetcode.medium;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * 103.二叉树的之字形层序遍历
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * return its zigzag level order traversal as:
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 */
public class BinaryTreeZigzagLevelOrderTraversal {
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    if ((int) (Math.random() * 10) % 2 == 0) {
      return zigzagLevelOrder1(root);
    }
    return zigzagLevelOrder2(root);
  }

  private List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    List<TreeNode> tree = TreeToArray_level_order(root);

    int curLevelCnt = 1;
    int nextLevelCnt = 1;
    int level = 0;

    for (int i = 0; i < tree.size(); i += curLevelCnt) {
      int cnt = 0;
      level++;
      List<Integer> v = new ArrayList<>();

      if (level % 2 == 0) {
        // if it is an even level, from the end to the begin
        for (int j = i + nextLevelCnt - 1; j >= i; j--) {
          if (tree.get(j) != null) {
            cnt += 2;
            v.add(tree.get(j).val);
          }
        }
      } else {
        // if it is an odd level, from the begin to the end
        for (int j = i; j < i + nextLevelCnt; j++) {
          if (tree.get(j) != null) {
            cnt += 2;
            v.add(tree.get(j).val);
          }
        }
      }

      curLevelCnt = nextLevelCnt;
      nextLevelCnt = cnt;
      if (v.size() > 0) {
        result.add(v);
      }
    }

    return result;
  }

  private List<TreeNode> TreeToArray_level_order(TreeNode root) {
    List<TreeNode> result = new ArrayList<>();

    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    while (q.size() > 0) {
      TreeNode n = q.poll();
      result.add(n);
      if (n == null) {
        continue;
      }

      q.offer(n.left);
      q.offer(n.right);
    }

    return result;
  }

  private List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
    List<List<Integer>> vv = new ArrayList<>();
    if (root == null) {
      return vv;
    }

    int level = 0;
    TreeNode last = root;
    LinkedList<TreeNode> q = new LinkedList<>();
    q.offer(root);
    vv.add(new ArrayList<>());

    while (!q.isEmpty()) {
      TreeNode p = q.poll();

      if (!vv.get(level).isEmpty()) {
        vv.get(level).add(level % 2 == 1 ? 0 : vv.get(level).size(), p.val);
      } else {
        List<Integer> v = new ArrayList<>();
        v.add(p.val);
        vv.set(level, v);
      }

      if (p.left != null) {
        q.offer(p.left);
      }
      if (p.right != null) {
        q.offer(p.right);
      }

      if (p == last) {
        level++;
        if (!q.isEmpty()) {
          last = q.getLast();
          vv.add(new ArrayList<>());
        }
      }
    }

    return vv;
  }

  private List<List<Integer>> zigzagLevelOrder3(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    helper(root, 0, res);
    return res;
  }

  void helper(TreeNode node, int level, List<List<Integer>> res) {
    if (node == null) {
      return;
    }

    if (res.size() <= level) {
      res.add(new ArrayList<>());
    }

    List<Integer> oneLevel = res.get(level);
    if (level % 2 == 0) {
      oneLevel.add(node.val);
    } else {
      oneLevel.add(0, node.val);
    }

    helper(node.left, level + 1, res);
    helper(node.right, level + 1, res);
  }

  public static void main(String[] args) {
    BinaryTreeZigzagLevelOrderTraversal obj = new BinaryTreeZigzagLevelOrderTraversal();

    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.left.left = new TreeNode(15);
    root.right.right = new TreeNode(7);

    System.out.println(obj.zigzagLevelOrder1(root));
    System.out.println(obj.zigzagLevelOrder2(root));
    System.out.println(obj.zigzagLevelOrder3(root));
  }
}
