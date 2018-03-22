package logika;


public class PrikazKonec implements IPrikaz {

    private static final String NAZEV = "konec";

    private Hra hra;


    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }


    @Override
    public String proved(String... parametry) {
        if (parametry.length > 1) {
            return "Ukončit co? Nechápu, proč jste zadal druhé slovo.";
        }
        else {
            hra.setKonecHry(true);
            return "hra ukončena příkazem konec";
        }
    }


    @Override
    public String getNazev() {
        return NAZEV;
    }
}
