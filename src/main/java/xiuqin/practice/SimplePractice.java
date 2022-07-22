package xiuqin.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimplePractice {
    /**
     * 一个简单的全排列
     * 
     * @param nums
     * @param track
     */
    private List<List<Integer>> res = new ArrayList<>();

    void permutation(Integer[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            // 这里注意创建新对象，要不会被remove成空的
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) {
                continue;
            }

            track.add(nums[i]);
            permutation(nums, track);
            track.remove(nums[i]);
        }
    }

    /**
     * 凑零钱，找到最少的硬币个数
     * 
     * @param args
     */
    int gatherMoney(int[] coins, int money) {
        if (money == 0) {
            return 0;
        }
        if (money < 0) {
            // 出现负数表示没有凑对，无解
            return -1;
        }

        int count = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int subCount = gatherMoney(coins, money - coins[i]);
            if (subCount == -1) {
                // 如果没有凑对，无解
                continue;
            }

            // 找到最优解决方案
            count = Math.min(count, subCount+1);
        }

        return count == Integer.MAX_VALUE ? -1 : count;
    }

    public static void main(String[] args) {
        SimplePractice obj = new SimplePractice();

        Integer[] nums = { 1, 2, 3 };
        obj.permutation(nums, new LinkedList<>());
        System.out.println(obj.res);

        int[] coins={1,2,5};
        System.out.println(obj.gatherMoney(coins, 10));
        System.out.println(obj.gatherMoney(coins, 9));
    }
}
