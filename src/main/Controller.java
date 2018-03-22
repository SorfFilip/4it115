package main;

import java.util.Observable;
import java.util.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logika.IHra;
import logika.Prostor;


public class Controller extends GridPane implements Observer {

    @FXML private TextField textVstup;
    @FXML private TextArea textVypis;
    @FXML private Button odesli;
    @FXML private ListView<Prostor> seznamMistnosti;
    private IHra hra;

    public void odesliPrikaz() {


        String vypis = hra.zpracujPrikaz(textVstup.getText());
        textVypis.appendText("\n--------\n"+textVstup.getText()+"\n--------\n");
        textVypis.appendText(vypis);
        textVstup.setText("");

        if(hra.konecHry()) {
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

        seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
        hra.getHerniPlan().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
       System.out.println("updateeeeeeeee");
//        seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());

    }

}
