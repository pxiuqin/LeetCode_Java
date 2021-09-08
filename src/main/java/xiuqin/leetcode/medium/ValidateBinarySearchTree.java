package xiuqin.leetcode.medium;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/
 * 98. 验证二叉搜索树
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 示例 1：
 * img(doc/img/tree1.jpg)
 * 输入：root = [2,1,3]
 * 输出：true
 * <p>
 * 示例 2：
 * img(doc/img/tree2.jpg)
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 * <p>
 * 提示：
 * 树中节点数目范围在[1, 104] 内
 * -2^31 <= Node.val <= 2^31 - 1
 */
public class ValidateBinarySearchTree {
  //Handle two methods at the same time
  public boolean isValidBST(TreeNode root) {
    return isBSTTraversal(root) && isBSTDivideAndConquer(root);
  }

  // Solution 1: Traversal
  // The inorder sequence of a BST is a sorted ascending list
  private int lastValue = 0; // the init value of it doesn't matter.
  private boolean firstNode = true;

  public boolean isBSTTraversal(TreeNode root) {
    if (root == null) {
      return true;
    }

    if (!isBSTTraversal(root.left)) {
      return false;
    }

    // firstNode is needed because of if firstNode is Integer.MIN_VALUE,
    // even if we set lastValue to Integer.MIN_VALUE, it will still return false
    if (!firstNode && lastValue >= root.val) {
      return false;
    }

    firstNode = false;
    lastValue = root.val;

    if (!isBSTTraversal(root.right)) {
      return false;
    }

    return true;
  }

  // Solution 2: divide && conquer
  private class Result {
    int min;
    int max;
    boolean isBST;

    Result(int min, int max, boolean isBST) {
      this.min = min;
      this.max = max;
      this.isBST = isBST;
    }
  }

  public boolean isBSTDivideAndConquer(TreeNode root) {
    return isBSTHelper(root).isBST;
  }

  public Result isBSTHelper(TreeNode root) {
    // For leaf node's left or right
    if (root == null) {
      // we set min to Integer.MAX_VALUE and max to Integer.MIN_VALUE
      // because of in the previous level which is the leaf level,
      // we want to set the min or max to that leaf node's val (in the last return line)
      return new Result(Integer.MAX_VALUE, Integer.MIN_VALUE, true);
    }

    Result left = isBSTHelper(root.left);
    Result right = isBSTHelper(root.right);

    if (!left.isBST || !right.isBST) {
      return new Result(0, 0, false);
    }

    // For non-leaf node
    // As long as the left and right nodes are not satisfied, it is false
    if (root.left != null && left.max >= root.val
      || root.right != null && right.min <= root.val) {
      return new Result(0, 0, false);
    }

    return new Result(Math.min(left.min, root.val),
      Math.max(right.max, root.val), true);
  }

  public static void main(String[] args){
    ValidateBinarySearchTree obj =new ValidateBinarySearchTree();

    TreeNode root=new TreeNode(2);
    root.left=new TreeNode(1);
    root.right=new TreeNode(3);
    System.out.println(obj.isBSTTraversal(root));
    System.out.println(obj.isBSTDivideAndConquer(root));

    root=new TreeNode(5);
    root.left=new TreeNode(1);
    root.right=new TreeNode(4);
    root.right.left=new TreeNode(3);
    root.right.right=new TreeNode(6);
    System.out.println(obj.isBSTTraversal(root));
    System.out.println(obj.isBSTDivideAndConquer(root));
  }
}
