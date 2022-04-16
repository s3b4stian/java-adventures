package it.seba.metodologie.ex06_supereroi;

import java.util.ArrayList;
import java.util.List;

public class Campo {

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void disputaRound(PersonaggioSupereroe s1, PersonaggioSupereroe s2) {
        System.out.println();
        System.out.println("  " + s1.getNome() + " attacca " + s2.getNome());
        // first attack
        s2.subisciAttacco(s1.attacca());

        System.out.println("  " + s2.getNome() + " subisce attacco da " + s1.attacca() + " punti vita, vita rimanente: "
                + s2.getVita());

        // check if s2 is still alive
        if (!s2.sconfitto()) {
            // s2 attack
            s1.subisciAttacco(s2.attacca());
            System.out.println();
            System.out.println("  " + s2.getNome() + " attacca " + s1.getNome());
            System.out.println("  " + s1.getNome() + " subisce attacco da " + s2.attacca()
                    + " punti vita, vita rimanente: " + s1.getVita());

            // check if s1 is still alive
            if (s1.sconfitto()) {
                System.out.println();
                System.out.println("  " + s2.getNome() + " ha sconfitto " + s1.getNome());
                System.out.println("  " + s2.getNome() + " vince l'incontro");
            }

        } else {
            System.out.println();
            System.out.println("  " + s1.getNome() + " ha sconfitto " + s2.getNome());
            System.out.println("  " + s1.getNome() + " vince l'incontro");
        }
    }

    public AbstractPersonaggio disputaMatch(AbstractPersonaggio p1, AbstractPersonaggio p2) {

        // not black magic
        // cast from AbstracPersonaggio to Supereroe passing from assumiIdentitaSegreta
        // if the hero has DoppiaVita
        PersonaggioSupereroe supereroe1 = p1.hasDoppiaVita()
                ? (PersonaggioSupereroe) ((PersonaggioDoppiaVita) p1).assumiIdentitaSegreta()
                : (PersonaggioSupereroe) p1;
        PersonaggioSupereroe supereroe2 = p2.hasDoppiaVita()
                ? (PersonaggioSupereroe) ((PersonaggioDoppiaVita) p2).assumiIdentitaSegreta()
                : (PersonaggioSupereroe) p2;

        // true superhero1
        // false superhero2
        boolean whoAttackFirst = (boolean) ((Campo.getRandomNumber(0, 100) % 2) == 0);

        System.out.println("Match tra " + supereroe1.getNome() + " e " + supereroe2.getNome() + ":");

        do {
            if (whoAttackFirst) {
                disputaRound(supereroe1, supereroe2);
            } else {
                disputaRound(supereroe2, supereroe1);
            }

        } while (!supereroe1.sconfitto() && !supereroe2.sconfitto());

        // return the winner
        return supereroe1.sconfitto() ? p2 : p1;
    }

    public static void main(String[] args) {

        List<AbstractPersonaggio> teamDisney = new ArrayList<AbstractPersonaggio>();

        teamDisney.add(new PersonaggioDoppiaVita("Paperino", "Paperinik", 600, 400));
        teamDisney.add(new PersonaggioDoppiaVita("Ciccio", "Ciccionik", 800, 350));
        teamDisney.add(new PersonaggioDoppiaVita("Pippo", "SuperPippo", 700, 550));

        List<AbstractPersonaggio> teamMarvel = new ArrayList<AbstractPersonaggio>();

        teamMarvel.add(new PersonaggioDoppiaVita("Peter Parker", "Spiderman", 1200, 200));
        teamMarvel.add(new PersonaggioSupereroe("Magneto", 1000, 150));
        teamMarvel.add(new PersonaggioSupereroe("La Cosa", 2000, 300));

        System.out.println("Le squadre in campo!");
        System.out.println("Si affronteranno in un epico scontro all'ultimo sangue!");
        System.out.println("La squadra che per prima rimane senza Supereroi, perde!");
        System.out.println();

        System.out.println("Squadra Disney:");
        for (AbstractPersonaggio p : teamDisney) {
            System.out.println("  " + p.getNome());
        }

        System.out.println();

        System.out.println("Squadra Marvel:");
        for (AbstractPersonaggio p : teamMarvel) {
            System.out.println("  " + p.getNome());
        }

        System.out.println();
        System.out.println("Si comincia");

        Campo campoDiBattaglia = new Campo();

        do {
            System.out.println();

            // select a random fighter from the lists
            int fighterDisney = Campo.getRandomNumber(0, teamDisney.size());
            int fighterMarvel = Campo.getRandomNumber(0, teamMarvel.size());

            // System.out.println(fighterDisney + " " + fighterMarvel);
            // System.out.println(teamDisney.size() + " " + teamMarvel.size());

            // get the fighter
            AbstractPersonaggio f1 = teamDisney.get(fighterDisney);
            AbstractPersonaggio f2 = teamMarvel.get(fighterMarvel);

            // do match and get the winner
            AbstractPersonaggio winner = campoDiBattaglia.disputaMatch(f1, f2);

            // remove the loser from the list
            if (winner == f1) {
                teamMarvel.remove(fighterMarvel);
            } else {
                teamDisney.remove(fighterDisney);
            }

        } while (!teamDisney.isEmpty() && !teamMarvel.isEmpty());

        System.out.println();

        if (teamDisney.isEmpty()) {
            System.out.println("Squadra Marvel VINCE");
        } else {
            System.out.println("Squadra Disney VINCE");
        }
    }
}
