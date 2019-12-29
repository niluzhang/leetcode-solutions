package com.huawei.nlz.leetcode.solution;//给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
//
// 示例 1:
//
// 输入: 123
//输出: 321
//
//
// 示例 2:
//
// 输入: -123
//输出: -321
//
//
// 示例 3:
//
// 输入: 120
//输出: 21
//
//
// 注意:
//
// 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
// Related Topics 数学

public class ReverseInteger {
    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        boolean negative = x < 0;
        int absX = Math.abs(x);
        StringBuilder sb = new StringBuilder();
        int tmp = absX;
        while (tmp > 0) {
            int mod = tmp % 10;
            tmp /= 10;
            sb.append(mod);
        }
        if (negative) {
            sb.insert(0, "-");
        }

        int result;
        try {
            result = Integer.parseInt(sb.toString());
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }
}
