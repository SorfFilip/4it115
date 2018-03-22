package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková, Filip Šorf
 * @version   pro skolní rok 2015/2016
 */
public class ProstorTest
{
    private logika.Hra hra1;
    private logika.HerniPlan herniPla1;
    private logika.SeznamPrikazu seznamPr1;
    private logika.PrikazJdi prikazJd1;
    private logika.PrikazSeber prikazSe1;
    private logika.PrikazVypracuj prikazVy1;
    private logika.PrikazTrhej prikazTr1;
    private logika.PrikazOdevzdej prikazOd1;
    private logika.PrikazKonec prikazKo1;
    private logika.Ja ja1;

    //== Datové atributy (statické i instancí)======================================
    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {

        hra1 = new logika.Hra();
        herniPla1 = new logika.HerniPlan();
        prikazJd1 = new logika.PrikazJdi(herniPla1);
        prikazSe1 = new logika.PrikazSeber(herniPla1);
        prikazVy1 = new logika.PrikazVypracuj(herniPla1);
        prikazTr1 = new logika.PrikazTrhej(herniPla1);
        prikazOd1 = new logika.PrikazOdevzdej(herniPla1);
        prikazKo1 = new logika.PrikazKonec(hra1);
        ja1 = new logika.Ja();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {

    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, Zda se opravdu nenechá projít kolem uklízečky 
     * bez úplatku/převleku 
     */
    @Test
    public  void testNelzeProjit() {        
        assertEquals("cesta", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi domov");
        assertEquals(false, hra1.konecHry());
        assertEquals("domov", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi cesta"); 
        assertEquals("cesta", hra1.getHerniPlan().getAktualniProstor().getNazev()); 
        hra1.zpracujPrikaz("jdi chodba");
        assertEquals("chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi sb202");
        assertEquals("chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi kolej");
        assertEquals("chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());
    }

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, Zda se nechá projít kolem uklízečky 
     * s úplatkem/v převleku 
     */
    @Test
    public  void testLzeProjit() {      
        assertEquals("cesta", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi kolej");
        assertEquals(false, hra1.konecHry());
        assertEquals("kolej", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber prestrojeni"); 
        hra1.zpracujPrikaz("jdi cesta"); 
        assertEquals(false, hra1.konecHry());
        assertEquals("cesta", hra1.getHerniPlan().getAktualniProstor().getNazev()); 
        hra1.zpracujPrikaz("jdi chodba");
        assertEquals(false, hra1.konecHry());
        assertEquals("chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi sb202");
        assertEquals("sb202", hra1.getHerniPlan().getAktualniProstor().getNazev());

    }

    /**
     * Testuje, zda lze do prostoru vložit a i z něj odebírat věci
     */
    @Test
    public void testVeci()
    {
        hra1.zpracujPrikaz("jdi kolej");
        hra1.zpracujPrikaz("seber osatka");
        assertEquals("osatka", hra1.getHerniPlan().jaInformace().mamOsatku().getNazev()); 
        hra1.zpracujPrikaz("jdi cesta");
        hra1.zpracujPrikaz("jdi louka");
        hra1.zpracujPrikaz("trhej");
        assertEquals("jablka", hra1.getHerniPlan().jaInformace().mamUplatek().getNazev()); 
        hra1.zpracujPrikaz("jdi cesta");
        hra1.zpracujPrikaz("jdi chodba");
        hra1.zpracujPrikaz("odevzdej jablka");
        assertEquals(null, hra1.getHerniPlan().jaInformace().mamOsatku()); 
        assertEquals(null, hra1.getHerniPlan().jaInformace().mamUplatek());
        assertEquals(1, hra1.getHerniPlan().uplaceno()); 

    }
}

