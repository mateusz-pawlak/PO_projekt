package pl.bieniek;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pl.bieniek.database.DatabaseHandler;

public class sendInfoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfName;

    @FXML
    private Button btSendInfo;

    @FXML
    private Text lbDisplayInfo;

    @FXML
    private Button btAdminLogin;

    @FXML
    void initialize() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        btAdminLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("adminLogin.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Client info");
                    stage.show();
                    // Hide this current window
                    btAdminLogin.getScene().getWindow().hide();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btSendInfo.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = tfName.getText().trim().toLowerCase();
                if (!name.equals("")) {
                    ResultSet set = databaseHandler.checkName(name);
                    try {
                        if (!set.next()) {
                            databaseHandler.addNewInfo(name, getHardwareInfo());
                            lbDisplayInfo.setText("You'r PC info added to database.");
                        } else {
                            databaseHandler.updateInfo(set, getHardwareInfo());
                            lbDisplayInfo.setText("You'r PC info updated in database.");
                        }
                    } catch (SQLException error) {
                        error.printStackTrace();
                    }
                } else {
                    lbDisplayInfo.setText("Input correct name!");
                }
            }
        });
    }

    public String[] getHardwareInfo (){
        String[] info = new String[4];
        SYSTEM os = new SYSTEM();
        CPU cpu1 = new CPU();
        GPU gpu1 = new GPU();
        RAM ram1 = new RAM();
        info[0] = os.manufacturer+" "+os.family+" "+ os.ver;
        info[1] = cpu1.name + " "+ cpu1.freq + "GHz";
        info[2] = gpu1.Name + " " + gpu1.VRAM + " GB VRAM";
        info[3] = ram1.total + " GB";

        return info;
    }
}