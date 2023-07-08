package class06;

import java.util.ArrayList;

/**
 * 节点
 */
public class Node {
    
    public int value; // 节点值
    public int in; // 入度
    public int out; // 出度
    public ArrayList<Node> nexts; // ->的节点
    public ArrayList<Edge> edges; // ->

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
