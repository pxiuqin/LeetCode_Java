package xiuqin.leetcode.easy;

/**
 * https://oj.leetcode.com/problems/climbing-stairs/
 *
 * 问：有n阶的楼梯，每次可以走一步或者两步，那么走完n阶楼梯有多少种方式？
 *
 * 解：
 * 1、由于每次只能走一步或两步，所以迈出最后一步前，必定在n-1阶或n-2阶，即f(n)=f(n-1)+f(n-2)。
 * 2、也可以根据数理统计方法，寻找规律，最后可以发现其实是一个斐波那契数列：0,1,2,3,5,8,13,21,34...

 */
public class ClimbingStairs {
  /**
   * @param n: An integer
   * @return: An integer
   */
  public int climbStairs(int n) {
    int[] matrix = new int[n];

    if (n < 3) {
      return n;
    }

    matrix[0] = 1;
    matrix[1] = 2;

    // write your code here
    for (int i = 2; i < matrix.length; i++) {
      matrix[i] = matrix[i-1] + matrix[i-2];
    }

    return matrix[n-1];
  }

  public static void main(String[] args){
    ClimbingStairs obj =new ClimbingStairs();

    System.out.println(obj.climbStairs(4));
    System.out.println(obj.climbStairs(3));
    System.out.println(obj.climbStairs(2));
    System.out.println(obj.climbStairs(1));
  }
}
