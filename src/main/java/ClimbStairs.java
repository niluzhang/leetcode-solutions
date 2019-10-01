public class ClimbStairs {
    /**
     * 题70：爬楼梯
     * f(n) = f(n - 1) + f(n - 2)
     *
     * @param n 台阶总数
     * @return 不同的走法总数
     */
    public int climbStairs(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }
}
