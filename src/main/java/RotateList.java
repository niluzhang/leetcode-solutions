//给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
//
// 示例 1:
//
// 输入: 1->2->3->4->5->NULL, k = 2
//输出: 4->5->1->2->3->NULL
//解释:
//向右旋转 1 步: 5->1->2->3->4->NULL
//向右旋转 2 步: 4->5->1->2->3->NULL
//
//
// 示例 2:
//
// 输入: 0->1->2->NULL, k = 4
//输出: 2->0->1->NULL
//解释:
//向右旋转 1 步: 2->0->1->NULL
//向右旋转 2 步: 1->2->0->NULL
//向右旋转 3 步: 0->1->2->NULL
//向右旋转 4 步: 2->0->1->NULL
// Related Topics 链表 双指针

public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (null == head) {
            return null;
        }
        if (k < 0) {
            throw new IllegalArgumentException("illegal input!");
        }

        int listLen = listLength(head);
        // 如果链表长度为l，那么移动n(0<=n<l)次和移动n+il(i为非负整数)效果相同。
        int validMov = k % listLen;

        if (validMov == 0) {
            // 无需移动，保持原样即可
            return head;
        }

        // 链表右移n次，其实就是把链表倒数第一个到倒数第n个元素整体转接到链表头部来。
        head = shift(head, validMov);
        return head;
    }

    private int listLength(ListNode head) {
        ListNode ptr = head;
        int len = 0;
        while (ptr != null) {
            len++;
            ptr = ptr.next;
        }
        return len;
    }

    private ListNode shift(ListNode head, int n) {
        // 保存原链表的倒一节点，倒n节点，倒n+1节点
        ListNode last = null;
        ListNode lastButN = null;
        ListNode lastButNP1 = null;

        ListNode slow = head;
        ListNode fast = head;

        // 寻找倒数第n+1节点

        for (int i = 1; i <= n; i++) {
            fast = fast.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        last = fast;
        lastButNP1 = slow;
        lastButN = slow.next;

        last.next = head;
        lastButNP1.next = null;
        return lastButN;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
