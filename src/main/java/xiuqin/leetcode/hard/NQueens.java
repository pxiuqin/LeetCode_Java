package xiuqin.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/n-queens/
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other.
 * <p>
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * <p>
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space respectively.
 * <p>
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 * <p>
 * [
 * [".Q..",  // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * <p>
 * 算法1
 * 这种棋盘类的题目一般是回溯法, 依次放置每行的皇后。在放置的时候，要保持当前的状态为合法，即当前放置位置的同一行、同一列、两条对角线上都不存在皇后。
 * <p>
 * 算法2
 * 用一个一位数组来存放当前皇后的状态。假设数组为int state[n], state[i]表示第 i 行皇后所在的列。那么在新的一行 k 放置一个皇后后:
 * 1) 判断列是否冲突，只需要看state数组中state[0…k-1] 是否有和state[k]相等；
 * 2) 判断对角线是否冲突：如果两个皇后在同一对角线，那么|row1-row2| = |column1 - column2|，（row1，column1），（row2，column2）分别为冲突的两个皇后的位置
 */
public class NQueens {
  public List<List<String>> solveNQueens(int n) {
    List<List<String>> result = new ArrayList<>();

    List<String> s = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      char[] temp = new char[n];
      for (int j = 0; j < n; j++) {
        temp[j] = '.';
      }
      s.add(new String(temp));
    }

    helper(s, 0, result);

    return result;
  }

  private void helper(List<String> cur, int row, List<List<String>> result) {
    if (row == cur.size()) {
      result.add(new ArrayList<>(cur));
      return;
    }

    for (int col = 0; col < cur.size(); col++) {
      if (isValid1(cur, row, col)) {
        updateRow(cur, row, col, 'Q');
        helper(cur, row + 1, result);
        updateRow(cur, row, col, '.');
      }
    }
  }

  //判断在cur[row][col]位置放一个皇后，是否是合法的状态
  //已经保证了每行一个皇后，只需要判断列是否合法以及对角线是否合法。
  private boolean isValid1(List<String> cur, int row, int col) {
    //列
    for (int i = 0; i < row; i++) {
      if (cur.get(i).charAt(col) == 'Q') {
        return false;
      }
    }

    //主对角线(只需要判断对角线上半部分，因为后面的行还没有开始放置)
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
      if (cur.get(i).charAt(j) == 'Q') {
        return false;
      }
    }

    //副对角线(只需要判断对角线上半部分，因为后面的行还没有开始放置)
    for (int i = row - 1, j = col + 1; i >= 0 && j < cur.size(); i--, j++) {
      if (cur.get(i).charAt(j) == 'Q') {
        return false;
      }
    }
    return true;
  }

  private void updateRow(List<String> s, int row, int col, char content) {
    char[] temp = s.get(row).toCharArray();   //to char
    temp[col] = content;
    s.set(row, new String(temp));  //update Str
  }


  /*******************************************************算法2********************************************/
  public List<List<String>> solveNQueens2(int n) {
    List<List<String>> result = new ArrayList<>();

    List<Integer> state = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      state.add(-1);
    }

    for (int row = 0, col; ; ) {
      for (col = state.get(row) + 1; col < n; col++)//从上一次放置的位置后面开始放置
      {
        if (isValid(col, row, state)) {
          state.set(row, col);
          if (row == n - 1)//找到了一个解,继续试探下一列
          {
            List<String> s = new ArrayList<>();
            for (int i = 0; i < n; i++) {
              char[] temp = new char[n];
              for (int j = 0; j < n; j++) {
                temp[j] = '.';
              }
              s.add(new String(temp));
            }

            for (int j = 0; j < n; j++) {
              char[] temp = s.get(j).toCharArray();   //to char
              temp[state.get(j)] = 'Q';  //update only char
              s.set(j, new String(temp));  //update Str
            }

            result.add(s);
          } else {
            row++;
            break;
          }//当前状态合法，去放置下一行的皇后
        }
      }

      if (col == n)//当前行的所有位置都尝试过，回溯到上一行
      {
        if (row == 0) break;//所有状态尝试完毕，退出
        state.set(row, -1);//回溯前清除当前行的状态
        row--;
      }
    }

    return result;
  }

  public List<List<String>> solveNQueens2_1(int n) {
    List<List<String>> result = new ArrayList<>();
    List<Integer> solution = Arrays.asList(new Integer[n]);

    solveNQueensRecursive(n, 0, solution, result);

    return result;
  }

  //The following recursion is easy to understand. Nothing's tricky.
  //1) recursively find all of possible columns row by row.
  //2) solution[] array only stores the columns index. `solution[row] = col;`
  private void solveNQueensRecursive(int n, int currentRow, List<Integer> solution, List<List<String>> result) {
    //if no more row need to do, shape the result
    if (currentRow == n) {
      List<String> s = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        char[] temp = new char[n];
        for (int j = 0; j < n; j++) {
          temp[j] = '.';
        }
        s.add(new String(temp));
      }

      for (int row = 0; row < n; row++) {
        char[] temp = s.get(row).toCharArray();   //to char
        temp[solution.get(row)] = 'Q';  //update only char
        s.set(row, new String(temp));  //update Str
      }

      result.add(s);

      return;
    }

    //for each column
    for (int col = 0; col < n; col++) {

      //if the current column is valid
      if (isValid(col, currentRow, solution)) {
        //place the Queue
        solution.set(currentRow, col);

        //recursively put the Queen in next row
        solveNQueensRecursive(n, currentRow + 1, solution, result);
      }
    }
  }

  //Attempting to put the Queen into [row, col], check it is valid or not
  //Notes:
  //1) we just checking the Column not Row, because the row cannot be conflicted
  //2) to check the diagonal, we just check |x'-x| == |y'-y|, (x',y') is a Queen will be placed
  private boolean isValid(int attemptedColumn, int attemptedRow, List<Integer> queenInColumn) {

    for (int i = 0; i < attemptedRow; i++) {
      if (
        //check the same column
        attemptedColumn == queenInColumn.get(i) ||

          //check the diagonal
          Math.abs(attemptedColumn - queenInColumn.get(i)) == Math.abs(attemptedRow - i)) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    NQueens obj = new NQueens();

    printMatrix(obj.solveNQueens(4));

    printMatrix(obj.solveNQueens(8));
  }

  public static void printMatrix(List<List<String>> source) {
    List<List<String>> result = source;
    System.out.println("result: " + result.size());
    for (List<String> strings : result) {
      for (String string : strings) {
        System.out.println(string);
      }
      System.out.println("------------------");
    }
  }

}
