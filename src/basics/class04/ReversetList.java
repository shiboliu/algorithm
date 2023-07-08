package basics.class04;

/**
 * 反转单向和双向链表
 * 链表长度为N，时间复杂度O(N)，额外空间复杂度O(1)
 */
public class ReversetList {
    
    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
        
    }

    public static class Node1 {
        int value;
        Node next;
        Node last;
        
        public Node1(int value) {
            this.value = value;
        }
        
    }

    /**
     * 反转单链表
     * O(N)，O(1)
     * @param head 无头节点
     */
    public static Node reverseList(Node head) {
        Node p = null;
        Node q = null;
        while (head != null) {
            p = head.next;
            head.next = q;
            q = head;
            head = p;
        }
        return q;
    }

    /**
     * 递归反转单链表
     * O(N)，O(N)
     * @param head 无头结点
     */
    public static Node reverseList1(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 反转双链表
     * @param head 无头节点
     */
    public static Node1 reverseList(Node1 head) {
        Node1 p = null;
        Node1 q = null;
        while (head != null) {
            p = head.next;
            head.last = p;
            head.next = q;
            q = head;
            head = p;
        }
        return q;
    }
}
