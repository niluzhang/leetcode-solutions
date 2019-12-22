//给定一个非负整数数组，你最初位于数组的第一个位置。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。
//
// 判断你是否能够到达最后一个位置。
//
// 示例 1:
//
// 输入: [2,3,1,1,4]
//输出: true
//解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
//
//
// 示例 2:
//
// 输入: [3,2,1,0,4]
//输出: false
//解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
//
// Related Topics 贪心算法 数组

public class JumpGame {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("input nums cannot be empty.");
        }
        /*
         * 思路：其实就是算从第一个点开始可以跳到的最大距离，若最大距离>=nums.length-1，则说明可以到达最末位置。
         * 因此采用动态规划思路：
         * 记f(n)为在n位置可以走的最大距离，则有：
         * f(n)=max(1+f(n+1), 2+f(n+2), ... nums[n]+f(n+nums[n]))
         */
        int len = nums.length;
        int[] maxDistances = new int[len];
        maxDistances[len - 1] = nums[len - 1];

        for (int i = nums.length - 2; i >= 0; i--) {
            int maxJumps = nums[i];
            for (int j = 1; j <= maxJumps && (i + j <= nums.length - 1); j++) {
                int curMax = j + maxDistances[i + j];
                if (curMax > maxJumps) {
                    maxJumps = curMax;
                }
            }
            maxDistances[i] = maxJumps;
        }

        return maxDistances[0] >= nums.length - 1;
    }
}
