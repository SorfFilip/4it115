package logika;

public class HerniPlan {

    private Prostor aktualniProstor;
    private Prostor viteznyProstor;
    private Ja ja;

    public int uplaceno;
    public int proselVPrevleku;
    public int odevzdanoUkoly;
    public int odevzdanoSemestralka;


    public HerniPlan() {
        zalozProstoryHry();
    }


    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor cesta = new Prostor("cesta","cesta ze které jdeš domů, na kolej, do školy, či na louku ");
        Prostor domov = new Prostor("domov", "sweet home, zde jsi doma");
        Prostor kolej = new Prostor("kolej","nic moc, ale nechá se tu přespat");
        Prostor louka = new Prostor("louka","louka za Prahou");
        Prostor chodba = new Prostor("chodba","školní chodba, přes kterou se dostaneš do vítězné místnosti sb202\nJe tu: Uklízečka - pozor!");
        Prostor sb202 = new Prostor("sb202","vítězná místnost sb202\nJe tu: Ing. Jarmile Pavlíčkové, Ph.D.");

        // přiřazují se průchody mezi prostory (sousedící prostory)
        cesta.setVychod(domov);
        cesta.setVychod(kolej);
        cesta.setVychod(louka);
        cesta.setVychod(chodba);
        domov.setVychod(cesta);
        kolej.setVychod(cesta);
        louka.setVychod(cesta);
        chodba.setVychod(cesta);
        chodba.setVychod(sb202);
        sb202.setVychod(chodba);

        Vec jablka = new Vec("jablka");
        louka.vlozVec(jablka);
        Vec prestrojeni = new Vec("prestrojeni");
        kolej.vlozVec(prestrojeni);
        Vec osatka = new Vec("osatka");
        kolej.vlozVec(osatka);

        ja = new Ja();

        aktualniProstor = cesta;  // hra začíná na cestě 
        viteznyProstor = sb202;

        uplaceno = 0;
        proselVPrevleku = 0;
        odevzdanoUkoly = 0;
        odevzdanoSemestralka = 0;
    }



    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }


    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
    }
    

    public boolean jeVyhra(){
        return aktualniProstor.equals(viteznyProstor);
    }
    

    public Ja jaInformace() {
        return ja;
    }
    

    public void zmenauplat() {
        uplaceno = 1;
    }
    

    public void zmenaOdevzdatUkoly() {
        odevzdanoUkoly = 1;
    }
    

    public void zmenaOdevzdatSemestralku() {
        odevzdanoSemestralka = 1;
    }
    

    public void projitVPrevleku() {
        proselVPrevleku = 1;
    }
    

    public int uplaceno() {
        return uplaceno;
    }
    

    public int odevzdaneUkoly() {
        return odevzdanoUkoly;
    }

    public int odevzdaneSemestralku() {
        return odevzdanoSemestralka;
    }

    public int proselVPrevleku() {
        return proselVPrevleku;
    }

}
