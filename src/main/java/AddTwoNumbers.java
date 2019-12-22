//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
// 示例：
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
//
// Related Topics 链表 数学

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? copyOfList(l2) : copyOfList(l1);
        }

        ListNode rst = null;

        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode p3 = null;
        int carrier = 0;

        while (p1 != null || p2 != null || carrier != 0) {
            if (p1 == null && p2 != null) {
                while (p2 != null) {
                    ListNode node = new ListNode((p2.val + carrier) % 10);
                    carrier = (p2.val + carrier) / 10;
                    p2 = p2.next;
                    p3.next = node;
                    p3 = p3.next;
                }
            } else if (p2 == null && p1 != null) {
                ListNode node = new ListNode((p1.val + carrier) % 10);
                carrier = (p1.val + carrier) / 10;
                p1 = p1.next;
                p3.next = node;
                p3 = p3.next;
            } else if (p1 == null && p2 == null) {
                ListNode node = new ListNode(1);
                p3.next = node;
                carrier = 0;
            } else {
                ListNode node = new ListNode((p1.val + p2.val + carrier) % 10);
                carrier = (p1.val + p2.val + carrier) / 10;
                if (p3 == null) {
                    p3 = node;
                    rst = p3;
                } else {
                    p3.next = node;
                    p3 = p3.next;
                }

                p1 = p1.next;
                p2 = p2.next;
            }
        }

        return rst;
    }

    private ListNode copyOfList(ListNode first) {
        if (first == null)
            return null;

        ListNode ptr = first;
        ListNode rst = null;

        while (ptr != null) {
            ListNode node = new ListNode(ptr.val);
            ptr = ptr.next;
            if (rst == null)
                rst = node;
            else
                rst.next = node;
        }

        return rst;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
