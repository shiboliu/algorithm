package basics.class01;

public class BubbleSort {
    /**
     * 冒泡排序
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        if(arr == null || arr.length < 2){
            return;
        }
        for(int e = arr.length - 1; e > 0; e--){
            for(int i = 0; i < e; i++){
                if(arr[i] > arr[i + 1]){
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    // 交换arr的i和j位置上的值
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}
