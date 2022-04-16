package it.seba.metodologie.ex06_supereroi;

public class PersonaggioSupereroe extends AbstractPersonaggio implements Supereroe {

    private int attackPoint;

    public PersonaggioSupereroe(String nomeIdentitaPubblico, int puntiVita, int puntiAttacco) {
        super(nomeIdentitaPubblico, puntiVita, false);
        attackPoint = puntiAttacco;
    }

    @Override
    public int attacca() {
        return attackPoint;
    }

    @Override
    public void subisciAttacco(int puntiInMeno) {
        life -= puntiInMeno;
    }
}
