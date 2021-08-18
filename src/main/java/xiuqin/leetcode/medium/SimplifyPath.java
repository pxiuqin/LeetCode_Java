package xiuqin.leetcode.medium;

/**
 * https://leetcode.com/problems/simplify-path/
 * Given an absolute path for a file (Unix-style), simplify it.
 *
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 *
 *
 * Corner Cases:
 *
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 */
public class SimplifyPath {
  public String simplifyPath(String path) {
    String result = "";

    String[] elems = path.split("/");

    int ignore = 0;   // handle ..
    for (int i = elems.length - 1; i >= 0; i--) {
      if (elems[i].isEmpty() || elems[i].equals(".")) {
        continue;
      }

      if (elems[i].equals("..")) {
        ignore++;
        continue;
      }

      if (ignore > 0) {
        ignore--;
        continue;
      }

      if (result.length() == 0) {
        result = "/" + elems[i];
      } else {
        result = "/" + elems[i] + result;
      }
    }

    return result.length() > 0 ? result : "/";
  }

  public static void main(String[] args){
    SimplifyPath obj =new SimplifyPath();

    System.out.println(obj.simplifyPath( "/home/"));
    System.out.println(obj.simplifyPath("/a/./b/../../c/"));
  }
}
