package xiuqin.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/4sum/
 * 18. 四数之和
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 注意：答案中不可以包含重复的四元组。
 * <p>
 * <p>
 * 示例 1：
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * <p>
 * 示例 2：
 * 输入：nums = [], target = 0
 * 输出：[]
 *  
 * 提示：
 * 0 <= nums.length <= 200
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 */
public class FourSum {
    /*
     * 1) Sort the array,
     * 2) traverse the array, and solve the problem by using "3Sum" soultion.
     */
    public List<List<Integer>> fourSum(List<Integer> num, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (num.size() < 4) {
            return result;
        }
        num.sort(Comparator.naturalOrder());

        for (int i = 0; i < num.size() - 3; i++) {
            //skip the duplication
            if (i > 0 && num.get(i - 1).equals(num.get(i))){
                continue;
            }

            List<Integer> n=num.subList(i+1,num.size());
            List<List<Integer>> ret = threeSum(n, target - num.get(i));
            for (List<Integer> each : ret) {
                each.add(num.get(i));
                result.add(each);
            }
        }

        return result;
    }

    List<List<Integer>> threeSum(List<Integer> num, int target) {
        List<List<Integer>> result = new ArrayList<>();

        if (num.size() < 3) {
            return result;
        }

        //sort the array, this is the key
        //num.sort(Comparator.naturalOrder());

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
                if (a + b + c == target) {
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
                } else if (a + b + c > target) {
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

    public static void main(String[] args) {
        FourSum obj=new FourSum();

        List<Integer> test= Arrays.asList(1,0,-1,0,-2,2);
        System.out.println(obj.fourSum(test,0));

    }
}
