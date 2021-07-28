package xiuqin.leetcode.medium;

import java.util.*;

/**
 * https://leetcode.com/problems/anagrams/
 * <p>
 * Given an array of strings, group anagrams together.
 * <p>
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Return:
 * <p>
 * [
 * ["ate", "eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * <p>
 * Note:
 * <p>
 * For the return value, each inner list's elements must follow the lexicographic order.
 * All inputs will be in lower-case.
 */
public class GroupAnagrams {
  List<List<String>> groupAnagrams(List<String> strs) {
    List<List<String>> result = new ArrayList<>();
    Map<String, Integer> m = new HashMap<>();

    for (int i = 0; i < strs.size(); i++) {
      String word = strs.get(i);
      word = sort(word);

      if (!m.containsKey(word)) {
        List<String> v = new ArrayList<>();
        v.add(strs.get(i));
        result.add(v);
        m.put(word, result.size() - 1);
      } else {
        result.get(m.get(word)).add(strs.get(i));
      }
    }

    for (int i = 0; i < result.size(); i++) {
      result.get(i).sort(Comparator.naturalOrder());
    }

    return result;
  }

  //using multiset
  List<List<String>> groupAnagrams01(List<String> strs) {
    List<List<String>> result = new ArrayList<>();
    Map<String, Set<String>> m = new HashMap<>();
    for (int i = 0; i < strs.size(); i++) {
      String word = strs.get(i);
      word = sort(word);

      if (!m.containsKey(word)) {
        m.put(word, new HashSet<>());
      }

      m.get(word).add(strs.get(i));
    }

    for (Map.Entry<String, Set<String>> item : m.entrySet()) {
      result.add(new ArrayList<>(item.getValue()));
    }

    return result;
  }


  //NOTICE: the below solution has been deprecated as the problem has been updated!
  List<String> anagrams(List<String> strs) {
    List<String> result = new ArrayList<>();
    Map<String, Integer> m = new HashMap<>();
    for (int i = 0; i < strs.size(); i++) {
      String word = strs.get(i);

      //sort it can easy to check they are anagrams or not
      word = sort(word);

      //only is not add
      if (!m.containsKey(word)) {
        m.put(word, i);
      } else {
        if (m.get(word) >= 0) {
          result.add(strs.get(m.get(word)));
          m.put(word, -1);
        }

        result.add(strs.get(i));
      }
    }

    return result;
  }

  private String sort(String word) {
    char[] word3chars = word.toCharArray();
    Arrays.sort(word3chars);
    return new String(word3chars);
  }

  public static void main(String[] args) {
    GroupAnagrams obj = new GroupAnagrams();

    List<String> test = Arrays.asList("eat", "tea", "tan", "ate", "nat", "bat");
    System.out.println(obj.groupAnagrams(test));
    System.out.println(obj.groupAnagrams01(test));
    System.out.println(obj.anagrams(test));
  }
}
