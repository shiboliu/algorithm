package class05;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 */
public class SerializeAndReconstruct {
    
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 以root为头的树，请序列化诚字符串返回
     * @param root
     * @return
     */
    public static String serialByPre(Node root) {
        if (root == null) {
            return "#_";
        }
        String res = root.value + " ";
        res += serialByPre(root.left);
        res += serialByPre(root.right);
        return res;
    }

    private static Node reconByPreString(String preStr) {
        String[] values = preStr.split("_");
        Queue<String> queue = new LinkedList<String>();
        for (int i = 0; i < values.length; i++) {
            queue.add(values[i]);
        }
        return reconPreOrder(queue);
    }

    public static Node reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        Node root = new Node(Integer.valueOf(value));
        root.left = reconPreOrder(queue);
        root.right = reconPreOrder(queue);
        return root;
    }
}
