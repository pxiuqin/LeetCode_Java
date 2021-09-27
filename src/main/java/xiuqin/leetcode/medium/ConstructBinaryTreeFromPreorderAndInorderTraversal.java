package xiuqin.leetcode.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * 105. 从前序与中序遍历序列构造二叉树
 * 给定一棵树的前序遍历preorder 与中序遍历 inorder。请构造二叉树并返回其根节点。
 * <p>
 * 示例 1:
 * img:doc/img/101-200/tree.jpg
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * <p>
 * 示例 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 * <p>
 * 提示:
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder和inorder均无重复元素
 * inorder均出现在preorder
 * preorder保证为二叉树的前序遍历序列
 * inorder保证为二叉树的中序遍历序列
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * 前序遍历 inorder =[3,9,15,20,7]
 * 中序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
  public TreeNode buildTree(List<Integer> preorder, List<Integer> inorder) {
    int preidx = 0;
    return buildTree(preorder, preidx, inorder);
  }

  /**
   * 1、通过先序遍历找出root根节点；
   * 2、查找root根节点在中序遍历中的位置，得到对应的index，求出左子树的长度num；
   * 3、递归求取左子树和右子树；
   *
   * @param preorder
   * @param preidx
   * @param inorder
   * @return
   */
  private TreeNode buildTree(List<Integer> preorder, int preidx, List<Integer> inorder) {
    if (preorder.size() <= 0 || inorder.size() <= 0) {
      return null;
    }

    TreeNode root = new TreeNode(preorder.get(preidx));  //get root node
    if (inorder.size() == 1) {
      return root;
    }

    int i;
    for (i = 0; i < inorder.size(); i++) {
      //find the number of nodes in the left subtree
      if (inorder.get(i) == preorder.get(preidx)) {
        break;
      }
    }

    //error: not found
    if (i == inorder.size()) {
      return null;
    }

    if (preidx + 1 >= preorder.size()) {
      return root;
    }

    List<Integer> v = inorder.subList(0, i);
    if (v.size() > 0) {
      preidx++;  //add one
      root.left = buildTree(preorder, preidx, v);
    }

    v = inorder.subList(i + 1, inorder.size());
    if (v.size() > 0) {
      preidx += i;  //add the number of left nodes

      root.right = buildTree(preorder, preidx, v);
    }

    return root;
  }

  public static void main(String[] args) {
    ConstructBinaryTreeFromPreorderAndInorderTraversal obj = new ConstructBinaryTreeFromPreorderAndInorderTraversal();

    TreeNode result = obj.buildTree(Arrays.asList(6, 2, 1, 4, 3, 5, 8, 7, 9), Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    TreeNode.printTree_pre_order(result);
    System.out.println();
    TreeNode.printTree_in_order(result);
    System.out.println();
    TreeNode.printTree_level_order(result);
  }
}
