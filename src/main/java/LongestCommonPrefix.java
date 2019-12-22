//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。
//
// 示例 1:
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
//
//
// 示例 2:
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
//
//
// 说明:
//
// 所有输入只包含小写字母 a-z 。
// Related Topics 字符串

public class LongestCommonPrefix {
    /**
     * 题14：最长公共前缀
     *
     * @param strs 字符串数组
     * @return 最长公共前缀
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        int idx = 0;
        char ch;
        boolean same = true;
        while (same) {
            for (int i = 1; i <= strs.length - 1; i++) {
                if (idx > strs[0].length() - 1) {
                    same = false;
                    break;
                } else {
                    ch = strs[0].charAt(idx);
                }

                if (idx > strs[i].length() - 1) {
                    same = false;
                    break;
                }
                char tmp = strs[i].charAt(idx);
                if (tmp != ch) {
                    same = false;
                    break;
                }
                if (i == strs.length - 1) {
                    idx++;
                }
            }
        }

        return strs[0].substring(0, idx);
    }
}
