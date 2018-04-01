package logika;


/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Filip Šorf
 *@version    pro školní rok 2015/2016
 */
public class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazJdi(HerniPlan plan) {

        this.plan = plan;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];


        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);


        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }

        if (smer.equals("sb202")) {
            if ((plan.proselVPrevleku() == 1) && (plan.uplaceno() == 0)) {
                return "Převlek ti již nepomůže! Abys mohl projít, musíš uplatit uklízečku";
            } else if ((plan.proselVPrevleku() == 0) && (plan.jaInformace().mamPrestrojeni() == null) && (plan.uplaceno() == 0)) {
                return "Nemáš převlek ani si neuplatil uklízečku, nemůžeš projít!";
            } else if ((plan.proselVPrevleku() == 0) && (plan.jaInformace().mamPrestrojeni() != null) && (plan.uplaceno() == 0)) {
                plan.projitVPrevleku();
                plan.setAktualniProstor(sousedniProstor);
                return sousedniProstor.dlouhyPopis();
            } else {

                plan.setAktualniProstor(sousedniProstor);
                return sousedniProstor.dlouhyPopis();
            }

        } else {

            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();
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
