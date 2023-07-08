package medium.class01;

/**
 * 预处理 用数组存储预处理的数据
 */

/**
 * 给定一个N*N的矩阵matrix，只有0和1两种值，返回边框全是1的最大正方形的边长长度。
 * 例如:
 * 01111
 * 01001
 * 01001
 * 01111
 * 01011
 * 其中边框全是1的最大正方形的大小为4*4，所以返回4。
 * 
 * 
 * N*N的矩阵，里面N^3规模个子正方形（随机一点N^2，右下方的正方形），N^4规模个长方形（随即一点N^2当长方形的左上点，随即一点N^2当长方形的右下点）
 */
public class MaxOneBorderSize {

    /**
     * @return
     */
    public static int maxAllOneBorder(int[][] m) {
        if (m == null || m.length < 1) {
            return -1;
        }
        int N = m.length;
        int M = m[0].length;
        int res = 0;
        int[][] right = new int[N][M];
        int[][] down = new int[N][M];
        setBorderMap(m, right, down);
        for (int i = 0; i < N; i++) { // 遍历m[i][j]
            for (int j = 0; j < M; j++) {
                
            }
        }
        return res;
    }

    /**
     * @param m     原数组数据
     * @param right 预处理得到的数组，right[i][j]表示从此位置(含此位置)开始右边有多少个连续的1
     * @param down  预处理得到的数组，down[i][j]表示从此位置(含此位置)开始下边有多少个连续的1
     */
    public static void setBorderMap(int[][] m, int[][] right, int[][] down) {
        int N = m.length;
        int M = m[0].length;
        for (int i = 0; i < N; i++) { // right[i][j]
            right[i][M - 1] = m[i][M - 1] == 1 ? 1 : 0;
            for (int j = M - 2; j >= 0; j--) {
                right[i][j] = m[i][j] == 1 ? 1 + right[i][j + 1] : 0;
            }
        }
        for (int i = 0; i < M; i++) { // down[i][j]
            down[N - 1][i] = m[N - 1][i] == 1 ? 1 : 0;
            for (int j = N - 2; j >= 0; j--) {
                down[j][i] = m[j][i] == 1 ? 1 + down[j + 1][i] : 0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] m = { { 0, 1, 1, 1, 1 },
                { 0, 1, 0, 0, 1 },
                { 0, 1, 0, 0, 1 },
                { 0, 1, 1, 1, 1 },
                { 0, 1, 0, 1, 1 }
        };


    }

}
