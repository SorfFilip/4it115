package logika;


public class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;


    public PrikazJdi(HerniPlan plan) {

        this.plan = plan;
    }


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

    @Override
    public String getNazev() {

        return NAZEV;
    }

}
