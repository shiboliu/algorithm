package class11;

public class KMP {

    /**
     * N >= M
     * 
     * @param s
     * @param m
     * @return
     */
    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int[] next = getNextArray(str2); // O(M)
        while (i1 < str1.length && i2 < str2.length) { // O(N)
            if (str1[i1] == str2[i2]) {
                i1++;
                i2++;
            } else if (i2 == 0) { // next[i2] == -1
                i1++;
            } else {
                i2 = next[i2];
            }
        }
        return i2 == str2.length ? i1 - i2 : -1; // i1越界或者i2越界了
    }

    /**
     * 
     * @param ms
     * @return
     */
    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[] { ms.length };
        next[0] = -1;
        next[1] = 0;
        int i = 2; // next数组的位置
        int cn = 0;
        while (i < next.length) {
            if (ms[i - 1] == ms[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) { // 当前跳到cn位置的字符，和i-1位置的字符匹配不上
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static int[] getNextValArray(char[] chs) {
        if (chs.length == 1) {
            return new int[]{0};
        }
        return null;
    }
}
