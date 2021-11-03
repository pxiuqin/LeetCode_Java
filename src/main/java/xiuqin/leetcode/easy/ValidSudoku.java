package xiuqin.leetcode.easy;

/**
 * https://leetcode.com/problems/valid-sudoku/
 * 36. 有效的数独
 * 请你判断一个9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 *
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用'.'表示。
 *
 * 注意：
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 *
 *
 * 示例 1：
 * img:doc/img/0-100/sudoku.png
 * 输入：board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * 输出：true
 *
 * 示例 2：
 * 输入：board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * 输出：false
 * 解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
 *
 *
 * 提示：
 * board.length == 9
 * board[i].length == 9
 * board[i][j] 是一位数字或者 '.'
 *
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
