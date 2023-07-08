package class06;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javafx.scene.web.WebHistory.Entry;

/**
 * 
 * 可以有权值为负数的边，不能有全职累加和为负数的环
 */
public class Dijkstra {
    
    /**
     * 从head出发到所有点的最小距离
     * @param head
     * @return
     */
    public static HashMap<Node, Integer> dijkstra1(Node head) {
        // <从head出发到达key, 从head出发到达key的最小距离>
        // 如果在表中，没有T的记录，含义是从head出发到T这个点的距离为正无穷
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(head, 0);
        // 已经求过距离的节点，存在selectedNodes中，以后再也不碰
        HashSet<Node> selectedSet = new HashSet<>();
        Node minNode = null;
        return distanceMap;
    }

    public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> touchedNodes) {
        Node minNode = null;
        for (Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getkey();
        }
        return minNode;
    }
}
