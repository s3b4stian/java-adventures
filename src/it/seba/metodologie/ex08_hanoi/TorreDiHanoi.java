package it.seba.metodologie.ex08_hanoi;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Hanoi Towers.
 * 
 * @author Sebastian Rapetti
 *
 */
public class TorreDiHanoi {

    private int size;
    private Deque<Disco> t1;
    private Deque<Disco> t2;
    private Deque<Disco> t3;

    /**
     * Class Constructor.
     *
     * @param t1
     * @param t2
     * @param t3
     */
    public TorreDiHanoi(Deque<Disco> t1, Deque<Disco> t2, Deque<Disco> t3) {
        size = t1.size();
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
    }

    /**
     * Starts disk moving from a tower to another.
     */
    public void move() {
        printTowers();
        swapStack(size, t1, t2, t3);
    }

    /**
     * Reverse an array.
     * 
     * @param a array to reverse
     * @param n number of elements in array
     *
     * @return
     */
    private Disco[] reverse(Disco[] a, int n) {

        Disco[] b = new Disco[n];
        // more concise than previous code
        for (int i = 0, j = n; i < n; i++) b[--j] = a[i];

        return b;
    }

    /**
     * Print towers, the difficult part of the exercise.
     */
    private void printTowers() {

        // get the three towers and reverse content to print
        Disco[] lt1 = reverse(t1.toArray(new Disco[t1.size()]), t1.size());
        Disco[] lt2 = reverse(t2.toArray(new Disco[t2.size()]), t2.size());
        Disco[] lt3 = reverse(t3.toArray(new Disco[t3.size()]), t3.size());

        System.out.println();
        System.out.println();

        // array of strings
        // I need it to print in reverse order after
        String[] str = new String[size];

        for (int i = 0; i < size; i++) {

            // void place holder
            String v = "    |    ";
            String s1 = v;
            String s2 = v;
            String s3 = v;

            // check if there is a disk
            if (lt1.length > i)
                s1 = lt1[i].toString();
            if (lt2.length > i)
                s2 = lt2[i].toString();
            if (lt3.length > i)
                s3 = lt3[i].toString();

            // concatenate levels of the tower
            str[i] = s1 + " " + s2 + " " + s3;
        }

        for (int i = size - 1; i >= 0; i--) {
            System.out.println(str[i]);
        }

        System.out.println("-----------------------------");
        System.out.println("-----------------------------");
    }

    /**
     * Swap disks in towers. Problem already studied years ago.
     * https://github.com/s3b4stian/c-adventures/blob/master/DataStructuresC/recursion/recursion_hanoi.c
     * 
     * @param n
     * @param tr1
     * @param tr2
     * @param tr3
     */
    private void swapStack(int n, Deque<Disco> tr1, Deque<Disco> tr2, Deque<Disco> tr3) {

        if (n > 0) {
            // first recursive call, swap column 3 with column 2
            swapStack(n - 1, tr1, tr3, tr2);
            // moving disk to tower 1 to tower 3
            tr3.push(tr1.pop());
            printTowers();
            // second recursive call, swap column 2 with column 1
            swapStack(n - 1, tr2, tr1, tr3);
        }
    }

    public static void main(String[] args) {
        // three towers as Deques.
        // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayDeque.html
        Deque<Disco> towerOne = new ArrayDeque<Disco>();
        Deque<Disco> towerTwo = new ArrayDeque<Disco>();
        Deque<Disco> towerThree = new ArrayDeque<Disco>();

        // fill the first tower with disks, pushing first the larger disks
        for (int i = 5; i >= 1; i--) {
            towerOne.push(new Disco(i));
        }

        TorreDiHanoi t = new TorreDiHanoi(towerOne, towerTwo, towerThree);
        t.move();
    }
}
