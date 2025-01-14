import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Node;
import java.io.IOException;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class MainController {
    @FXML
    private Label welcome_text;
    @FXML
    private Button adminbutton;
    @FXML
    private Button customerbutton;
    @FXML
    private Button restaurantbutton;
    public void initialize() {

    }
    private synchronized void changeScene(Scene newScene) {
        Platform.runLater(() -> {
        Stage stage = (Stage) welcome_text.getScene().getWindow();
        stage.setScene(newScene);
    });
    }
@FXML
    public void customerbuttonpressed(ActionEvent event) {
        System.out.println("Customer Button Pressed");
        Thread customerThread = new Thread(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("customerlogin.fxml"));
                Parent root1 = loader.load();
                Scene scene1 = new Scene(root1, 800, 700);
                changeScene(scene1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        customerThread.start();
    }
@FXML
    public void adminbuttonpressed(ActionEvent event) {
        System.out.println("Admin Button Pressed");
        Thread adminThread = new Thread(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("adminscene.fxml"));
                Parent root1 = loader.load();
                Scene scene1 = new Scene(root1, 800, 700);
                changeScene(scene1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        adminThread.start();
    }
@FXML
    public void restaurantbuttonpressed(ActionEvent event) {
        System.out.println("Restaurant Button Pressed");
        Thread restaurantThread = new Thread(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("restaurantinterface.fxml"));
                Parent root1 = loader.load();
                Scene scene1 = new Scene(root1, 800, 700);
                changeScene(scene1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        restaurantThread.start();
               
    }
}

/*
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Node;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
public class MainController {
    @FXML
    private Label welcome_text;
    @FXML
      private  Button adminbutton;
   @FXML
         private Button customerbutton;
    @FXML
            private Button restaurantbutton;     
    public void initialize() {
    
    }
    public void customerbuttonpressed(ActionEvent event) throws IOException {
        System.out.println("Customer Button Pressed");
          FXMLLoader loader = new FXMLLoader(getClass().getResource("customerlogin.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1, 800, 700);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene1);
    }
    public void adminbuttonpressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adminscene.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1, 800, 700);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene1);
    }
    
    public void restaurantbuttonpressed(ActionEvent event) {
        System.out.println("restu Button Pressed");
    }
    
}
*/