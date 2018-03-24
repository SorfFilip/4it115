package main;

import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logika.IHra;
import logika.Ja;
import logika.Prostor;
import logika.Vec;


public class Controller extends GridPane implements Observer {

    @FXML
    private TextField textVstup;
    @FXML
    private TextArea textVypis;
    @FXML
    private Button odesli;
    @FXML
    private ListView seznamVychodu;
    @FXML
    private ListView seznamVeci;
    @FXML
    private ListView ja;
    private IHra hra;

    public void odesliPrikaz() {


        String vypis = hra.zpracujPrikaz(textVstup.getText());
        textVypis.appendText("\n--------\n" + textVstup.getText() + "\n--------\n");
        textVypis.appendText(vypis);
        textVstup.setText("");

        if (hra.konecHry()) {
            textVypis.appendText("\n\n Konec hry \n");
            textVstup.setDisable(true);
            odesli.setDisable(true);
        }

        System.out.println("odeslan prikaz");

    }

    public void init(IHra hra) {
        this.hra = hra;
        textVypis.setText(hra.vratUvitani());
        System.out.println("initovano");


        hra.getHerniPlan().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

        seznamVychodu.getItems().clear();
        for (Prostor item : this.hra.getHerniPlan().getAktualniProstor().getVychody()) {
            seznamVychodu.getItems().add(item.getNazev());
        }

        seznamVeci.getItems().clear();
        for (Object item : this.hra.getHerniPlan().getAktualniProstor().getVeci().keySet()) {
            seznamVeci.getItems().add(item);
        }
//todo vecconesu -> nazev ty veci
        ja.getItems().clear();
        for (Object item : this.hra.getHerniPlan().getJa().getVsechnyVeciUsebe().keySet()) {
            ja.getItems().add(item);
        }




    }

}
