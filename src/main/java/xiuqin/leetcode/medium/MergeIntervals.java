package xiuqin.leetcode.medium;

import java.util.*;

/**
 * https://leetcode.com/problems/merge-intervals/
 * 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * <p>
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 示例2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * 提示：
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 * <p>
 * Given a collection of intervals, merge all overlapping intervals.
 * <p>
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */
public class MergeIntervals {
  //Two factos sorting [start:end]
  private boolean compare(Interval lhs, Interval rhs) {
    return (lhs.start == rhs.start) ? lhs.end < rhs.end : lhs.start < rhs.start;
  }

  List<Interval> merge(List<Interval> intervals) {
    List<Interval> result = new ArrayList<>();

    if (intervals.size() <= 0) {
      return result;
    }

    //sort the inervals. Note: using the customized comparing function.
    intervals.sort(Comparator.naturalOrder());

    for (int i = 0; i < intervals.size(); i++) {
      int size = result.size();

      // if the current intervals[i] is overlapped with previous interval.
      // merge them together
      if (size > 0 && result.get(size - 1).end >= intervals.get(i).start) {
        result.get(size - 1).end = Math.max(result.get(size - 1).end, intervals.get(i).end);
      } else {
        result.add(intervals.get(i));
      }
    }

    return result;
  }

  public static void main(String[] args) {
    MergeIntervals obj = new MergeIntervals();

    List<Interval> test = new ArrayList<>();
    test.add(new Interval(1, 3));
    test.add(new Interval(2, 6));
    test.add(new Interval(8, 10));
    test.add(new Interval(15, 18));
    System.out.println(obj.merge(test));
  }
}

class Interval implements Comparable<Interval> {
  int start;
  int end;

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public String toString() {
    return String.format("[%d,%d]", this.start, this.end);
  }

  @Override
  public int compareTo(Interval interval) {
    if (start == interval.start) {
      if (end == interval.end) {
        return 0;
      } else if (end < interval.end) {
        return -1;
      } else {
        return 1;
      }
    } else {
      if (start < interval.start) {
        return -1;
      } else {
        return 1;
      }
    }
  }
}
