package class06;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图
 * 无、有向图都可以用此类来表示
 */
public class Graph {
    
    public HashMap<Integer, Node> nodes; // <编号, 节点>
    public HashSet<Edge> edges; // 边

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
