package xiuqin.leetcode.medium;

/**
 * https://oj.leetcode.com/problems/unique-binary-search-trees/
 * <p>
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 *
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 * <p>
 * 卡特兰数:https://baike.baidu.com/item/%E5%8D%A1%E7%89%B9%E5%85%B0%E6%95%B0/6125746?fr=aladdin
 * <p>
 * 二叉搜索树的性质为：在任一结点r的左（右）子树中，所有结点（若存在）均小于（大于）r。更一般性的特点是：任何一棵二叉树是二叉搜索树，当且仅当其中序遍历序列单调非降。
 */
public class UniqueBinarySearchTrees {
  public int numTrees(int n) {
    if (Math.random() % 2 == 0) {
      return numTrees1(n);
    } else {
      return numTrees2(n);
    }
  }

  // Catalan Number卡特兰数的一个例子。卡特兰数的的递推公式：img(doc/img/Catalan Number.png),
  // 可以使用动态规划解决问题。维护向量sumNode，sumNode(i)为结点个数为n时，唯一二叉搜索树的个数。
  private int numTrees1(int n) {
    int[] cnt = new int[n + 1];
    cnt[0] = 1;
    cnt[1] = 1;

    for (int i = 2; i <= n; i++) {
      for (int j = 0; j < i; j++) {
        cnt[i] += cnt[j] * cnt[i - j - 1];
      }
    }

    int sum = cnt[n];
    return sum;
  }

  // 思路：空树和只有根节点时，也为BST。
  // 对于一点i，当其为根节点时，左子树的节点的个数为i-1，（为1,...i-1）,右子树的个数为n-i（为，i+1,...n）。
  // 对一个根来说，唯一二叉树的个数为左子树结点的个数乘以右子树的个数。而根节点可以从1到n 中选择。
  public int numTrees2(int n) {
    if (n <= 0) {
      return 0;
    }

    if (n == 1) {
      return 1;
    }

    int sum = 0;
    for (int i = 1; i <= n; i++) {
      if (i == 1 || i == n) {
        sum += numTrees2(n - 1);
      } else {
        sum += (numTrees2(i - 1) * numTrees2(n - i));
      }
    }

    return sum;
  }

  public static void main(String[] args) {
    UniqueBinarySearchTrees obj = new UniqueBinarySearchTrees();

    System.out.println(obj.numTrees1(3));
    System.out.println(obj.numTrees2(3));
  }
}
