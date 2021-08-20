package xiuqin.leetcode.medium;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/sort-colors/
 * <p>
 * Given an array with n objects colored red, white or blue, sort them so that objects of
 * the same color are adjacent, with the colors in the order red, white and blue.
 * <p>
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * <p>
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 * <p>
 * Follow up:
 * > A rather straight forward solution is a two-pass algorithm using counting sort.
 * > First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array
 * with total number of 0's, then 1's and followed by 2's.
 * > Could you come up with an one-pass algorithm using only constant space?
 */
public class SortColors {

  void sortColors(int a[]) {
    int n = a.length;
    int zero = 0, two = n - 1;

    for (int i = 0; i <= two; i++) {
      if (a[i] == 0) {
        int temp = a[zero];
        a[zero] = a[i];
        a[i] = temp;

        zero++;
      }

      if (a[i] == 2) {
        int temp = a[two];
        a[two] = a[i];
        a[i] = temp;

        two--;
        i--;
      }
    }
  }

  public void sortColors2(int[] colors) {
    int left = 0;
    int right = colors.length - 1;

    int i = 0;
    while (i <= right) {
      if (colors[i] == 0) {
        swap(colors, i, left);
        left++;
        i++;
      } else if (colors[i] == 1) {
        i++;
      } else {
        swap(colors, i, right);
        right--;
      }
    }
  }

  private void swap(int[] source, int i, int j) {
    int temp = source[j];
    source[j] = source[i];
    source[i] = temp;
  }

  public static void main(String[] args) {
    SortColors obj = new SortColors();

    int[] test = new int[]{2, 0, 2, 1, 1, 0};
    obj.sortColors2(test);
    print(test);
  }

  public static void print(int[] result) {
    String rowStr = Arrays.stream(result).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    System.out.println(rowStr);
  }
}
