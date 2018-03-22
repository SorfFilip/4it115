package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import main.*;
import logika.*;
import uiText.TextoveRozhrani;

/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy  
 * SeznamPrikazu
 * 
 * @author    Luboš Pavlíček, Filip Šorf
 * @version   pro školní rok 2015/2016
 */
public class SeznamPrikazuTest
{
    private Hra hra;
    private PrikazKonec prKonec;
    private PrikazJdi prJdi;
    private PrikazSeber prSeber;
    private PrikazTrhej prTrhej;
    private PrikazVypracuj prVypracuj;
    private PrikazOdevzdej prOdevzdej;

    @Before
    public void setUp() {
        hra = new Hra();
        prKonec = new PrikazKonec(hra);
        prJdi = new PrikazJdi(hra.getHerniPlan());
        prSeber = new PrikazSeber(hra.getHerniPlan());
        prTrhej = new PrikazTrhej(hra.getHerniPlan());
        prVypracuj = new PrikazVypracuj(hra.getHerniPlan());
        prOdevzdej = new PrikazOdevzdej(hra.getHerniPlan());
    }

    @Test
    public void testVlozeniVybrani() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prSeber);
        seznPrikazu.vlozPrikaz(prTrhej);
        seznPrikazu.vlozPrikaz(prVypracuj);
        seznPrikazu.vlozPrikaz(prOdevzdej);
        assertEquals(prKonec, seznPrikazu.vratPrikaz("konec"));
        assertEquals(prJdi, seznPrikazu.vratPrikaz("jdi"));
        assertEquals(prSeber, seznPrikazu.vratPrikaz("seber"));
        assertEquals(prTrhej, seznPrikazu.vratPrikaz("trhej"));
        assertEquals(prVypracuj, seznPrikazu.vratPrikaz("vypracuj"));
        assertEquals(prOdevzdej, seznPrikazu.vratPrikaz("odevzdej"));
        assertEquals(null, seznPrikazu.vratPrikaz("napoveda"));
    }

    @Test
    public void testJePlatnyPrikaz() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prSeber);
        seznPrikazu.vlozPrikaz(prTrhej);
        seznPrikazu.vlozPrikaz(prOdevzdej);
        seznPrikazu.vlozPrikaz(prVypracuj);
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("konec"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("jdi"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("seber"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("trhej"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("odevzdej"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("vypracuj"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("napoveda"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("Konec"));
    }

    @Test
    public void testNazvyPrikazu() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        String nazvy = seznPrikazu.vratNazvyPrikazu();
        assertEquals(true, nazvy.contains("konec"));
        assertEquals(true, nazvy.contains("jdi"));
        assertEquals(false, nazvy.contains("napoveda"));
        assertEquals(false, nazvy.contains("Konec"));
    }

}
