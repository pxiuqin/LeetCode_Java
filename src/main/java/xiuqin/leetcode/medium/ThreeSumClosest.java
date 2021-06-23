package xiuqin.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/3sum-closest/
 * 16. 最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。
 * 假定每组输入只存在唯一答案。
 * <p>
 * 示例：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *  
 * 提示：
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 */
public class ThreeSumClosest {
    int threeSumClosest(List<Integer> num, int target) {
        //sort the array
        num.sort(Comparator.naturalOrder());

        int n = num.size();
        int distance = Integer.MAX_VALUE;
        int result=0;

        for (int i=0; i<n-2; i++) {
            //skip the duplication
            if (i > 0 && num.get(i - 1).equals(num.get(i))) {
                continue;
            }
            int a = num.get(i);
            int low = i + 1;
            int high = n - 1;

            //convert the 3sum to 2sum problem
            while (low < high) {
                int b = num.get(low);
                int c = num.get(high);
                int sum = a + b + c;
                if (sum - target == 0) {
                    //got the final soultion
                    return target;
                } else {
                    //tracking the minmal distance
                    if (Math.abs(sum - target) < distance ) {
                        distance = Math.abs(sum - target);
                        result = sum;
                    }

                    if (sum - target > 0) {
                        //skip the duplication
                        while(high > 0 && num.get(high).equals(num.get(high - 1))){
                            high--;
                        }
                        //move the `high` pointer
                        high--;
                    } else {
                        //skip the duplication
                        while(low < n && num.get(low).equals(num.get(low + 1))){
                            low++;
                        }
                        //move the `low` pointer
                        low++;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ThreeSumClosest obj = new ThreeSumClosest();

        List<Integer> test= Arrays.asList(-1,2,1,-4);
        System.out.println(obj.threeSumClosest(test,1));

        test=Arrays.asList(-4,-1,-1,1,2);
        System.out.println(obj.threeSumClosest(test,2));
        System.out.println(obj.threeSumClosest(test,5));
    }
}
