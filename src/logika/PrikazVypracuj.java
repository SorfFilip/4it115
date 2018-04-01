
package logika;

/**
 *  Třída PrikazVypracuj implementuje pro hru příkaz vypracuj.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Filip Šorf
 *@version    pro školní rok 2015/2016
 */
public class PrikazVypracuj implements IPrikaz{

    private static final String NAZEV = "vypracuj";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se budou ve hře vypracovávat věci
     */
    public PrikazVypracuj(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "vypracuj". Zkouší se vypracovat požadovanou věc, lze vypracovat pouze "ukoly", nebo "semestralka".
     *  Pokud se hráč pokouší vypracovávat jinde, než v prostoru "domov", vypíše se chybové hlášení.
     *
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        if(plan.getAktualniProstor().getNazev()!="domov") {
            return "Zde nelze nic vypracovat (vypracovávat můžeš pouze doma)."; }
        else {

            Vec ukoly = new Vec("ukoly");
            plan.getAktualniProstor().vlozVec(ukoly);    
            Vec semestralka = new Vec("semestralka");
            plan.getAktualniProstor().vlozVec(semestralka);
            plan.notifyController();
            return "Vypracoval si úkoly a semestrálka";

                   
                
        }
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
