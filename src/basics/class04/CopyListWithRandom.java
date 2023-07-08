package basics.class04;

import java.util.HashMap;

/**
 * 复制含有随即指针节点的链表
 * rand指针可能指向链表中的任意一个节点，也可能指向null。
 */
public class CopyListWithRandom {
    
        public static class Node {
        public int value;
        public Node rand;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 复制含有随即指针节点的链表
     * 使用hashmap<老Node, 新Node>记录新老Node的关系
     * 时间复杂度O(N)，额外空间复杂度O(N)
     * @param head 无头节点
     */
    public static Node copyListWithRand1(Node head) {
        HashMap<Node, Node> map = new HashMap<Node, Node>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            // cur老; map.get(cur)新
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * 利用克隆节点位置1 和1'的位置关系省略了hashmap
     * 时间复杂度O(N)，额外空间复杂度O(1)
     * @param head
     */
    public static Node copyListWithRand2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        // 1 -> 2       ==>     1 -> 1' -> 2 -> 2'
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        // 设置新Node 的rand
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        Node res = head;
        cur = head;
        // 分开新老Node
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }
}
