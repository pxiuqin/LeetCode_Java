package xiuqin.leetcode.hard;

/**
 * https://leetcode.com/problems/edit-distance/
 * 72. 编辑距离
 * 给你两个单词word1 和word2，请你计算出将word1转换成word2 所使用的最少操作数。
 * 你可以对一个单词进行如下三种操作：
 * 1.插入一个字符
 * 2.删除一个字符
 * 3.替换一个字符
 * <p>
 * 示例1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * <p>
 * 示例2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * <p>
 * 提示：
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 */
public class EditDistance {
  /*
   *  Dynamic Programming
   *
   *    Definitaion
   *
   *        m[i][j] is minimal distance from word1[0..i] to word2[0..j]
   *
   *    So,
   *
   *        1) if word1[i] == word2[j], then m[i][j] == m[i-1][j-1].
   *
   *        2) if word1[i] != word2[j], then we need to find which one below is minimal:
   *
   *             min( m[i-1][j-1], m[i-1][j],  m[i][j-1] )
   *
   *             and +1 - current char need be changed.
   *
   *        Let's take a look  m[1][2] :  "a" => "ab"
   *
   *               +---+  +---+
   *        ''=> a | 1 |  | 2 | '' => ab
   *               +---+  +---+
   *
   *               +---+  +---+
   *        a => a | 0 |  | 1 | a => ab
   *               +---+  +---+
   *
   *        To know the minimal distance `a => ab`, we can get it from one of the following cases:
   *
   *            1) delete the last char in word1,  minDistance( '' => ab ) + 1
   *            2) delete the last char in word2,  minDistance(  a => a ) + 1
   *            3) change the last char,           minDistance( '' => a ) + 1
   *
   *
   *    For Example:
   *
   *        word1="abb", word2="abccb"
   *
   *        1) Initialize the DP matrix as below:
   *
   *               "" a b c c b
   *            "" 0  1 2 3 4 5
   *            a  1
   *            b  2
   *            b  3
   *
   *        2) Dynamic Programming
   *
   *               "" a b c c b
   *            ""  0 1 2 3 4 5
   *            a   1 0 1 2 3 4
   *            b   2 1 0 1 2 3
   *            b   3 2 1 1 2 2
   *
   */
  int minDistance(String word1, String word2) {
    int n1 = word1.length();
    int n2 = word2.length();

    if (n1 == 0) {
      return n2;
    }
    if (n2 == 0) {
      return n1;
    }

    int[][] m = new int[n1 + 1][n2 + 1];
    for (int i = 0; i < m.length; i++) {
      m[i][0] = i;
    }
    for (int i = 0; i < m[0].length; i++) {
      m[0][i] = i;
    }

    //Dynamic Programming
    for (int row = 1; row < m.length; row++) {
      for (int col = 1; col < m[row].length; col++) {
        if (word1.charAt(row - 1) == word2.charAt(col - 1)) {
          m[row][col] = m[row - 1][col - 1];
        } else {
          int minValue = Math.min(m[row - 1][col - 1], Math.min(m[row - 1][col], m[row][col - 1]));
          m[row][col] = minValue + 1;
        }
      }
    }

    return m[n1 - 1][n2 - 1];
  }

  public static void main(String[] args) {
    EditDistance obj = new EditDistance();

    String word1 = "horse", word2 = "ros";
    System.out.println(obj.minDistance(word1, word2));

    word1 = "intention";
    word2 = "execution";
    System.out.println(obj.minDistance(word1, word2));
  }
}
