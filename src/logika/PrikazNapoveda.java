package logika;


class PrikazNapoveda implements IPrikaz {

    private static final String NAZEV = "napoveda";
    private SeznamPrikazu platnePrikazy;


    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }


    @Override
    public String proved(String... parametry) {
        return "Tvým úkolem je - odevzdat úkoly a poté semestrální práci vyučijící Ing. Jarmile Pavlíčkové, Ph.D. do místnosti SB202. \n"
        + "\n"
        +"\n"
        + "Můžeš zadat tyto příkazy:\n"
        + platnePrikazy.vratNazvyPrikazu();
    }


    @Override
    public String getNazev() {
        return NAZEV;
    }

}
