//Пред командантот на батаљонот наредени се сите војници и во двојно поврзана листа дадени се нивните ID-a. На командантот не му се допаѓа како се наредени војниците и решава да одбере еден под-интервал од војници и истиот да го преврти.
//
//        Влез: Во првиот ред даден е бројот на војници. Во вториот ред дадени се ID-то на секој од војниците. Во третиот ред дадени се два броеви, ID на првиот војник и ID на последниот војник од интервалот.
//
//        Излез: Да се испечати новиот редослед на војниците (т.е. на нивните ID-a), од почеток на листата до крај, и обратно.
//
//        Забелешка 1: Интервалот, како и целата листа, ќе содржи барем два војници.
//
//        Забелешка 2: Обратете посебно внимание кога интервалoт почнува од првиот војник или завршува со последниот војник.
//
//        Внимавајте:
//
//        1. Даден е целосниот код на структурата којашто треба да се користи. Дадена е и тест класата DLLBattalion.java, со целосно имплементиран input и output. Потребно е да се менува само во рамки на void battalion(DLL<Integer> list, int a, int b) функцијата.
//
//        2. Притоа, поместувањето на интервалите треба да се однесува на менувањето на самите врски во јазлите од листата.
//
//        3. Не смее да менувате во main функцијата !

package Lab3.Task3;

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
        } else
            return null;
    }

    public E delete(DLLNode<E> node) {
        if (node == first) {
            return deleteFirst();
        }
        if (node == last) {
            return deleteLast();
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int getSize() {
        int listSize = 0;
        DLLNode<E> tmp = first;
        while(tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp.toString();
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp.toString();
            while (tmp.pred != null) {
                tmp = tmp.pred;
                ret += "<->" + tmp.toString();
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

    public void setFirst(DLLNode<E> node) {
        this.first = node;
    }

    public void setLast(DLLNode<E> node) {
        this.last = node;
    }

    public void mirror() {

        DLLNode<E> tmp = null;
        DLLNode<E> current = first;
        last = first;
        while(current!=null) {
            tmp = current.pred;
            current.pred = current.succ;
            current.succ = tmp;
            current = current.pred;
        }

        if(tmp!=null && tmp.pred!=null) {
            first=tmp.pred;
        }
    }
}

public class DLLBattalion {

    //TODO: implement function
    public static void battalion(DLL<Integer> list, int a, int b) {

        DLLNode<Integer> sublistFirst = list.getFirst();
        DLLNode<Integer> sublistLast = list.getFirst();
        DLLNode<Integer> tmp = null;
        int counter = 0;

        while (sublistFirst != null){
            if(sublistFirst.element.equals(a)){
                break;
            }
            sublistFirst = sublistFirst.succ;
        }

        sublistLast = sublistFirst;

        while (sublistLast !=null){
            if(sublistLast.element.equals(b)){
                break;
            }
            sublistLast = sublistLast.succ;
        }
        tmp = sublistFirst;

        while (tmp != sublistLast) {
            if(tmp.succ == null ){
                counter++;
                break;
            }
            counter++;
            tmp = tmp.succ;
        }

        for(int i = 0; i< counter; i++){
            list.insertBefore(sublistLast.element, sublistFirst);
            list.delete(sublistLast);
            sublistLast = sublistLast.pred;
        }


    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        DLL<Integer> list = new DLL<>();
        for(int i=0;i<n;i++) {
            list.insertLast(input.nextInt());
        }

        int a = input.nextInt();
        int b = input.nextInt();

        battalion(list, a, b);

        System.out.println(list);
        System.out.println(list.toStringR());


    }
}