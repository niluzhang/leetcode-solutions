package com.huawei.nlz.leetcode.solution;//给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
//
// 示例 1:
//
// 输入:
//[
// [ 1, 2, 3 ],
// [ 4, 5, 6 ],
// [ 7, 8, 9 ]
//]
//输出: [1,2,3,6,9,8,7,4,5]
//
//
// 示例 2:
//
// 输入:
//[
//  [1, 2, 3, 4],
//  [5, 6, 7, 8],
//  [9,10,11,12]
//]
//输出: [1,2,3,4,8,12,11,10,9,5,6,7]
//
// Related Topics 数组

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("invalid input");
        }
        int rows = matrix.length;
        if(rows <= 0){
            return Collections.emptyList();
        }
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];

        int direction = 1;   // 1: 向右，2: 向下，3: 向左，4: 向上

        int x = 0;
        int y = 0;

        List<Integer> result = new ArrayList<>();
        while (result.size() < rows * cols) {
            result.add(matrix[x][y]);
            visited[x][y] = true;
            if (direction == 1) {
                if (y + 1 <= cols - 1 && !visited[x][y + 1]) {
                    y++;
                } else {
                    direction = 2;
                    x++;
                }
            } else if (direction == 2) {
                if (x + 1 <= rows - 1 && !visited[x + 1][y]) {
                    x++;
                } else {
                    direction = 3;
                    y--;
                }
            } else if (direction == 3) {
                if (y - 1 >= 0 && !visited[x][y - 1]) {
                    y--;
                } else {
                    direction = 4;
                    x--;
                }
            } else {
                if (x - 1 >= 0 && !visited[x - 1][y]) {
                    x--;
                } else {
                    direction = 1;
                    y++;
                }
            }
        }

        return result;
    }
}
