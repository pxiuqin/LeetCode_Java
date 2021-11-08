package xiuqin.leetcode.hard;

/**
 * https://leetcode.com/problems/sudoku-solver/
 * 37. 解数独
 * 编写一个程序，通过填充空格来解决数独问题。
 * <p>
 * 数独的解法需 遵循如下规则：
 * <p>
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用'.'表示。
 * <p>
 * 示例：
 * (img:doc/img/sudoku-by-9.png)
 * <p>
 * 输入：board = [
 * ["5","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]]
 * <p>
 * 输出：[
 * ["5","3","4","6","7","8","9","1","2"],
 * ["6","7","2","1","9","5","3","4","8"],
 * ["1","9","8","3","4","2","5","6","7"],
 * ["8","5","9","7","6","1","4","2","3"],
 * ["4","2","6","8","5","3","7","9","1"],
 * ["7","1","3","9","2","4","8","5","6"],
 * ["9","6","1","5","3","7","2","8","4"],
 * ["2","8","7","4","1","9","6","3","5"],
 * ["3","4","5","2","8","6","1","7","9"]]
 * 解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
 * (img:doc/img/0-100/sudoku-by-9-solutionsvg.png)
 */
public class SudokuSolver {
  int cnt = 9;
  boolean row_mask[][];
  boolean col_mask[][];
  boolean area_mask[][];

  boolean initSudokuMask(char[][] board) {
    row_mask = new boolean[cnt][cnt];
    col_mask = new boolean[cnt][cnt];
    area_mask = new boolean[cnt][cnt];

    //check each rows and cols
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[r].length; c++) {
        if (!Character.isDigit(board[r][c])) {
          continue;
        }

        int idx = board[r][c] - '0' - 1;

        //check the rows/cols/areas
        int area = (r / 3) * 3 + (c / 3);
        if (row_mask[r][idx] || col_mask[c][idx] || area_mask[area][idx]) {
          return false;
        }

        row_mask[r][idx] = col_mask[c][idx] = area_mask[area][idx] = true;
      }
    }

    return true;
  }

  boolean recursiveSudoKu(char[][] board, int row, int col) {
    if (row >= this.cnt) {
      return true;   //recursive all rows
    }

    if (col >= this.cnt) {
      return recursiveSudoKu(board, row + 1, 0);  //recursive next row when column equal 9
    }

    if (board[row][col] != '.') {
      return recursiveSudoKu(board, row, col + 1); //recursive next column
    }

    //pick a number for empty cell
    int area;
    for (int i = 0; i < this.cnt; i++) {
      area=(row/3)*3+(col/3);
      if(row_mask[row][i]||col_mask[col][i]||area_mask[area][i]){
        continue;
      }

      //set the number and solve it recursively
      board[row][col]=(char) (i+'1');
      row_mask[row][i]=col_mask[col][i]=area_mask[area][i]=true;

      if(recursiveSudoKu(board,row,col+1)==true){
        return true;  //recursive each columns
      } else {
        //backtrace
        board[row][col]='.';  //把后边儿的“.“改成数字后，发现做不下去了，得回溯，把这个数字改回.
        row_mask[row][i]=col_mask[col][i]=area_mask[area][i]=false;
      }
    }

    return false;
  }

  boolean solveSudoku(char[][] board) {
    if (initSudokuMask(board) == false) {
      return false;
    }

    return recursiveSudoKu(board, 0, 0);
  }

  public static void main(String[] args) {
    SudokuSolver obj = new SudokuSolver();

    char[][] test = new char[][]{
      {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
      {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
      {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
      {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
      {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
      {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
      {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
      {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
      {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };
    System.out.println(obj.solveSudoku(test));

    test = new char[][]{
      {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
      {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
      {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
      {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
      {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
      {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
      {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
      {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
      {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };
    System.out.println(obj.solveSudoku(test));
  }
}
