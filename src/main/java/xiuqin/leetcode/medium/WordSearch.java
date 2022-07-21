package xiuqin.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/word-search/
 * 79. 单词搜索
 * 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例 1：
 * img(doc/img/word2.jpg)
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * <p>
 * 示例 2：
 * img(doc/img/word-1.jpg)
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 * <p>
 * 示例 3：
 * img(doc/img/word3.jpg)
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 * <p>
 * 提示：
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 */
public class WordSearch {
  private boolean exist(List<List<Character>> board, String word, int idx, int row, int col) {
    if (row < 0 || row >= board.size() ||
      col < 0 || col >= board.get(0).size() ||
      board.get(row).get(col) != word.charAt(idx)) {
      return false;
    }

    if (idx + 1 == word.length()) {
      return true;
    }

    //replace to a special char to avoid duplication.
    board.get(row).set(col, '\0');

    // right/left and up/down
    if (exist(board, word, idx + 1, row + 1, col) ||
      exist(board, word, idx + 1, row - 1, col) ||
      exist(board, word, idx + 1, row, col + 1) ||
      exist(board, word, idx + 1, row, col - 1)) {
      return true;
    }

    //restore the char
    board.get(row).set(col, word.charAt(idx));

    return false;
  }

  public boolean wordExist(List<List<Character>> board, String word) {
    if (board.size() <= 0 || word.length() <= 0) {
      return false;
    }

    for (int row = 0; row < board.size(); row++) {
      for (int col = 0; col < board.get(row).size(); col++) {
        //start first of the word
        if (board.get(row).get(col) == word.charAt(0)) {
          boolean result = exist(board, word, 0, row, col);
          if (result) {
            return true;
          }
        }
      }
    }

    return false;
  }

  public static void main(String[] args) {
    WordSearch obj = new WordSearch();

    /**
    List<List<Character>> board = Arrays.asList(
      Arrays.asList('A', 'B', 'C', 'E'),
      Arrays.asList('S', 'F', 'C', 'S'),
      Arrays.asList('A', 'D', 'E', 'E'));
     */
    Character[][] board=new Character[][] {
      {'A', 'B', 'C', 'E'},
      {'S', 'F', 'C', 'S'},
      {'A', 'D', 'E', 'E'}
    };

    System.out.println(obj.wordExist(array2List(board), "ABCCED"));
    System.out.println(obj.wordExist(array2List(board), "SEE"));
    System.out.println(obj.wordExist(array2List(board), "ABCB"));
  }

  public static <T> List<List<T>> array2List(T[][] source) {
    List<List<T>> list = new ArrayList<>();
    for (T[] array : source) {
      list.add(Arrays.asList(array.clone()));
    }

    return list;
  }
}
