package class05;

/**
 * 平衡二叉树
 */
public class IsBalanced {
    
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 需要知道的左、右子树信息(树的高度、是否平衡)用下面的类存储
     */
    public static class ReturnType {
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    /**
     * 递归后序遍历判断是否是二叉平衡树
     * 左、右子树是二叉平衡树 && 左子树和右子树的高度差 <= 1    ==> true
     * @param head
     */
    public static boolean isBalanced(Node head) {
        return process(head).isBalanced;
    }

    public static ReturnType process(Node x) {
        if (x == null) {
            return new ReturnType(true, 0);
        }
        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced && Math.abs(leftData.height - rightData.height) < 2;
        return new ReturnType(isBalanced, height);
    }
}
