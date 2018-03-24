
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


    public PrikazTrhej(HerniPlan plan) {
        this.plan = plan;
    }


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


    @Override
    public String getNazev() {
        return NAZEV;
    }


}
