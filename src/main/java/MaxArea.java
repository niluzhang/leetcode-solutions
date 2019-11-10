public class MaxArea {
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
