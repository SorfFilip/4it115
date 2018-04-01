package main;

import java.io.File;
import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.*;

/*******************************************************************************
 * Třída  Controller
 * obsahuje logiku grafickeho rozhrani
 *
 * @author    Filip Šorf
 * @version   LS 2018
 */
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
    /**
     *  Metoda odesilajici textovy prikaz zadany v textovem poli
     */
    public void odesliPrikaz() {

        String vypis = hra.zpracujPrikaz(textVstup.getText());
        this.vypisText(textVstup.getText(), vypis);

        if (hra.konecHry()) {
            textVypis.appendText("\n\n Konec hry \n");
            textVstup.setDisable(true);

        }

        System.out.println("odeslan prikaz");

    }
    /**
     *  Zachyceni udalosti vyvolane buttonem
     *
     *  @param event udalost zachycena buttonem
     */
    @FXML
    private void napovedaPomocHandler(javafx.event.ActionEvent event) {
        Node node = (Node) event.getSource();
        String prikaz = node.getId();

        String vypis = hra.zpracujPrikaz(prikaz);
        this.vypisText(prikaz, vypis);

    }
    /**
     *  Zachyceni udalosti vyvolane buttonem
     */
    @FXML
    private void napovedaHandler() {


        StackPane secondaryLayout = new StackPane();
        Scene secondScene = new Scene(secondaryLayout, 400, 400);
        Stage newWindow = new Stage();
        newWindow.setTitle("Napoveda");
        newWindow.setScene(secondScene);

        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();

        String napovedaPath = getClass().getProtectionDomain().getCodeSource().getLocation() + "../../../napoveda.html";
        webEngine.load(napovedaPath);

        secondaryLayout.getChildren().add(browser);
        newWindow.show();

    }
    /**
     *  Zachyceni udalosti vyvolane buttonem
     */
    @FXML
    private void jdiHandler() {
        String prikaz = "jdi " + jdiCombo.getValue();
        String vypis = hra.zpracujPrikaz(prikaz);
        this.vypisText(prikaz, vypis);
    }
    /**
     *  Zachyceni udalosti vyvolane buttonem
     */
    @FXML
    private void seberHandler() {
        String prikaz = "seber " + seberCombo.getValue();
        String vypis = hra.zpracujPrikaz(prikaz);
        this.vypisText(prikaz, vypis);
    }
    /**
     *  Zachyceni udalosti vyvolane buttonem
     */
    @FXML
    private void odevzdejHandler() {
        String prikaz = "odevzdej " + odevzdejCombo.getValue();
        String vypis = hra.zpracujPrikaz(prikaz);
        this.vypisText(prikaz, vypis);

        if (this.hra.konecHry()) {
            this.disableButtons(true);
        }
    }
    /**
     *  Zachyceni udalosti vyvolane buttonem
     */
    @FXML
    private void vypracujHandler() {
        String prikaz = "vypracuj " + vypracujCombo.getValue();
        String vypis = hra.zpracujPrikaz(prikaz);
        this.vypisText(prikaz, vypis);
    }
    /**
     *  Zachyceni udalosti vyvolane buttonem
     */
    @FXML
    private void trhejHandler() {
        String prikaz = "trhej " + trhejCombo.getValue();
        String vypis = hra.zpracujPrikaz(prikaz);
        this.vypisText(prikaz, vypis);
    }
    /**
     *  Zachyceni udalosti vyvolane buttonem
     */
    @FXML
    private void novaHra() {
        IHra hra = new Hra();
        this.init(hra);

    }
    /**
     *  Metoda pro vypis textu do textoveho pole
     */
    private void vypisText(String prikaz, String vypis) {
        textVypis.appendText("\n\n-> " + prikaz + " <-\n");
        textVypis.appendText(vypis);
        textVstup.setText("");
    }

    /**
     *  Metoda pro nastaveni promennych prostredi
     */
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
    /**
     *  Metoda pro disablovani / enablovani buttonu
     */
    private void disableButtons(Boolean bool) {
        if (bool) {
            textVypis.appendText("\n\n Konec hry \n");
        }
        textVstup.setDisable(bool);
        jdiCombo.setDisable(bool);
        seberCombo.setDisable(bool);
        odevzdejCombo.setDisable(bool);
        vypracujCombo.setDisable(bool);
        trhejCombo.setDisable(bool);
        napoveda.setDisable(bool);
        pomoc.setDisable(bool);
    }

    /**
     *  Metoda implementovana ze tridy Observer, spousti se pri zachyceni udalosti notify
     */
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
    /**
     *  Metoda pro obarvovani aktualni pozice v hernim planu
     */
    private void obarvi(Prostor aktualniProstorP) {

        String aktualniProstor = aktualniProstorP.getNazev();

        for (String key : stringMapOctagon.keySet()) {
            stringMapOctagon.get(key).setFill(null);
        }
        stringMapOctagon.get(aktualniProstor).setFill(Color.ORANGE);
    }

}
