package Spoi_Listi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

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

//Дадени се две еднострано поврзани листи чии јазли содржат по еден природен број. Листите се сортирани во растечки редослед.
// Треба да се спојат двете листи во една така што резултантната листа да е сортирана. Сортирањето е подредување со слевање.
// Јазлите кои се јавуваат како дупликати (од иста листа или од различна) да се отстранат.
// Во првиот ред од влезот е даден бројот на јазли во првата листа, потоа во вториот ред се дадени броевите од кои
// се составени јазлите по редослед во првата листа, па во третиот ред е даден бројот на јазли во втората листа,
// и на крај во четвртиот ред броевите од кои се составени јазлите по редослед во втората листа. На излез треба
// да се испечатат јазлите по редослед во резултантната споена листа. Име на класата: SLLJoinLists


public class SLLJoinLists {

    private static void spoi_listi(SLL<Integer> lista1, SLL<Integer> lista2, SLL<Integer> lista3) {
        SLLNode<Integer> tmp1 = lista1.getFirst();
        SLLNode<Integer> tmp2 = lista2.getFirst();

        while (tmp1 != null && tmp2 != null) {
            if (tmp1.element <= tmp2.element) {
                lista3.insertLast(tmp1.element);
                tmp1 = tmp1.succ;
            } else {
                lista3.insertLast(tmp2.element);
                tmp2 = tmp2.succ;
            }
        }

        while (tmp1 != null) {
            lista3.insertLast(tmp1.element);
            tmp1 = tmp1.succ;
        }

        while (tmp2 != null) {
            lista3.insertLast(tmp2.element);
            tmp2 = tmp2.succ;
        }

        SLLNode<Integer> tmp3 = lista3.getFirst();

        while (tmp3 != null && tmp3.succ != null) {
            if (tmp3.element == tmp3.succ.element) {
                lista3.delete(tmp3);
            }

            tmp3 = tmp3.succ;
        }
        System.out.println(lista3);
    }

    public static void main(String[] args) throws IOException {
        //TODO: main
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int n = Integer.parseInt(s);

        SLL<Integer> lista1 = new SLL<>();

        s = br.readLine();
        String[] parts = s.split(" ");

        for (int i = 0; i < n; i++) {
            lista1.insertLast(Integer.parseInt(parts[i]));
        }

        s = br.readLine();
        int m = Integer.parseInt(s);

        SLL<Integer> lista2 = new SLL<>();

        s = br.readLine();
        parts = s.split(" ");

        for (int i = 0; i < m; i++) {
            lista2.insertLast(Integer.parseInt(parts[i]));
        }

        SLL<Integer> lista3 = new SLL<>();

        spoi_listi(lista1, lista2, lista3);
    }


}
