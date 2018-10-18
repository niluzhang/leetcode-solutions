import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("abba"));
    }

    /**
     * 题3：无重复的最长子串
     * <p>
     * 思路：滑动窗口[left, right], 初始时left = right = 0，控制right向右滑动，当遇到字符和窗口
     * 内i位置的字符重复时，更新窗口left为i+1，right重新从left处开始移动。
     *
     * @param s 字符串
     * @return 最长子串的长度
     */
    public int lengthOfLongestSubstring(String s) {
        if (isEmpty(s)) {
            return 0;
        }

        int max = 0;
        int left = 0;
        int strLen = s.length();
        Map<Character, Integer> map = new HashMap<>();

        while (left <= strLen - 1) {
            int right = left;
            map.clear();
            while (right <= strLen - 1) {
                if (map.containsKey(s.charAt(right))) {
                    max = Math.max(max, map.size());
                    int idx = map.get(s.charAt(right));
                    left = idx + 1;
                    break;
                } else {
                    map.put(s.charAt(right), right);
                    if (right == strLen - 1) {
                        max = Math.max(max, map.size());
                        left = strLen;
                        break;
                    } else {
                        right++;
                    }
                }
            }
        }

        return max;
    }

    private boolean isEmpty(String s) {
        return s == null || s.equals("");
    }

}
