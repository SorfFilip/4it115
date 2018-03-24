package logika;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;


public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Map<String, Vec> veci;


    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        veci = new HashMap<>();
    }

    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }


    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }


    public String getNazev() {
        return nazev;       
    }


    public String dlouhyPopis() {
        return "Jsi v mistnosti/prostoru " + popis + ".\n"
        + popisVychodu()+ "\n"
        + popisVeci();
    }


    private String popisVychodu() {
        String vracenyText = "vychody:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }


    public Prostor vratSousedniProstor(String nazevSouseda) {
        if (nazevSouseda == null) {
            return null;
        }
        for (Prostor sousedni : vychody) {
            if (sousedni.getNazev().equals(nazevSouseda)) {
                return sousedni;
            }
        }
        return null;  // prostor nenalezen
    }


    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    public Map getVeci() {
        return this.veci;
    }


    public boolean vlozVec(Vec neco){
        if(veci.containsKey(neco.getNazev())){
            return false;            
        }
        else{
            veci.put(neco.getNazev(), neco);
            return true;
        }
    }


    public Vec odeberVec(String nazevOdebirane){        
        return veci.remove(nazevOdebirane);
    }


    private String popisVeci(){
        String popisVeci = "";
        if (veci.isEmpty()){
            popisVeci = "Nejsou tu žádné věci";
        }
        else{
            popisVeci = "A je tu: ";
            for (String nazev : veci.keySet()) {
                popisVeci += nazev + " ";
            }
        }
        return popisVeci;            
    }

}
