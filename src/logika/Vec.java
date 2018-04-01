
package logika;


/**
 * Třída Vec je součástí jednoduché textové adventury.
 * Její instance představují předměty ve hře.
 * @author Jarmila Pavlíčková, Filip Šorf
 * verze pro rok 2015/2016
 */
public class Vec {

    private String nazev;

    /**
     * Konstruktor
     * @param nazev název věci
     */
    public Vec(String nazev) {
        this.nazev = nazev;

    }


    /**
     * Vrací název věci
     *
     * @return název věci
     */
    public String getNazev() {
        return nazev;
    }

}
