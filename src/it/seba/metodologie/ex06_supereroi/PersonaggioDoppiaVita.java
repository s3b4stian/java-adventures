package it.seba.metodologie.ex06_supereroi;

public class PersonaggioDoppiaVita extends AbstractPersonaggio implements DoppiaVita {

    private String superHeroName;
    private int attackPoint;

    private class SecretIdentity extends PersonaggioSupereroe implements DoppiaVita {

        private PersonaggioDoppiaVita publicIdentityReference;

        public SecretIdentity(PersonaggioDoppiaVita personaggioPubblico, String nomeIdentitaSegreta, int puntiVita,
                int puntiAttacco) {
            super(nomeIdentitaSegreta, puntiVita, puntiAttacco);
            publicIdentityReference = personaggioPubblico;
        }

        @Override
        public Supereroe assumiIdentitaSegreta() {
            // already superhero
            // it could throw an exception
            return null;
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
        return new SecretIdentity(this, superHeroName, life, attackPoint);
    }

    @Override
    public DoppiaVita assumiIdentitaPubblica() {
        // already public
        // it could throw an exception
        return null;
    }

    @Override
    public void subisciAttacco(int puntiVitaInMeno) {
        life -= life + puntiVitaInMeno;
    }
}
