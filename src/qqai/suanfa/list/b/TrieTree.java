package qqai.suanfa.list.b;

/**
 * @author qqai
 * @createTime 2020/12/13 22:38
 * @description： 前缀树
 */

public class TrieTree {

    // 根节点
    private static final TrieNode root = new TrieNode();

    /*查询以pre为前缀的字符串的数量*/
    public static int prefixNumber(String pre) {
        if (pre.isEmpty()) return 0;
        TrieNode node = TrieTree.root;
        for (char c : pre.toCharArray()) {
            int index = c - 'a';
            // 如果出现null就表示没有以pre为前缀的字符串
            if (node.next[index] == null) {
                return 0;
            }
            node = node.next[index];
        }
        // 返回的path就是所有经过这个节点的数量就是以pre前缀的字符串的数量
        return node.path;
    }

    /*删除一个词汇*/
    public static void delete(String word) {
        if (word.isEmpty()) return;
        TrieNode node = TrieTree.root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            // 如果这个节点的path等于0了就表示只有当前单词经过了这个节点 那么直接把后继全部删除就可以了
            if (node.next[index] != null && --node.next[index].path == 0) {
                node.next[index] = null;
                return;
            }
            node.end--;
        }
    }

    /*查询一个词汇添加的次数，就是查最后一个字母结尾的次数*/
    public static int search(String word) {
        if (word.isEmpty()) return 0;
        TrieNode node = TrieTree.root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.next[index] == null) {
                return 0;
            }
            node = node.next[index];
        }
        return node.end;
    }

    /*添加一个词汇*/
    public static void insert(String word) {
        // 判断这个字符串是否为空
        if (word.isEmpty()) return;
        char[] words = word.toCharArray();
        TrieNode node = root;
        int index;
        // 这个词汇的字符-a的ascII值就是所属通道的index
        for (char c : words) {
            index = c - 'a';
            // 如果没有这个通道 就创建出来
            if (node.next[index] == null) {
                node.next[index] = new TrieNode();
            }
            // 到达了这个节点
            node = node.next[index];
            // 此处的path来到了一次  +1
            node.path++;
        }
        // 最后遍历完成的node就是最后一个字母通道指向的node   以这个node结尾 end++
        node.end++;
    }

    /**
     * 前缀树的节点
     */
    public static class TrieNode {
        // 有多少个字符串到达过这个节点
        public int path;
        // 有多少字符串是以这个节点结尾的
        public int end;
        // 路 此处规定是a-z 26条路
        public TrieNode[] next;

        public TrieNode() {
            path = 0;
            end = 0;
            next = new TrieNode[26];
        }
    }
}
