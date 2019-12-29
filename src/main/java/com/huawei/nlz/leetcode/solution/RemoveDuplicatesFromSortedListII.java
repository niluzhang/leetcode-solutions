package com.huawei.nlz.leetcode.solution;//给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
//
// 示例 1:
//
// 输入: 1->2->3->3->4->4->5
//输出: 1->2->5
//
//
// 示例 2:
//
// 输入: 1->1->1->2->3
//输出: 2->3
// Related Topics 链表

public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode ptr = head;
        ListNode lastTail = null;
        ListNode newHead = null;
        while (ptr != null) {
            ListNode lastSame = findLastNodeWithSameValue(ptr);
            if (ptr == lastSame) {
                // 说明没有重复
                if (newHead == null) {
                    newHead = ptr;
                }
                if (lastTail == null) {
                    lastTail = ptr;
                } else {
                    lastTail.next = ptr;
                    lastTail = ptr;
                }
                ptr = ptr.next;
            } else {
                // 说明从ptr到lastSame这一段值是重复的
                if (lastTail != null) {
                    lastTail.next = lastSame.next;
                }
                ptr = lastSame.next;
            }
        }

        return newHead;
    }

    private ListNode findLastNodeWithSameValue(ListNode node) {
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
