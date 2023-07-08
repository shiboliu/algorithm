package class05;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 树的层次遍历
 * 求一个树的宽度
 */
public class TreeMaxWidth {
    
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 求树的最大宽度
     * @param head
     */
    public static int getMaxWidth(Node root) {
        if (root == null)
            return 0;
        Queue<Node> q = new LinkedList<>();
        HashMap<Node, Integer> map = new HashMap<>(); // <节点, 节点所处层>
        int maxNodes = Integer.MIN_VALUE; // 最大宽度
        int curLevel = 1; // 当前层
        int curLevelNodes = 0; // 当前层的节点数
        int curNodeLevel; // 当前出队列的节点所在层
        map.put(root, 1);
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            curNodeLevel = map.get(cur);
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                maxNodes = Math.max(maxNodes, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
            if (cur.left != null) {
                map.put(cur.left, curNodeLevel + 1);
                q.add(cur.left);
            }
            if (cur.right != null) {
                map.put(cur.right, curNodeLevel + 1);
                q.add(cur.right);
            }
        }
        return Math.max(maxNodes, curLevelNodes);
    }
    

    public static int getMaxWidth1(Node root) {
        if (root == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        int curLevelNodes = 0;
        Node curNode = null;
        Node curEnd = null;
        Node nextEnd = null;
        curEnd = root;
        queue.add(root);
        while (!queue.isEmpty()) {
            curNode = queue.poll();
            curLevelNodes++;
            if (curNode == curEnd) {
                curEnd = nextEnd;
                nextEnd = null;
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
            } else {
                
            }
            if (curNode.left != null) {
                queue.add(curNode.left);
                nextEnd = curNode.left;
            }
            if (curNode.right != null) {
                queue.add(curNode.right);
                nextEnd = curNode.left;
            }
        }
        return max;
    }
    
}
