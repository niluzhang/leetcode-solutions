package com.huawei.nlz.leetcode.solution;//给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
//
// 返回被除数 dividend 除以除数 divisor 得到的商。
//
// 示例 1:
//
// 输入: dividend = 10, divisor = 3
//输出: 3
//
// 示例 2:
//
// 输入: dividend = 7, divisor = -3
//输出: -2
//
// 说明:
//
//
// 被除数和除数均为 32 位有符号整数。
// 除数不为 0。
// 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
//
// Related Topics 数学 二分查找

public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("invalid input");
        }

        if (dividend == 0) {
            return 0;
        }

        if(dividend == Integer.MIN_VALUE && divisor == -1){
            return Integer.MAX_VALUE;
        }

        int dividendPositiveFlag = dividend > 0 ? 1 : 0;
        int divisorPositiveFlag = divisor > 0 ? 1 : 0;
        int sameFlag = dividendPositiveFlag ^ divisorPositiveFlag;
        boolean resultPositive = sameFlag != 1;

        long dividendL = dividend;
        long divisorL = divisor;

        dividendL = Math.abs(dividendL);
        divisorL = Math.abs(divisorL);

        if (divisorL == 1) {
            return (int)(resultPositive ? dividendL : -dividendL);
        }

        if (divisorL == dividendL) {
            return resultPositive ? 1 : -1;
        }

        int divResult = (int)divResult(dividendL, divisorL);

        return resultPositive ? divResult : -divResult;
    }

    private long divResult(long dividend, long divisor) {
        // 在[1, dividend]之间查找倍数
        long lo = 1;
        long hi = dividend;
        while (lo <= hi) {
            long tmp = (lo + hi) / 2;
            if (tmp * divisor == dividend) {
                return tmp;
            } else if ((tmp + 1) * divisor == dividend) {
                return tmp + 1;
            } else if ((tmp - 1) * divisor == dividend) {
                return tmp - 1;
            } else if (tmp * divisor > dividend && (tmp - 1) * divisor < dividend) {
                return tmp - 1;
            } else if (tmp * divisor < dividend && (tmp + 1) * divisor > dividend) {
                return tmp;
            } else if (tmp * divisor > dividend && (tmp - 1) * divisor > dividend) {
                hi = tmp - 1;
            } else if (tmp * divisor < dividend && (tmp + 1) * divisor < dividend) {
                lo = tmp + 1;
            }
        }

        return 1;
    }
}
