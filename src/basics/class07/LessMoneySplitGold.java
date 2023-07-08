package class07;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * tips：
 * 1.根据某标准建立一个比较器来排序
 * 2.根据某标准建立一个比较器来组成堆
 * 
 * 题目：
 * 分金条的最小代价
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。比如长度为20的金条，不管切成长度多大的两半，都要花费20个铜板。
 * 一群人想整分整块金条，怎么分最省铜板?
 * 例如,给定数组[10.20.30]，代表一共三个人，整块金条长度为10+20+30=60金条要分成10.20.30三个部分。
 * 如果先把长度60的金条分成10和50，花费60:再把长度50的金条分成20和30，花费50;一共花费110铜板。但是如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20花费30:一共花费90铜板。
 * 输入一个数组，返回分割的最小代价。
 */
public class LessMoneySplitGold {

    public static int lessMoney(int[] arr) {
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while (pQ.size() > 1) {
            cur = pQ.poll() + pQ.poll();
            sum += cur;
            pQ.add(cur);
        }
        return sum;
    }

    public static class MinheapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            // TODO Auto-generated method stub
            return o1 - o2;
        }

    }
}
