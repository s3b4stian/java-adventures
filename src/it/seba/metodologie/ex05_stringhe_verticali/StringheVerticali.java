package it.seba.metodologie.ex05_stringhe_verticali;

public class StringheVerticali {

    public static void stampaVerticale(String word1, String word2, String word3) {

        int l1 = word1.length();
        int l2 = word2.length();
        int l3 = word3.length();

        int maxLen = Math.max(l1, Math.max(l2, l3));

        for (int i = 0; i < maxLen; i++) {

            char c1 = ' ';
            char c2 = ' ';
            char c3 = ' ';

            if (i < l1) {
                c1 = word1.charAt(i);
            }
            if (i < l2) {
                c2 = word2.charAt(i);
            }
            if (i < l3) {
                c3 = word3.charAt(i);
            }

            System.out.print(c1);
            System.out.print(c2);
            System.out.print(c3);

            System.out.println();
        }
    }

    public static void main(String[] args) {
        StringheVerticali.stampaVerticale("ciao", "buondì", "hello");

    }

}
