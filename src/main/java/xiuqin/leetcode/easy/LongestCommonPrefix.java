package xiuqin.leetcode.easy;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/longest-common-prefix/
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(List<String> strs) {
        String word="";
        if (strs.size()<=0) {
            return word;
        }
        for(int i=1; i<=strs.get(0).length(); i++){
            String w = strs.get(0).substring(0, i);
            boolean match = true;

            for(int j=1; j<strs.size(); j++){
                if (i>strs.get(j).length() || !w.equals(strs.get(j).substring(0, i)) ) {
                    match=false;
                    break;
                }
            }

            if (!match) {
                return word;
            }
            word = w;
        }

        return word;
    }

    public static void main(String[] args){
        LongestCommonPrefix obj=new LongestCommonPrefix();

        List<String> strings= Arrays.asList("abc","ab111","ab234");
        System.out.println(obj.longestCommonPrefix(strings));
    }

}
