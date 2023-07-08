package class07;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 贪心算法：在某一个标准下，优先考虑最满足标准的样本，最后考虑最不满足标准的样本，最终得到一个答案的算法，叫做贪心算法。
 * 也就是说，不从整体最优上加以考虑，所做出的是在某种意义上的局部最优解。
 * 
 * 贪心算法的在笔试时的解题思路
 * 1.实现一个不依靠贪心策略的解法X，可以用最暴力的尝试
 * 2.脑补出贪心策略A、贪心策略B、贪心策略C
 * 3.用解法X和对数器，去验证每一个贪心策略，用实验的方式得知哪个贪心策略正确
 * 4.不要去纠结贪心策略的证明
 * 
 * 贪心多准备模板和对数器
 * 
 * 题目：
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你一个项目开始的时间和结束的时间（给你一个数组，里面是一个个具体的项目），你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
 * 返回这个最多的宣讲场次。
 */
public class BestArrange {
    
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class ProgramComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
        
    }

    /**
     * 
     * @param programs
     * @param timePoint
     * @return
     */
    public static int bestArrange(Program[] programs, int timePoint) {
        Arrays.sort(programs, new ProgramComparator());
        int result = 0;
        // 从左往右依次遍历所有的会议
        for (int i = 0; i < programs.length; i++) {
            if (timePoint <= programs[i].start) {
                result++;
                timePoint = programs[i].end;
            }
        }
        return result;
    }
}
