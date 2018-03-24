
package logika;


import java.util.HashMap;
import java.util.Map;


public class Ja
{
    private Vec vecCoNesu;
    private Vec osatka;
    private Vec uplatek;
    private Vec prestrojeni;

    private Map<String, Vec> vsechnyVeciUSebe;


    public Ja()
    {
        vecCoNesu = null;
        prestrojeni = null;
        osatka = null;
        uplatek = null;
        vsechnyVeciUSebe = new HashMap<>();
    }


    public Vec vlozVec(Vec vec) {
        vecCoNesu = vec;
        vsechnyVeciUSebe.put("vecCoNesu", vecCoNesu);
        return null;
    }

    public Vec prestrojit(Vec vec) {
        prestrojeni = vec;
        vsechnyVeciUSebe.put(prestrojeni.getNazev(), prestrojeni);
        return null;
    }


    public Vec vezmiOsatku(Vec vec) {
        osatka = vec;
        vsechnyVeciUSebe.put(osatka.getNazev(), osatka);
        return null;
    }


    public Vec uplatek(Vec vec) {
        uplatek = vec;
        vsechnyVeciUSebe.put(uplatek.getNazev(), uplatek);
        return null;
    } 
    

    public Vec uplatit() {
        uplatek = null;
        osatka = null;
        vsechnyVeciUSebe.remove("uplatek");
        vsechnyVeciUSebe.remove("osatka");
        return null;
    } 
    

    public Vec odevzdat() {
        vsechnyVeciUSebe.remove("vecCoNesu");
        vecCoNesu = null;

        return null;
    } 

    public Map getVsechnyVeciUsebe() {
        return this.vsechnyVeciUSebe;
    }

    public Vec vecCoNesu() {

        return vecCoNesu;
    }


    public Vec mamOsatku() {

        return osatka;
    }


    public Vec mamUplatek() {

        return uplatek;
    }


    public Vec mamPrestrojeni() {

        return prestrojeni;
    }



}
