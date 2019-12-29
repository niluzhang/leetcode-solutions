package com.huawei.nlz.leetcode.solution;//给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
//
// 你应当保留两个分区中每个节点的初始相对位置。
//
// 示例:
//
// 输入: head = 1->4->3->2->5->2, x = 3
//输出: 1->2->2->4->3->5
//
// Related Topics 链表 双指针

public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode smallerHead = null;  // 存放比x小的节点组成的链表的头部
        ListNode smallerTail = null;  // 存放<x的节点组成的链表的尾部
        ListNode biggerHead = null;   // 存放>=x的节点组成的链表的头部
        ListNode biggerTail = null;   // 存放>=x的节点组成的链表的尾部

        ListNode ptr = head;
        while (ptr != null) {
            int tmp = ptr.val;
            if (tmp < x) {
                if (smallerHead == null) {
                    smallerHead = ptr;
                }
                if (smallerTail == null) {
                    smallerTail = ptr;
                } else {
                    smallerTail.next = ptr;
                    smallerTail = ptr;
                }
            } else {
                if (biggerHead == null) {
                    biggerHead = ptr;
                }
                if (biggerTail == null) {
                    biggerTail = ptr;
                } else {
                    biggerTail.next = ptr;
                    biggerTail = ptr;
                }
            }

            ptr = ptr.next;
        }

        if (biggerTail != null) {
            biggerTail.next = null;
        }

        if (smallerTail != null) {
            smallerTail.next = biggerHead;
        }

        return smallerHead != null ? smallerHead : biggerHead;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
