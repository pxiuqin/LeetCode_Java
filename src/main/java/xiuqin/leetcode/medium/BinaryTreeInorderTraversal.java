package xiuqin.leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * 94. 二叉树的中序遍历
 * 给定一个二叉树的根节点 root ，返回它的 中序遍历。
 * <p>
 * 示例 1：
 * 1
 * \
 * 2
 * /
 * 3
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * <p>
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 * <p>
 * 示例 4：
 * 1
 * /
 * 2
 * 输入：root = [1,2]
 * 输出：[2,1]
 * <p>
 * 示例 5：
 * 1
 * \
 * 2
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 * <p>
 * 提示：
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 */
public class BinaryTreeInorderTraversal {
  List<Integer> inorderTraversal(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    List<Integer> v = new ArrayList<>();

    while (stack.size() > 0 || root != null) {
      if (root != null) {
        stack.push(root);
        root = root.left;  //start left
      } else {
        if (stack.size() > 0) {
          root = stack.peek(); //find stack top
          stack.pop();  //stack pop
          v.add(root.val);  //record root
          root = root.right; //start right
        }
      }
    }

    return v;
  }

  public static void main(String[] args) {
    BinaryTreeInorderTraversal obj = new BinaryTreeInorderTraversal();

    TreeNode node = new TreeNode(1);
    node.right = new TreeNode(2);
    node.right.left = new TreeNode(3);
    TreeNode.printList(node);
    System.out.println(obj.inorderTraversal(node));
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
        System.out.print("node:"+h.val+" left:");
        printList(h.left);
      }

      if (h.right != null) {
        System.out.print("node:"+h.val+" right:");
        printList(h.right);
      }
    }
  }
}
