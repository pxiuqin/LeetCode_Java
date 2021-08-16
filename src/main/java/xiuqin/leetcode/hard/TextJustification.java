package xiuqin.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/text-justification/
 * 68. 文本左右对齐
 * 给定一个单词数组和一个长度maxWidth，重新排版单词，使其成为每行恰好有maxWidth个字符，且左右两端对齐的文本。
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格' '填充，使得每行恰好有 maxWidth个字符。
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 * <p>
 * 说明:
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于maxWidth。
 * 输入单词数组 words至少包含一个单词。
 * <p>
 * 示例1:
 * 输入:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * 输出:
 * [
 * "This  is  an",
 * "example of text",
 * "justification. "
 * ]
 * <p>
 * 示例2:
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 * "What  must  be",
 * "acknowledgment ",
 * "shall be    "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 * 因为最后一行应为左对齐，而不是左右两端对齐。
 * 第二行同样为左对齐，这是因为这行只包含一个单词。
 * <p>
 * 示例3:
 * 输入:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 * "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * 输出:
 * [
 * "Science is what we",
 * "understand   well",
 * "enough to explain to",
 * "a computer. Art is",
 * "everything else we",
 * "do         "
 * ]
 */
public class TextJustification {
  public List<String> fullJustify(List<String> words, int L) {
    List<String> result = new ArrayList<>();

    int len = 0;
    int start = 0;
    int end = 0;
    double space = 0;
    boolean lastLine = false;

    for (int i = 0; i < words.size(); i++) {
      len += words.get(i).length();
      if (len + i - start > L || i == words.size() - 1) {
        // remove the last one
        if (len + i - start > L) {
          len -= words.get(i).length();
          end = i - 1;
          lastLine = false;
        } else {
          end = i;
          lastLine = true;
        }

        // calculate the space number
        space = L - len;
        double mspace, extra;
        if (lastLine) {
          mspace = 1;
          extra = 0;
        } else {
          mspace = Math.floor(space / (end - start));
          extra = space - mspace * (end - start);
        }

        String line = words.get(start);
        for (int j = start + 1; j <= end; j++) {
          for (int k = 0; k < mspace && space > 0; k++, space--) {
            line += " ";
          }
          if (j - start - 1 < extra) {
            line += " ";
            space--;
          }
          line += words.get(j);
        }

        // add the rest space
        if (space > 0) {
          for (; space > 0; space--) {
            line += " ";
          }
        }

        result.add(line);
        start = i;
        i = end;
        len = 0;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    TextJustification obj = new TextJustification();

    print(obj.fullJustify(Arrays.asList("This", "is", "an", "example", "of", "text", "justification."), 16));
    print(obj.fullJustify(Arrays.asList("What", "must", "be", "acknowledgment", "shall", "be"), 16));
    print(obj.fullJustify(Arrays.asList("Science", "is", "what", "we", "understand", "well", "enough", "to", "explain",
      "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"), 20));
  }

  public static void print(List<String> result) {
    for (String each : result) {
      System.out.println(each);
    }
    System.out.println();
  }
}
