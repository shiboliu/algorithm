package medium.class01;

/**
 * 打表法：输入和输出都是整数，先用常规方法解答，然后开始从base0开始打印结果，分析规律，优化代码。
 */

/**
 * 小虎去附近的商店买苹果，奸诈的商贩使用了捆绑交易，只提供6个每袋和8个每袋的包装包装不可拆分。可是小虎现在只想购买恰好n个苹果，小虎想购买尽量少的袋数方便携带。如果不能购买恰好n个苹果，小虎将不会购买。输入一个整数n，表示小虎想购买的个苹果，返回最小使用多少袋子。如果无论如何都不能正好装下，返回-1。
 */
public class AppleMinBags {

    /**
     * 
     * @param apple
     * @return
     */
    public static int minBags(int apple) {
        if (apple < 0) {
            return -1;
        }
        int bag6 = -1;
        int bag8 = apple / 8;
        int rest = apple - 8 * bag8;
        while (bag8 >= 0 && rest < 24) {
            int restUse6 = minBagBase6(rest);
            if (restUse6 != -1) {
                bag6 = restUse6;
                break;
            }
            rest = apple - 8 * (--bag8);
        }
        return bag6 == -1 ? -1 : bag6 + bag8;
    }

    /**
     * @param rest
     * @return 如果剩余苹果rest可以被装6个苹果的袋子搞定，返回袋子数量；否则不能搞定返回-1
     */
    public static int minBagBase6(int rest) {
        return rest % 6 == 0 ? (rest / 6) : -1;
    }

    /**
     * 使用打表法总结出来的结论
     * @param apple
     * @return
     */
    public static int minBagAwesome(int apple) {
        if ((apple & 1) != 0) { // 如果是奇数返回-1D
            return -1;
        }
        if (apple < 18) {
            return apple == 0 ? 0 : (apple == 6 || apple == 8) ? 1 : (apple == 12 || apple == 14 || apple == 16) ? 2 : -1; 
        }
        return (apple - 18) / 8 + 3;
    }

    public static void main(String[] args) {
        for (int apple = 1; apple < 100; apple++) {
            System.out.println(apple + " : " + minBags(apple) + " , " + minBagAwesome(apple) + " , " +  (minBagAwesome(apple) == minBags(apple)));
        }
    }
}
