//给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。
//
// 示例 1:
//
// 输入: [1,2,3]
//输出: [1,2,4]
//解释: 输入数组表示数字 123。
//
//
// 示例 2:
//
// 输入: [4,3,2,1]
//输出: [4,3,2,2]
//解释: 输入数组表示数字 4321。
//
// Related Topics 数组

import java.util.Arrays;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        if (digits == null) {
            throw new IllegalArgumentException("input digits array cannot be null");
        }

        if (digits.length == 0) {
            throw new IllegalArgumentException("length of input digits cannot be zero");
        }

        // 良好的编程风格，这里操作复制的数组。
        int[] result = Arrays.copyOf(digits, digits.length);

        int len = result.length;
        int idx = len - 1;
        boolean firstLocCarry = false;
        while (idx >= 0) {
            result[idx]++;
            if (result[idx] < 10) {
                return result;
            }
            result[idx--] = 0;
            if (idx == -1) {
                firstLocCarry = true;
            }
        }

        if (firstLocCarry) {
            result = new int[digits.length + 1];
            result[0] = 1;
            Arrays.fill(result, 1, result.length - 1, 0);
        }
        return result;
    }
}
