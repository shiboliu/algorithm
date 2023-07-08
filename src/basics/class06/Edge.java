package class06;

/**
 * 边
 */
public class Edge {
    
    public int weight; // 权值
    public Node from; // 头
    public Node to; // 尾
    
    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

}
