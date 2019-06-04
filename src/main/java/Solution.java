import java.util.*;

public class Solution {

    public static void main(String[] args) {
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

    private boolean isTrimEmpty(String s) {
        return s == null || s.trim().equals("");
    }

    /**
     * 题5：最长回文子串
     * <p>
     * 思路：动态规划。
     *
     * @param s 字符串
     * @return 最长回文子串
     */
    public String longestPalindrome(String s) {
        if (null == s) {
            return null;
        }

        if ("".equals(s)) {
            return "";
        }

        int lo = 0, hi = 0;
        int strLen = s.length();
        boolean[][] arr = new boolean[strLen][strLen];

        //初始化数组
        for (int i = 0; i <= strLen - 1; i++) {
            arr[i][i] = true;
            if (i <= strLen - 2) {
                arr[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
                if (arr[i][i + 1]) {
                    lo = i;
                    hi = i + 1;
                }
            }
        }

        //填充arr
        for (int len = 3; len <= strLen; len++) {
            for (int i = 0; i <= strLen - len; i++) {
                arr[i][i + len - 1] = arr[i + 1][i + len - 2] && s.charAt(i) == s.charAt(i + len - 1);
                if (arr[i][i + len - 1]) {
                    lo = i;
                    hi = i + len - 1;
                }
            }
        }

        return s.substring(lo, hi + 1);
    }

    /**
     * 题8：字符串转整数
     *
     * @param str 字符串
     * @return 整数
     */
    public int myAtoi(String str) {
        if (isTrimEmpty(str)) {
            return 0;
        }

        boolean spaceArea = true;
        boolean firstNonSpaceChar = false;
        boolean positive = true;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= str.length() - 1; i++) {
            char ch = str.charAt(i);
            if (!spaceArea || !Character.isSpace(ch)) {
                spaceArea = false;
                if (!firstNonSpaceChar) {
                    firstNonSpaceChar = true;
                    if (ch != '+') {
                        if (ch == '-') {
                            positive = false;
                        } else if (Character.isDigit(ch)) {
                            sb.append(ch);
                        } else {
                            return 0;
                        }
                    }
                } else {
                    if (Character.isDigit(ch)) {
                        sb.append(ch);
                    } else {
                        break;
                    }
                }
            }
        }

        if (isTrimEmpty(sb.toString())) {
            return 0;
        }

        try {
            int val = Integer.parseInt(sb.toString());
            return positive ? val : -val;
        } catch (Exception e) {
            return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
    }

    /**
     * 题15：三数之和
     * 对于每个数字n，判断其他数字里存不存在两数之和等于-n。
     *
     * @param nums 数组
     * @return 和为0的所有三元组
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }

        // 去除出现次数超出3次的超出部分的数字
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= nums.length - 1; i++) {
            Integer times = map.get(nums[i]);
            if (null == times) {
                map.put(nums[i], 1);
            } else {
                times++;
                if (times > 3) times = 3;
                map.put(nums[i], times);
            }
        }

        // 保存结果
        List<List<Integer>> resultList = new ArrayList<>();

        // 保存去除多余数字后的剩余数字
        List<Integer> linkedList = new LinkedList<>();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer number = entry.getKey();
            Integer times = entry.getValue();
            for (int i = 0; i <= times - 1; i++) {
                linkedList.add(number);
            }
        }

        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Integer a = iterator.next();
            iterator.remove();

            // 假设存在和为0的三元组包含该整数n，从剩余的整数中找和为-n的另外两个整数。
            Set<Integer> tmpSet = new HashSet<>();

            Iterator<Integer> tmpIterator = linkedList.iterator();
            while (tmpIterator.hasNext()) {
                Integer b = tmpIterator.next();
                int c = -a - b;
                if (tmpSet.contains(c)) {
                    List<Integer> result = new ArrayList<>(3);
                    result.add(a);
                    result.add(b);
                    result.add(c);
                    result.sort((o1, o2) -> {
                        if (o1.equals(o2)) return 0;
                        return o1 - o2;
                    });
                    resultList.add(result);
                }
                tmpSet.add(b);
            }
        }

        // 对结果List去重
        Set<List<Integer>> filterSet = new LinkedHashSet<>(resultList);

        return new ArrayList<>(filterSet);
    }

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
