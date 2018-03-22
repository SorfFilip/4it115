package logika;


class PrikazPomoc implements IPrikaz {

    private static final String NAZEV = "pomoc";
    private SeznamPrikazu platnePrikazy;


    public PrikazPomoc(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }


    @Override
    public String proved(String... parametry) {
        return "Tvým úkolem je - odevzdat úkoly a poté semestrální práci vyučijící Ing. Jarmile Pavlíčkové, Ph.D. do místnosti SB202. \n"
        + "\n"
        + "Můžeš zadat tyto příkazy:\n"
        + platnePrikazy.vratNazvyPrikazu();
    }


    @Override
    public String getNazev() {
        return NAZEV;
    }

}
