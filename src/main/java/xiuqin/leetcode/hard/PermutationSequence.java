package xiuqin.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutation-sequence/
 * 60. 排列序列
 * 给出集合[1,2,3,...,n]，其所有元素共有n! 种排列。
 * <p>
 * 按大小顺序列出所有排列情况，并一一标记，当n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定n 和k，返回第k个排列。
 * <p>
 * 示例 1：
 * 输入：n = 3, k = 3
 * 输出："213"
 * <p>
 * 示例 2：
 * 输入：n = 4, k = 9
 * 输出："2314"
 * <p>
 * 示例 3：
 * 输入：n = 3, k = 1
 * 输出："123"
 * <p>
 * 提示：
 * 1 <= n <= 9
 * 1 <= k <= n!
 * <p>
 * method:
 * 1. 以某一数字开头的排列有(n-1)! 个。
 * 例如： 123， 132， 以1开头的是 2！个
 * 2. 所以第一位数字就可以用 （k-1） / (n-1)!  来确定 .这里K-1的原因是，序列号我们应从0开始计算，否则在边界时无法计算。
 * 3. 第二位数字。假设前面取余后为m，则第二位数字是 第 m/(n-2)! 个未使用的数字。
 * 4. 不断重复2，3，取余并且对(n-k)!进行除法，直至计算完毕
 * <p>
 * <p>
 * 回溯法:
 * 如何找出第16个（按字典序的）{1,2,3,4,5}的全排列？
 * 1. 首先用16-1得到15
 * 2. 用15去除4! 得到0余15
 * 3. 用15去除3! 得到2余3
 * 4. 用3去除2! 得到1余1
 * 5. 用1去除1! 得到1余0
 * 有0个数比它小的数是1，所以第一位是1
 * 有2个数比它小的数是3，但1已经在之前出现过了所以是4
 * 有1个数比它小的数是2，但1已经在之前出现过了所以是3
 * 有1个数比它小的数是2，但1,3,4都出现过了所以是5
 * 最后一个数只能是2
 */
public class PermutationSequence {
  /* Extreamly Optimized */
  public String getPermutation(int n, int k) {
    List<Integer> num = new ArrayList<>();
    int total = 1;
    for (int i = 1; i <= n; i++) {
      num.add(i);
      total *= i;
    }

    //invalid k;
    if (total < k) {
      return "";
    }

    // Construct the k-th permutation with a list of n numbers
    // Idea: group all permutations according to their first number (so n groups, each of
    // (n-1)! numbers), find the group where the k-th permutation belongs, remove the common
    // first number from the list and append it to the resulting string, and iteratively
    // construct the (((k-1)%(n-1)!)+1)-th permutation with the remaining n-1 numbers
    int group = total;
    StringBuffer ss = new StringBuffer();
    while (n > 0) {
      group = group / n;
      int idx = (k - 1) / group;
      ss.append(num.get(idx));
      num.remove(idx);
      n--;

      //the next k also can be caculated like this:
      //k = (k-1)%group + 1;
      k -= group * idx;
    }

    return ss.toString();
  }

  public String getPermutation1(int n, int k) {
    ArrayList<Integer> list = new ArrayList<>();
    k = k - 1; //序列号我们应从0开始计算

    for (int i = n - 1; i >= 0; i--) {
      int sum = factorial(i);
      int temp = k / sum;

      for (int j = 1; j <= n; j++) {
        if (!list.contains(j)) {
          temp--;
          if (temp < 0) {
            list.add(j);
            break;
          }
        }
      }

      k = k % sum;
    }

    String res = "";
    for (int i = 0; i < n; i++) {
      res += list.get(i);
    }

    return res;
  }

  public int factorial(int n) {
    int sum = 1;
    for (int i = n; i >= 1; i--) {
      sum *= i;
    }
    return sum;
  }

  /* Optimization by determining the group */
  public String getPermutation_2(int n, int k) {
    List<Integer> num = new ArrayList<>();
    int total = 1;
    for (int i = 1; i <= n; i++) {
      num.add(i);
      total *= i;
    }

    //invalid k;
    if (total < k) {
      return "";
    }
    int group = total / n;
    int idx = (k - 1) / group;
    int nn = num.get(idx);
    num.remove(idx);
    num.add(0, nn);

    int offset = (k - 1) % group;
    for (int i = 0; i < offset; i++) {
      nextPermutation(num);
    }

    //string result;
    StringBuffer ss = new StringBuffer();
    for (int i = 0; i < n; i++) {
      ss.append(num.get(i));
    }

    return ss.toString();
  }

  /* Time Limit Exceeded */
  public String getPermutation_3(int n, int k) {
    List<Integer> num = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      num.add(i);
    }

    for (int i = 1; i < k; i++) {
      nextPermutation(num);
    }

    //string result;
    StringBuffer ss = new StringBuffer();
    for (int i = 0; i < n; i++) {
      ss.append(num.get(i));
    }

    return ss.toString();
  }

  private void nextPermutation(List<Integer> num) {
    if (num.size() <= 1) return;
    for (int i = num.size() - 1; i > 0; i--) {
      if (num.get(i - 1) < num.get(i)) {
        int j = num.size() - 1;
        while (num.get(i - 1) > num.get(j)) {
          j--;
        }
        int temp = num.get(i - 1);
        num.set(i - 1, num.get(j));
        num.set(j, temp);

        reverse(num, i, num.size() - 1);
        return;
      }
    }

    reverse(num, 0, num.size() - 1);
  }

  private void reverse(List<Integer> num, int start, int end) {
    if (start >= end) {
      return;
    }

    int index = 0;
    for (int i = start; i <= (start + end) / 2; i++) {
      int temp = num.get(i);
      num.set(i, num.get(end - index));
      num.set(end - index, temp);
      index++;
    }
  }

  public static void main(String[] args) {
    PermutationSequence obj = new PermutationSequence();

    System.out.println(obj.getPermutation(3, 3));
    System.out.println(obj.getPermutation1(3, 3));
    System.out.println(obj.getPermutation_2(3, 3));
    System.out.println(obj.getPermutation_3(3, 3));

    System.out.println(obj.getPermutation(4, 9));
    System.out.println(obj.getPermutation1(4, 9));
    System.out.println(obj.getPermutation_2(4, 9));
    System.out.println(obj.getPermutation_3(4, 9));

    System.out.println(obj.getPermutation(3, 1));
    System.out.println(obj.getPermutation1(3, 1));
    System.out.println(obj.getPermutation_2(3, 1));
    System.out.println(obj.getPermutation_3(3, 1));
  }

}
