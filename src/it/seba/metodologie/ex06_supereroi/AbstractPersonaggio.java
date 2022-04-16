package it.seba.metodologie.ex06_supereroi;

public abstract class AbstractPersonaggio {
    protected String name;
    protected int life;
    protected boolean hasPublicLife;

    public AbstractPersonaggio(String nome, int puntiVita, boolean haDoppiaVita) {
        name = nome;
        life = puntiVita;
        hasPublicLife = haDoppiaVita;
    }

    public String getNome() {
        return name;
    }

    public int getVita() {
        return life;
    }

    public boolean hasDoppiaVita() {
        return hasPublicLife;
    }

    public boolean sconfitto() {
        if (life <= 0) {
            return true;
        }
        return false;
    }

    public abstract void subisciAttacco(int puntiVitaInMeno);
}
