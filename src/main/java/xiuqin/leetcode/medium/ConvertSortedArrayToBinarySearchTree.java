package xiuqin.leetcode.medium;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
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
