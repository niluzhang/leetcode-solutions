public class RemoveElement {
    /**
     * 题27：移除相同元素
     *
     * @param nums 数组
     * @param val  值
     * @return 剩余数组的大小
     */
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}
