package xiuqin.leetcode.medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/next-permutation/
 * 31. 下一个排列
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 * (找到一个排列按照字母表顺序的情况下，下一个更大的排列是多少。如果已经是最大的，那么应该翻转。必须原地翻转)
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * <p>
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * <p>
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * <p>
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * <p>
 * 解题方法
 * 逆序数字交换再翻转
 * 很有技巧的题目。
 * 先找到从后向前数，第一个降序的位置，把此位置后面的翻转。再把这个降序数字和后面第一个比它大的位置交换即可。
 * 首先说一下这题怎么想到的。有如下的一个数组
 * 1　　2　　7　　4　　3　　1
 * 下一个排列为：
 * 1　　3　　1　　2　　4　　7
 * 观察可以发现，再给出的数组中，2之后的数字都是降序排列的，我们把2后面第一个比2大的数字放到最前面，然后让3后面的数字升序排列。
 * 简单思路的证明：从7开始是降序的，也就是说7 4 3 1不可能通过重新排列构成更大的数字。如果要得到next permutation，那么必须把2这个位置的数字给换掉才行，而且只能换成比2大的数字在才能使next permutation > current permutation.至于换成多大的数字，很明显的需要换成在2后面的数字中刚好比2大的数字，证明可以使用反证法来说明换成其他数字要么比当前数字小，要么大于正确的next permutation.
 * 下面这个做法是先逆序再交换，本质和上面的证明一样：
 * 如果从第n个数字到最后都是递减的并且第n-1个数字小于第n个，说明从第n个数字开始的这段序列是字典序组合的最后一个，在下一个组合中他们要倒序变为字典序第一个，然后从这段序列中找出第一个大于第n-1个数字的数和第n-1个数字交换就可以了。
 * 举个栗子，当前组合为12431，可以看出431是递减的，同时4>2，这样我们把431倒序，组合就变为12134，然后从134中找出第一个大于2的数字和2交换，这样就得到了下一个组合13124。对于完全递减的组合例如4321在倒序之后就可以结束了。
 */
public class NextPermutation {
    public int[] nextPermutation(int[] num) {
        if (num.length <= 1) {
            return num;
        }

        for (int i = num.length - 1; i > 0; i--) {
            if (num[i - 1] < num[i]) {
                int j = num.length - 1;
                while (num[i - 1] >= num[j]) {
                    //先找到从后向前数，第一个降序的位置
                    j--;
                }

                //把此位置后面的翻转
                int tmp = num[j];
                num[j] = num[i - 1];
                num[i - 1] = tmp;

                //再把这个降序数字和后面第一个比它大的位置交换
                Arrays.sort(num, i, num.length);
                break;
            }

            //edge case: 4 3 2 1
            if (i == 1) {
                Arrays.sort(num);
                break;
            }
        }

        return num;
    }

    public static void main(String[] args) {
        NextPermutation obj = new NextPermutation();

        System.out.println(toString(obj.nextPermutation(new int[]{1, 2, 3})));
        System.out.println(toString(obj.nextPermutation(new int[]{3, 2, 1})));
        System.out.println(toString(obj.nextPermutation(new int[]{1, 1, 5})));
        System.out.println(toString(obj.nextPermutation(new int[]{1})));
    }

    public static String toString(int[] num) {
        return Arrays.stream(num).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }
}
