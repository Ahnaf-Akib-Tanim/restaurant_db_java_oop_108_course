import java.io.IOException;
import java.util.HashMap;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class restaurantcontroller {
    public static String loggedinid;
    @FXML
    private TableView<customizedclass3> ordered=new TableView<customizedclass3>();
    private ObservableList orderedlist=FXCollections.observableArrayList();
    @FXML
    private static Button[] orderbuttons=new Button[200];
    private static HashMap<Button,customizedclass3> orderhistory=new HashMap<Button,customizedclass3>();
    @FXML
    private TableColumn<customizedclass3, String> orderuserid=new TableColumn<customizedclass3,String>();
    @FXML
    private TableColumn<customizedclass3, String> orderrestaurantid=new TableColumn<customizedclass3,String>();
    @FXML
    private TableColumn<customizedclass3, String> orderfoodname=new TableColumn<customizedclass3,String>();
    @FXML
    private TableColumn<customizedclass3,Button> orderbutton=new TableColumn<customizedclass3, Button>();
    public static HashMap<String, SocketWrapper> socketWrappers = new HashMap<>();
    @FXML
    private TableView<food> foodtableview=new TableView<food>();
    @FXML
     private TableColumn<food, Integer > restaurantid=new TableColumn<food,Integer>();
    @FXML
    private TableColumn<food, String> foodcategory=new TableColumn<food,String>();
    @FXML
    private TableColumn<food, String> foodname=new TableColumn<food,String>();
    @FXML
    private TableColumn<food, Double> foodprice=new TableColumn<food,Double>();
    @FXML
    private Button signinbutton=new Button();
    @FXML
    private ObservableList foodlist=FXCollections.observableArrayList();
    @FXML
    private Button gobackbutton=new Button();
    @FXML
    private TextField idfield=new TextField();
    @FXML
    private PasswordField passwordfield=new PasswordField();
    @FXML
    public static Stage primarystage;
    @FXML
    private  Text alerttext=new Text();
    @FXML
    private  Text restuname=new Text();
    @FXML
    private  Text restuid=new Text();
    @FXML
    private  Text restuscore=new Text();
    @FXML
    private  Text restuprice=new Text();
    @FXML
    private Text restuzipcode=new Text();
    @FXML
    private Button logoutbutton=new Button();
    @FXML
    private ImageView pandaimage=new ImageView();
   private static Object db=new Object();
   private ExecutorService executorService = Executors.newFixedThreadPool(5);
public void initialize(){
alerttext.setVisible(false);
foodtableview.setVisible(false);
restuname.setVisible(false);
restuid.setVisible(false);
restuscore.setVisible(false);
restuprice.setVisible(false);
restuzipcode.setVisible(false);
logoutbutton.setVisible(false);
ordered.setVisible(false);
orderuserid.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getorderuserid()));
orderrestaurantid.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getorderrestaurantid()));
orderfoodname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getorderfoodname()));
orderbutton.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getbutton()));
}
@FXML
public void gobackbuttonpressed(ActionEvent event){

             try {
              /*  SocketWrapper sock=socketWrappers.get(loggedinid);
                if(sock!=null){
                sock.closeConnection();
                } */
                loggedinid=null;
                socketWrappers.clear();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("rootscene.fxml"));
                Parent root1 = loader.load();
                Scene scene1 = new Scene(root1, 800, 700);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene1);
            } catch (IOException e) {
                e.printStackTrace();
            }
}
@FXML
public void signinbuttonpressed(ActionEvent event){
    alerttext.setVisible(false);
    String id = idfield.getText();
        String pass = passwordfield.getText();
        loggedinid = id;
        if (!id.isEmpty() && !pass.isEmpty()) {
            idfield.clear();
            passwordfield.clear();
        }
        System.out.println("Sign in button pressed");
        primarystage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        System.out.println("Sign in button pressed");
        SocketWrapper socketWrapper;
        System.out.println("Sign in button pressed");
        if (socketWrappers.containsKey(id)) {
            // Use the existing SocketWrapper
            System.out.println("SocketWrapper exists for " + id);
            socketWrapper = socketWrappers.get(id);
        } else {
            System.out.println("SocketWrapper does not exist for " + id);
            try {
               
                socketWrapper = new SocketWrapper("127.0.0.1", 3333);
                 System.out.println("SocketWrapper does not exist for " + id);
                socketWrappers.put(id, socketWrapper); // Store the SocketWrapper in the map
                loggedinid = id;
                System.out.println("SocketWrapper created for restaurant" + id);
            } catch (IOException e) {
                e.printStackTrace();
                return; // Handle the exception appropriately
            }
        }
        try {
            socketWrapper.write(id);
            String[] msg = new String[1];
            msg[0] = id + "," + "server" + "," + "loginrestaurant" + "," + id + "," + pass;

            socketWrapper.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Platform.runLater(() -> {
            new ReadThreadClient(socketWrapper, this);
        });
}
public void changescreen(database dbc) {
    db= (database) dbc;
    Platform.runLater(() -> {
        idfield.setVisible(false);
        passwordfield.setVisible(false);
        signinbutton.setVisible(false);
        gobackbutton.setVisible(false);
        pandaimage.setVisible(false);
        alerttext.setVisible(false);
        restuname.setVisible(true);
        restuid.setVisible(true);
        restuscore.setVisible(true);
        restuprice.setVisible(true);
        restuzipcode.setVisible(true);
        logoutbutton.setVisible(true);
        alerttext.setText("Login successful!!");
    System.out.println("loggedinid"+loggedinid);
    restaurant r=((database) db).getrestaurant(loggedinid);
    if(r==null){
        System.out.println("res is null");
    }
    restuname.setText("Name: "+r.getname());
    System.out.println(restuname.getText());
    restuid.setText("Id: "+Integer.toString(r.getid()));
    restuscore.setText("Score: "+Double.toString(r.getscore()));
    restuprice.setText(("Price: "+r.getprice()));
    restuzipcode.setText("Zipcode: "+r.getzipcode());
     restaurantid.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getid()).asObject());
        foodcategory.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getcategory()));
        foodname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getname()));
        foodprice.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getprice()).asObject());
    food[] fo=new food[100];
    fo=((database) db).getallfoods(r.getname());
    if(fo==null){
        System.out.println("fo is null");
    }
    System.out.println("fo[0]"+fo[0].getname());
    for(int i=0;i<fo.length;i++){
        if(fo[i]==null){
            break;
        }
        foodlist.add(fo[i]);
    }
    if(foodlist==null){
        System.out.println("foodlist is null");
    }
    foodtableview.setItems(foodlist);
    foodtableview.setVisible(true);
    });
    ordered.setVisible(true);
}
public void orderrecieved(customizedclass2[] cus){
for(int i=0;i<cus.length;i++){
    if(cus[i]==null){
        break;
    }
    String str1=cus[i].getorderuserid();
    String str2=cus[i].getorderrestaurantid();
    String str3=cus[i].getorderfoodname();
    orderbuttons[i]=new Button("Deliver Food");
    orderbuttons[i].setOnAction(this::handledeliverbutton);
    customizedclass3 c=new customizedclass3(str1, str2, str3, orderbuttons[i]);
    orderhistory.put(orderbuttons[i],c);
    orderedlist.add(c);
      ordered.setItems(orderedlist);
      ordered.setVisible(false);
      ordered.setVisible(true);
}
}
@FXML
public void handledeliverbutton(ActionEvent event){

    for(int i=0;i<orderbuttons.length;i++){
        if(orderbuttons[i]==null){continue;}
             if(event.getSource()==orderbuttons[i]){
                System.out.println("okk");
                  customizedclass3 cus=orderhistory.get(orderbuttons[i]);
                  orderedlist.remove(i);//here
                  ordered.setItems(orderedlist);
                  SocketWrapper sock=socketWrappers.get(loggedinid);
                  sock.print();
                  String[] msg=new String[1];
                  msg[0]=cus.getorderuserid()+","+cus.getorderfoodname()+","+"delivery";
                  System.out.println(msg[0]);
                  try {
                    sock.write(msg);
                    System.out.println("ok still now1"+cus.getorderfoodname());
                } catch (IOException e) {
                    System.out.println("pronblem here");
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                 break;
             }
    }
}
@FXML
public void logoutbuttonpressed(ActionEvent event){
    executeInBackground(() -> {
        Platform.runLater(() -> {
                 try {
                SocketWrapper sock=socketWrappers.get(loggedinid);
                if(sock!=null){
                sock.closeConnection();
                } 
             FXMLLoader loader = new FXMLLoader(getClass().getResource("rootscene.fxml"));
             Parent root1 = loader.load();
             Scene scene1 = new Scene(root1, 800, 700);
             primarystage.setScene(scene1);
         } catch (IOException e) {
             e.printStackTrace();
         }
             });
     });
}

public void changealerttext() {
    Platform.runLater(() -> {
        alerttext.setVisible(true);
    });
}
private void executeInBackground(Runnable task) {
    executorService.execute(task);
}

}