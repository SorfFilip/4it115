
package logika;

/**
 *  Třída PrikazOdevzdej implementuje pro hru příkaz seber.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Filip Šorf
 *@version    pro školní rok 2015/2016
 */
public class PrikazOdevzdej implements IPrikaz{

    private static final String NAZEV = "odevzdej";
    private HerniPlan plan;
    

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém se budou ve hře sbírat věci
     */ 
    public PrikazOdevzdej(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "Odevzdej". Zkouší se odevzdatt požadovanou věc, věc musí být v ruckách, nebo v ošatce a musí ji bžt komu odevzdat.
     *  Pokud věc hráč nemá u sebe nebo nejsou splněny podmínky odevzdání, vypíše se chybové hlášení.
     *
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if(plan.getAktualniProstor().getNazev()=="chodba") 
        {

            if (parametry[0].equals("jablka")) 
            {   if (plan.jaInformace().mamUplatek()!=null){
                    plan.getAktualniProstor().vlozVec(plan.jaInformace().mamUplatek());
                    plan.jaInformace().uplatit();
                    plan.zmenauplat();
                    return "Odevzdán úplatek (jablka)";}
                else {return "Jablka nemáš u sebe!";}
            }

            else {
                return "Toto nelze odevzdat"; 
            }

        }
        if (plan.getAktualniProstor().getNazev()=="sb202")
        {

            if (parametry[0].equals("ukoly")) 
            {if (plan.jaInformace().vecCoNesu()!=null){
                    plan.getAktualniProstor().vlozVec(plan.jaInformace().vecCoNesu());
                    plan.jaInformace().odevzdat();
                    plan.zmenaOdevzdatUkoly();   
                    return "Odevzdány úkoly.";}
                else {return "Nemáš u sebe úkoly!";}
            }

            else if (parametry[0].equals("semestralka")) 
            {
                if(plan.odevzdaneUkoly()==1){
                    if (plan.jaInformace().vecCoNesu()!=null){ 
                        plan.getAktualniProstor().vlozVec(plan.jaInformace().vecCoNesu());
                        plan.jaInformace().odevzdat();
                        plan.zmenaOdevzdatSemestralku();
                        return "Odevzdána semestrálka.";}
                    else {return "Nemáš u sebe semestrálku!";}
                }
                else {return "Nejdříve musíš odevzdat úkoly!";}
            }           

            else {
                return "Toto nelze odevzdat"; 
            }

        }

        else 
        {
            return "Zde není nikdo, komu bys cokoliv mohl odevzdat.";
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
