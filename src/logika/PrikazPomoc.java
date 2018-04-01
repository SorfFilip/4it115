package logika;

/**
 *  Třída PrikazPomoc implementuje pro hru příkaz pomoc.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Filip Šorf
 *@version    pro školní rok 2015/2016
 *
 */
class PrikazPomoc implements IPrikaz {

    private static final String NAZEV = "pomoc";
    private SeznamPrikazu platnePrikazy;

    /**
     *  Konstruktor třídy
     *
     *  @param platnePrikazy seznam příkazů,
     *                       které je možné ve hře použít,
     *                       aby je nápověda mohla zobrazit uživateli.
     */
    public PrikazPomoc(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }

    /**
     *  Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     *  vcelku primitivní zpráva a seznam dostupných příkazů.
     *
     *  @return napoveda ke hre
     */
    @Override
    public String proved(String... parametry) {
        return "Tvým úkolem je - odevzdat úkoly a poté semestrální práci vyučijící Ing. Jarmile Pavlíčkové, Ph.D. do místnosti SB202. \n"
        + "\n"
        + "Můžeš zadat tyto příkazy:\n"
        + platnePrikazy.vratNazvyPrikazu();
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
