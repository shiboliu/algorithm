package basics.class02;

/**
 * 堆
 * 堆结构就是用数组实现的完全二叉树结构
 * 完全二叉树中如果每颗子树的最大值都在顶部就是大根堆
 * 完全二叉树中如果每颗子树的最小值都在顶部就是小根堆
 * 堆结构的heapInsert与heapify操作
 * 堆结构的增大和减少
 * 优先级队列结构，就是堆结构
 */
public class HeapSort {
    
    /**
     * 最大堆
     * 将数组中的数逐个加入到堆中，插入的时候时间复杂度高。
     * 将整个数组当成未成形的堆，然后调整时间复杂度O(N)
     * 
     * 堆排序
     * 1.先让整个数组都变成大根堆结构，建立堆的过程：
     *      1）从上到下的方法，时间复杂度为O(N*logN)
     *      2）从下到上的方法，时间复杂度为O(N)
     * 2.把堆的最大值和堆末尾的值交换，然后减少堆的大小之后，再去调整堆，一直周而复始，时间复杂度为O(N*logN)
     * 3.堆的大小减小到0之后，排序完成
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return ;
        }
        for (int i = 0; i < arr.length; i++) { // O(N)
            heapInsert(arr, i); // O(logN)
        }

        // 下面的for比上面的for节省时间
        // for (int i = arr.length - 1; i >= 0; i--) {
        //     heapify(arr, i, arr.length);
        // }

        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) { // O(N)
            heapify(arr, 0, heapSize); // O(logN)
            swap(arr, 0, --heapSize); // O(1)
        }
    }

    /**
     * 某个数现在处在index位置，往上继续移动
     */
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) >> 1]) {
            swap(arr, index, (index - 1) >> 1);
            index = (index - 1) >> 1;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = (index << 1) + 1; // 左孩子下标
        while (left < heapSize) { // 下方还有右孩子的时候
            // 两个孩子中，谁的值大，把下标给largest
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left : left + 1;
            // 父亲和孩子之间，谁的值大，把下标给largest
            largest = arr[largest] > arr[index] ? largest : index;
            if (index == largest) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = (index << 1) + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}