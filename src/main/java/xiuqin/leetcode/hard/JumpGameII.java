package xiuqin.leetcode.hard;

/**
 * https://leetcode.com/problems/jump-game-ii/
 * 45. 跳跃游戏 II
 * 给你一个非负整数数组nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 * <p>
 * 示例 1:
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳1步，然后跳3步到达数组的最后一个位置。
 * <p>
 * 示例 2:
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 * <p>
 * 提示:
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 1000
 */
public class JumpGameII {
  //Acutally, using the Greedy algorithm can have the answer
  public int jump(int A[]) {
    int n = A.length;
    if (n <= 1) return 0;

    int steps = 0;
    int coverPos = 0;

    for (int i = 0; i <= coverPos && i < n; ) {
      if (A[i] == 0) {
        return -1;
      }

      if (coverPos < A[i] + i) {
        coverPos = A[i] + i;  //max cover
        steps++;
      }

      //if pos is end then return steps
      if (coverPos >= n - 1) {
        return steps;
      }

      //Greedy: find the next place which can have biggest distance
      int nextPos = 0;
      int maxDistance = 0;
      for (int j = i + 1; j <= coverPos; j++) {
        if (A[j] + j > maxDistance) {
          maxDistance = A[j] + j;  //find max distance for greedy
          nextPos = j;
        }
      }
      i = nextPos;
    }

    return steps;
  }

  public static void main(String[] args) {
    JumpGameII obj = new JumpGameII();

    System.out.println(obj.jump(new int[]{2, 3, 1, 1, 4}));
    System.out.println(obj.jump(new int[]{2, 3, 0, 1, 4}));
    System.out.println(obj.jump(new int[]{1, 3, 0, 1, 4}));
    System.out.println(obj.jump(new int[]{3, 3, 2, 1, 4}));
    System.out.println(obj.jump(new int[]{3, 3, 4, 1, 4}));
  }
}
