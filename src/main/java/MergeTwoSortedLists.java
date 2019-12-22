//将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
//
// 示例：
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
//
// Related Topics 链表

public class MergeTwoSortedLists {
    /**
     * 题21：合并两个有序链表
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 最终的有序链表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode ptr1 = l1;
        ListNode ptr2 = l2;

        ListNode head = null;
        ListNode tail = null;

        /*
         * 两个指针分别指向两个链表的头部，然后从头部开始比较，将较小值的节点链入结果链表，同时指针移向下一个，直至一方为null。
         */
        while (ptr1 != null && ptr2 != null) {
            int val1 = ptr1.val;
            int val2 = ptr2.val;
            if (val1 <= val2) {
                if (head == null) {
                    tail = head = ptr1;
                } else {
                    tail.next = ptr1;
                    tail = ptr1;
                }
                ptr1 = ptr1.next;
            } else {
                if (head == null) {
                    tail = head = ptr2;
                } else {
                    tail.next = ptr2;
                    tail = ptr2;
                }
                ptr2 = ptr2.next;
            }
        }

        /*
         * 链入剩余的值。
         */
        if (ptr1 != null) {
            tail.next = ptr1;
        }
        if (ptr2 != null) {
            tail.next = ptr2;
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
