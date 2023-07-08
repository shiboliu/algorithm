package basics.class02;

public class MergeSort {

    /**
     * 归并排序
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R){
        if (L == R) { // 小数组中仅有一个元素
            return;
        }
        int mid = L + ((R - L) >> 1); // 防止(L + R) / 2溢出
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L,int M, int R){
        int[] help = new int[L - R + 1]; // 辅助数组
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for(int j = 0;j < help.length; j++) {
            arr[L + j] = help[j];
        }
    }
}
