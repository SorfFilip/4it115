
package logika;

/**
 *  Třída PrikazSeber implementuje pro hru příkaz seber.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Jarmila Pavlickova, , Filip Šorf
 *@version    pro školní rok 2015/2016
 */
public class PrikazSeber implements IPrikaz{

    private static final String NAZEV = "seber";
    private HerniPlan plan;
    private Ja batoh;
    private Vec veci;  
    private Vec pomocna;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se budou ve hře sbírat věci
     */
    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
    }
    /**
     *  Provádí příkaz "seber". Zkouší se sebrat požadovanou věc, věc musí být v daném prostoru.
     *  Pokud věc v prostoru není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje název sbírané věci
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        if(parametry.length == 0){
            return "Co mám sebrat? Musíš zadat název věci.";
        }

        String nazevSbiraneVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec sbirana = aktualniProstor.odeberVec(nazevSbiraneVeci);

        if (sbirana == null){
            return "To tu není";
        } 

        if (sbirana.getNazev()=="osatka") {
            aktualniProstor.vlozVec(sbirana);
            plan.jaInformace().vezmiOsatku(plan.getAktualniProstor().odeberVec(parametry[0]));
            plan.notifyController();
            return "Sebral si "+ sbirana.getNazev();
        }

        if (sbirana.getNazev()=="prestrojeni") {
            aktualniProstor.vlozVec(sbirana);
            plan.jaInformace().prestrojit(plan.getAktualniProstor().odeberVec(parametry[0]));
            plan.notifyController();
            return "Právě si se přestrojil.";
        }

        else{ aktualniProstor.vlozVec(sbirana);
            if(plan.jaInformace().vecCoNesu()!=null) {
                plan.getAktualniProstor().vlozVec(plan.jaInformace().vecCoNesu());
                pomocna = plan.jaInformace().vecCoNesu();
                plan.jaInformace().vlozVec(plan.getAktualniProstor().odeberVec(parametry[0]));
                plan.notifyController();
                return "Sebral si "+ sbirana.getNazev()+ " äle nechal si tu "+ pomocna.getNazev();
            }
            else {
                plan.jaInformace().vlozVec(plan.getAktualniProstor().odeberVec(parametry[0]));
                plan.notifyController();
                return "Sebral si "+ sbirana.getNazev();
            }

        }
    }

    /**
     *  Metoda vrací název příkazu (Slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

    
}
