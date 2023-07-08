package class09;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集
 */
public class UnionFind {
    
    /**
     * 样本进来会包一层，叫作元素
     */
    public static class Element<V> {

        public V value;

        public Element(V value) {
            this.value = value;
        }
    }

    /**
     * 
     */
    public static class UnionFindSet<V> {

        public HashMap<V, Element<V>> elementMap;
        public HashMap<Element<V>, Element<V>> fatherMap; // <某个元素, 该元素的父>
        public HashMap<Element<V>, Integer> sizeMap; // <某个集合的代表元素, 该集合的大小>

        public UnionFindSet(List<V> list) {
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : list) {
                Element<V> element = new Element<V>(value);
                elementMap.put(value, element);
                fatherMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }

        /**
         * 给定一个element，往上查找，把所在集合的代表元素返回
         * @param element
         * @return
         */
        private Element<V> findHead(Element<V> element) {
            Stack<Element<V>> path = new Stack<>();
            while (element != fatherMap.get(element)) {
                path.push(element);
                element = fatherMap.get(element);
            } // element = 代表元素
            while (!path.isEmpty()) { // 仅修改element到代表节点(不含代表节点)路径上的节点
                fatherMap.put(path.pop(), element);
            }
            return element;
        }

        /**
         * 
         * @param a
         * @param b
         * @return a和b是否同一集合
         */
        public boolean isSameSet(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }

        /**
         * 合并两个集合
         * @param a
         * @param b
         */
        public void union(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                Element<V> aF = findHead(elementMap.get(a));
                Element<V> bF = findHead(elementMap.get(b));
                if (aF != bF) {
                    Element<V> big = sizeMap.get(aF) >= sizeMap.get(bF) ? aF : bF;
                    Element<V> small = big == aF ? bF : aF;
                    fatherMap.put(small, big);
                    sizeMap.put(big, sizeMap.get(aF) + sizeMap.get(bF));
                    sizeMap.remove(small);
                }
            }
        }
    }
    
}
