package class07;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 一个数据流中，随时可以取得中位数
 */
public class FindMedian {

    public static class MinComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }

    }

    public static class MaxComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }

    }

    public static float findMedian(int[] arr) {
        PriorityQueue<Integer> minQ = new PriorityQueue<>(new MinComparator());
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(new MaxComparator());
        maxQ.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] <= maxQ.peek()) {
                maxQ.add(arr[i]);
            } else {
                minQ.add(arr[i]);
            }
            if (Math.abs(maxQ.size() - minQ.size()) >= 2) {
                if (maxQ.size() > minQ.size()) {
                    minQ.add(maxQ.poll());
                } else {
                    maxQ.add(minQ.poll());
                }
            }
        }
        System.out.println(maxQ.peek() + ", " + minQ.peek());
        return (float) (arr.length % 2 == 0 ? (maxQ.peek() + minQ.peek()) / 2.0
                : maxQ.size() > minQ.size() ? maxQ.peek() : minQ.peek());
    }

    public static void main(String[] args) {
        System.out.println(findMedian(new int[] { 1, 2, 3, 3, 4, 5, 9 }));
    }

}
