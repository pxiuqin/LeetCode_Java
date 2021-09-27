package xiuqin.leetcode.easy;

/**
 * https://leetcode.com/problems/balanced-binary-tree/
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1 。
 * <p>
 * 示例 1：
 * img:doc/img/101-200/balance_1.jpg
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 * <p>
 * 示例 2：
 * img:doc/img/101-200/balance_2.jpg
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：root = []
 * 输出：true
 * <p>
 * Given a binary tree, determine if it is height-balanced.
 * <p>
 * For this problem, a height-balanced binary tree is defined as:
 * <p>
 * a binary tree in which the depth of the two subtrees of everynode never differ by more than 1.
 * <p>
 * Example 1:
 * Given the following tree [3,9,20,null,null,15,7]:
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * Return true.
 * <p>
 * Example 2:
 * Given the following tree [1,2,2,3,3,null,null,4,4]:
 * <p>
 * 1
 * / \
 * 2   2
 * / \
 * 3   3
 * / \
 * 4   4
 * Return false.
 */
public class BalancedBinaryTree {

  // This is not needed. Can just check the depth
  private class Result {
    boolean isBalanced;
    int height;

    Result(boolean isBalanced, int height) {
      this.isBalanced = isBalanced;
      this.height = height;
    }
  }

  /**
   * @param root: The root of binary tree.
   * @return: True if this Binary tree is Balanced, or false.
   */
  public boolean isBalanced(TreeNode root) {
    // write your code here
    return helper(root, 0).isBalanced;
  }

  private Result helper(TreeNode root, int depth) {
    if (root == null) {
      return new Result(true, depth);
    }

    Result left = helper(root.left, depth + 1);
    Result right = helper(root.right, depth + 1);

    if (!left.isBalanced || !right.isBalanced) {
      return new Result(false, 0);
    }

    if (Math.abs(left.height - right.height) > 1) {
      return new Result(false, 0);
    }

    return new Result(true, Math.max(left.height, right.height));
  }

  public boolean isBalanced2(TreeNode root) {
    if (checkDepth(root) == -1) {
      return false;
    } else {
      return true;
    }
  }

  private int checkDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int left = checkDepth(root.left);
    if (left == -1) {
      return -1;
    }

    int right = checkDepth(root.right);
    if (right == -1) {
      return -1;
    }

    int diff = Math.abs(left - right);
    if (diff > 1) {
      return -1;
    } else {
      return 1 + Math.max(left, right);
    }
  }

  public static void main(String[] args) {
    BalancedBinaryTree obj = new BalancedBinaryTree();

    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);
    System.out.println(obj.isBalanced(root));
    System.out.println(obj.isBalanced2(root));

    root.right.left.left = new TreeNode(10);
    System.out.println(obj.isBalanced(root));
    System.out.println(obj.isBalanced2(root));
  }
}
