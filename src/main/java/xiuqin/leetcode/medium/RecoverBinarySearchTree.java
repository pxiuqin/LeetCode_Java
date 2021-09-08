package xiuqin.leetcode.medium;

/**
 * https://oj.leetcode.com/problems/recover-binary-search-tree/
 */
public class RecoverBinarySearchTree {
  //
  // We can convert the BST to a sorted array,  then we can find the two nodes which missed the order.
  //
  // To cover the BST to sorted array, we needn't use an extra array, we just traverse the tree in order.
  //
  //                   8
  //           _______/ \_______
  //          /                 \
  //         4                  12
  //      __/ \__             __/ \__
  //     /       \           /       \
  //    2         6        10        14
  //   / \       / \       / \       / \
  //  1   3     5   7     9  11    13  15
  //
  //

  private TreeNode n1, n2, prev;

  private void recoverTreeHelper(TreeNode root) {
    if (root == null) {
      return;
    }

    recoverTreeHelper(root.left);

    // Determine whether the pre-node meets the condition
    if (prev != null) {
      if (prev.val > root.val) {
        if (n1 == null) {
          n1 = prev;
        }
        n2 = root;
      }
    }

    prev = root;

    recoverTreeHelper(root.right);
  }

  public void recoverTree(TreeNode root) {
    n1 = n2 = prev = null;
    recoverTreeHelper(root);
    if (n1 != null && n2 != null) {
      swap(n1, n2);
    }
  }

  private void swap(TreeNode n1, TreeNode n2) {
    int temp = n1.val;
    n1.val = n2.val;
    n2.val = temp;
  }

  public static void main(String[] args) {
    RecoverBinarySearchTree obj = new RecoverBinarySearchTree();

    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(1);
    root.right = new TreeNode(4);
    root.right.left = new TreeNode(3);
    root.right.right = new TreeNode(6);
    obj.recoverTree(root);
    TreeNode.printList(root);
  }
}
