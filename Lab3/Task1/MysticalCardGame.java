//You are given a single linked list with integer nodes. Additionally, you are given one more integer M (M>1). You need to delete every M-th element from the list.
//
//        Input: In the first line from the input the number of elements in the list is given, and then in the next line the elements themselves. In the last line, the integer M is given.
//
//        Output: The transformed list (with every M-th element deleted) should be printed at the output.
//
//         All the needed code for the structure that you need to use is given. The test class SpecialSLLDelete.java is also given, with completely implemented input and output. You only need to change the code of the void specialDelete(SLL<Integer> list, int m) method.
//         You must not change the main method

package Lab3.Task1;

import java.util.Scanner;

class Card {
    private int type;
    private int health;
    private int magicPower;

    public Card(int type, int health, int magicPower) {
        this.type = type;
        this.health = health;
        this.magicPower = magicPower;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMagicPower() {
        return magicPower;
    }

    public void setMagicPower(int magicPower) {
        this.magicPower = magicPower;
    }

    public int significance() {
        return health * magicPower;
    }


    @Override
    public String toString() {
        return String.valueOf(type);
    }
}

class SLLNode1<E> {
    protected E element;
    protected SLLNode1<E> succ;

    public SLLNode1(E elem, SLLNode1<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class SLL1<E> {
    private SLLNode1<E> first;

    public SLL1() {
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int ret;
        if (first != null) {
            SLLNode1<E> tmp = first;
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
            SLLNode1<E> tmp = first;
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
        SLLNode1<E> ins = new SLLNode1<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode1<E> node) {
        if (node != null) {
            SLLNode1<E> ins = new SLLNode1<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(E o, SLLNode1<E> before) {
        if (first != null) {
            SLLNode1<E> tmp = first;
            if (first == before) {
                this.insertFirst(o);
                return;
            }
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode1<E> ins = new SLLNode1<E>(o, before);
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
            SLLNode1<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode1<E> ins = new SLLNode1<E>(o, null);
            tmp.succ = ins;
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode1<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode1<E> node) {
        if (first != null) {
            SLLNode1<E> tmp = first;
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

    public SLLNode1<E> getFirst() {
        return first;
    }

    public SLLNode1<E> find(E o) {
        if (first != null) {
            SLLNode1<E> tmp = first;
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

public class MysticalCardGame {

    //TODO: implement function
    public static void startDuel(SLL1<Card> firstSorcererCards, SLL1<Card> secondSorcererCards) {

        SLLNode1<Card> Volsebnik1 = firstSorcererCards.getFirst();
        SLLNode1<Card> Najznacajna1 = null;

        int max = 0;

        while (Volsebnik1 !=null)
        {
            int importance = Volsebnik1.element.getHealth()*Volsebnik1.element.getMagicPower();

            if(importance>max)
            {
                max = importance;
                Najznacajna1 = Volsebnik1;
            }

            Volsebnik1=Volsebnik1.succ;
        }

        firstSorcererCards.delete(Najznacajna1);

        int size = secondSorcererCards.size();
        int middle = size/2;

        SLLNode1<Card> Volsebnik2 = secondSorcererCards.getFirst();

        for(int i = 0; i<middle;i++)
        {
            Volsebnik2=Volsebnik2.succ;
        }

        secondSorcererCards.insertBefore(Najznacajna1.element,Volsebnik2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SLL1<Card> firstSorcererCards = new SLL1<Card>();
        SLL1<Card> secondSorcererCards = new SLL1<Card>();

        for (int i = 0; i < 8; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            firstSorcererCards.insertLast(new Card(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        for (int i = 0; i < 8; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            secondSorcererCards.insertLast(new Card(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        startDuel(firstSorcererCards, secondSorcererCards);
        System.out.println(firstSorcererCards);
        System.out.println(secondSorcererCards);
    }
}