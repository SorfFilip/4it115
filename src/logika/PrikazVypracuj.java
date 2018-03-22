
package logika;


public class PrikazVypracuj implements IPrikaz{

    private static final String NAZEV = "vypracuj";
    private HerniPlan plan;


    public PrikazVypracuj(HerniPlan plan) {
        this.plan = plan;
    }


    @Override
    public String proved(String... parametry) {
        if(plan.getAktualniProstor().getNazev()!="domov") {
            return "Zde nelze nic vypracovat (vypracovávat můžeš pouze doma)."; }
        else {

            Vec ukoly = new Vec("ukoly");
            plan.getAktualniProstor().vlozVec(ukoly);    
            Vec semestralka = new Vec("semestralka");
            plan.getAktualniProstor().vlozVec(semestralka);
            return "Vypracoval si úkoly a semestrálka";

                   
                
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }

    
}
