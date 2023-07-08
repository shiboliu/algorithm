package class05;

import java.util.Stack;

/**
 * 树的遍历
 */
public class PreInPosTraversal {
    
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void f(Node head) {
        // 1
        f(head.left);
        // 2
        f(head.right);
        // 3
    }

    /**
     * 递归先序
     * @param head
     */
    public static void preOrderRecur(Node head) {
        if (head == null) {
            return ;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    /**
     * 递归中序
     * @param head
     */
    public static void inOrderRecur(Node head) {
        if (head == null) {
            return ;
        }
        preOrderRecur(head.left);
        System.out.print(head.value + " ");
        preOrderRecur(head.right);
    }

    /**
     * 递归后序
     * @param head
     */
    public static void PosOrderRecur(Node head) {
        if (head == null) {
            return ;
        }
        preOrderRecur(head.left);
        preOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    /**
     * 非递归先序遍历：进栈顺序是头右左
     * 自己进行压栈替代函数栈
     * 在不是空树的情况下，先将根入栈
     * 栈不空时循环，每次取出栈顶，进行处理，再将此节点的右孩子和左孩子依次入栈(如果有的话)
     * @param head 根
     */
    public static void preOrderUnRecur(Node head) {
        System.out.print("pre-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.value + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    /**
     * 非递归中序遍历
     * 每当来到一个节点，如果做不为空，则将左边界依次入栈；否则栈顶出栈并处理，再看出栈元素的右子树(如果有)，再一只将左边界压栈
     * 整棵树可以被左边界(或者有边界)分解掉
     * @param head
     */
    public static void inOrderUnRecur(Node head) {
        System.out.print("in-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    /**
     * 非递归后序遍历1：双栈
     * 先序遍历是头左右，我们可以在非递归先序遍历的时候将左、右孩子依次入栈(如果有的话)，那么先序遍历'的结果就是头右左
     * 栈可以反转一个串，故我们在先序遍历'中处理每一个弹出来的节点时，将节点压入另一个栈中
     * 当所有的节点都压入另一个栈中时，逐个弹出并处理，就会得到左右头序列，即后序遍历
     * @param head
     */
    public static void PosOrderUnRecur1(Node head) {
        System.out.print("pos-order: ");
        if (head != null) {
            Stack<Node> s1 = new Stack<Node>();
            Stack<Node> s2 = new Stack<Node>();
            s1.push(head);
            while (!s1.isEmpty()) {
                head = s1.pop();
                s2.push(head);
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().value + " ");
            }
        }
        System.out.println();
    }

    /**
     * 非递归后序遍历2：单栈
     * @param head
     */
    public static void PosOrderUnRecur2(Node head) {
        System.out.print("pos-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.push(head);
            Node c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && head != c.left && head != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && head != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().value + " ");
                    head = c;
                }
            }
        }
        System.out.println();
    }
}
