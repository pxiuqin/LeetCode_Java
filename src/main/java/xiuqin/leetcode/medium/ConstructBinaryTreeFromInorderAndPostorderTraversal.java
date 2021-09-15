package xiuqin.leetcode.medium;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * 中序遍历 inorder =[9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
  TreeNode buildTree(List<Integer> inorder, List<Integer> postorder) {
    return buildTree(inorder, 0, postorder, 0, postorder.size());
  }

  // n - how many number,  offset - start from where?
  TreeNode buildTree(List<Integer> inorder, int in_offset, List<Integer> postorder, int post_offset, int n) {

    if (n <= 0 || postorder.size() <= 0 || inorder.size() <= 0) {
      return null;
    }

    // find last node for the post order
    TreeNode root = new TreeNode(postorder.get(post_offset + n - 1));
    if (n == 1) {
      return root;
    }

    //searching in inorder -- can be optimized by using <map>
    int i;
    for (i = in_offset; i < in_offset + n; i++) {
      if (inorder.get(i) == postorder.get(post_offset + n - 1)) {
        break;
      }
    }

    //error: not found
    if (i == inorder.size()) {
      return null;
    }

    // left_n+right_n=n-1
    int left_n = i - in_offset;
    int right_n = in_offset + n - i - 1;
    root.left = buildTree(inorder, in_offset, postorder, post_offset, left_n);

    // i+1 represents the offset of the right subtree by inorder tree
    // post_offset+left_n represents the offset of the right subtree by postorder tree
    root.right = buildTree(inorder, i + 1, postorder, post_offset + left_n, right_n);

    return root;
  }

  //cause the problem: memory limited error
  TreeNode buildTree2(List<Integer> inorder, List<Integer> postorder) {

    if (postorder.size() <= 0 || inorder.size() <= 0) {
      return null;
    }

    int post_n = postorder.size();
    TreeNode root = new TreeNode(postorder.get(post_n - 1));
    if (inorder.size() == 1 && postorder.size() == 1) {
      return root;
    }

    //searching in inorder -- can be optimized by using <map>
    int i;
    for (i = 0; i < inorder.size(); i++) {
      if (inorder.get(i) == postorder.get(post_n - 1)) {
        break;
      }
    }

    //error: not found
    if (i == inorder.size()) {
      return null;
    }

    List<Integer> in = inorder.subList(0, i);
    List<Integer> post = postorder.subList(0, i);
    if (in.size() > 0) {
      root.left = buildTree2(in, post);
    }

    in = inorder.subList(i + 1, inorder.size());
    post = postorder.subList(i, postorder.size() - 1);  //postorder`s last node is root
    if (in.size() > 0) {
      root.right = buildTree2(in, post);
    }

    return root;
  }

  public static void main(String[] args) {
    ConstructBinaryTreeFromInorderAndPostorderTraversal obj = new ConstructBinaryTreeFromInorderAndPostorderTraversal();

    TreeNode result = obj.buildTree2(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9),
      Arrays.asList(1, 3, 5, 4, 2, 7, 9, 8, 6));
    TreeNode.printTree_pre_order(result);
    System.out.println();
    TreeNode.printTree_in_order(result);
    System.out.println();
    TreeNode.printTree_level_order(result);
  }
}
