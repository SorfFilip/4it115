package main;

import java.awt.event.ActionEvent;
import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import logika.*;


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
    @FXML
    private ComboBox<String> jdiCombo;
    @FXML
    private ComboBox<String> seberCombo;
    @FXML
    private ComboBox<String> odevzdejCombo;
    @FXML
    private ComboBox<String> trhejCombo;
    @FXML
    private ComboBox<String> vypracujCombo;

    private IHra hra;

    public void odesliPrikaz() {


        String vypis = hra.zpracujPrikaz(textVstup.getText());
        textVypis.appendText("\n\n-> " + textVstup.getText() + " <-\n");
        textVypis.appendText(vypis);
        textVstup.setText("");

        if (hra.konecHry()) {
            textVypis.appendText("\n\n Konec hry \n");
            textVstup.setDisable(true);
            odesli.setDisable(true);
        }

        System.out.println("odeslan prikaz");

    }

    @FXML
    private void buttonClickHandler(javafx.event.ActionEvent event) {
        Node node = (Node) event.getSource();
        String prikaz = node.getId();

        String vypis = hra.zpracujPrikaz(prikaz);
        textVypis.appendText("\n\n-> " + prikaz + " <-\n");
        textVypis.appendText(vypis);
        textVstup.setText("");

    }

    @FXML
    private void jdiHandler() {
        String prikaz = "jdi " + jdiCombo.getValue();
        String vypis = hra.zpracujPrikaz(prikaz);
        textVypis.appendText("\n\n-> " + prikaz + " <-\n");
        textVypis.appendText(vypis);
        textVstup.setText("");
    }

    @FXML
    private void seberHandler() {
        String prikaz = "seber " + seberCombo.getValue();
        String vypis = hra.zpracujPrikaz(prikaz);
        textVypis.appendText("\n\n-> " + prikaz + " <-\n");
        textVypis.appendText(vypis);
        textVstup.setText("");
    }

    @FXML
    private void odevzdejHandler() {
        String prikaz = "odevzdej " + odevzdejCombo.getValue();
        String vypis = hra.zpracujPrikaz(prikaz);
        textVypis.appendText("\n\n-> " + prikaz + " <-\n");
        textVypis.appendText(vypis);
        textVstup.setText("");
    }

    @FXML
    private void vypracujHandler() {
        String prikaz = "vypracuj " + vypracujCombo.getValue();
        String vypis = hra.zpracujPrikaz(prikaz);
        textVypis.appendText("\n\n-> " + prikaz + " <-\n");
        textVypis.appendText(vypis);
        textVstup.setText("");
    }

    @FXML
    private void trhejHandler() {
        String prikaz = "trhej " + trhejCombo.getValue();
        String vypis = hra.zpracujPrikaz(prikaz);
        textVypis.appendText("\n\n-> " + prikaz + " <-\n");
        textVypis.appendText(vypis);
        textVstup.setText("");
    }

    @FXML
    private void novaHra() {
        IHra hra = new Hra();
        this.init(hra);
        //self notify
        HerniPlan plan = new HerniPlan();
        this.update(plan, this);
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

        ja.getItems().clear();
        Map<String, Vec> vsechnyVeciUSebe = this.hra.getHerniPlan().getJa().getVsechnyVeciUsebe();
        for (String item : vsechnyVeciUSebe.keySet()) {
            ja.getItems().add(vsechnyVeciUSebe.get(item).getNazev());
        }

    }

}
