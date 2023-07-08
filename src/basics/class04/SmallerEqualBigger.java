package basics.class04;

public class SmallerEqualBigger {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }
    
    /**
     * 将单向链表按某值划分成左边小、中间相等、右边大的形式
     * @param head
     * @param pivot
     */
    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        int i = 0; // 记录有多少个节点
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        cur = head;
        for (i = 0; i < nodeArr.length; i++) { // 将链表中的Node存储到一个Node数组nodeArr中
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr, pivot);
        for (i = 1; i < nodeArr.length; i++) { // 将数组中单独的Node 穿成链表
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    /**
     * 类似于merge排序中的partition
     */
    public static void arrPartition(Node[] nodeArr, int pivot) {
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index < big) {
            if (nodeArr[index].value < pivot) {
                swap(nodeArr, ++small, index++);
            } else if (nodeArr[index].value == pivot) {
                index++;
            } else {
                swap(nodeArr, --big, index);
            }
        }
    }

    public static void swap(Node[] nodeArr, int i, int j) {
        Node tmp = nodeArr[i];
        nodeArr[i] = nodeArr[j];
        nodeArr[j] = tmp;
    }

    /**
     * O(N)，O(1)，稳定
     * @param head
     * @param pivot
     * @return
     */
    public static Node listPartition2(Node head, int pivot) {
        Node sH = null, sT = null; // <区头，<区尾
        Node eH = null, eT = null; // =区头，=区尾
        Node bH = null, bT = null; // >区头，>区尾
        Node next = null;
        // 每一个node被分配到三个区
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }
        // 链接<区和=区
        if (sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT; // 下一步，谁去连>区的头，谁就变成eT
        }
        // 链接>区
        if (eT != null) { // 如果<区和=区至少有一个
            eT.next = bH;
        }
        return sH != null ? sH : (eH != null ? eH : bH);
    }
}
