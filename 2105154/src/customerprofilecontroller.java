import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class customerprofilecontroller {
    @FXML
    private Text profilename;

    @FXML
    private Text profileid;

    @FXML
    private Text profileage;

    @FXML
    private Text profilefavfood;

    @FXML
    private Text profilefavres;

    public void initialize() {
       String gotloggedinid = customermaincontroller.loggedinid;
        String[] str = new String[6];
        try (BufferedReader br = new BufferedReader(new FileReader("customerlogin.txt"))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                str = line.split(",");
                if (str.length >= 1 && str[0].equals(gotloggedinid)) {
                    // Assign the elements to str[0] to str[5]
                    String str0 = str[0];
                    String str1 = str.length >= 2 ? str[1] : "";
                    String str2 = str.length >= 3 ? str[2] : "";
                    String str3 = str.length >= 4 ? str[3] : "";
                    String str4 = str.length >= 5 ? str[4] : "";
                    String str5 = str.length >= 6 ? str[5] : "";
                    System.out.println("str[0]: " + str0);
                    System.out.println("str[1]: " + str1);
                    System.out.println("str[2]: " + str2);
                    System.out.println("str[3]: " + str3);
                    System.out.println("str[4]: " + str4);
                    System.out.println("str[5]: " + str5);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        profilename.setText(str[2]);
        profileid.setText(str[0]);
        profileage.setText(str[3]);
        profilefavfood.setText(str[4]);
        profilefavres.setText(str[5]);
    }

    @FXML
    public void logoutimageclicked2() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customerinterface.fxml"));
            Parent root1 = loader.load();
            Scene scene1 = new Scene(root1, 800, 700);
            Platform.runLater(() -> {
                Stage stage = customermaincontroller.primarystage;
                stage.setScene(scene1);
            });
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error while loading customerinterface.fxml: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

}

/*
 * import java.io.IOException;
import java.util.HashMap;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class customerprofilecontroller {
     @FXML
    private  Text profilename;

    @FXML
    private  Text profileid;

    @FXML
    private Text profileage;

    @FXML
    private  Text profilefavfood;

    @FXML
    private  Text profilefavres;

         public void initialize() {
            HashMap< String, SocketWrapper> clientMap1=customermaincontroller.socketWrappers;
            String id=customermaincontroller.loggedinid;
            new ReadThreadClient(clientMap1.get(id), this);
            String[] msg = new String[1];
            msg[0] = "getprofile";
            System.out.println("Request for profile is on the way to the server");

            // Use the SocketWrapper associated with the logged-in user
            System.out.println("loggedinid: " + id);
            SocketWrapper socketWrapper = clientMap1.get(id);
             if (socketWrapper != null) {
                System.out.println("socketwrapper is not null");
                try {
                  socketWrapper.write(msg);
                } catch (IOException e) {
                  e.printStackTrace();
                }
                System.out.println("Request for profile is sent to the server");
            } else {
                System.out.println("socketwrapper is null");
            }

         }
  @FXML
    public void logoutimageclicked2(){
 
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("customerinterface.fxml"));
                Parent root1 = loader.load();
                Scene scene1 = new Scene(root1, 800, 700);
                Platform.runLater(() -> {
                    Stage stage = customermaincontroller.primarystage;
                    stage.setScene(scene1);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        

    }
    public void getprofileinfo(String[] info) {
        String[] str = info[1].split(",");
            System.out.println("profile info from server is received"+str[0]+str[1]+str[2]+str[3]+str[4]+str[5]);
            profilename.setText(str[2]);
            profileid.setText(str[0]);
            profileage.setText(str[3]);
            profilefavfood.setText(str[4]);
            profilefavres.setText(str[5]);
    }


}

 */