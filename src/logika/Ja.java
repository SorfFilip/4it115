
package logika;


import java.util.HashMap;
import java.util.Map;

/*******************************************************************************
 * Třída Ja
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *  Třída Já reprezentuje úložný prostor pro sebrané věci
 *  Věci jsou vkládány do rukou, na sebe, ši do ošatky a kdykoli si pomocí příkazu jaInformace
 *  můžeme nechat vypsat obsah.
 *
 * @author    Filip Šorf
 * @version   2015/2016
 */
public class Ja
{
    private Vec vecCoNesu;
    private Vec osatka;
    private Vec uplatek;
    private Vec prestrojeni;

    private Map<String, Vec> vsechnyVeciUSebe;

    /***************************************************************************
     * Konstruktor třídy
     */
    public Ja()
    {
        vecCoNesu = null;
        prestrojeni = null;
        osatka = null;
        uplatek = null;
        vsechnyVeciUSebe = new HashMap<String, Vec>();
    }

    /**
     * Metoda vloží věc do "rukou".
     */
    public Vec vlozVec(Vec vec) {
        vecCoNesu = vec;
        vsechnyVeciUSebe.put("vecCoNesu", vecCoNesu);
        return null;
    }
    /**
     * Metoda, pomoci které se přestojím.
     */
    public Vec prestrojit(Vec vec) {
        prestrojeni = vec;
        vsechnyVeciUSebe.put(prestrojeni.getNazev(), prestrojeni);
        return null;
    }

    /**
     * Metoda, pomocí které vezmu ošatku.
     */
    public Vec vezmiOsatku(Vec vec) {
        osatka = vec;
        vsechnyVeciUSebe.put(osatka.getNazev(), osatka);
        return null;
    }

    /**
     * Metoda, pomocí které naplním ošatku úplatkem pro uklízečku (jablka).
     */
    public Vec uplatek(Vec vec) {
        uplatek = vec;
        vsechnyVeciUSebe.put(uplatek.getNazev(), uplatek);
        return null;
    }

    /**
     * Metoda volana po uplacení uklízečky.
     */
    public Vec uplatit() {
        uplatek = null;
        osatka = null;
        vsechnyVeciUSebe.remove("jablka");
        vsechnyVeciUSebe.remove("osatka");
        return null;
    }

    /**
     * Metoda volana po odevzdání předmětu.
     */
    public Vec odevzdat() {
        vsechnyVeciUSebe.remove("vecCoNesu");
        vecCoNesu = null;

        return null;
    }
    /**
     * Metoda vrací vsechny věci, kterou nesu.
     */
    public Map getVsechnyVeciUsebe() {
        return this.vsechnyVeciUSebe;
    }
    /**
     * Metoda vrací věc, kterou nesu.
     */
    public Vec vecCoNesu() {

        return vecCoNesu;
    }

    /**
     * Metoda vrací zda nesu ošatku.
     */
    public Vec mamOsatku() {

        return osatka;
    }

    /**
     * Metoda vrací zda mám úplatek pro uklízečku.
     */
    public Vec mamUplatek() {

        return uplatek;
    }

    /**
     * Metoda vrací zda mám na sobě přestrojení.
     */
    public Vec mamPrestrojeni() {

        return prestrojeni;
    }



}
