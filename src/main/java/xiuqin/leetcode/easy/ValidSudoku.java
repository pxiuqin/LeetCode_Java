package xiuqin.leetcode.easy;

/**
 * https://leetcode.com/problems/valid-sudoku/
 * <p>
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * <p>
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * <p>
 * A partially filled sudoku which is valid.
 * <p>
 * Note:
 * > A valid Sudoku board (partially filled) is not necessarily solvable.
 * Only the filled cells need to be validated.
 */
public class ValidSudoku {
  public boolean isValidSudoku(char[][] board) {
    int cnt = 9;
    boolean row_mask[][] = new boolean[cnt][cnt];
    boolean col_mask[][] = new boolean[cnt][cnt];
    boolean area_mask[][] = new boolean[cnt][cnt];

    //check rows columns and areas
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[r].length; c++) {
        if (!Character.isDigit(board[r][c])) {
          continue;
        }
        int idx = board[r][c] - '0' - 1;

        //check the rows
        if (row_mask[r][idx] == true) {
          return false;
        }
        row_mask[r][idx] = true;

        //check the columns
        if (col_mask[c][idx] == true) {
          return false;
        }
        col_mask[c][idx] = true;

        //check the areas
        int area = (r / 3) * 3 + (c / 3);
        if (area_mask[area][idx] == true) {
          return false;
        }
        area_mask[area][idx] = true;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    ValidSudoku obj = new ValidSudoku();

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

    System.out.println(obj.isValidSudoku(test));
  }
}
