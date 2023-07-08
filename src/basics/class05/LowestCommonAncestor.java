package class05;

import java.util.HashMap;
import java.util.HashSet;

import basics.class02.HeapSort;

/**
 * 给定两个二叉树节点node1和node2，找到它们的最低公共祖先节点
 */
public class LowestCommonAncestor {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 用hashmap存储每个节点的父亲节点
     * 用一个hashset存储node1及其所有祖先节点
     * 再遍历node2及其所有祖先节点，看那个节点最先出现在hashset中，哪个节点就是最低公共祖先
     * @param root
     * @param node1
     * @param node2
     */
    public static Node lowestCommonAncestor(Node root, Node node1, Node node2) {
        HashMap<Node, Node> fatherMap = new HashMap<>();
        fatherMap.put(root, root);
        process(root, fatherMap);
        HashSet<Node> set = new HashSet<>();
        Node cur = node1;
        while (cur != fatherMap.get(cur)) {
            set.add(cur);
            cur = fatherMap.get(cur);
        }
        set.add(root);
        cur = node2;
        while (cur != root) {
            if (set.contains(cur)) {
                return cur;
            }
            cur = fatherMap.get(cur);
        }
        return root;
    }

    /**
     * 寻找每个节点（除根节点）的父亲节点
     * @param root
     * @param fatherMap
     */
    public static void process(Node root, HashMap<Node, Node> fatherMap) {
        if (root == null) {
            return ;
        }
        fatherMap.put(root.left, root);
        fatherMap.put(root.right, root);
        process(root.left, fatherMap);
        process(root.right, fatherMap);
    }
    
    /**
     * 只存在两种情况
     * 1）node1是node2的祖先，或node2是node1的祖先
     * 2）node1与node2不互为祖先，需要向上找
     * @param root
     * @param node1
     * @param node2
     */
    public static Node lowestAncestor(Node root, Node node1, Node node2) {
        if (root == null || root == node1 || root == node2) {
            return root;
        }
        Node left = lowestAncestor(root.left, node1, node2);
        Node right = lowestAncestor(root.right, node1, node2);
        if (left != null && right != null) {
            return root;
        }
        // 左右两棵树，并不都有返回值
        return left != null ? left : right;
    }
}
