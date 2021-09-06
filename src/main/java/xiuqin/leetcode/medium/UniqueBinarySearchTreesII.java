package xiuqin.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://oj.leetcode.com/problems/unique-binary-search-trees-ii/
 * 95. 不同的二叉搜索树 II
 * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
 * <p>
 * 示例 1：
 * img(doc/img/uniquebstn3.jpg)
 * 输入：n = 3
 * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 * <p>
 * 提示：
 * 1 <= n <= 8
 *
 * 二叉搜索树的性质为：在任一结点r的左（右）子树中，所有结点（若存在）均小于（大于）r。更一般性的特点是：任何一棵二叉树是二叉搜索树，当且仅当其中序遍历序列单调非降。
 */
public class UniqueBinarySearchTreesII {

  public List<TreeNode> generateTrees(int n) {

    List<TreeNode> v = new ArrayList<>();
    v = generateTrees(1, n);

    return v;
  }

  private List<TreeNode> generateTrees(int low, int high) {
    List<TreeNode> v = new ArrayList<>();
    if (low > high || low <= 0 || high <= 0) {
      v.add(null);
      return v;
    }

    if (low == high) {
      TreeNode node = new TreeNode(low);
      v.add(node);
      return v;
    }

    for (int i = low; i <= high; i++) {
      List<TreeNode> left = generateTrees(low, i - 1);
      List<TreeNode> right = generateTrees(i + 1, high);
      for (int l = 0; l < left.size(); l++) {
        for (int r = 0; r < right.size(); r++) {
          TreeNode root = new TreeNode(i);
          root.left = left.get(l);
          root.right = right.get(r);
          v.add(root);
        }
      }
    }

    return v;
  }

  public static void printTree(TreeNode root) {
    if (root == null) {
      System.out.print("null ");
      return;
    }

    System.out.print(root.val + " ");

    printTree(root.left);
    printTree(root.right);
  }

  public static void main(String[] args) {
    UniqueBinarySearchTreesII obj = new UniqueBinarySearchTreesII();

    List<TreeNode> result = obj.generateTrees(3);
    for (TreeNode node : result) {
      printTree(node);
      System.out.println();
    }
  }
}
