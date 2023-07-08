package class07;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入:
 * 正数数组costs
 * 正数数组profits
 * 正数k
 * 正数m
 * 含义:
 * costs[i]表示：i号项目的花费
 * profits[i]表示：i号项目在扣除花费之后还能挣到的钱(利润)
 * k表示你只能串行的最多做k个项目
 * m表示你初始的资金说明
 * 说明:
 * 你每做完一个项目，马上获得的收益，可以支持你去做下一个项目。
 * 输出:
 * 你最后获得的最大钱数。
 */
public class IPO {

    public static class Node {
        public int p;
        public int c;

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            // TODO Auto-generated method stub
            return o1.c - o2.c;
        }

    }

    public static class MaxProfitComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            // TODO Auto-generated method stub
            return o1.p - o2.p;
        }

    }

    /**
     * 使用小根堆（花费）上锁和大根堆（利润）解锁
     * 
     * @param k
     * @param w
     * @param profits
     * @param captical
     * @return
     */
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] captical) {
        PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        // 所有项目放到被锁池中，花费组织的小根堆
        for (int i = 0; i < profits.length; i++) {
            minCostQ.add(new Node(profits[i], captical[i]));
        }
        for (int i = 0; i < k; i++) { // 进行k轮
            // 能力所及的项目，全解锁
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= w) {
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()) {
                return w;
            }
            w += maxProfitQ.poll().p;
        }
        return w;
    }

}
