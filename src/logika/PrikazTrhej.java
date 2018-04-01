
package logika;

/**
 *  Třída PrikazTrhej implementuje pro hru příkaz trhej.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Filip Šorf
 *@version    pro školní rok 2015/2016
 */
public class PrikazTrhej implements IPrikaz{

    private static final String NAZEV = "trhej";
    private HerniPlan plan;
    private Ja batoh;
    private Vec veci;  
    private Vec pomocna;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se budou ve hře trhat věci
     */
    public PrikazTrhej(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "trhej". Zkouší se utrhnout jakoukoliv věc, lze trhat pouze "jablka" pokud má hráč u sebe věc "ošatka".
     *  Pokud se hráč pokouší trhat jinde, než na louce, nebo bez věci "ošatka", vypíše se chybové hlášení.
     *
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        if (plan.jaInformace().mamOsatku()==null) {
            return "Nemáš ošatku, nemůžeš trhat."; }

        if(plan.getAktualniProstor().getNazev()!="louka") {
            return "Zde není nic na trhání"; }
        else {

            plan.jaInformace().uplatek(plan.getAktualniProstor().odeberVec("jablka"));
            plan.notifyController();
            return "Natrhal si jablka a získal tím úplatek pr uklízečku!";

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
