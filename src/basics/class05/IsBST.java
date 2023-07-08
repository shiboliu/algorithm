package class05;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IsBST {
    
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int preValue = Integer.MIN_VALUE;

    /**
     * 递归中序遍历判断是否是二叉搜索树
     * @param root
     */
    public static boolean checkBST(Node root) {
        if (root == null) {
            return true;
        }
        boolean isLeftBST = checkBST(root.left);
        if (!isLeftBST) {
            return false;
        }
        if (root.value <= preValue) {
            return false;
        } else {
            preValue = root.value;
        }
        return checkBST(root.right);
    }

    /**
     * 将中序遍历顺序存储到List中，然后在判断List是否递增
     * @param root
     */
    public static boolean checkBST1(Node root) {
        List<Node> inOrderList = new ArrayList<>();
        process(root, inOrderList);
        int preValue = Integer.MIN_VALUE;
        for (Node node : inOrderList) {
            if (node.value <= preValue) {
                return false;
            }
            preValue = node.value;
        }
        return true;
    }

    public static void process(Node root, List<Node> inOrderList) {
        if (root != null) {
            process(root.left, inOrderList);
            inOrderList.add(root);
            process(root.right, inOrderList);
        }
    }

    /**
     * 非递归中序遍历判读二叉搜索树
     * @param root
     */
    public static boolean checkBST2(Node root) {
        if (root != null) {
            int preValue = Integer.MIN_VALUE;
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    if (root.value <= preValue) {
                        return false;
                    } else {
                        preValue = root.value;
                    }
                    root = root.right;
                }
            }
        }
        return true;
    }

    public static class ReturnType {
        public boolean isBST;
        public int max;
        public int min;

        public ReturnType(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    /**
     * 树形DP动态规划判断搜索二叉树
     * @param root
     */
    public static ReturnType process(Node root) {
        if (root == null) {
            return null;
        }
        ReturnType leftData = process(root.left);
        ReturnType rightData = process(root.right);
        int max = root.value;
        int min = root.value;
        boolean isBST = true;
        if (leftData != null) {
            max = Math.max(max, leftData.max);
            min = Math.min(min, leftData.min);
        }
        if (rightData != null) {
            max = Math.max(max, rightData.max);
            min = Math.min(min, rightData.min);
        }
        if (leftData != null && (!leftData.isBST || leftData.max >= root.value)) {
            isBST = false;
        }
        if (rightData != null && (!rightData.isBST || root.value >= rightData.min)) {
            isBST = false;
        }


        isBST = false;
        if (
            (leftData == null ? true : (leftData.isBST && leftData.max < root.value)) 
            && 
            (rightData == null ? true : (leftData.isBST && root.value < rightData.min))) {
            isBST = true;
        }


        return new ReturnType(isBST, max, min);
    }

}
