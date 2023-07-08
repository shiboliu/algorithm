package class12;

/**
 * 树形dp套路
 * 树形dp套路使用前提:如果题目求解目标是S规则，则求解流程可以定成以每一个节点为头节点的子树在S规则下的每一个答案，并且最终答案一定在其中
 * 
 * 树形dp套路第一步:以某个节点X为头节点的子树中，分析答案有哪些可能性，并且这种分析是以X的左子树、X的右子树和X整棵树的角度来考虑可能性的
 * 树形dp套路第二步:根据第一步的可能性分析，列出所有需要的信息
 * 树形dp套路第三步:合并第二步的信息，对左树和右树提出同样的要求，并写出信息结构
 * 树形dp套路第四步:设计递归函数，遂归函数是处理以X为头节点的情况下的答案。包括设计递归的basecase，默认直接得到左树和右树的所有信息，以及把可能性做整合，并且要返回第三步的信息结构这四个小步骤
 */

/**
 * 二叉树节点间的最大距离问题
 * 从二叉树的节点a出发，可以向上或者向下走，但沿途的节点只能经过一次，到达节点b时路径上的节点个数叫作a到b的距离，那么二叉树任何两个节点之间都有距离，求整棵树上的最大距商。
 */
public class MaxDistanceInTree {

    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }

    }

    public static int maxDistance(Node head) {
        return process(head).maxDistance;
    }

    public static class Info {
        public int maxDistance;
        public int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }

    }

    /**
     * 返回以x为头的整棵树，两个信息
     * 最大距离
     *  1.x不参与最大距离可能产生在左子树或右子树上
     *  2.x参与最大距离可能是左子树的高+右子树的高+1
     * @param x
     * @return
     */
    public static Info process(Node x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        // info
        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        int p3 = leftInfo.height + rightInfo.height + 1;
        int maxDistance = Math.max(p3, Math.max(p1, p2));
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(maxDistance, height);
    }
}
