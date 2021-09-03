package xiuqin.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/restore-ip-addresses/
 * 93. 复原 IP 地址
 * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * <p>
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * <p>
 * 示例 2：
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * <p>
 * 示例 3：
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * <p>
 * 示例 4：
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * <p>
 * 示例 5：
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * <p>
 * 提示：
 * 0 <= s.length <= 3000
 * s 仅由数字组成
 */
public class RestoreIPAddresses {
  public List<String> restoreIpAddresses(String s) {
    List<String> result = new ArrayList<>();
    String ip = "";
    restoreIpAddressesHelper(s, 0, 0, ip, result);

    return result;
  }

  private void restoreIpAddressesHelper(String s, int start, int partNum, String ip, List<String> result) {
    int len = s.length();
    if (len - start < 4 - partNum || len - start > (4 - partNum) * 3) {
      //can not < 4 and > 12
      return;
    }

    if (partNum == 4 && start == len) {
      ip = ip.substring(0, ip.length() - 1);
      result.add(ip);
    }

    int num = 0;
    for (int i = start; i < start + 3 && i < len; i++) {
      num = num * 10 + s.charAt(i) - '0';
      if (num < 256) {
        ip += s.charAt(i);
        restoreIpAddressesHelper(s, i + 1, partNum + 1, ip + '.', result);
      }

      //0.0.0.0 valid, but 0.1.010.01 is not valid
      if (num == 0) {
        break;
      }
    }
  }

  public static void main(String[] args) {
    RestoreIPAddresses obj = new RestoreIPAddresses();

    System.out.println(obj.restoreIpAddresses("25525511135"));
    System.out.println(obj.restoreIpAddresses("0000"));
    System.out.println(obj.restoreIpAddresses("1111"));
    System.out.println(obj.restoreIpAddresses("010010"));
    System.out.println(obj.restoreIpAddresses("101023"));
  }
}
