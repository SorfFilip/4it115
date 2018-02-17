package logika;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Filip Šorf
 *@version    pro školní rok 2015/2016
 */
public class HerniPlan {

    private Prostor aktualniProstor;
    private Prostor viteznyProstor;
    private Ja ja;

    public int uplaceno;
    public int proselVPrevleku;
    public int odevzdanoUkoly;
    public int odevzdanoSemestralka;

    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví cestu.
     */
    public HerniPlan() {
        zalozProstoryHry();

    }

    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví cestu.
     */
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

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
    }
    
    /**
     *  Metoda zjištuje zda je hra vyhrána.
     *
     */
    public boolean jeVyhra(){
        return aktualniProstor.equals(viteznyProstor);
    }
    
    /**
     *  Metoda vrací informace o hráčově postavě.
     *
     */
    public Ja jaInformace() {
        return ja;
    }
    
    /**
     *  Metoda nastavuje hodnotu uplacení na 1 (pravda).
     *
     */
    public void zmenauplat() {
        uplaceno = 1;
    }
    
    /**
     *  Metoda nastavuje hodnotu odevzdaných ukolů na 1 (pravda).
     *
     */
    public void zmenaOdevzdatUkoly() {
        odevzdanoUkoly = 1;
    }
    
    /**
     *  Metoda nastavuje hodnotu odevzdane semestrálky na 1 (pravda).
     *
     */
    public void zmenaOdevzdatSemestralku() {
        odevzdanoSemestralka = 1;
    }
    
    /**
     *  Metoda nastavuje hodnotu prijití v převleku kolem uklízečky na 1 (pravda).
     *
     */
    public void projitVPrevleku() {
        proselVPrevleku = 1;
    }
    
    /**
     *  Metoda vrací hodnotu zda byla či nebyla uklízečka uplacena: 1 ano/ 0 ne,
     *
     */
    public int uplaceno() {
        return uplaceno;
    }
    
    /**
     *  Metoda vrací hodnotu zda byly či nebyly odevzdíny ukoly: 1 ano/ 0 ne,
     *
     */
    public int odevzdaneUkoly() {
        return odevzdanoUkoly;
    }
    
    /**
     *  Metoda vrací hodnotu zda byla či nebyla odevzdána semestrálka: 1 ano/ 0 ne,
     *
     */
    public int odevzdaneSemestralku() {
        return odevzdanoSemestralka;
    }
    
    /**
     *  Metoda vrací hodnotu zda si prošel či neprošel kolem uklízečky v převleku: 1 ano/ 0 ne,
     *
     */
    public int proselVPrevleku() {
        return proselVPrevleku;
    }

}
