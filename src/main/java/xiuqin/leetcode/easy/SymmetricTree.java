package xiuqin.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/symmetric-tree/
 * 101. 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树[1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *
 * 但是下面这个[1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * 进阶：
 *
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 *
 */
public class SymmetricTree {
  public boolean isSymmetric(TreeNode root) {
    if (root == null ) {
      return true;
    }

    return isSymmetric2(root.left, root.right);

  }

  private boolean isSymmetric(TreeNode p, TreeNode q){
    if ((int)(Math.random()*10)%2==0){
      return isSymmetric1(p, q);
    }
    return isSymmetric2(p, q);
  }

  private boolean isSymmetric1(TreeNode p, TreeNode q){
    if (p==null && q==null){
      return true;
    }
    if (p==null || q==null){
      return false;
    }

    return (p.val == q.val) &&
      isSymmetric1(p.left, q.right) &&
      isSymmetric1(p.right, q.left);
  }

  private boolean isSymmetric2(TreeNode p, TreeNode q){
    List<TreeNode> q1=new ArrayList<>();
    List<TreeNode> q2=new ArrayList<>();
    q1.add(p);
    q2.add(q);

    while (q1.size()>0 && q2.size()>0){
      TreeNode p1=q1.remove(0);
      TreeNode p2=q2.remove(0);

      if(p1==null && p2==null){
        continue;
      }
      if(p1==null || p2==null){
        return false;
      }

      if(p1.val!=p2.val){
        return false;
      }

      q1.add(p1.left);
      q2.add(p2.right);

      q1.add(p1.right);
      q2.add(p2.left);
    }

    return true;
  }

  public static void main(String[] args){
    SymmetricTree obj=new SymmetricTree();

    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(2);
    root.left.left = new TreeNode(3);
    root.left.right = new TreeNode(4);
    root.right.left = new TreeNode(4);
    root.right.right = new TreeNode(3);

    TreeNode root2 = new TreeNode(1);
    root2.left = new TreeNode(2);
    root2.right = new TreeNode(2);
    root2.left.right = new TreeNode(3);
    root2.right.right = new TreeNode(3);

    System.out.println(obj.isSymmetric(root));
    System.out.println(obj.isSymmetric(root2));
  }
}
