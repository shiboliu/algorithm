package basics.class04;

import java.util.Stack;

/**
 * 面试时链表解题的方法论
 * 1）笔试，不用太在乎空间复杂度，一切为了时间复杂度
 * 2）面试，时间复杂度依然放在第一位，但是一定要找到空间最省的方法
 * 重要技巧：
 * 1）额外数据结构记录（哈希表等）
 * 2）快慢指针
 */
public class Ispalindrome {
    

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 给定一个单链表的头节点head，判断该链表是否为回文结构
     * 需要n个额外空间
     * 先使用一个栈将所有的节点存入
     * 节点依次出栈，链表从头遍历，相互比较，不等false
     * @param head 无头节点
     */
    public static boolean ispalindrome1(Node head) {
        Stack<Node> stack = new Stack<Node>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }


    /**
     * 需要n / 2个额外空间，用来存储后半个链表
     * 采用(快慢)双指针找中间分割节点，从此节点(包括此节点)开始入栈
     * 若奇数个节点：right -> 正中间的节点的下一个节点; cur -> 倒数第一个节点
     * 若偶数个节点：right -> 正好后一半的第一个节点; cur -> 倒数第二个节点
     * @param head 无头节点
     */
    public static boolean ispalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node right = head.next;
        Node cur = head;
        while (cur.next != null && cur.next.next != null) {
            right = right.next;
            cur = cur.next.next;
        }
        Stack<Node> stack = new Stack<Node>();
        while (right != null) {
            stack.push(right);
            right = right.next;
        }
        while (!stack.isEmpty()) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 需要O(1)额外空间
     * find mid node 
     * 若奇数个节点：n1 -> 正中间节点; n2 -> 最后一个节点
     * 若偶数个节点：n1 -> 第一办的最后一个节点; n2 -> 倒数第二个节点
     * @param head 无头节点
     */
    public static boolean ispalindrome3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null) { // find mid node
            n1 = n1.next; // n1 -> mid
            n2 = n2.next.next;
        }
        n2 = n1.next; // n2 -> right part first node
        n1.next = null; // mid.next = null
        Node n3 = null;
        // right part convert
        // -> -> -> ->      ==>     -> -> <- <-
        // -> -> -> -> ->       ==>     -> -> ->(null) <- <-
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = n1; // n3 -> 最后一个节点 right first node
        n2 = head; // n2 -> left first node
        boolean res = true; // 使用这个是为了恢复链表为原来的样子
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        n1 = n3.next; // 倒数第二个节点
        n3.next = null; // 将最后一个节点的next指向null
        while (n1 != null) { // 回复链表，运行到mid(mid.next = null)那里
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }
}
