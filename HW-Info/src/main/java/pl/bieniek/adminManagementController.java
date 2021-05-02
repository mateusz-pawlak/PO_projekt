package pl.bieniek;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class adminManagementController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btReport;

    @FXML
    private Button btDelete;

    @FXML
    private Button btGetInfo;

    @FXML
    private Button btClear;

    @FXML
    private TextField tfName;

    @FXML
    void initialize() {
    }
}
