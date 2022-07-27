package xiuqin.practice;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
            count = Math.min(count, subCount + 1);
        }

        return count == Integer.MAX_VALUE ? -1 : count;
    }

    int[] memo=null;
    int gatherMoney4DP(int[] coins, int money){
        memo=new int[money+1];
        Arrays.fill(memo, -100);
        return gatherMoney4DP1(coins, money);
    }
    
    int gatherMoney4DP1(int[] coins, int money) {
        if (money == 0) {
            return 0;
        }
        if (money < 0) {
            // 出现负数表示没有凑对，无解
            return -1;
        }

        if(memo[money]!=-100){
            return memo[money];
        }

        int count = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int subCount = gatherMoney(coins, money - coins[i]);
            if (subCount == -1) {
                // 如果没有凑对，无解
                continue;
            }

            // 找到最优解决方案
            count = Math.min(count, subCount + 1);
        }

        memo[money]= count == Integer.MAX_VALUE ? -1 : count;
        return memo[money];
    }


    int gatherMoney4DP2(int[] coins, int money) {
        int[] dp = new int[money + 1];
        Arrays.fill(dp, money + 1);

        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    // 无解
                    continue;
                }

                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }

        return dp[money] == dp[money + 1] ? -1 : dp[money];
    }

    /**
     * 前序遍历
     * 
     * @param args
     */
    LinkedList<Integer> preorderTree(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        result.add(root.val);
        result.addAll(preorderTree(root.left));
        result.addAll(preorderTree(root.right));
        return result;
    }

    public static void main(String[] args) {
        SimplePractice obj = new SimplePractice();

        Integer[] nums = { 1, 2, 3 };
        obj.permutation(nums, new LinkedList<>());
        System.out.println(obj.res);

        int[] coins = { 1, 2, 5 };
        System.out.println(obj.gatherMoney(coins, 10));
        System.out.println(obj.gatherMoney(coins, 9));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int x) {
        val = x;
        left = null;
        right = null;
    }

    public static void printList(TreeNode h) {
        if (h != null) {
            System.out.println(h.val);
            if (h.left != null) {
                System.out.print("node:" + h.val + " left:");
                printList(h.left);
            }

            if (h.right != null) {
                System.out.print("node:" + h.val + " right:");
                printList(h.right);
            }
        }
    }
}
