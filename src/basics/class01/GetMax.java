package basics.class01;

/**
 * 递归行为时间复杂度计算：T(N) = a * T(N / b) + O(N ^ d)
 * a：分几分，b：每份占总数N的几分之一，O(N ^ d)：其它操作需要的时间复杂度
 * master公式的使用
 * 1）log(b, a) > d -> O(N ^ log(b, a))
 * 2）log(b, a) = d -> O(N ^ d * logN)
 * 3）log(b, a) < d -> O(N ^ d)
 */
public class GetMax {

    /**
     * 递归求arr[L……R]范围上的最大值          N
     * @param arr
     * @return
     */
    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) { // arr[L……R]范围上只有一个数，直接返回
            return arr[L];
        }
        int mid = L + ((R - L) >> 1); // 中点
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }
}
