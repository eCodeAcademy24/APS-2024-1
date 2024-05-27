package Spoi_Listi;

import java.util.Scanner;

class SLLNode {
    int id;
    int age;
    SLLNode succ;

    public SLLNode(int id, int age, SLLNode succ) {
        this.id = id;
        this.age = age;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

class SLL {
    SLLNode first;

    public SLL() {
        this.first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;
    }

    public void deleteFirst() {
        if (first != null) {
            SLLNode tmp = first;
            first = first.succ;
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void delete(SLLNode node) {
        if (first != null) {
            SLLNode tmp = first;
            if (first == node) {
                this.deleteFirst();
                return;
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }

    }

    public void insertFirst(int id, int age) {
        SLLNode ins = new SLLNode(id, age, first);
        first = ins;
    }

    public void insertAfter(int id, int age, SLLNode node) {
        if (node != null) {
            SLLNode ins = new SLLNode(id, age, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(int id, int age, SLLNode before) {

        if (first != null) {
            SLLNode tmp = first;
            if (first == before) {
                this.insertFirst(id, age);
                return;
            }
            //ako first!=before
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode ins = new SLLNode(id, age, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }


    public void insertLast(int id, int age) {
        if (first != null) {
            SLLNode tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode ins = new SLLNode(id, age, null);
            tmp.succ = ins;
        } else {
            insertFirst(id, age);
        }
    }

    public SLLNode getFirst() {
        return first;
    }

    @Override
    public String toString() {
        String s = new String();
        SLLNode dvizi = first;
        while (dvizi != null) {
            s = s + dvizi.id + " ";
            dvizi = dvizi.succ;
        }
        return s;
    }
}


//Дадени се две еднострано поврзани листи чии јазли содржат по еден природен број. Листите се сортирани во растечки редослед.
// Треба да се спојат двете листи во една така што резултантната листа да е сортирана. Сортирањето е подредување со слевање.
// Јазлите кои се јавуваат како дупликати (од иста листа или од различна) да се отстранат.
// Во првиот ред од влезот е даден бројот на јазли во првата листа, потоа во вториот ред се дадени броевите од кои
// се составени јазлите по редослед во првата листа, па во третиот ред е даден бројот на јазли во втората листа,
// и на крај во четвртиот ред броевите од кои се составени јазлите по редослед во втората листа. На излез треба
// да се испечатат јазлите по редослед во резултантната споена листа. Име на класата: SLLJoinLists

//TODO: make test cases

public class SLLJoinLists {

    //TODO: implement function

    public static void main(String[] args) {
        //TODO: main
    }
}
