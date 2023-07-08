package medium.class01;

/**
 * 滑动窗口[L.........R]，构建一种单调，L、R都不需要回退
 */

/**
 * 给定一个有序数组arr，代表数轴上从左到右有n个点arr[0]、arr[1]...arr[n-1]。给定一个正数len，代表一根长度为len的绳子，求绳子最多能覆盖其中的几个点。
 * 
 * 以A为开头，最后一个不超过L的位置为结尾B，以第一个点为A，找满足条件的最远的B的位置，然后将A往前移动一个位置，B从当前位置继续往下遍历，寻找满足的结尾。O(n)
 */
public class CordCoverMaxPoint {

    /**
     * 
     * @param arr
     * @param len 绳子长度
     * @return
     */
    public static int maxPoint(int[] arr, int len) {
        int l = 0;
        int r = 0;
        int max = 0;
        while (l < arr.length) {
            while (r < arr.length && arr[r] - arr[l] <= len) {
                r++;
            }
            max = Math.max(max, r - (l++));
        }
        return max;
    }

}