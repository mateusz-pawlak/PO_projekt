package pl.bieniek;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sendInfo.fxml"));
        primaryStage.setTitle("Client info");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        SYSTEM os = new SYSTEM();
        System.out.println("----------OS-----------");
        System.out.println(os.manufacturer+" "+os.family+" "+ os.ver);
        CPU cpu1 = new CPU();
        System.out.println("----------CPU----------");
        System.out.println(cpu1.name);
        System.out.println("Częstotliwość: "+cpu1.freq+" GHz");
        GPU gpu1 = new GPU();
        System.out.println("----------GPU----------");
        System.out.println("Nazwa: "+gpu1.Name);
        System.out.println("VRAM: "+gpu1.VRAM+" GB");
        RAM ram1 = new RAM();
        System.out.println("----------RAM----------");
        System.out.println("Ilość RAMu: "+ram1.total+" GB");
    }
}
