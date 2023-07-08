package class07;

/**
 * 前缀树
 * 一个字符串类型的数组arr1，另一个字符串类型的数组arr2。
 * arr2中有哪些字符，是arr1中出现的？请打印
 * arr2中有哪些字符，是作为arr1中某个字符串前缀出现的？请打印
 * arr2中有哪些字符，是作为arr1中某个字符串的前缀出现的？请打印arr2中出现次数最大的前缀
 */
public class TrieTree {

    public static class TrieNode {

        public int pass; // 有几个字符串经过此节点
        public int end; // 有几个字符串以此节点结尾
        public TrieNode[] nexts; // HashMap<Char, Node> nexts或TreeMap<Char, Node> nexts;

        public TrieNode() {
            pass = 0;
            end = 0;
            /**
             * nexts[0] == null     没有走向'a'的路
             * nexts[0] != null     有走向'a'的路
             * ……
             * nexts[25] != null    有走向'z'的路
             */
            nexts = new TrieNode[26];
        }

    }

    public static class Trie {
        public TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        /**
         * 插入
         * @param word
         */
        public void insert(String word) {
            if (word == null) {
                return ;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            node.pass++;
            int index = 0;
            for (int i = 0; i < chs.length; i++) { // 从左往右遍历字符
                index = chs[i] - 'a'; // 由字符，对应成走向哪条路
                if (node.nexts[index] == null) { 
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        /**
         * 删除
         * @param word
         */
        public void delete(String word) {
            if (search(word) != 0) { // 确定树中确实加入过word，才删除
                char[] chs = word.toCharArray();
                TrieNode node = root;
                node.pass--;
                int index = 0;
                for (int i = 0; i < chs.length; i++) {
                    index = chs[i] - 'a';
                    if (--node.nexts[index].pass == 0) {
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

        /**
         * 查询
         * @param word
         * @return word这个单词之前加入过几次
         */
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        /**
         * 
         * @param pre
         * @return 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
         */
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }

    }
    
}
