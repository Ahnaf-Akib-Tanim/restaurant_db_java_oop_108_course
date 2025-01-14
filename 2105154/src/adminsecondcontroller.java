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

public class adminsecondcontroller {
   
    @FXML
    private Button gobackbutton;
    @FXML
    private Button addfoodbutton;
    @FXML
    private Button addrestaurantbutton;
    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane2;
    @FXML
    private javafx.scene.control.TextField insertrestaurantname; 
    @FXML
    private TextField insertfoodcategory;
    @FXML
    private TextField insertfoodname;
    @FXML
    private TextField insertfoodprice;
    @FXML
    private Button okbutton1;
    @FXML
    private Button okbutton2;
    @FXML
    private TextField insertrestaurantid;
    @FXML
    private TextField insertrestaurantname2;
    @FXML
    private TextField insertrestaurantscore;
    @FXML
    private TextField insertrestaurantzipcode;
    @FXML
    private TextField insertrestaurantprice;
    @FXML
    private TextField foodcategory1;
    @FXML
    private TextField foodcategory2;
    @FXML
    private TextField foodcategory3;
    @FXML
    private Label alerttext2;
    
    @FXML
    public void initialize() {
        
    }
    
    @FXML
    public void gobackbuttonpressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("rootscene.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1, 800, 700);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene1);
       cleartextfield();

    }
    @FXML
    public void addfoodbuttonpressed(ActionEvent event) throws IOException {
        pane1.setVisible(true);
        pane2.setVisible(false);
        alerttext2.setVisible(false);
        cleartextfield();
        
    }
    @FXML
    public void okbutton1pressed(ActionEvent event) throws IOException {
        String str = insertrestaurantname.getText();
        String str1 = insertfoodcategory.getText();
        String str2 = insertfoodname.getText();
        String str3 = insertfoodprice.getText();
        double temp = Double.parseDouble(str3);
        database restaurantdatabase = new database();
        if (!restaurantdatabase.restunamecheck(str)) {
            alerttext2.setText("Restaurant Name Not Found");
              alerttext2.setVisible(true);
        } else if (restaurantdatabase.samenamecatinrestu(str, str2, str1)) {
            alerttext2.setText(" Food with the same name already exists in this category");
              alerttext2.setVisible(true);
        } else {
            restaurantdatabase.addfood(str, str1, str2, temp);
                restaurantdatabase.exitdatabase();                    
            alerttext2.setText("Food Added Successfully");
            alerttext2.setVisible(true);
        }
        cleartextfield();
    }
    @FXML
    public void addrestaurantbuttonpressed(ActionEvent event) throws IOException {
        cleartextfield();
        pane2.setVisible(true);
        pane1.setVisible(false);
        alerttext2.setVisible(false);
    }
    @FXML
    public void okbutton2pressed(ActionEvent event) throws IOException {
       
        database restaurantdatabase = new database();
        String str = insertrestaurantid.getText();
        String str1 = insertrestaurantname2.getText();
        String str2 = insertrestaurantscore.getText();
        String str3 = insertrestaurantzipcode.getText();
        String str4 = insertrestaurantprice.getText();
        String str5 = foodcategory1.getText();
        String str6 = foodcategory2.getText();
        String str7 = foodcategory3.getText();
        double temp = Double.parseDouble(str2);
        int temp1 = Integer.parseInt(str);
        if (restaurantdatabase.restuidcheck(temp1)) {
            alerttext2.setText("Restaurant ID already exists");
              alerttext2.setVisible(true);  
        } else if (restaurantdatabase.restunamecheck(str1)) {
            alerttext2.setText("Restaurant Name already exists");  
              alerttext2.setVisible(true);
        } else {
            alerttext2.setText("Restaurant Added Successfully");
              alerttext2.setVisible(true);
            if (foodcategory2.getText().isEmpty()) {
                restaurantdatabase.addrestaurant(temp1, str1, temp, str4, str3, str5);
            } else if (foodcategory3.getText().isEmpty()) {
                restaurantdatabase.addrestaurant(temp1, str1, temp, str4, str3, str5, str6);
            } else {
                restaurantdatabase.addrestaurant(temp1, str1, temp, str4, str3, str5, str6, str7);
            }
        }
        restaurantdatabase.exitdatabase();
         cleartextfield();
    }
    public void cleartextfield(){
         insertfoodcategory.clear();
        insertfoodname.clear();
        insertfoodprice.clear();
        insertrestaurantid.clear();
        insertrestaurantname.clear();
        insertrestaurantname2.clear();
        insertrestaurantprice.clear();
        insertrestaurantscore.clear();
        insertrestaurantzipcode.clear();
        foodcategory1.clear();
        foodcategory2.clear();
        foodcategory3.clear();
    }
}
