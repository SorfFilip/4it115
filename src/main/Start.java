/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;


public class Start extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        IHra hra = new Hra();
        new TextoveRozhrani(hra);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("gui.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();
        controller.init(hra);

        primaryStage.setTitle("Adventura - Sorf00");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();


    }

    public static void main(String[] args) {
        if (args.length != 0 && args[0].equals("text")) {
            IHra hra = new Hra();
            TextoveRozhrani ui = new TextoveRozhrani(hra);
            ui.hraj();
        } else {
            launch(args);
        }
    }


}





