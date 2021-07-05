package xiuqin.leetcode.hard;

import java.util.*;

/**
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 * 30. 串联所有单词的子串
 * <p>
 * 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
 * <p>
 * 示例 1：
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * <p>
 * 示例 2：
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 *  
 * 提示：
 * 1 <= s.length <= 10^4
 * s 由小写英文字母组成
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i] 由小写英文字母组成
 *
 * 思路仍然是维护一个窗口，如果当前单词在字典中，则继续移动窗口右端，否则窗口左端可以跳到字符串下一个单词了
 */
public class SubstringWithConcatenationOfAllWords {
    public List<Integer> findSubstring(String S, List<String> L) {

        List<Integer> result = new ArrayList<>();
        if (S.length() <= 0 || L.size() <= 0) {
            return result;
        }

        int n = S.length(), m = L.size(), l = L.get(0).length();  // 长度相同的单词words

        //put all of words into a map
        Map<String, Integer> expected = new HashMap<>();
        for (int i = 0; i < m; i++) {
            if (expected.containsKey(L.get(i))) {
                expected.put(L.get(i), expected.get(L.get(i)) + 1);
            } else {
                expected.put(L.get(i), 1);
            }
        }

        for (int i = 0; i < l; i++) {
            Map<String, Integer> actual = new HashMap<>();
            int count = 0; //total count
            int winLeft = i;
            for (int j = i; j <= n - l; j += l) {
                String word = S.substring(j, j+l);
                //if not found, then restart from j+1;
                if (!expected.containsKey(word)) {
                    actual.clear();
                    count = 0;
                    winLeft = j + l;
                    continue;
                }
                count++;
                //count the number of "word"
                if (!actual.containsKey(word)) {
                    actual.put(word, 1);
                } else {
                    actual.put(word, actual.get(word) + 1);
                }
                // If there is more appearance of "word" than expected
                if (actual.get(word) > expected.get(word)) {
                    String tmp = "";
                    do {
                        tmp = S.substring(winLeft, winLeft+l);
                        count--;
                        actual.put(tmp, actual.get(tmp) - 1);
                        winLeft += l;
                    } while (!tmp.equals(word));
                }

                // if total count equals L's size, find one result
                if (count == m) {
                    result.add(winLeft);
                    String tmp = S.substring(winLeft, winLeft+l);
                    actual.put(tmp, actual.get(tmp) - 1);
                    winLeft += l;
                    count--;
                }

            }
        }

        return result;
    }

    public ArrayList<Integer> findSubstring(String S, String[] L) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(S==null || S.length()==0 || L==null || L.length==0)
            return res;
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        for(int i=0;i<L.length;i++)
        {
            if(map.containsKey(L[i]))
            {
                map.put(L[i],map.get(L[i])+1);
            }
            else
            {
                map.put(L[i],1);
            }
        }
        for(int i=0;i<L[0].length();i++)
        {
            HashMap<String,Integer> curMap = new HashMap<String,Integer>();
            int count = 0;
            int left = i;
            for(int j=i;j<=S.length()-L[0].length();j+=L[0].length())
            {
                String str = S.substring(j,j+L[0].length());

                if(map.containsKey(str))
                {
                    if(curMap.containsKey(str))
                        curMap.put(str,curMap.get(str)+1);
                    else
                        curMap.put(str,1);
                    if(curMap.get(str)<=map.get(str))
                        count++;
                    else
                    {
                        while(curMap.get(str)>map.get(str))
                        {
                            String temp = S.substring(left,left+L[0].length());
                            if(curMap.containsKey(temp))
                            {
                                curMap.put(temp,curMap.get(temp)-1);
                                if(curMap.get(temp)<map.get(temp))
                                    count--;
                            }
                            left += L[0].length();
                        }
                    }
                    if(count == L.length)
                    {
                        res.add(left);
                        //if(left<)
                        String temp = S.substring(left,left+L[0].length());
                        if(curMap.containsKey(temp))
                            curMap.put(temp,curMap.get(temp)-1);
                        count--;
                        left += L[0].length();
                    }
                }
                else
                {
                    curMap.clear();
                    count = 0;
                    left = j+L[0].length();
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SubstringWithConcatenationOfAllWords obj = new SubstringWithConcatenationOfAllWords();

        List<String> test = Arrays.asList("foo", "bar");
        System.out.println(obj.findSubstring("barfoothefoobarman", test));

        test=Arrays.asList("word","good","best","word");
        System.out.println(obj.findSubstring("wordgoodgoodgoodbestword",test));

        test=Arrays.asList("bar","foo","the");
        System.out.println(obj.findSubstring("barfoofoobarthefoobarman",test));
    }
}
