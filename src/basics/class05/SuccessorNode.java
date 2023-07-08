package class05;

/**
 * 在二叉树中找到一个节点的后继节点
 * 只给一个在二叉树中的某个节点node，请实现返回node的后继节点的函数。
 * 在二叉树的中序遍历的序列中，node的下一个节点叫作node的后继节点。
 * 
 * 1）node有右子树，node的后继是右子树上最左节点
 * 2）node无右子树，往上找第一个是其父节点的左孩子的节点
 * 整棵树最右节点后继为null
 */
public class SuccessorNode {
    
    public class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent; // 指向父节点的指针，根节点的parent指向null

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 有右孩子，就返回有孩子的最左节点；
     * 否则从当前节点开始遍历其祖先到根节点，返回第一个是其父亲节点左孩子的节点的父亲节点（最右节点后继返回null）
     * @param root
     * @return
     */
    public static Node getSuccessorNode(Node root) {
        if (root == null) {
            return root;
        }
        if (root.right != null) {
            return getLeftMost(root.right);
        } else { // 无右子树
            Node parent = root.parent;
            while (parent != null && parent.left != root) { // 当前节点root是其父亲节点parent的右孩子进入循环
                root = parent;
                parent = root.parent;
            }
            return parent; // 最右节点会返回null
        }
    }

    /**
     * 获得root子树的最左节点
     * @param root
     * @return 无返回null
     */ 
    public static Node getLeftMost(Node root) {
        if (root == null) {
            return root;
        }
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}
