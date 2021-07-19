package xiuqin.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/first-missing-positive/
 * <p>
 * Given an unsorted integer array, find the first missing positive integer.
 * <p>
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 * <p>
 * Your algorithm should run in O(n) time and uses constant space.
 */
public class FirstMissingPositive {
  /*
   *  Idea:
   *
   *    We can move the num to the place which the index is the num.
   *
   *    for example,  (considering the array is zero-based.
   *       1 => A[0], 2 => A[1], 3=>A[2]
   *
   *    Then, we can go through the array check the i+1 == A[i], if not ,just return i+1;
   *
   *    This solution comes from StackOverflow.com
   *    http://stackoverflow.com/questions/1586858/find-the-smallest-integer-not-in-a-list
   */
  int firstMissingPositive_move(int A[]) {
    int n = A.length;
    if (n <= 0) return 1;
    int num;
    for (int i = 0; i < n; i++) {
      num = A[i];
      while (num > 0 && num < n && A[num - 1] != num) {
        //swap(A[i] A[num-1])
        int temp = A[i];
        A[i] = A[num - 1];
        A[num - 1] = temp;

        num = A[i];
      }
    }

    for (int i = 0; i < n; i++) {
      if (i + 1 != A[i]) {
        return i + 1;
      }
    }

    return n + 1;
  }

  /*
   *    The idea is simple:
   *
   *    1) put all of number into a map.
   *    2) for each number a[i] in array, remove its continous number in the map
   *        2.1)  remove ... a[i]-3, a[i]-2, a[i]-1, a[i]
   *        2.2)  remove a[i]+1, a[i]+2, a[i]+3,...
   *    3) during the removeing process, if some number cannot be found, which means it's missed.
   *
   *    considering a case [-2, -1, 4,5,6],
   *        [-2, -1] => missed 0
   *        [4,5,6]  => missed 3
   *
   *    However, we missed 1, so, we have to add dummy number 0 whatever.
   *
   *    NOTE: this solution is not constant space slution!!!!
   *
   */
  int firstMissingPositive_map(int A[]) {
    int n = A.length;
    Map<Integer, Integer> cache = new HashMap<>();
    for (int i = 0; i < n; i++) {
      cache.put(A[i], i);
    }

    int miss = Integer.MAX_VALUE;
    int x;
    for (int i = 0; i < n && cache.size() > 0; i++) {
      x = A[i];
      if (!cache.containsKey(x)) {
        continue;
      }

      int num;

      // remove the ... x-3, x-2, x-1, x
      for (num = x; cache.containsKey(num); num--) {
        cache.remove(num);
      }
      if (num > 0 && num < miss) {
        miss = num;
      }

      // remove the x+1, x+2, x+3 ...
      for(num=x+1; cache.containsKey(num);num++){
        cache.remove(num);
      }
      if(num>0&& num<miss){
        miss=num;
      }
    }

    return miss;
  }

  public static void main(String[] args) {
    FirstMissingPositive obj = new FirstMissingPositive();

    int[] test = new int[]{1, 2, 0};
    System.out.println(obj.firstMissingPositive_move(test));
    System.out.println(obj.firstMissingPositive_map(test));

    test = new int[]{3, 4, -1, 1};
    System.out.println(obj.firstMissingPositive_move(test));
    System.out.println(obj.firstMissingPositive_map(test));
  }
}
