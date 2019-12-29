package com.huawei.nlz.leetcode.solution;//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
//
// 示例 1：
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
//
//
// 示例 2：
//
// 输入: "cbbd"
//输出: "bb"
//
// Related Topics 字符串 动态规划

public class LongestPalindromeSubstring {
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
}
