
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
     * @param prenositelna přenositelnost věci, tj. zda hráč může či nemůže věc z prostoru sebrat a odnést 
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
