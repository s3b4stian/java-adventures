package it.seba.metodologie.ex04_stampa_vocali;

public class StampaVocali {

    private static int[] conta(String s) {

        int[] counter = new int[5];
        int len = s.length();

        for (int i = 0; i < len; i++) {
            switch (s.charAt(i)) {
            case 'a':
                counter[0] += 1;
                break;
            case 'e':
                counter[1] += 1;
                break;
            case 'i':
                counter[2] += 1;
                break;
            case 'o':
                counter[3] += 1;
                break;
            case 'u':
                counter[4] += 1;
                break;
            }
        }

        return counter;
    }

    public static String getPrintString(String s) {
        int[] c = conta(s);

        return "a=" + c[0] + " e=" + c[1] + " i=" + c[2] + " o=" + c[3] + " u=" + c[4];
    }

    public static void main(String[] args) {
        System.out.println(StampaVocali.getPrintString("le aiuole sono pulite"));
    }
}
