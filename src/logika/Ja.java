
package logika;


public class Ja
{
    public Vec vecCoNesu;
    public Vec osatka;
    public Vec uplatek;
    public Vec prestrojeni;


    public Ja()
    {
        vecCoNesu = null;
        prestrojeni = null;
        osatka = null;
        uplatek = null;
    }


    public Vec vlozVec(Vec vec) {
        vecCoNesu = vec;
        return null;
    }

    public Vec prestrojit(Vec vec) {
        prestrojeni = vec;
        return null;
    }


    public Vec vezmiOsatku(Vec vec) {
        osatka = vec;
        return null;
    }


    public Vec uplatek(Vec vec) {
        uplatek = vec;
        return null;
    } 
    

    public Vec uplatit() {
        uplatek = null;
        osatka = null;
        return null;
    } 
    

    public Vec odevzdat() {
        vecCoNesu = null;
        return null;
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
