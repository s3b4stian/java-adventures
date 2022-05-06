package it.seba.metodologie.ex06_supereroi;

public class PersonaggioDoppiaVita extends AbstractPersonaggio implements DoppiaVita {

    private String superHeroName;
    private int attackPoint;

    private class IdentitaSegreta extends PersonaggioSupereroe implements DoppiaVita {

        private PersonaggioDoppiaVita publicIdentityReference;

        public IdentitaSegreta(PersonaggioDoppiaVita personaggioPubblico, String nomeIdentitaSegreta, int puntiVita,
                int puntiAttacco) {
            super(nomeIdentitaSegreta, puntiVita, puntiAttacco);
            publicIdentityReference = personaggioPubblico;
        }

        @Override
        public Supereroe assumiIdentitaSegreta() {
            // already superhero
            // it could throw an exception
            return this;
        }

        @Override
        public DoppiaVita assumiIdentitaPubblica() {
            return publicIdentityReference;
        }
    }

    public PersonaggioDoppiaVita(String nomeIdentitaPubblico, String nomeIdentitaSegreta, int puntiVita,
            int puntiAttacco) {
        super(nomeIdentitaPubblico, puntiVita, true);
        superHeroName = nomeIdentitaSegreta;
        attackPoint = puntiAttacco;
    }

    @Override
    public Supereroe assumiIdentitaSegreta() {
        return new IdentitaSegreta(this, superHeroName, life, attackPoint);
    }

    @Override
    public DoppiaVita assumiIdentitaPubblica() {
        // already public
        // it could throw an exception
        return this;
    }

    @Override
    public void subisciAttacco(int puntiVitaInMeno) {
        life -= life + puntiVitaInMeno;
    }
}
