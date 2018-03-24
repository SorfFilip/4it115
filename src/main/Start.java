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
        TextoveRozhrani ui = new TextoveRozhrani(hra);
//todo ->
        //        ui.hraj();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample2.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();
        controller.init(hra);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();


    }

    public static void main(String[] args) {

        launch(args);
    }




}





