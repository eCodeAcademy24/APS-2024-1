package Spoi_List_Naizmenichno;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ; // i++
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp + "->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + "->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadeniot jazol e null");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            if (first == before) {
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            SLLNode<E> tmp = first;
            while (tmp.succ != before)
                tmp = tmp.succ;

            if (tmp.succ == before) {
                SLLNode<E> ins = new SLLNode<E>(o, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            if (first == node) {
                return this.deleteFirst();
            }

            SLLNode<E> tmp = first;
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;

            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.element != o && tmp.succ != null)
                tmp = tmp.succ;

            if (tmp.element == o) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return first;
    }

    public Iterator<E> iterator() {
        // Return an iterator that visits all elements of this list, in left-to-right order.
        return new LRIterator<E>();
    }


    // //////////Inner class ////////////

    private class LRIterator<E> implements Iterator<E> {

        private SLLNode<E> place, curr;

        private LRIterator() {
            place = (SLLNode<E>) first;
            curr = null;
        }

        public boolean hasNext() {
            return (place != null);
        }

        public E next() {
            if (place == null)
                throw new NoSuchElementException();
            E nextElem = place.element;
            curr = place;
            place = place.succ;
            return nextElem;
        }

        public void remove() {
            //Not implemented
        }
    }

    public void mirror() {
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while (tmp != null) {
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }

    }

    public void merge(SLL<E> in) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = in.getFirst();
        } else {
            first = in.getFirst();
        }
    }
}

//Дадени се две еднострано поврзани листи чии што јазли содржат по еден природен број.
// Треба да се спојат двете листи во една резултантна на тој начин што наизменично прво ќе се додаваат првите два јазли
// од првата листа во резултантната, па првите два од втората листа, па следните два од првата, па следните два од втората итн.
// Јазлите што ќе останат треба да се додадат на крај во резултантната листа, прво оние што останале од првата листа,
// потоа оние што останале од втората листа.
// Во првиот ред од влезот се дадени броевите од кои се составени јазлите по редослед во првата листа,
// а во вториот ред броевите од кои се составени јазлите по редослед во втората листа. На излез треба да се испечатат
// јазлите по редослед во резултантната споена листа.


// 4 --> n
// 1 2 3 4 --> lista1
// 5 --> m
// 1 2 3 4 5 --> lista2
public class SpecialSLLJoin {

    //TODO: implement function
    private static void spoi_list_naizmenichno(SLL<Integer> lista1, SLL<Integer> lista2, SLL<Integer> lista3) {
        SLLNode<Integer> tmp1 = lista1.getFirst();
        SLLNode<Integer> tmp2 = lista2.getFirst();

        while (tmp1 != null && tmp1.succ != null && tmp2 != null && tmp2.succ != null) {
            lista3.insertLast(tmp1.element);
            lista3.insertLast(tmp1.succ.element);

            lista3.insertLast(tmp2.element);
            lista3.insertLast(tmp2.succ.element);
            tmp1 = tmp1.succ.succ;
            tmp2 = tmp2.succ.succ;
        }

        while (tmp1 != null) {
            lista3.insertLast(tmp1.element);
            tmp1 = tmp1.succ;
        }

        while (tmp2 != null) {
            lista3.insertLast(tmp2.element);
            tmp2 = tmp2.succ;
        }

        System.out.println(lista3);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        SLL<Integer> lista1 = new SLL<>();

        for (int i = 0; i < n; i++) {
            int element = sc.nextInt();
            lista1.insertLast(element);
        }

        int m = sc.nextInt();

        SLL<Integer> lista2 = new SLL<>();

        for (int i = 0; i < m; i++) {
            lista2.insertLast(sc.nextInt());
        }

        SLL<Integer> lista3 = new SLL<>();

        spoi_list_naizmenichno(lista1, lista2, lista3);


    }

}