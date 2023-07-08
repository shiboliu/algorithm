package class12;

import java.util.List;

/**
 * 派对的最大快乐值
 * 员工信息的定文如下:
 * class Employee {
 * public int happy; // 这名员工可以带来的快乐值
 * List<Employee> subordinates;//这名员工有哪些直接下级
 * }
 * 公司的每个员工都符合
 * Employee类的描述，整个公司的人员结构可以看作是一棵标准的、没有环的多叉树。树的头节点是公司维一的老板。除老版之外的每个员工都有唯一的直接上圾，叶节点是没有任何下属的基层员工(subordinates列表为空)除基层员工外，每个员工都有一个或多个直接下圾。
 * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来。但是要遵福如下规则。
 * 1.如果某个员工来了，那么这个员工的所有直接下圾都不能来
 * 2.派对的整体快乐值是所有到场员工快乐值的累加
 * 3.你的目标是让派对的整体快乐值尽量大
 * 
 * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值，
 */
public class MaxHappy {

    public static class Employee {
        public int happy; // 这名员工可以带来的快乐值
        public List<Employee> nexts; // 这名员工有哪些直接下级
    }

    /**
     * 存储节点来、不来时的最大快乐值
     */
    public static class Info {
        public int comeMaxHappy;
        public int noComeMaxHappy;

        public Info(int comeMaxHappy, int noComeMaxHappy) {
            this.comeMaxHappy = comeMaxHappy;
            this.noComeMaxHappy = noComeMaxHappy;
        }

    }

    public static int maxHappy(Employee boss) {
        Info bossInfo = process(boss);
        return Math.max(bossInfo.comeMaxHappy, bossInfo.noComeMaxHappy);
    }


    public static Info process(Employee x) {
        if (x.nexts.isEmpty()) { // x是基层员工的时候
            return new Info(x.happy, 0);
        }
        int come = x.happy; // x来的情况下，整棵树最大收益
        int noCome = 0; // x不来的情况下，整棵树的最大收益
        for (Employee next : x.nexts) {
            Info nextInfo = process(next);
            come += nextInfo.noComeMaxHappy;
            noCome += Math.max(nextInfo.comeMaxHappy, nextInfo.noComeMaxHappy);
        }
        return new Info(come, noCome);
    }
}
