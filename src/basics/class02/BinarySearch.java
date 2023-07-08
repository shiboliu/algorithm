package basics.class02;

/**
 * 二分法的详解与扩展
 * 1）在一个有序数组中，找某个数是否存在
 * 2）在一个有序数组中，找>=某个数最左侧的位置
 * 3）局部最小值问题
 */
public class BinarySearch {
    
    /**
     * 在一个有序数组中，找某个数是否存在
     * @param arr
     * @param n
     * @return 存在返回所在位置下标；不存在返回-1
     */
    public static int fun1(int[] arr, int n) {
        int l = 0, r = arr.length - 1;
        int m;
        while (l <= r) {
            m = l + ((r - l) >> 1);
            if (arr[m]  == n) {
                return m;
            } else if (arr[m] > n) {
                r = m - 1;
            } else {
                l = r + 1;
            }  
        }
        return -1;
    }

    /**
     * 在一个有序数组中，找>=某个数最左侧的位置
     * @param arr
     * @param n
     * @return 存在返回下标；否则返回-1
     */
    public static int fun2(int[] arr, int n) {
        int l = 0, r = arr.length - 1;
        int idx = -1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] >= n) {
                r = m - 1;
                idx = m;
            } else {
                l = m + 1;
            }
        }
        return idx;
    }

    /**
     * 局部最小值问题（arr无序，相邻位置上的数不一定相等，找到其中一个局部最小值即可）
     * @param arr
     * @return 返回局部最小值，若不存在返回-1
     */
    public static int fun3(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr[0] < arr[1]) {
            return arr[0];
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr[arr.length - 1];
        }
        int l = 1, r = arr.length - 2;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (isMin(arr, m)) {
                return m;
            } else {
                if (isOn(arr, m)) {
                    r = m - 1;
                }
                if (isOff(arr, m)) {
                    l = m + 1;
                }
            }
        }
        return -1;
    }

    public static boolean isMin(int[] arr, int i) {
        return arr[i - 1] > arr[i] && arr[i + 1] > arr[i];
    }

    public static boolean isOn(int[] arr, int i) {
        return arr[i - 1] > arr[i] && arr[i] > arr[i + 1];
    }

    public static boolean isOff(int[] arr, int i) {
        return arr[i - 1] < arr[i] && arr[i] < arr[i + 1];
    }
}
