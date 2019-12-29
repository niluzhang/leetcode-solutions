package com.huawei.nlz.leetcode.solution;//实现 int sqrt(int x) 函数。
//
// 计算并返回 x 的平方根，其中 x 是非负整数。
//
// 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
//
// 示例 1:
//
// 输入: 4
//输出: 2
//
//
// 示例 2:
//
// 输入: 8
//输出: 2
//说明: 8 的平方根是 2.82842...,
//     由于返回类型是整数，小数部分将被舍去。
//
// Related Topics 数学 二分查找

public class SqrtX {
    public int mySqrt(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("input number cannot be negative");
        }
        // 由于x为非负整数时根号x<=x，所以在[0,x]间查找值v，使得v*v==x或者v*v<x&&(v+1)(v+1)>x
        long lo = 0;
        long hi = x;
        while (lo <= hi) {
            long mid = (lo + hi) / 2;
            if (mid * mid == x) {
                return (int) mid;
            } else if ((mid + 1) * (mid + 1) == x) {
                return (int) (mid + 1);
            } else if ((mid - 1) * (mid - 1) == x) {
                return (int) (mid - 1);
            } else if (mid * mid < x && (mid + 1) * (mid + 1) > x) {
                return (int) mid;
            } else if (mid * mid > x && (mid - 1) * (mid - 1) < x) {
                return (int) (mid - 1);
            } else if (mid * mid < x && (mid + 1) * (mid + 1) < x) {
                lo = mid + 1;
            } else if (mid * mid > x && (mid - 1) * (mid - 1) > x) {
                hi = mid - 1;
            }
        }
        return 0;
    }
}
