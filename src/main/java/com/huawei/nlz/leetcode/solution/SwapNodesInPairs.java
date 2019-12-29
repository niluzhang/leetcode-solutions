package com.huawei.nlz.leetcode.solution;//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
//
//
//
// 示例:
//
// 给定 1->2->3->4, 你应该返回 2->1->4->3.
//
// Related Topics 链表

public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode ptr = head;
        ListNode newHead = (ptr == null || ptr.next == null) ? ptr : ptr.next;

        // 保存上个两两对的尾节点(即第二个节点)
        ListNode lastSegTail = null;

        while (ptr != null) {
            ListNode next = ptr.next;
            if (next == null) {
                break;
            }
            // 保存下下个节点的引用
            ListNode nextNext = next.next;

            next.next = ptr;
            ptr.next = nextNext;
            if(null != lastSegTail){
                lastSegTail.next = next;
            }

            // 刷新lastSegTail
            lastSegTail = ptr;
            // 从下下个节点开始继续交换过程
            ptr = nextNext;
        }

        return newHead;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
