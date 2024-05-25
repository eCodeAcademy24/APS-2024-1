package Athletics;

import java.util.Scanner;

class Athlete {
    private int id;
    private float time;

    public Athlete(int id, float time) {
        this.id = id;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public float getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

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
                tmp = tmp.succ;
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
            ret += tmp;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += " " + tmp;
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
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == before) {
                this.insertFirst(o);
                return;
            }
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
            SLLNode<E> tmp = first;
            if (first == node) {
                return this.deleteFirst();
            }
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
}

//        Податоците за атлетичарите кои учествувале на трките на 100т се чуваат во две еднострано поврзани листи.
//
//        Во првата листа се чуваат податоците за атлетичарите кои победиле на трките во изминатите години
//        (не вклучувајќи тековната година),
//        а во втората листа се чуваат податоците за атлетичарите кои се натпреварувале во тековната година.

//        И кај двете листи, во секоj од јазлите се чуваат број за идентификациjа (id) и време (во секунди како децимален број)
//        за кое атлетичарот jа завршил трката.

//        Потребно е да се изберат атлетичари, за тековната година, кои ке продолжат на следната Олимпијадата.

//        За таа цел, потребно е од листата на атлетичари за тековната година да се отстранат (избришат) сите атлетичари кои
//        имаат поголемо/полошо време од максималното (најлошото) време кое го остварил некоj од победниците во изминатите години.
//        Влез:
//        Во првиот ред е даден броjот на победници од изминатите години.
//        Во вториот ред е даден броjот на атлетичари од тековната година.
//        Во следните редови се дадени паровите податоци за секоj атлетичар, одделени со празно место, во формат id време.

//        Излез:
//        Во еден ред іd на сите атлетичари кои ке продолжат на Олимпијадата.
//        Забелешка: Даден е целосниот код на структурата којашто треба да се користи. Дадена е и тест класата Race.java,
//        со целосно имплементиран Input и output. Потребно е да се менува само во рамки на void competition (SLL <Athlete> prevWinners, SLL <Athlete>
//        currYearRunners) функцијата. Притоа, бришењето треба да биде имплементирано како бришенье на цел јазел!

// Input:
// 4
// 4
// 1 0.50 --> Athlete
// 2 3.40
// 3 5.59
// 4 3.29
// 5 1.00
// 6 9.50
// 7 10.00
// 8 1.55

public class Race {
    // todo: complete function
    public static void competition(SLL<Athlete> prevWinners, SLL<Athlete> currYearRunners) {
        // 1. da go najdeme atleticarot so najlosho vreme od listata prevWinners
        SLLNode<Athlete> prevWinnerIterator = prevWinners.getFirst();
        SLLNode<Athlete> currYearWinnerIterator = currYearRunners.getFirst();

        float worstTime = prevWinnerIterator.element.getTime();
        prevWinnerIterator = prevWinnerIterator.succ;

        while (prevWinnerIterator != null) {
            if (prevWinnerIterator.element.getTime() > worstTime) {
                worstTime = prevWinnerIterator.element.getTime();
            }

            prevWinnerIterator = prevWinnerIterator.succ;
        }

        // 2. da gi sporeduvame vreminjata na site atleticari od currYearRunners i ako ima polosho vreme od maks da go izbrisheme
        while (currYearWinnerIterator != null) {
            if (currYearWinnerIterator.element.getTime() > worstTime) {
                currYearRunners.delete(currYearWinnerIterator);
            }

            currYearWinnerIterator = currYearWinnerIterator.succ;
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num1 = Integer.parseInt(scanner.nextLine());
        int num2 = Integer.parseInt(scanner.nextLine());

        SLL<Athlete> olimpijada = new SLL<Athlete>();
        SLL<Athlete> tekovna = new SLL<Athlete>();

        for (int i = 0; i < num1; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            Athlete athlete = new Athlete(Integer.parseInt(parts[0]), Float.parseFloat(parts[1]));
            olimpijada.insertLast(athlete);
        }

        for (int i = 0; i < num2; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            tekovna.insertLast(new Athlete(Integer.parseInt(parts[0]), Float.parseFloat(parts[1])));
        }

        competition(olimpijada, tekovna);
        System.out.println(tekovna);
    }
}