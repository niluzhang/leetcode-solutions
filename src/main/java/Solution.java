import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().myAtoi("  -1234 1234www"));
    }

    /**
     * 题3：无重复的最长子串
     * <p>
     * 思路：滑动窗口[left, right], 初始时left = right = 0，控制right向右滑动，当遇到字符和窗口
     * 内i位置的字符重复时，更新窗口left为i+1，right重新从left处开始移动。
     *
     * @param s 字符串
     * @return 最长子串的长度
     */
    public int lengthOfLongestSubstring(String s) {
        if (isEmpty(s)) {
            return 0;
        }

        int max = 0;
        int left = 0;
        int strLen = s.length();
        Map<Character, Integer> map = new HashMap<>();

        while (left <= strLen - 1) {
            int right = left;
            map.clear();
            while (right <= strLen - 1) {
                if (map.containsKey(s.charAt(right))) {
                    max = Math.max(max, map.size());
                    int idx = map.get(s.charAt(right));
                    left = idx + 1;
                    break;
                } else {
                    map.put(s.charAt(right), right);
                    if (right == strLen - 1) {
                        max = Math.max(max, map.size());
                        left = strLen;
                        break;
                    } else {
                        right++;
                    }
                }
            }
        }

        return max;
    }

    private boolean isEmpty(String s) {
        return s == null || s.equals("");
    }

    private boolean isTrimEmpty(String s) {
        return s == null || s.trim().equals("");
    }

    /**
     * 题5：最长回文子串
     * <p>
     * 思路：动态规划。
     *
     * @param s 字符串
     * @return 最长回文子串
     */
    public String longestPalindrome(String s) {
        if (null == s) {
            return null;
        }

        if ("".equals(s)) {
            return "";
        }

        int lo = 0, hi = 0;
        int strLen = s.length();
        boolean[][] arr = new boolean[strLen][strLen];

        //初始化数组
        for (int i = 0; i <= strLen - 1; i++) {
            arr[i][i] = true;
            if (i <= strLen - 2) {
                arr[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
                if (arr[i][i + 1]) {
                    lo = i;
                    hi = i + 1;
                }
            }
        }

        //填充arr
        for (int len = 3; len <= strLen; len++) {
            for (int i = 0; i <= strLen - len; i++) {
                arr[i][i + len - 1] = arr[i + 1][i + len - 2] && s.charAt(i) == s.charAt(i + len - 1);
                if (arr[i][i + len - 1]) {
                    lo = i;
                    hi = i + len - 1;
                }
            }
        }

        return s.substring(lo, hi + 1);
    }

    /**
     * 题8：字符串转整数
     *
     * @param str 字符串
     * @return 整数
     */
    public int myAtoi(String str) {
        if (isTrimEmpty(str)) {
            return 0;
        }

        boolean spaceArea = true;
        boolean firstNonSpaceChar = false;
        boolean positive = true;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= str.length() - 1; i++) {
            char ch = str.charAt(i);
            if (!spaceArea || !Character.isSpace(ch)) {
                spaceArea = false;
                if (!firstNonSpaceChar) {
                    firstNonSpaceChar = true;
                    if (ch != '+') {
                        if (ch == '-') {
                            positive = false;
                        } else if (Character.isDigit(ch)) {
                            sb.append(ch);
                        } else {
                            return 0;
                        }
                    }
                } else {
                    if (Character.isDigit(ch)) {
                        sb.append(ch);
                    } else {
                        break;
                    }
                }
            }
        }

        if (isTrimEmpty(sb.toString())) {
            return 0;
        }

        try {
            int val = Integer.parseInt(sb.toString());
            return positive ? val : -val;
        } catch (Exception e) {
            return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
    }

}
