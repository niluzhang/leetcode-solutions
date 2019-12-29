package com.huawei.nlz.leetcode.solution;//判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
//
// 示例 1:
//
// 输入: 121
//输出: true
//
//
// 示例 2:
//
// 输入: -121
//输出: false
//解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
//
//
// 示例 3:
//
// 输入: 10
//输出: false
//解释: 从右向左读, 为 01 。因此它不是一个回文数。
//
//
// 进阶:
//
// 你能不将整数转为字符串来解决这个问题吗？
// Related Topics 数学

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        String str = x + "";
        int size = str.length();

        if (size == 1) {
            return true;
        }

        for (int i = 0; i <= (size + 1) / 2; i++) {
            char ch0 = str.charAt(i);
            char ch1 = str.charAt(size - i - 1);
            if (ch0 != ch1) {
                return false;
            }
        }
        return true;
    }
}
