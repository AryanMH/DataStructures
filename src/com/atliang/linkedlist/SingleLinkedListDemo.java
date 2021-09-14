package com.atliang.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // test 进行测试
        // create some nodes
        HeroNode hero1 = new HeroNode(1,"宋江","jishiyu");
        HeroNode hero2 = new HeroNode(2,"lujunyi","yuqilin");
        HeroNode hero3 = new HeroNode(3,"wuyong","zhiduoxing");
        HeroNode hero4 = new HeroNode(4,"linchong","baozitou");

        //加入对象之前要先创建一个链表 creat a linkedlist
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        // add
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        // addByOrder
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        // show
        singleLinkedList.list();

        // test the update method
        HeroNode newHeroNode = new HeroNode(1,"songjiang","yuqilin");
        singleLinkedList.update(newHeroNode);
        System.out.println("modified siglelinkedlist");
        singleLinkedList.list();

        // delete a node
        singleLinkedList.del(1);
        System.out.println("删除后的情况");
        singleLinkedList.list();
    }

}

//step 2: define SingleLinedList to manage the HeroNode
class SingleLinkedList {
    //initialize the head node, don't change the head node, and it doesn't store any specific data
    private HeroNode head = new HeroNode(0,"","");

    //add node to singlelinkedlist
    //when the order of numbers is not considered
    //1. find the last node of this linkedlist
    //2. let the next of the last node refer to new node
    public void add(HeroNode heroNode){ //heroNode是需要添加的新节点
        // we need a temperate node temp, because the head node couldn't be changed
        HeroNode temp = head;
        // go through the linkedlist to find the last node
        while (true){
            //find the last node of this linkedlist
            if (temp.next==null){
                break;
            }
            temp = temp.next;
        }
        // temp points to the end of the linkedlist when exiting the while
        // let the next of the last node point to the new node
        temp.next = heroNode;
    }

    //the second add method. 在添加英雄时根据英雄排名插入到指定位置
    //如果有这个排名，则添加失败，并给出提示
    public void addByOrder(HeroNode heroNode){
        //因为头结点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        //因为是单链表，所以我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false; //flag 表侄添加的编号是否存在，默认为false。默认不存在
        while (true){
            if (temp.next == null){ //说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) { //位置找到，就在temp的后面插入
                break;
            }else if (temp.next.no == heroNode.no) { //说明想要添加的heroNode的编号已存在
                flag = true;
                break;
            }
            temp = temp.next; //move forward. 遍历当前列表
        }
        // check the flag. 判断flag的值
        if (flag == true){
            System.out.printf("the no of hero %d has been exist\n",heroNode.no);
        }else {
            //add the new node into the after of temp. 插入到链表中，temp的后面
            heroNode.next = temp.next; //不能写 temp.next = heroNode。这样写就把链表断开了，heroNode的的后面链接的谁不知道
            temp.next = heroNode;
        }
    }

    // delete node
    //思路
    // 1.head节点不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
    // 2.我们在比较时，是temp.next.no和需要删除的节点的no比较
    public void del(int no){
        HeroNode temp = head;
        boolean flag = false; //标志是否找到待删除的节点
        while (true){
            if (temp.next == null){//已经到链表的最后
                break;
            }
            if (temp.next.no == no) { //找到待删除的节点
                flag = true;
                break;
            }
            temp = temp.next; //temp后移，遍历
        }
        //判断flag
        if (flag){ //找到
            //找到，可以删除
            temp.next = temp.next.next;
        }else {
            System.out.println("the node %d that need to be deleted doesn't exist");
        }
    }


    // 修改 change the information of node according to the no, ie. no cann't be changed
    //说明
    // 1. 根据newHeroNode的no来就该即可
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("the linkedlist is null");
            return;
        }
        //找到需要修改的节点，根据no编号
        // 先定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false; //表示是否找到该节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.no == newHeroNode.no) { //找到
                flag = true;
                break;
            }
            temp = temp.next; //遍历链表
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else { //没有找到
            System.out.printf("didn't find the no %d node\n", newHeroNode.no);
        }
    }



    // show the linkedlist
    public void list(){
        //check the linkedlist for null or not?
        if (head.next==null){
            System.out.println("linkedlist is null");
            return; //如果这里没有return会怎么样？
        }
        //因为头节点不能动，所以我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true){
            // check if it's the end
            if (temp == null){
                break;
            }
            System.out.println(temp);
            // 将temp后移 move forward the temp ，否则就是个死循环了
            temp = temp.next;
        }

    }
}





//step 1: define HeroNode, each HeroNode object is a node
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //next指向下一个节点，所以next的类型是HeroNode

    //构造器 constructor
    public HeroNode(int no, String name, String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //override toString in order to show method
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\''  +
                '}';
    }
}



