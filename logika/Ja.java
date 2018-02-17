
package logika;

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
    private final int NOSNOST = 1; //kolik věcí lze vložit do batohu
    public Vec vecCoNesu;
    public Vec osatka;
    public Vec uplatek;
    public Vec prestrojeni;

    /***************************************************************************
     * Konstruktor třídy
     */
    public Ja()
    {
        vecCoNesu = null;
        prestrojeni = null;
        osatka = null;
        uplatek = null;
    }

    /**
     * Metoda vloží věc do "rukou".
     */

    public Vec vlozVec(Vec vec) {
        vecCoNesu = vec;
        return null;
    }

    /**
     * Metoda, pomoci které se přestojím.
     */
    public Vec prestrojit(Vec vec) {
        prestrojeni = vec;
        return null;
    }

    /**
     * Metoda, pomocí které vezmu ošatku.
     */
    public Vec vezmiOsatku(Vec vec) {
        osatka = vec;
        return null;
    }

    /**
     * Metoda, pomocí které naplním ošatku úplatkem pro uklízečku (jablka).
     */
    public Vec uplatek(Vec vec) {
        uplatek = vec;
        return null;
    } 
    
    /**
     * Metoda volana po uplacení uklízečky.
     */
    public Vec uplatit() {
        uplatek = null;
        osatka = null;
        return null;
    } 
    
    /**
     * Metoda volana po odevzdání předmětu.
     */
    public Vec odevzdat() {
        vecCoNesu = null;
        return null;
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

   
    /**
     * Metoda vrací kolik unesu.
     */
    public int getKapacita() {
        return NOSNOST;
    }

}
