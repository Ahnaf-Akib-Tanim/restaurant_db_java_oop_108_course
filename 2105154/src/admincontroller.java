import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class admincontroller {
    @FXML
    private TextField loginid;
    @FXML
    private TextField loginpass;
    @FXML
    private Button submitbuButton;
        @FXML
    private AnchorPane loginidpass;
    @FXML
    private Label alerttext;
    @FXML
    private Button gobackbutton2;
    public void initialize() {
        
    }
    @FXML
    public void submitbuttonpressed(ActionEvent event) throws IOException {
        String str=loginid.getText();
        loginid.clear();

        String str1=loginpass.getText();
        loginpass.clear();
        if(str.equals("2105154") && str1.equals("admin"))
        {
            alerttext.setText("\n");
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminsecondscene.fxml"));
                   System.out.println("Login Successful");
            Parent root1 = loader.load();
            Scene scene1 = new Scene(root1, 800, 700);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene1);
        }
        else
        {
            alerttext.setText("Login Unsuccessful");
        }
    }
    @FXML
    public void gobackbutton2pressed(ActionEvent event) throws IOException {
        loginid.clear();
        loginpass.clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("rootscene.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1, 800, 700);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene1);
    }
    
    
}
