package xiuqin.leetcode.medium;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * 108. 将有序数组转换为二叉搜索树
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 *
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 *
 *
 * 示例 1：
 * img:doc/img/101-200/btree1.jpg
 * 输入：nums = [-10,-3,0,5,9]
 * 输出：[0,-3,9,-10,null,5]
 * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
 * img:doc/img/101-200/btree2.jpg
 *
 * 示例 2：
 * img:doc/img/101-200/btree.jpg
 * 输入：nums = [1,3]
 * 输出：[3,1]
 * 解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。

 * 这道题是要将有序数组转为二叉搜索树，所谓二叉搜索树，是一种始终满足左<根<右的特性，如果将二叉搜索树按中序遍历的话，得到的就是一个有序数组了。那么反过来，我们可以得知，根节点应该是有序数组的中间点，从中间点分开为左右两个有序数组，在分别找出其中间点作为原中间点的左右两个子节点，这不就是是二分查找法的核心思想
 */
public class ConvertSortedArrayToBinarySearchTree {
  TreeNode sortedArrayToBST2(List<Integer> nums) {
    return helper(nums, 0 , nums.size() - 1);
  }

  TreeNode helper(List<Integer> nums, int left, int right) {
    if (left > right) {
      return null;
    }

    int mid = left + (right - left) / 2;
    TreeNode cur = new TreeNode(nums.get(mid));
    cur.left = helper(nums, left, mid - 1);
    cur.right = helper(nums, mid + 1, right);

    return cur;
  }

  TreeNode sortedArrayToBST(List<Integer> num) {
    if (num.size() == 0) {
      return null;
    }

    if (num.size() == 1) {
      return new TreeNode(num.get(0));
    }

    int mid = num.size() / 2;
    TreeNode root = new TreeNode(num.get(mid));

    List<Integer> v = num.subList(0, mid);
    root.left = sortedArrayToBST(v);

    if (mid == num.size() - 1) {
      root.right = null;
    } else {
      v = num.subList(mid + 1, num.size());
      root.right = sortedArrayToBST(v);
    }

    return root;
  }

  public static void main(String[] args) {
    ConvertSortedArrayToBinarySearchTree obj = new ConvertSortedArrayToBinarySearchTree();

    TreeNode result = obj.sortedArrayToBST2(Arrays.asList(1, 2, 3, 4, 5, 6));
    TreeNode.printTree_pre_order(result);
    System.out.println();
    TreeNode.printTree_in_order(result);
    System.out.println();
    TreeNode.printTree_level_order(result);
  }
}
