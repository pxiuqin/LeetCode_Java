package xiuqin.leetcode.easy;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * 104. 二叉树的最大深度
 * <p>
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明:叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9 20
 * / \
 * 15 7
 * 返回它的最大深度3 。
 */
public class MaximumDepthOfBinaryTree {
  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    return theDepth(root);
  }

  private int theDepth(TreeNode root) {
    int leftDepth = 1;
    int rightDepth = 1;

    if (root.left != null) {
      leftDepth = theDepth(root.left) + 1;
    }
    if (root.right != null) {
      rightDepth = theDepth(root.right) + 1;
    }

    return Math.max(leftDepth, rightDepth);
  }

  public int maxDepth2(TreeNode root) {
    if (root==null){
      return 0;
    }
    
    int left = maxDepth2(root.left);
    int right = maxDepth2(root.right);
    return Math.max(left, right) + 1;
  }

  public static void main(String[] args) {
    MaximumDepthOfBinaryTree obj = new MaximumDepthOfBinaryTree();

    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.left.left = new TreeNode(15);
    root.right.right = new TreeNode(7);
    System.out.println(obj.maxDepth(root));
  }
}
