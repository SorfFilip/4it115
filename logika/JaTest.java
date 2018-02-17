package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Filip Šorf
 * @version  pro školní rok 2015/2016
 */
public class JaTest {
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

    /***************************************************************************
     * Testuje co má hráčova postava u sebe v závislostech na stavu hry
     */
    @Test
    public void testPostavaHrace() {
        hra1.zpracujPrikaz("jdi domov");
        hra1.zpracujPrikaz("vypracuj");
        hra1.zpracujPrikaz("seber ukoly");
        assertEquals("ukoly", hra1.getHerniPlan().jaInformace().vecCoNesu().getNazev());
        hra1.zpracujPrikaz("jdi cesta");
        hra1.zpracujPrikaz("jdi kolej");
        hra1.zpracujPrikaz("seber prestrojeni");
        assertEquals("prestrojeni", hra1.getHerniPlan().jaInformace().mamPrestrojeni().getNazev());
        hra1.zpracujPrikaz("seber osatka");
        assertEquals("osatka", hra1.getHerniPlan().jaInformace().mamOsatku().getNazev());
        hra1.zpracujPrikaz("jdi cesta");
        hra1.zpracujPrikaz("jdi louka");
        hra1.zpracujPrikaz("trhej");
        assertEquals("jablka", hra1.getHerniPlan().jaInformace().mamUplatek().getNazev());
        hra1.zpracujPrikaz("jdi cesta");
        hra1.zpracujPrikaz("jdi chodba");
        hra1.zpracujPrikaz("odevzdej jablka");
        assertEquals(null, hra1.getHerniPlan().jaInformace().mamUplatek());
        assertEquals(null, hra1.getHerniPlan().jaInformace().mamOsatku());
        hra1.zpracujPrikaz("jdi sb202");
        hra1.zpracujPrikaz("odevzdej ukoly");
        assertEquals(null, hra1.getHerniPlan().jaInformace().vecCoNesu());
        assertEquals(null, hra1.getHerniPlan().jaInformace().mamUplatek());
    }
}
