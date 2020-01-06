package com.mingful.www.datastructure.hashtable;
/**
 * @author fmf
 * @version 1.0
 * @className LinkedHashTableTester
 * @description 用数组 + 链表表示LinkedHashTable
 * @create 2019-12-27 16:47
 **/
public class LinkedHashTableTester {

    public static void main(String[] args) {

        User user1 = new User(1, "fmf");
        User user2 = new User(2, "zxh");
        User user3 = new User(3, "zgl");
        User user4 = new User(6, "ll");

        UserLinkedHashTable userLinkedHashTable = new UserLinkedHashTable(5);
        userLinkedHashTable.add(user1);
        userLinkedHashTable.list();
        System.out.println("=======================================");
        userLinkedHashTable.add(user2);
        userLinkedHashTable.add(user3);
        userLinkedHashTable.list();
        System.out.println("=======================================");
        userLinkedHashTable.add(user4);
        userLinkedHashTable.list();

    }
}
class User {
    private int id;
    private String name;
    private User next;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getNext() {
        return next;
    }

    public void setNext(User next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class UserLinkedList {

    /**
     * 链表头指针
     */
    User head;

    public void add(User user) {
        if (head == null) {
            head = user;
            return;
        }
        User temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(user);
    }

    public void list() {
        if (head == null) {
            System.out.println("该链表为空!");
            return;
        }
        User temp = head;
        while (temp != null) {
            System.out.print("->" + temp);
            temp = temp.getNext();
        }
        System.out.println();
    }
}

class UserLinkedHashTable {


    /**
     * 链表对象数组
     */
    private UserLinkedList[] userLinkedLists;
    /**
     * HashTable大小
     */
    private int size;

    public UserLinkedHashTable(int size) {
        this.size = size;
        userLinkedLists = new UserLinkedList[size];
        for (int i = 0; i < userLinkedLists.length; i++) {
            userLinkedLists[i] = new UserLinkedList();
        }
    }

    public void add(User user) {
        int hashCode = hashCode(user.getId());
        userLinkedLists[hashCode].add(user);
    }

    public void list() {
        for (UserLinkedList userLinkedList : userLinkedLists) {
            userLinkedList.list();
        }
    }

    /**
     * 获取hash值
     * @param id
     * @return
     */
    private int hashCode(int id) {
        return id % size;
    }
}
