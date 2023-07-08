package basics.class02;

/**
 * 荷兰国旗问题
 */
public class QuickSort {

    /**
     * 给定一个数组arr，和一个数num，请把小于等于num的数放在数组的左边，大于num的数放在数组的右边。
     * 要求额外空间复杂度O(1)，时间复杂度O(N)
     * 
     * 如果[i] <= num，[i]和<=区的下一个数交换，<=区右扩，i++；否则（[i] > num）i++
     * @param arr
     * @param num
     */
    public static void fun1(int[] arr, int num) {
        int l = -1;
        int i = 0;
        while (i < arr.length) {
            if (arr[i] <= num) {
                swap(arr, ++l, i++);
            } else {
                i++;
            }
        }
    }

    /**
     * 荷兰国旗问题
     * 给定一个数组arr，和一个数num，请把小于num的数放在数组的左边，等于num的数放在数组的中间，大于num的数放在数组的右边。
     * 要求额外空间复杂度O(1)，时间复杂度O(N)
     * @param arr
     * @param num
     */
    public static void fun2(int[] arr, int num) {
        int l = -1, r = arr.length;
        int i = 0;
        while (i < arr.length) {
            if (arr[i] < num) {
                swap(arr, ++l, i++);
            } else if (arr[i] == num) {
                i++;
            } else {
                swap(arr, --r, i);
            }
        }
    }
    
    /**
     * 快速排序
     * @param arr
     */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return ;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    // arr[L……R]排好序
    public static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
            int[] p = partition(arr, L, R);
            quickSort(arr, L, p[0] - 1); // <区
            quickSort(arr, p[1] + 1, R); // >区
        }
    }

    /**
     * 这是一个处理arr[L……R]的函数
     * 默认以arr[R]做划分，arr[R] ->p   <p  ==p >p
     * 返回等于区域(左边界，右边界)，所以返回一个长度为2的数组res，res[0]，res[1]
     */
    public static int[] partition(int[] arr, int L, int R) {
        int less = L - 1; // <区右边界
        int more = R; // >区左边界
        while (L < more) { // L表示当前数的位置 arr[R]  ->  划分值
            if (arr[L] < arr[R]) { // 当前数 < 划分值
                swap(arr, ++less, L++);
            } else if (arr[L] > arr[R]){ // 当前数 > 划分值
                swap(arr, --more, L);
            } else {
                L++;
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
