package basics.class01;

/**
 * 异或运算的性质与扩展
 * 1）0 ^ N = N     N ^ N = 0
 * 2）异或运算满足交换律和结合律
 * 3）不用额外变量交换两个数（这两个数不能是相同的存储空间，否则将会置为0）
 * 4）一个数组中有一种数出现了奇数次，其它数都出现了偶数次，怎么找到这一个数
 * 5）一个数组中有两种数出现了奇数次，其它数都出现了偶数次，怎么找到这两个数
 */
public class EvenTimesOddTimes {
    
    /**
     * 一个数组中有一种数出现了奇数次，其它数都出现了偶数次，怎么找到这一个数
     * @param arr
     */
    public static int printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int cur : arr) {
            eor ^= cur;
        }
        return eor;
    }

    /**
     * 一个数组中有两种数出现了奇数次，其它数都出现了偶数次，怎么找到这两个数
     * @param arr
     */
    public static int[] printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int curNum : arr) {
            eor ^= curNum;
        }
        /*
         * eor = a ^ b
         * eor != 0
         * eor 必然有一个二进制位上是1
        */
        int rightOne = eor & (~eor + 1); // 提取出最右的1，得到的数二进制形式为00……0100……
        int onlyOne = 0; // eor'
        for (int cur : arr) {
            if((cur & rightOne) == 0){ // = 0说明那个二进制位为0
                onlyOne ^= cur;
            }
        }
        // onlyOne = a or b
        return new int[]{onlyOne, (eor ^ onlyOne)};
    }

}
