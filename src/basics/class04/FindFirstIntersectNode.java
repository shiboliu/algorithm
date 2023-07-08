package basics.class04;

/**
 * 两个单链表相交的一系列问题
 */
public class FindFirstIntersectNode {
    
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 给定两个可能有环也可能无环的单链表，头节点head1和head2。
     * 如果两个链表相交，返回相交的第一个节点。如果不想交，返回null。
     * O(N)，O(1)
     * @param head1
     * @param head2
     * @return
     */
    public static Node[] getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        // 下面两个if是可能相交的两种情况
        if (loop1 == null && loop2 == null) { // 两个无环的可能相交
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) { // 两个有环的可能相交，环公用，入环节点可同可不同
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    /**
     * 找到链表第一个环节点，如果无环，返回null
     * @param head
     */
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node n1 = head.next; // n1 -> slow
        Node n2 = head.next.next; // n2 -> fast
        while (n1 != n2) {
            if (n2.next == null || n2.next.next == null) {
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }
        n2 = head; // n2 -> walk again from head
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    /**
     * 如果两个链表都无环，返回第一个相交节点，如果不想交，返回null
     * @param head1
     * @param head2
     */
    public static Node[] noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return new Node[]{cur1};
    }

    /**
     * 两个有环链表，返回第一个相交节点，如果不相交返回null
     * @param head1
     * @param loop1
     * @param head2
     * @param loop2
     * @return
     */
    public static Node[] bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) { // 两个链表共用一个入环节点
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return new Node[]{cur1};
        } else { // 两个链表相交，但不是相同的入环节点
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return new Node[]{loop1, loop2};
                }
                cur1 = cur1.next;
            }
        }
        return null; // 不相交
    }
}