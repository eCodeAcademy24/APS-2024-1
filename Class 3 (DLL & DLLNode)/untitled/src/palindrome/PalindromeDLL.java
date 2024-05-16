package palindrome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return "<-" + element.toString() + "->";
    }
}

class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            DLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;
    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null) {
            last = ins;
        } else {
            first.pred = ins;
        }

        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        }
        // else throw Exception
        return null;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp + "<->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + "<->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {
        return last;
    }

}

//        Дадена е двојно поврзана листа со N јазли каде секој јазел содржи по еден карактер (буква).
//        Да се провери дали двојно поврзаната листа е палиндром: односно ако ја изминете од почеток до крај и од крај
//        до почеток, дали ќе добиете ист збор. Во првиот ред од влезот даден е бројот на јазли во листата N,
//        а во вториот ред се дадени броевите. На излез треба да се испечати 1 ако листата е палиндром, -1 ако не е.
//
//        For example:
//
//        Input
//        3
//        a n a
//        Result
//        1

//        Input
//        4
//        m a a m
//        Result
//        1

//        Input
//        5
//        1 2 3 1 2
//        Result
//        -1

public class PalindromeDLL {

    private static int palindrome(DLL<String> lista) {
        DLLNode<String> fromFirst = lista.getFirst();
        DLLNode<String> fromLast = lista.getLast();

        // Vo while-ot namesto fromFirst.pred != fromLast mozhe da se napishe fromFirst != fromLast.succ
        while (fromFirst != null && fromLast != null && fromFirst != fromLast && fromFirst.pred != fromLast) {
            if (!(fromFirst.element.equals(fromLast.element))) {
                return -1;
            }

            fromFirst = fromFirst.succ;
            fromLast = fromLast.pred;
        }

        return 1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();

        int n = Integer.parseInt(line);

        DLL<String> lista = new DLL<>();

        line = br.readLine();
        // "m a a m"

        String[] parts = line.split(" ");
        // parts = ["m", "a", "a", "m"]

        for (int i = 0; i < n; i++) {
            lista.insertLast(parts[i]);
        }

        int isPalindrome = palindrome(lista);
        System.out.println(isPalindrome);
    }


}