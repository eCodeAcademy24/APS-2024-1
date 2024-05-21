package zadaca2;

import java.util.Scanner;

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
        return element.toString();
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

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
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

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
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

    public E delete(DLLNode<E> node) {
        if (node == first) {
            deleteFirst();
            return node.element;
        }
        if (node == last) {
            deleteLast();
            return node.element;
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

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

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp + "<->";
            while (tmp.pred != null) {
                tmp = tmp.pred;
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

    public void izvadiDupliIPrebroj() {

    }
}

//Дадена е двојно поврзана листа. Да се напише функција која ќе создаде нова листа, во која ќе ги префрли јазлите од
// првата листа со инфо поле помало или еднакво од средната вредност на јаслите од првата листа. Не смеете да ги менувате само елементите во јазлите туку
// мора цели јазли. Новата листа мора постојано да биде сортирана.
//Влез:
//10
//3 14 5 6 2 77 33 4 66 100
//Излез:
//2 3 4 5 6 14

public class Zadaca2 {

    public static void bubbleSwap(DLL<Integer> lista) {
        DLLNode<Integer> first = lista.getFirst();

        while (first != null) {
            DLLNode<Integer> sleden = first.succ;
            while (sleden != null) {
                if (first.element > sleden.element) {
                    int temp = first.element;
                    first.element = sleden.element;
                    sleden.element = temp;
                }
                sleden = sleden.succ;
            }
            first = first.succ;
        }

    }

    public static void zadaca2(DLL<Integer> lista1, DLL<Integer> lista2) {
        DLLNode<Integer> tmp = lista1.getFirst();
        int sum = 0;

        while (tmp != null) {
            sum += tmp.element;
            tmp = tmp.succ;
        }

        int counter = lista1.length();
        float avg = (float) sum / counter;

        tmp = lista1.getFirst();
        while (tmp != null) {
            if (tmp.element > avg) {
                tmp = tmp.succ;
                continue;
            }

            lista2.insertLast(tmp.element);

//            bubbleSwap(lista2);
            // tret chekor postojano sortiranje
            DLLNode<Integer> from_first = lista2.getFirst();
            DLLNode<Integer> from_last = lista2.getLast();
            while (from_first != null && from_first != from_last) {
                if (from_first.element > from_last.element) {
                    lista2.insertBefore(from_last.element, from_first);
                    lista2.delete(from_last);
                    break;
                }

                from_first = from_first.succ;
            }

            tmp = tmp.succ;
        }


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //10
        //3 14 5 6 2 77 33 4 66 100

        int n = scanner.nextInt();

        DLL<Integer> lista = new DLL<Integer>();

        for (int i = 0; i < n; i++) {
            int broj = scanner.nextInt();
            lista.insertLast(broj);
        }

        DLL<Integer> lista1 = new DLL<>();

        zadaca2(lista, lista1);

        System.out.println(lista1);
    }
}