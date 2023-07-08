package class12;

/**
 * Morris遍历
 * 一种遍历二叉树的方式，并且时间复杂度O(N)。额外空间复杂度O(1)
 * 通过利用原树中大量空闲指针的方式，达到节省空间的目的
 * 
 * 
 * Morris遍历细节（morris遍历叶子节点有孩子指向中序遍历的后一个节点）
 * 假设来到当前节点cur，开始时cur来到头节点位置
 * 1)如果cur没有左孩子，cur向右移动(cur = cur.right)
 * 2)如果cur有左孩子，找到左子树上最右的节点mostRight:
 * a.如果mostRight的右指针指向空，让其指向cur,然后cur向左移动(cur = cur.left)
 * b.如果mostRight的右指针指向cur，让其指向null然后cur向右移动(cur = cur.right)
 * 3) cur为空时遍历停止
 * 
 * 
 * 先序、中序可以由morris遍历加工得到
 * 后序遍历也可由morris遍历加工得到，但是把处理时机放在，能够达到两次的节点并且是第二次到达的时候
 * 先序：
 * 在第一次到达的时候打印
 */
public class MorrisTraversal {

    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }

    }

    /**
     * morris遍历
     * 
     * @param head
     */
    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) { // morris遍历过程
            mostRight = cur.left; // mostRight是cur的左孩子
            if (mostRight != null) { // 有左子树
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                } // mostRight变成cur左子树上，最右的节点
                if (mostRight.right == null) { // 第一次来到cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // mostRight.right == cur
                    mostRight.right = null;
                }
            }
            cur = cur.right; // 如果cur没有左孩子，cur向右移动(cur = cur.right)
        }
    }

    /**
     * morris先序遍历
     * 只经过一次的节点直接打印
     * 经过两次的节点第一次打印
     * 
     * @param head
     */
    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left; // mostRight是cur左孩子
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    System.out.println(cur.val);
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                System.out.println(cur.val);
            }
            cur = cur.right;
        }
    }

    /**
     * morris中序遍历
     * 只经过一次的节点直接打印
     * 经过两次的节点第二次打印
     * 
     * @param head
     */
    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) { // morris遍历过程
            mostRight = cur.left; // mostRight是cur的左孩子
            if (mostRight != null) { // 有左子树
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                } // mostRight变成cur左子树上，最右的节点
                if (mostRight.right == null) { // 第一次来到cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // mostRight.right == cur
                    mostRight.right = null;
                }
            }
            System.out.println(cur.val);
            cur = cur.right; // 如果cur没有左孩子，cur向右移动(cur = cur.right)
        }
    }

    /**
     * morris后序遍历
     * 1.经过两次的节点在经过时，逆序打印它的左子树的右边界
     * 2.最后单独打印整棵树的右边界
     * 
     * @param head
     */
    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    /**
     * 以x为头的树，逆序打印这棵树的右边界
     * 
     * @param x
     */
    public static void printEdge(Node x) {
        Node tail = reverseEdge(x);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    /**
     * 反转
     * 
     * @param from
     * @return 头
     */
    public static Node reverseEdge(Node from) {
        Node pre = null;
        Node next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

    /**
     * morris判断搜索二叉树
     * 中序遍历是否递增
     * 
     * @param head
     * @return
     */
    public static boolean morrisIsBST(Node head) {
        if (head == null) {
            return true;
        }
        Node cur = head;
        Node mostRight = null;
        int preValue = Integer.MIN_VALUE;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight != null && mostRight != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            if (cur.val <= preValue) {
                return false;
            }
            preValue = cur.val;
            cur = cur.right;
        }
        return true;
    }
}
