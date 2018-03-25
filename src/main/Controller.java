package main;

import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import logika.*;


public class Controller extends GridPane implements Observer {

    @FXML
    private TextField textVstup;
    @FXML
    private TextArea textVypis;
    @FXML
    private Button napoveda, pomoc;
    @FXML
    private ListView seznamVychodu, seznamVeci, ja;
    @FXML
    private ComboBox<String> jdiCombo, seberCombo, odevzdejCombo, trhejCombo, vypracujCombo;
    @FXML
    private javafx.scene.shape.Polygon kolej, cesta, domov, louka, sb202, chodba;

    private HashMap<String, javafx.scene.shape.Polygon> stringMapOctagon;

    private IHra hra;

    public void odesliPrikaz() {

        String vypis = hra.zpracujPrikaz(textVstup.getText());
        this.vypisText(textVstup.getText(), vypis);

        if (hra.konecHry()) {
            textVypis.appendText("\n\n Konec hry \n");
            textVstup.setDisable(true);

        }

        System.out.println("odeslan prikaz");

    }

    @FXML
    private void napovedaPomocHandler(javafx.event.ActionEvent event) {
        Node node = (Node) event.getSource();
        String prikaz = node.getId();

        String vypis = hra.zpracujPrikaz(prikaz);
        this.vypisText(prikaz, vypis);

    }

    @FXML
    private void jdiHandler() {
        String prikaz = "jdi " + jdiCombo.getValue();
        String vypis = hra.zpracujPrikaz(prikaz);
        this.vypisText(prikaz, vypis);
    }

    @FXML
    private void seberHandler() {
        String prikaz = "seber " + seberCombo.getValue();
        String vypis = hra.zpracujPrikaz(prikaz);
        this.vypisText(prikaz, vypis);
    }

    @FXML
    private void odevzdejHandler() {
        String prikaz = "odevzdej " + odevzdejCombo.getValue();
        String vypis = hra.zpracujPrikaz(prikaz);
        this.vypisText(prikaz, vypis);

        if (this.hra.konecHry()) {
            this.disableButtons(true);
        }
    }

    @FXML
    private void vypracujHandler() {
        String prikaz = "vypracuj " + vypracujCombo.getValue();
        String vypis = hra.zpracujPrikaz(prikaz);
        this.vypisText(prikaz, vypis);
    }

    @FXML
    private void trhejHandler() {
        String prikaz = "trhej " + trhejCombo.getValue();
        String vypis = hra.zpracujPrikaz(prikaz);
        this.vypisText(prikaz, vypis);
    }

    @FXML
    private void novaHra() {
        IHra hra = new Hra();
        this.init(hra);

    }

    private void vypisText(String prikaz, String vypis) {
        textVypis.appendText("\n\n-> " + prikaz + " <-\n");
        textVypis.appendText(vypis);
        textVstup.setText("");
    }

    public void init(IHra hra) {
        this.hra = hra;

        stringMapOctagon = new HashMap<>();
        stringMapOctagon.put("kolej", kolej);
        stringMapOctagon.put("cesta", cesta);
        stringMapOctagon.put("domov", domov);
        stringMapOctagon.put("louka", louka);
        stringMapOctagon.put("chodba", chodba);
        stringMapOctagon.put("sb202", sb202);

        textVypis.setText(hra.vratUvitani());

        hra.getHerniPlan().addObserver(this);

        this.disableButtons(false);

        //self notify
        HerniPlan plan = new HerniPlan();
        this.update(plan, this);
    }

    private void disableButtons(Boolean bool) {
        textVypis.appendText("\n\n Konec hry \n");
        textVstup.setDisable(bool);
        jdiCombo.setDisable(bool);
        seberCombo.setDisable(bool);
        odevzdejCombo.setDisable(bool);
        vypracujCombo.setDisable(bool);
        trhejCombo.setDisable(bool);
        napoveda.setDisable(bool);
        pomoc.setDisable(bool);
    }

    @Override
    public void update(Observable o, Object arg) {

        Prostor aktualniProstor = this.hra.getHerniPlan().getAktualniProstor();

        seznamVychodu.getItems().clear();
        for (Prostor item : aktualniProstor.getVychody()) {
            seznamVychodu.getItems().add(item.getNazev());
        }

        seznamVeci.getItems().clear();
        for (Object item : aktualniProstor.getVeci().keySet()) {
            seznamVeci.getItems().add(item);
        }

        ja.getItems().clear();
        Map<String, Vec> vsechnyVeciUSebe = this.hra.getHerniPlan().getJa().getVsechnyVeciUsebe();
        for (String item : vsechnyVeciUSebe.keySet()) {
            ja.getItems().add(vsechnyVeciUSebe.get(item).getNazev());
        }

        this.obarvi(aktualniProstor);


    }

    private void obarvi(Prostor aktualniProstorP) {

        String aktualniProstor = aktualniProstorP.getNazev();

        for (String key : stringMapOctagon.keySet()) {
            stringMapOctagon.get(key).setFill(null);
        }
        stringMapOctagon.get(aktualniProstor).setFill(Color.ORANGE);
    }

}
