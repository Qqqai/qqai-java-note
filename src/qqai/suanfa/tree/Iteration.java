package qqai.suanfa.tree;

import qqai.suanfa.common.TreeNode;

import java.util.Stack;

/**
 * @author qqai
 * @createTime 2020/12/3 16:59
 * @description：遍历
 */

public class Iteration {

    /*标记 后序遍历 src/qqai/suanfa/img/二叉树得后序遍历.png*/
    public static void posOrderUnRecur(TreeNode head) {
        System.out.print("pos-order:");
        if (head != null) {
            Stack<TreeNode> s1 = new Stack<>();
            // 收集逆序完成得栈
            Stack<TreeNode> s2 = new Stack<>();
            s1.push(head);
            // s1栈不为空就进入
            while (!s1.isEmpty()) {
                head = s1.pop();
                // 把当前节点压到s2栈内
                s2.push(head);
                // 把当前节点得右节点压入s2
                if (head.left != null) {
                    s1.push(head.left);
                }
                // 把当前节点得左节点压入s2
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            // 打印逆序结果
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().val + "\t");
            }
        }
        System.out.println();
    }

    /*标记 中序遍历  src/qqai/suanfa/img/二叉树得中序遍历.png*/
    public static void inOrderUnRecur(TreeNode head) {
        System.out.print("in-order:");
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            // 栈不空，head不空都进入循环
            while (!stack.isEmpty() || head != null) {
                // 如果head是null
                if (head != null) {
                    // 压入当前节点纵深得所有左子节点
                    stack.push(head);
                    head = head.left;
                } else {
                    // head为空就说明左节点到底了就弹出栈顶，然后栈顶这个右孩子也可能是空得就在弹出栈顶这就到了一个根节点了
                    // 然后打印这个根节点，然后找到这个根节点得右子节点，然后就得到了一个完整得压栈和出栈过程
                    head = stack.pop();
                    System.out.println(head.val + "\t");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    /*标记 先序遍历  src/qqai/suanfa/img/二叉树得先序遍历.png*/
    public static void preOrderUnRecur(TreeNode head) {
        System.out.print("pre-order:");
        // 判断当前节点头是否为空  其实可以复制一份  TreeNode temp = head.....
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            // 压入栈头
            stack.push(head);
            while (!stack.isEmpty()) {
                // 栈弹出一个元素
                head = stack.pop();
                // 打印
                System.out.println(head.val + "\t");
                // 压入右节点
                if (head.right != null) {
                    stack.push(head.right);
                }
                // 压入左节点
                if (head.left != null) {
                    stack.push(head.left);
                }
                // 重复while循环
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {

    }
}
