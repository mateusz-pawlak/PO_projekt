package pl.bieniek;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pl.bieniek.database.DatabaseHandler;

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
    private Text lbDisplayInfo;

    @FXML
    void initialize() {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        btDelete.setOnAction(event -> {
            String name = tfName.getText().trim().toLowerCase();
            if(!name.equals("")){
                Employee employee = new Employee(name);
                databaseHandler.deleteUser(employee);
                lbDisplayInfo.setText(employee.getName()+ " correctly deleted.");
            }else{
                tfName.setPromptText("Input correct name!");
            }
        });

        btClear.setOnAction(event -> {
            try {
                databaseHandler.clearDatabase();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });

        btGetInfo.setOnAction(event -> {

        });

        btReport.setOnAction(event -> {

        });
    }
}
