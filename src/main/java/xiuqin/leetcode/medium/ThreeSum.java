package xiuqin.leetcode.medium;

import java.util.*;

/**
 * https://oj.leetcode.com/problems/3sum/
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * <p>
 * 示例 2：
 * 输入：nums = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[]
 *  
 * 提示：
 * 0 <= nums.length <= 3000
 * -10^5 <= nums[i] <= 10^5
 */
public class ThreeSum {
    /*
     *   Similar like "Two Number" problem, we can have the simlar solution.
     *
     *   Suppose the input array is S[0..n-1], 3SUM can be solved in O(n^2) time on average by
     *   inserting each number S[i] into a hash table, and then for each index i and j,
     *   checking whether the hash table contains the integer - (s[i]+s[j])
     *
     *   Alternatively, the algorithm below first sorts the input array and then tests all
     *   possible pairs in a careful order that avoids the need to binary search for the pairs
     *   in the sorted list, achieving worst-case O(n^n)
     *
     *   Solution:  Quadratic algorithm
     *   http://en.wikipedia.org/wiki/3SUM
     *   排序预处理后，设置一个指针i 用来遍历，剩下两个元素，设置两个指针j 指向i+ 1, 和 k 指向 size() -1, 这两个指针从两侧向中间移动，寻找符合条件的元素。
     */
    public List<List<Integer>> threeSum(List<Integer> num) {
        List<List<Integer>> result = new ArrayList<>();

        if (num.size() < 3) {
            return result;
        }

        //sort the array, this is the key
        num.sort(Comparator.naturalOrder());

        int n = num.size();

        for (int i = 0; i < n - 2; i++) {
            //skip the duplication
            if (i > 0 && num.get(i - 1).equals(num.get(i))) {
                continue;
            }

            int a = num.get(i);
            int low = i + 1;
            int high = n - 1;
            while (low < high) {
                int b = num.get(low);
                int c = num.get(high);
                if (a + b + c == 0) {
                    //got the soultion
                    List<Integer> v = new ArrayList<>();
                    v.add(a);
                    v.add(b);
                    v.add(c);
                    result.add(v);

                    // Continue search for all triplet combinations summing to zero.
                    //skip the duplication
                    while (low < n - 1 && num.get(low).equals(num.get(low + 1))) {
                        low++;
                    }
                    while (high > 0 && num.get(high).equals(num.get(high - 1))) {
                        high--;
                    }
                    low++;
                    high--;
                } else if (a + b + c > 0) {
                    //skip the duplication
                    while (high > 0 && num.get(high).equals(num.get(high - 1))) {
                        high--;
                    }
                    high--;
                } else {
                    //skip the duplication
                    while (low < n - 1 && num.get(low).equals(num.get(low + 1))) {
                        low++;
                    }
                    low++;
                }
            }
        }

        return result;
    }

    /**************************************************************************************************************/

    List<List<Integer>> threeSum2(List<Integer> num) {
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> r = combination(num, 3);
        for (int i = 0; i < r.size(); i++) {
            //r.get(i).stream().mapToInt(e->e).sum()==0
            if (isSumZero(r.get(i))) {
                result.add(r.get(i));
            }
        }
        return result;
    }

    List<List<Integer>> combination(List<Integer> v, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> d =new ArrayList<>();
        int n = v.size();
        for (int i = 0; i < n; i++) {
            d.add( (i < k) ? 1 : 0 );
        }

        //1) from the left, find the [1,0] pattern, change it to [0,1]
        //2) move all of the 1 before the pattern to the most left side
        //3) check all of 1 move to the right
        while(true) {
            List<Integer> tmp=new ArrayList<>();
            for(int x = 0; x < n; x++) {
                if (d.get(x)>0){
                    tmp.add(v.get(x));
                }
            }

            tmp.sort(Comparator.naturalOrder());
            result.add(tmp);

            //step 1), find [1,0] pattern
            int i;
            boolean found = false;
            int ones = 0;
            for(i = 0; i < n - 1; i++) {
                if (d.get(i) == 1 && d.get(i + 1) == 0) {
                    d.set(i, 0);
                    d.set(i + 1, 1);
                    found = true;

                    //step 2) move all of right 1 to the most left side
                    for (int j = 0; j < i; j++) {
                        d.set(j, ones > 0 ? 1 : 0);
                        ones--;
                    }
                    break;
                }
                if (d.get(i) == 1) {
                    ones++;
                }
            }
            if (!found) {
                break;
            }
        }

        return result;
    }

    boolean isSumZero(List<Integer> v) {
        return sum(v) == 0;
    }

    int sum(List<Integer> v) {
        int s = 0;
        for(int i = 0; i < v.size(); i++) {
            s += v.get(i);
        }
        return s;
    }

    public static void main(String[] args) {
        ThreeSum obj = new ThreeSum();

        List<Integer> test = Arrays.asList(-1, 0, 1, 2, -1, -4);
        System.out.println(obj.threeSum(test));
        System.out.println(obj.threeSum2(test));

        test = Arrays.asList();
        System.out.println(obj.threeSum(test));
        System.out.println(obj.threeSum2(test));

        test = Arrays.asList(0);
        System.out.println(obj.threeSum(test));
        System.out.println(obj.threeSum2(test));

        test = Arrays.asList(0,0,0,0);
        System.out.println(obj.threeSum(test));
        System.out.println(obj.threeSum2(test));
    }
}
