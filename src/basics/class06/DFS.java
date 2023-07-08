package class06;

import java.util.HashSet;
import java.util.Stack;

/**
 * 图的广度优先遍历
 */
public class DFS {
    
    /**
     * 利用栈实现
     * 从源节点开始把节点按照深度放入栈，然后弹出
     * 每弹出一个点，把该节点下一个没有进过栈的邻接点放入栈
     * 直到栈变空
     * @param node
     */
    public static void dfs(Node node) {
        if (node == null) {
            return ;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
