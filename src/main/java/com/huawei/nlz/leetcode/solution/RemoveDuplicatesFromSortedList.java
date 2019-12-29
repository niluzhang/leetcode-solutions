package com.huawei.nlz.leetcode.solution;//给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
//
// 示例 1:
//
// 输入: 1->1->2
//输出: 1->2
//
//
// 示例 2:
//
// 输入: 1->1->2->3->3
//输出: 1->2->3
// Related Topics 链表

public class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode ptr = head;
        ListNode lastTail = null;
        while (ptr != null) {
            ListNode lastSame = findLastNodeWithSameValue(ptr);
            if (lastSame == ptr) {
                // 不重复
                if (lastTail == null) {
                    lastTail = ptr;
                } else {
                    lastTail.next = ptr;
                    lastTail = ptr;
                }
                ptr = ptr.next;
            } else {
                if (lastTail == null) {
                    lastTail = ptr;
                } else {
                    lastTail.next = ptr;
                    lastTail = ptr;
                }
                ptr = lastSame.next;
            }
        }

        if (lastTail != null) lastTail.next = null;

        return head;
    }

    public ListNode findLastNodeWithSameValue(ListNode node) {
        int val = node.val;
        ListNode ptr = node.next;
        ListNode prev = node;
        while (ptr != null) {
            int tmp = ptr.val;
            if (val == tmp) {
                prev = ptr;
                ptr = ptr.next;
            } else {
                break;
            }
        }
        return prev;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
