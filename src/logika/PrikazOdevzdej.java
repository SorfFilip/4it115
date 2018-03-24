
package logika;


public class PrikazOdevzdej implements IPrikaz{

    private static final String NAZEV = "odevzdej";
    private HerniPlan plan;
    


    public PrikazOdevzdej(HerniPlan plan) {
        this.plan = plan;
    }


    @Override
    public String proved(String... parametry) {
        if(plan.getAktualniProstor().getNazev()=="chodba") 
        {

            if (parametry[0].equals("jablka")) 
            {   if (plan.jaInformace().mamUplatek()!=null){
                    plan.getAktualniProstor().vlozVec(plan.jaInformace().mamUplatek());
                    plan.jaInformace().uplatit();
                    plan.zmenauplat();
                plan.notifyController();
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
            {if (plan.jaInformace().vecCoNesu().getNazev().equals("ukoly")){
                    plan.getAktualniProstor().vlozVec(plan.jaInformace().vecCoNesu());
                    plan.jaInformace().odevzdat();
                    plan.zmenaOdevzdatUkoly();
                plan.notifyController();
                    return "Odevzdány úkoly.";}
                else {return "Nemáš u sebe úkoly!";}
            }

            else if (parametry[0].equals("semestralka")) 
            {
                if(plan.odevzdaneUkoly()==1){
                    if (plan.jaInformace().vecCoNesu().getNazev().equals("semestralka")){
                        plan.getAktualniProstor().vlozVec(plan.jaInformace().vecCoNesu());
                        plan.jaInformace().odevzdat();
                        plan.zmenaOdevzdatSemestralku();
                        plan.notifyController();
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

    @Override
    public String getNazev() {
        return NAZEV;
    }

    
}
