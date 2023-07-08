package medium.class01;

import java.util.Arrays;

/**
 * 预处理 ：空间换时间， 频繁的操作想办法节省时间，下面的可以用一个数组来节省查询R、G个数的时间
 */

/**
 * 牛牛有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。牛牛现在可以选择任意一个正方形然后用这两种颜色的任意一种进行染色这个正方形的颜色将会被覆盖。牛牛的目标是在完成染色之后,每个红色R都比每个绿色G距离最左侧近。牛牛想知道他最少需要涂染几个正方形。如样例所示:
 * s = RGRGR我们涂染之后变成RRRGG满足要求了,涂染的个数为2,没有比这个更好的涂染方案。
 */
public class ColorLeftRight {

    /**
     * fori是用来计算[0……i]全部变成R + [i+1……len-1]全部变成G所需要涂染的次数。
     * 这里使用arrG和arrR记录信息，
     */
    public static int minPain(String s) {
        char[] chs = s.toCharArray();
        int res = chs.length;
        // begin
        int[] arrG = getAssistArrG(chs);
        int[] arrR = getAssistArrR(chs);
        for (int i = 0; i < chs.length; i++) {
            res = Math.min(res, arrG[i] + arrR[i]);
        }
        // end
        return res;
    }

    /**
     * @return int[]，[i]表示[0……i]上一共有多少个G
     */
    public static int[] getAssistArrR(char[] chs) {
        if (chs == null || chs.length < 1) {
            return null;
        }
        int[] res = new int[chs.length];
        res[0] = chs[0] == 'R' ? 0 : 1;
        for (int i = 1; i < res.length; i++) {
            res[i] = chs[i] == 'R' ? res[i - 1] : res[i - 1] + 1;
        }
        return res;
    }

    /**
     * @return int[]，[i]表示[i+1……len-1]上一共有多少个R
     */
    public static int[] getAssistArrG(char[] chs) {
        if (chs == null || chs.length < 1) {
            return null;
        }
        int[] res = new int[chs.length];
        res[chs.length - 1] = 0;
        for (int i = chs.length - 2; i >= 0; i--) {
            res[i] = chs[i + 1] == 'G' ? res[i + 1] : res[i + 1] + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getAssistArrG("RGRGR".toCharArray())));
        System.out.println(minPain("RGRGR"));
    }

}
