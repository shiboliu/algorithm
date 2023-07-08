package class05;

/**
 * 完全二叉树
 */
public class IsFBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    
    public class ReturnType {
        int height;
        int nodes;
        
        public ReturnType(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static boolean isFBT(Node root) {
        if (root == null) {
            return true;
        }
        ReturnType data = process(root);
        return data.nodes == (1 << (data.height - 1));
    }

    /**
     * 如果解决树型问题可以向左、右子树要信息就可以解决问题（符合树型DP），可以通过这个方法解决
     * @param root
     * @return
     */
    public static ReturnType process(Node root) {
        if (root == null) {
            return new ReturnType(0, 0);
        }
        ReturnType leftData = process(root.left);
        ReturnType rightData = process(root.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        int nodes = leftData.nodes + rightData.nodes + 1;
        return new ReturnType(height, nodes);
    }
}
