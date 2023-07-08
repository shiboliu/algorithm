package class05;

import java.util.LinkedList;

/**
 * 完全二叉树
 */
public class IsCBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 使用层次遍历判断完全二叉树
     * 1）有右子树没有左子树==》false
     * 2）1）成立的情况下，如果遇到第一个左右孩子不双全的情况，那么接下来遇到的所有节点皆为叶子节点。
     * @param head
     */
    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            // (head应为叶子节点 && head不是叶子节点) || (head有右无左)
            if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

}
