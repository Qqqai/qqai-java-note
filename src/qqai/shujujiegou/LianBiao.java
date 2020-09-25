package qqai.shujujiegou;

/**
 * 描述：链表节点
 *
 * @author qqai
 * @createTime 2020-09-17 20:39
 */

public class LianBiao {
    public static void main(String[] args) {
        HeroNode second = new HeroNode(1, "张三", "屁股大");
        HeroNode third = new HeroNode(2, "里斯", "嘴巴大");
        HeroNode four = new HeroNode(3, "王五", "胸大");
        HeroNode five = new HeroNode(4, "赵六", "最好看");
        HeroNode sxi = new HeroNode(5, "陈启", "最可爱");
        singleLinkedList singleLinkedList = new singleLinkedList();
        singleLinkedList.add(second);
        singleLinkedList.add(four);
        singleLinkedList.add(sxi);
        singleLinkedList.add(third, true);
        singleLinkedList.add(five, true);

    }
}

class singleLinkedList {
    private static final HeroNode head = new HeroNode(0, "", "");

    public void add(HeroNode node) {
        //temp是表头不能动！！！所以这里需要临时变量
        HeroNode temp = head;
        //一直找，直到下一个节点是null就表示到了链表边缘
        while (true) {
            if (temp.getNext() != null) {
                //没找到边缘  吧下一个节点
                temp = temp.getNext();
            } else {
                //找到边缘了 直接把边缘的下一个设置成传递过来的节点
                temp.setNext(node);
                break;
            }
        }
    }

    public void add(HeroNode node, boolean flag) {
        //按照no编号插入
        HeroNode temp = singleLinkedList.head;
        while (true) {
            if (temp.getNext() == null) {
                System.out.println("到达边界");
            }
            if (temp.getNo() == temp.getNext().getNo()) {
                //已存在
                System.out.println("节点已存在");
                break;
            }
            if (temp.getNext().getNo() < node.getNo() && temp.getNext().getNext() == null) {
                //编号比前一个大 插入
                node.setNext(temp.getNext().getNext());
                temp.getNext().setNext(node);
                System.out.println("成功");
                break;
            } else if (temp.getNext().getNo() < node.getNo() && temp.getNext().getNext().getNo() > node.getNo()) {
                //编号比前一个大 插入
                node.setNext(temp.getNext().getNext());
                temp.getNext().setNext(node);
                System.out.println("成功");
                break;
            }
            temp = temp.getNext();
        }
    }

    public HeroNode pop(int no) {
        //弹出  根据no找到并弹出
        HeroNode temp = head;
        while (true) {
            if (temp.getNext() == null) {
                System.out.printf("没有编号位%d的节点", no);
            }
            if (temp.getNext().getNo() == no) {
                temp.setNext(temp.getNext().getNext());
            }
        }
    }
}


class HeroNode {
    private int no;
    private String name;
    private String nickname;
    private HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }
}
