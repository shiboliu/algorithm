package class11;

/**
 * Manacher算法解决的问题
 * 字符串str中，最长回文子串的长度如何求解？
 * 如何做到时间复杂度O(N)完成
 */
public class Manacher {

    /**
     * 12321 ==》 #1#2#3#2#1#
     * 
     * @param str
     * @return
     */
    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() >> 1 + 1];
        for (int i = 0, index = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static int maxLcpsLength(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s); // 1221 -> #1#2#2#1#
        int[] pArr = new int[str.length]; // 回文半径数组
        int c = -1; // 中心
        int r = -1; // 回文右边界的再往右一个位置，最右的有效区域是r-1位置
        int max = Integer.MIN_VALUE; // 扩出来的最大值
        for (int i = 0; i < str.length; i++) { // 每一个位置都求回文半径
            // i至少的会问半径区域，先给pArr[i]
            pArr[i] = r > i ? Math.min(r - i, pArr[2 * c - i]) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > r) {
                r = i + pArr[i];
                c = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }
}
