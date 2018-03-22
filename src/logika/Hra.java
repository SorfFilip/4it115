package logika;



public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;

    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazVypracuj(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazTrhej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazOdevzdej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPomoc(platnePrikazy));
    }


    public String vratUvitani() {
        return "Vítejte!\n" +
        "Toto je zjednodušená simulace studentského života.\n" +
        "Napište 'napoveda', pokud si nevíte rady, jak hrát dál.\n" +
        "\n" +
        herniPlan.getAktualniProstor().dlouhyPopis();
    }

    public String vratEpilog() {
        return "Děkuji za hru";
    }

    public boolean konecHry() {
        return konecHry;
    }

    public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
            if ((herniPlan.jeVyhra()) && (herniPlan.odevzdaneSemestralku()==1)){
                konecHry = true;
                textKVypsani += "\n" + "Semestrální práce úspěšně odevzdána, vyhráváš!";
            }

        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        return textKVypsani;
    }

    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }

    public HerniPlan getHerniPlan(){
        return herniPlan;
    }

    
}

