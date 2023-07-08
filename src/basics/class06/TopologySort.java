package class06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 拓扑排序算法
 * 适用范围：要求有向图，且有入度为0的节点，且没有环
 */
public class TopologySort {
    
    /**
     * directed graph and no loop
     * @return
     */
    public static List<Node> sortedTopology(Graph graph) {
        // <某一个node，剩余的入度>
        HashMap<Node, Integer> inMap = new HashMap<>();
        // 入度为0才能进这个队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        // 拓扑排序的结果，依次加入res中
        List<Node> res = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            res.add(cur);
            for (Node next : res.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return res;
    }
}
