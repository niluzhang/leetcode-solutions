public class IsPalindrome {
    /**
     * 题9：回文数
     *
     * @param x 输入的整数
     * @return true 是回文数; false 不是回文数
     */
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
