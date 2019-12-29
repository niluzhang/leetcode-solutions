package com.huawei.nlz.leetcode.solution;//给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
//
// 说明：你不能倾斜容器，且 n 的值至少为 2。
//
//
//
// 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
//
//
//
// 示例:
//
// 输入: [1,8,6,2,5,4,8,3,7]
//输出: 49
// Related Topics 数组 双指针

public class ContainerWithMostWater {
    /**
     * 题11：盛水最多的容器。
     * 法1，暴力枚举，时间复杂度O(N^2)。
     *
     * @param height
     * @return
     */
    public int maxAreaBruteForce(int[] height) {
        preconditionCheck(height);

        // 找最大的(j - i) * min(ai, aj)
        int max = 0;
        for (int i = 0; i <= height.length - 2; i++) {
            for (int j = i + 1; j <= height.length - 1; j++) {
                int val = (j - i) * Math.min(height[i], height[j]);
                max = Math.max(max, val);
            }
        }

        return max;
    }

    /**
     * 法2，双指针法，从两端往中间收缩。时间复杂度O(N)。
     *
     * @param height
     * @return
     */
    public int maxAreaDoublePtr(int[] height) {
        preconditionCheck(height);

        int max = 0, l = 0, r = height.length - 1;
        while (l < r) {
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return max;
    }

    private void preconditionCheck(int[] height) {
        if (null == height) {
            throw new IllegalArgumentException("height is null.");
        }

        if (height.length == 0 || height.length == 1) {
            throw new IllegalArgumentException("height must contain at least 2 values.");
        }
    }
}
