import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.util.Duration;
public class customermaincontroller {

    @FXML
    private Text alerttext;
     @FXML
     private AnchorPane searchboxpane;
    @FXML
    private Button submibutton;

    @FXML
    private TextField loginid;

    @FXML
    private TextField loginpass;

    @FXML
    private Button signupbutton;

    @FXML
    private Button signupsubmitbutton;
    @FXML
    private Button signupgobackbutton;

    @FXML
    private TextField signupname;
    @FXML
    private  Text beeptxt=new Text();
    @FXML
    AnchorPane lastone;

    @FXML
    private TextField signupid;

    @FXML
    private TextField signuppass;

    @FXML
    private TextField signupage;

    @FXML
    private TextField signupfavfood;

    @FXML
    private TextField signupfavres;

    @FXML
    private Button logingobackbutton;

    @FXML
    private Button submitButton;
    @FXML
    private Button confirmcart =new Button();

    @FXML
    public  Text logouttext;

    @FXML
    private Text profiletext;
     @FXML
    private static ImageView profileimage;
    @FXML
    private  ImageView cartimage=new ImageView();
    @FXML
    private static ImageView profileimage2;
    @FXML
    private AnchorPane profilepane;
    @FXML
    private static Button[] foodbuttons=new Button[200];
    private static String currentsearchedrestaurant=new String("");
    @FXML
     private ChoiceBox<String> searchchoicebox=new ChoiceBox<String>();
     @FXML
     private ChoiceBox<String> allrestaurantschoicebox=new ChoiceBox<String>();
     @FXML
     private ChoiceBox<String> searchfoodchoicebox=new ChoiceBox<String>();
    @FXML
    private TableView<restaurant> tableview=new TableView<restaurant>();
    @FXML
    private TableView<food> foodtableview=new TableView<food>();
    @FXML
    private TableView<customedclass> customtableview=new TableView<customedclass>();
    @FXML
    private TableView<customizedclass2> cart=new TableView<customizedclass2>();
    private static HashMap<Button,customedclass> buttontocustomedclass=new HashMap<Button,customedclass>();
     @FXML
    private TableColumn<restaurant, Integer > id=new TableColumn<restaurant,Integer>();
    @FXML
    private TableColumn<customizedclass2, String> orderuserid=new TableColumn<customizedclass2,String>();
    @FXML
    private TableColumn<customizedclass2, String> orderrestaurantid=new TableColumn<customizedclass2,String>();
    @FXML
    private TableColumn<customizedclass2, String> orderfoodname=new TableColumn<customizedclass2,String>();
    @FXML
    private TableColumn<customedclass, String> customfoodname=new TableColumn<customedclass,String>();
    @FXML
    private TableColumn<customedclass, String> customfoodprice=new TableColumn<customedclass,String>();
    @FXML
    private TableColumn<customedclass, String> customfoodcategory=new TableColumn<customedclass,String>();
    @FXML
    private TableColumn<customedclass, Button> customedbutton=new TableColumn<customedclass,Button>();
    @FXML
    private TableColumn<food, Integer > restaurantid=new TableColumn<food,Integer>();
    @FXML
    private TableColumn<restaurant, String> name=new TableColumn<restaurant,String>();
    @FXML
    private TableColumn<food, String> foodcategory=new TableColumn<food,String>();
    @FXML
    private TableColumn<food, String> foodname=new TableColumn<food,String>();
    @FXML
    private TableColumn<food, Double> foodprice=new TableColumn<food,Double>();
    @FXML
    private TableColumn<restaurant, Double> score=new TableColumn<restaurant,Double>();
    @FXML
    private TableColumn<restaurant, String> price=new TableColumn<restaurant,String>();
    @FXML
    private TableColumn<restaurant, String> zipcode=new TableColumn<restaurant,String>();
    @FXML
    private TableColumn<restaurant, String> category1=new TableColumn<restaurant,String>();
    @FXML
    private TableColumn<restaurant, String> category2=new TableColumn<restaurant,String>();
    @FXML
    private TableColumn<restaurant, String> category3=new TableColumn<restaurant,String>();
   @FXML
   Button applybutton=new Button();
   @FXML
   private TextField restaurantnamefield=new TextField();
   @FXML
   private TextField foodnamefield=new TextField();
    @FXML
   private TextField foodresnamefield=new TextField();
    @FXML
   private TextField foodcategoryfield=new TextField();
    @FXML
   private TextField foodpricefield=new TextField();
   @FXML
   private TextField restaurantcategoryfield=new TextField();
   public static int flagused=0;
   @FXML
    private TextField restaurantscorefield=new TextField();
    @FXML
    private TextField restaurantpricefield=new TextField();
    @FXML
    private TextField restaurantzipcodefield=new TextField();
    @FXML
    private AnchorPane restaurantviewPane;
    @FXML
    private Text nothingfound=new Text();
    @FXML
    private ImageView nothingfoundalert=new ImageView();
    
    @FXML
    private GridPane categorywisepane=new GridPane();
    @FXML
    private GridPane totalfoodspane=new GridPane();
    private ObservableList restulist=FXCollections.observableArrayList();
    private ObservableList foodlist=FXCollections.observableArrayList();
    private ObservableList orderlist=FXCollections.observableArrayList();
    private ObservableList cartlist=FXCollections.observableArrayList();
    public static HashMap<String, SocketWrapper> socketWrappers = new HashMap<>();
    public static String loggedinid;
    public static Stage primarystage;
    public static HashMap<String, SocketWrapper> clientMap;
    private customerclass customer = new customerclass();
   public static database db=new database();
    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    public void initialize() {
        String[] searchchoiceboxlist ={"By Name", "By Score", "By Category", "By Price", "By ZipCode", "By Categorywise List"};
        String[] allrestaurantschoiceboxlist =new String[10];
        database dbc=new database();
        allrestaurantschoiceboxlist=dbc.getrestunames();
        String[] searchfoodchoiceboxlist ={"By Name", "By Category", "By Price","By Res and Food Name","By Cat in Res","By Price in Res","Csotliest Food in a res","Number of items in a Restaurant"};
        tableview.setVisible(false);
        foodtableview.setVisible(false);
        customtableview.setVisible(false);
        cart.setVisible(false);
        cartimage.setVisible(false);
        confirmcart.setVisible(false);
        applybutton.setVisible(false);
        restaurantnamefield.setVisible(false);
        foodnamefield.setVisible(false);
        nothingfound.setVisible(false);
        nothingfoundalert.setVisible(false);
        restaurantcategoryfield.setVisible(false);
        foodresnamefield.setVisible(false);
        foodcategoryfield.setVisible(false);
        foodpricefield.setVisible(false);
        restaurantscorefield.setVisible(false);
        restaurantpricefield.setVisible(false);
        restaurantzipcodefield.setVisible(false);
    searchchoicebox.getItems().addAll(searchchoiceboxlist);
    allrestaurantschoicebox.getItems().addAll(allrestaurantschoiceboxlist);
    searchfoodchoicebox.getItems().addAll(searchfoodchoiceboxlist);
    searchchoicebox.setOnAction(this::handleSearchOption);
    allrestaurantschoicebox.setOnAction(this::handleallrestaurantschoicebox);
    searchfoodchoicebox.setOnAction(this::handlefoodsearchoption);
         id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getid()).asObject());
         customfoodname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getcustomfoodname()));
         customfoodprice.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getcustomfoodprice()));
         customfoodcategory.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getcustomfoodcategory()));
            customedbutton.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getbutton()));
            orderuserid.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getorderuserid()));
            orderrestaurantid.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getorderrestaurantid()));
            orderfoodname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getorderfoodname()));
         restaurantid.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getid()).asObject());
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getname()));
        foodcategory.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getcategory()));
        foodname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getname()));
        foodprice.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getprice()).asObject());
        score.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getscore()).asObject());
        price.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getprice()));
        zipcode.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getzipcode()));
        category1.setCellValueFactory(new PropertyValueFactory<restaurant,String>("category1"));
             category2.setCellValueFactory(new PropertyValueFactory<restaurant,String>("category2"));
             category3.setCellValueFactory(new PropertyValueFactory<restaurant,String>("category3"));
             //here is the grid pane below 
             categorywisepane.setHgap(90);
             totalfoodspane.setHgap(90);
       categorywisepane.setVgap(10);
         totalfoodspane.setVgap(10);
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();
        ColumnConstraints col4 = new ColumnConstraints();
        col1.setPercentWidth(90); 
       col3.setPercentWidth(90);
col2.setPercentWidth(90); 
col4.setPercentWidth(90);
        categorywisepane.getColumnConstraints().addAll(col1, col2);
        totalfoodspane.getColumnConstraints().addAll(col3, col4);
        categorywisepane.setVisible(false);
        totalfoodspane.setVisible(false);
    }
    @FXML
    public void logingobackbuttonpressed(ActionEvent event) {
      

             try {
               if(loggedinid!=null){
                 SocketWrapper sock=socketWrappers.get(loggedinid);
                sock.closeConnection();
                loggedinid=null;
                socketWrappers.clear();
               }
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
    public void signupgobackbuttonpressed(ActionEvent event) {
         
        executeInBackground(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("customerlogin.fxml"));
                Parent root1 = loader.load();
                Scene scene1 = new Scene(root1, 800, 700);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Platform.runLater(() -> {
                    stage.setScene(scene1);
                    alerttext.setVisible(false);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @FXML
    private void handleallrestaurantschoicebox(ActionEvent event){
        foodnamefield.setVisible(false);
        foodcategoryfield.setVisible(false);
        foodpricefield.setVisible(false);
        foodresnamefield.setVisible(false);
        restaurantnamefield.setVisible(false);
        restaurantcategoryfield.setVisible(false);
        restaurantscorefield.setVisible(false);
        restaurantpricefield.setVisible(false);
        restaurantzipcodefield.setVisible(false);
        applybutton.setVisible(false);
         cart.setVisible(false);
        cartimage.setVisible(false);
        confirmcart.setVisible(false);
                  tableview.setVisible(false);
        foodtableview.setVisible(false);
        customtableview.setVisible(true);
        restulist.clear();
        foodlist.clear();
        orderlist.clear();
        cartlist.clear();
        foodtableview.setVisible(false);
        categorywisepane.setVisible(false);
        totalfoodspane.setVisible(false);
        applybutton.setVisible(false);
        restaurantnamefield.setVisible(false);
        foodnamefield.clear();
        foodpricefield.clear();
        foodcategoryfield.clear();
        foodresnamefield.clear();
        tableview.setVisible(false);
        nothingfound.setVisible(false);
        nothingfoundalert.setVisible(false);
        String selectedOption = allrestaurantschoicebox.getValue(); 
        System.out.println(selectedOption+"selected option");
        currentsearchedrestaurant=selectedOption;
        System.out.println("currentsearchedrestu"+currentsearchedrestaurant);
        food[] fo=new food[100];
        customedclass[] customed=new customedclass[200];
        database dbc=new database();
        fo=dbc.getallfoods(selectedOption);
        System.out.println("fo[0]"+fo[0].getname());
        for(int i=0;i<fo.length;i++){
            if(fo[i]==null){break;}
           String stri1=fo[i].getname();
              String stri2=fo[i].getcategory();
                String stri3=Double.toString(fo[i].getprice());
                foodbuttons[i]=new Button("Add to Cart");
                foodbuttons[i].setOnAction(this::handleorderbutton);
                customed[i]=new customedclass(stri1,stri3,stri2,foodbuttons[i]);
                buttontocustomedclass.put(foodbuttons[i],customed[i]);
                orderlist.add(customed[i]);
        }
        customtableview.setItems(orderlist);
    }
    @FXML
    public void handleorderbutton(ActionEvent event){
         cart.setVisible(false);
        cartimage.setVisible(true);
        confirmcart.setVisible(true);
               for(int i=0;i<foodbuttons.length;i++){
                if(event.getSource()==foodbuttons[i]){
                   foodbuttons[i].setText("Added to cart");
                     foodbuttons[i].setDisable(true);
                     if(foodbuttons[i]==null){
                        System.out.println("button null at "+i);
                     }
                    customedclass c=buttontocustomedclass.get(foodbuttons[i]);//
                    if(buttontocustomedclass.get(foodbuttons[i])==null){
                      System.out.println("its null");
                    }
                     String first=loggedinid;
                     database dbc=new database(); 
                     System.out.println("currentsearched res"+currentsearchedrestaurant);
                     String second=dbc.getrestaurantid(currentsearchedrestaurant);
                     System.out.println("loggedinid"+loggedinid);
                        String third=c.getcustomfoodname();
                        System.out.println("customfood name or ordered food"+c.getcustomfoodname());
                        customizedclass2 c2=new customizedclass2(first,second,third);
                        cartlist.add(c2);
                        if(c2==null || cartlist.isEmpty()){
                            System.out.println("something is wrong");
                        }
                }   
               }
    }
    @FXML
    public void cartselected(){
        System.out.println("ok still now");
        customtableview.setVisible(false);
        System.out.println("ok still now");
        cart.setItems(cartlist);
        System.out.println("ok still now");
        cart.setVisible(true);
        System.out.println("ok still now");
    }
    @FXML
    public void confirmcartpressed(ActionEvent event){
        for(int i=0;i<foodbuttons.length;i++){
            if(foodbuttons[i]==null) break;
                   foodbuttons[i].setText("Add to cart");
                   foodbuttons[i].setDisable(false);
        }
        customizedclass2[] cust=new customizedclass2[200];
        int i=0;
       for(i=0;i<cartlist.size();i++){
              Object ob=cartlist.get(i);
              if(ob!=null){
                cust[i]=(customizedclass2)ob;
              }
       }
        try {
            SocketWrapper sock = socketWrappers.get(loggedinid);
            sock.write(cust);
        } catch (IOException e) {
            // Handle the IOException here
            e.printStackTrace(); // For demonstration purposes; you can replace this with your error-handling logic
        }
//here implement socketwriting;
cartlist.clear();
cart.setVisible(false);
cartimage.setVisible(false);
confirmcart.setVisible(false);
    }

    @FXML
    private void handleSearchOption(ActionEvent event) {
         cart.setVisible(false);
        cartimage.setVisible(false);
        confirmcart.setVisible(false);
         tableview.setVisible(false);
        foodtableview.setVisible(false);
        customtableview.setVisible(false);
        cart.setVisible(false);
        orderlist.clear();
        restulist.clear();
        foodlist.clear();
        cartlist.clear();
        foodtableview.setVisible(false);
        categorywisepane.setVisible(false);
        totalfoodspane.setVisible(false);
        applybutton.setVisible(true);
        restaurantnamefield.setVisible(true);
        foodnamefield.clear();
        foodpricefield.clear();
        foodcategoryfield.clear();
        foodresnamefield.clear();
        tableview.setVisible(false);
        nothingfound.setVisible(false);
        nothingfoundalert.setVisible(false);
        String selectedOption = searchchoicebox.getValue();

        if (selectedOption != null) {
            switch (selectedOption) {
                case "By Name":
                    System.out.println("By Name");
                    socketWrappers.get(loggedinid).print();//debug
                     restaurantnamefield.setVisible(true);
                restaurantcategoryfield.setVisible(false);
                restaurantzipcodefield.setVisible(false);
                restaurantpricefield.setVisible(false);
                restaurantscorefield.setVisible(false);
                    flagused=1;    
                    break;
                case "By Score":
                restaurantnamefield.setVisible(false);
                restaurantcategoryfield.setVisible(false);
                restaurantzipcodefield.setVisible(false);
                restaurantpricefield.setVisible(false);
                restaurantscorefield.setVisible(true);
                    System.out.println("By Score");
                    nothingfound.setVisible(false);
                    nothingfoundalert.setVisible(false);
                    if(nothingfound.isVisible()==true)  {
                        System.out.println("nothingfound is visible");
                    }
                    flagused=2;
                    break;
                case "By Category":
                 restaurantnamefield.setVisible(false);
                restaurantcategoryfield.setVisible(true);
                restaurantzipcodefield.setVisible(false);
                restaurantpricefield.setVisible(false);
                restaurantscorefield.setVisible(false);
                      System.out.println("By Category");
                      flagused=3;
                    break;
                case "By Price":
                 restaurantnamefield.setVisible(false);
                restaurantcategoryfield.setVisible(false);
                restaurantzipcodefield.setVisible(false);
                restaurantpricefield.setVisible(true);
                restaurantscorefield.setVisible(false);
                   System.out.println("By Price");
                     flagused=4;
                    break;
                case "By ZipCode":
                 restaurantnamefield.setVisible(false);
                restaurantcategoryfield.setVisible(false);
                restaurantzipcodefield.setVisible(true);
                restaurantpricefield.setVisible(false);
                restaurantscorefield.setVisible(false);
                    System.out.println("By ZipCode");
                    flagused=5;
                    break;
                case "By Categorywise List":
                    System.out.println("By Categorywise List");
                    restaurantnamefield.setVisible(false);
                restaurantcategoryfield.setVisible(false);
                restaurantzipcodefield.setVisible(false);
                restaurantpricefield.setVisible(false);
                restaurantscorefield.setVisible(false);
                flagused=6;
                showcategorywise();
                    break;
                default:
                    break;
            }
        }
    }
    @FXML
    public void handlefoodsearchoption(ActionEvent event){
         cart.setVisible(false);
        cartimage.setVisible(false);
        confirmcart.setVisible(false);
        tableview.setVisible(false);
        foodtableview.setVisible(false);
        customtableview.setVisible(false);
        orderlist.clear();
        foodlist.clear();
        restulist.clear();
        cartlist.clear();
        System.out.println("in the handle food search option");
        applybutton.setVisible(true);
        restaurantnamefield.clear();
        restaurantpricefield.clear();
        restaurantscorefield.clear();
        restaurantzipcodefield.clear();
        restaurantcategoryfield.clear();
        foodnamefield.setVisible(false);
        foodcategoryfield.setVisible(false);
        foodpricefield.setVisible(false);
        foodresnamefield.setVisible(false);
        foodtableview.setVisible(false);
        nothingfound.setVisible(false);
        nothingfoundalert.setVisible(false);
        String selectedOption = searchfoodchoicebox.getValue();

        if (selectedOption != null) {
            switch (selectedOption) {
                case "By Name":
                    System.out.println("By Name in foood");
                    foodnamefield.setVisible(true);
                    foodcategoryfield.setVisible(false);
                    foodpricefield.setVisible(false);
                    foodresnamefield.setVisible(false);
                    flagused=7;    
                    break;
                case "By Category":
                   foodnamefield.setVisible(false);
                    System.out.println("By cat in foood");
                    foodcategoryfield.setVisible(true);
                    foodpricefield.setVisible(false);
                    foodresnamefield.setVisible(false);
                    nothingfound.setVisible(false);
                    nothingfoundalert.setVisible(false);
                    flagused=8;
                    break;
                case "By Price":
                foodnamefield.setVisible(false);
                 System.out.println("By price in foood");
                    foodcategoryfield.setVisible(false);
                    foodpricefield.setVisible(true);
                    foodresnamefield.setVisible(false);
                      flagused=9;
                    break;
                case "By Res and Food Name":
                foodnamefield.setVisible(true);
                 System.out.println("By both Name in foood");
                    foodcategoryfield.setVisible(false);
                    foodpricefield.setVisible(false);
                    foodresnamefield.setVisible(true);
                     flagused=10;
                    break;
                case "By Cat in Res":
                 foodnamefield.setVisible(false);
                  System.out.println("By cat in a res in foood");
                    foodcategoryfield.setVisible(true);
                    foodpricefield.setVisible(false);
                    foodresnamefield.setVisible(true);
                    System.out.println("By ZipCode");
                    flagused=11;
                    break;
                case "By Price in Res":
                    foodnamefield.setVisible(false);
                     System.out.println("By price and res in foood");
                    foodcategoryfield.setVisible(false);
                    foodpricefield.setVisible(true);
                    foodresnamefield.setVisible(true);
                flagused=12;
                    break;
                    case "Csotliest Food in a res":
                  foodnamefield.setVisible(false);
                    foodcategoryfield.setVisible(false);
                    foodpricefield.setVisible(false);
                    foodresnamefield.setVisible(true);
                       flagused=13;

                    break;
                    case "Number of items in a Restaurant":
                        foodnamefield.setVisible(false);
                    foodcategoryfield.setVisible(false);
                    foodpricefield.setVisible(false);
                    foodresnamefield.setVisible(false);
                       flagused=0;
                        showtotalfoods();
                    break;
                default:
                    break;
            }
        }


    }
    public void showcategorywise(){
         String[] allinfo=new String[50];
         allinfo=db.showcategorywiserestaurants();
         System.out.println(allinfo[0]);
              categorywisepane.add(new Label("Categories"), 0, 0); 
        categorywisepane.add(new Label("Restaurants"), 1, 0);
        int i=1;
          for(int j=0;j<allinfo.length;j++){
            if(allinfo[j]==null) break;
                  System.out.println(allinfo[j]+j);
              String [] info=new String[2];
              int index=allinfo[j].indexOf(":");
                info[0]=allinfo[j].substring(0,index);
                info[1]=allinfo[j].substring(index+1);
                categorywisepane.add(new Label(info[0]), 0,i);
                categorywisepane.add(new Label(info[1]), 1,i);  i++;
        }
    categorywisepane.setVisible(true);
    flagused=0;
    }
    public void showtotalfoods(){
                  String [] allinfo=new String[50];
            allinfo=db.restaurantwithtotalfoods();
            totalfoodspane.add(new Label("Restaurant"), 0, 0);
            totalfoodspane.add(new Label("Total Food Items"), 1, 0);
            int i=1;
          for(int j=0;j<allinfo.length;j++){
            if(allinfo[j]==null){System.out.println("null found inside allrestu food total");break;}
              String [] info=new String[100];
              int index=allinfo[j].indexOf(":");
                info[0]=allinfo[j].substring(0,index);
                info[1]=allinfo[j].substring(index+1);
                System.out.println(allinfo[j]+"inside allrestu food total");
               System.out.println(info[0]+"inside allrestu food total");
                System.out.println(info[1]+"inside allrestu food total");
                totalfoodspane.add(new Label(info[0]), 0,i);
                totalfoodspane.add(new Label(info[1]), 1,i);  i++;
        }
    totalfoodspane.setVisible(true);
    flagused=0;
    }
    public void applybuttonpressed(ActionEvent event){
         cart.setVisible(false);
        cartimage.setVisible(false);
        confirmcart.setVisible(false);
        customtableview.setVisible(false);
        orderlist.clear();
        int gotit=flagused;
                if(flagused==1){
            String recieved=new String();
        recieved=restaurantnamefield.getText();
        System.out.println(recieved+"searched by name");
         restaurantscorefield.clear();
        restaurantnamefield.clear();
        restaurantcategoryfield.clear();
        restaurantzipcodefield.clear();
        restaurantpricefield.clear();
        restaurantnamefield.setVisible(false);
        String[] attributes=db.searchrestaurantbyname(recieved);//here
                       if(attributes[0].equals("No such restaurant with this name")){
                                   nothingfound.setVisible(true);
                                      nothingfoundalert.setVisible(true);
                                      restaurantnamefield.clear();
                       }
                       else{
                           String[] names=new String[1];
                           names[0]=recieved;
                           System.out.println(names[0]+"searched by name names");
                           settableview(names);       
                       }
                      flagused=0; 
        }
        else if( flagused==2){
              String recieved=new String();
        recieved=restaurantscorefield.getText();
        restaurantscorefield.clear();
        restaurantnamefield.clear();
        restaurantcategoryfield.clear();
        restaurantzipcodefield.clear();
        restaurantpricefield.clear();
        restaurantscorefield.setVisible(false);
          String[] sc= recieved.split(",");
               Double a=Double.parseDouble(sc[0]);
                Double b=Double.parseDouble(sc[1]);
                String[] restunameswithinscorerange=db.displayrestaurantswithinrange(a,b);
                if(restunameswithinscorerange[0].equals("No such restaurant with this score range")){
                    System.out.println("No such restaurant with this score range");
                    restaurantscorefield.clear();
                    restaurantscorefield.setVisible(true);
                    nothingfound.setVisible(true);
                    nothingfoundalert.setVisible(true);
                }
                else{
                    String[] expectednames=restunameswithinscorerange[0].split(",");
                    System.out.println(expectednames[0]+"searched by score names"+expectednames.length);
                     nothingfound.setVisible(false);
                    nothingfoundalert.setVisible(false);
                    settableview(expectednames);

                }
               flagused=0; 
        }
        else if(flagused==3){
             String recieved=new String();
        recieved=restaurantcategoryfield.getText();
        restaurantscorefield.clear();
        restaurantnamefield.clear();
        restaurantcategoryfield.clear();
        restaurantzipcodefield.clear();
        restaurantpricefield.clear();
        restaurantcategoryfield.setVisible(false);
                String[] restunameswithinscorerange=db.categorywiserestaurant(recieved);
                if(restunameswithinscorerange[0].equals("No such restaurant with this score range")){
                    System.out.println("No such restaurant with this score range");
                    restaurantscorefield.clear();
                    restaurantscorefield.setVisible(true);
                    nothingfound.setVisible(true);
                    nothingfoundalert.setVisible(true);
                }
                else{
                    String[] expectednames=restunameswithinscorerange[0].split(",");
                    System.out.println(expectednames[0]+"searched by category names"+expectednames.length);
                     nothingfound.setVisible(false);
                    nothingfoundalert.setVisible(false);
                    settableview(expectednames);

                }
               flagused=0; 

        }
        else if(flagused==4){
 String recieved=new String();
        recieved=restaurantpricefield.getText();
        restaurantscorefield.clear();
        restaurantnamefield.clear();
        restaurantcategoryfield.clear();
        restaurantzipcodefield.clear();
        restaurantpricefield.clear();
        restaurantpricefield.setVisible(false);
                String[] restunameswithinscorerange=db.pricewiserestaurant(recieved);
                if(restunameswithinscorerange[0].equals("No such restaurant with this score range")){
                    System.out.println("No such restaurant with this score range");
                    restaurantscorefield.clear();
                    restaurantscorefield.setVisible(true);
                    nothingfound.setVisible(true);
                    nothingfoundalert.setVisible(true);
                }
                else{
                    String[] expectednames=restunameswithinscorerange[0].split(",");
                    System.out.println(expectednames[0]+"searched by price "+expectednames.length);
                     nothingfound.setVisible(false);
                    nothingfoundalert.setVisible(false);
                    settableview(expectednames);

                }
               flagused=0; 


        }
        else if(flagused==5){

              String recieved=new String();
        recieved=restaurantzipcodefield.getText();
        restaurantscorefield.clear();
        restaurantnamefield.clear();
        restaurantcategoryfield.clear();
        restaurantzipcodefield.clear();
        restaurantpricefield.clear();
        restaurantzipcodefield.setVisible(false);
                String[] restunameswithinscorerange=db.zipcodewiserestaurant(recieved);
                if(restunameswithinscorerange[0].equals("No such restaurant with this score range")){
                    System.out.println("No such restaurant with this score range");
                    restaurantscorefield.clear();
                    restaurantscorefield.setVisible(true);
                    nothingfound.setVisible(true);
                    nothingfoundalert.setVisible(true);
                }
                else{
                    String[] expectednames=restunameswithinscorerange[0].split(",");
                    System.out.println(expectednames[0]+"searched by category names"+expectednames.length);
                     nothingfound.setVisible(false);
                    nothingfoundalert.setVisible(false);
                    settableview(expectednames);

                }
               flagused=0; 
        }
        else if(flagused==7){
   String recieved=new String();
        recieved=foodnamefield.getText();
        System.out.println(recieved+"searched by food name flag7");
         foodnamefield.clear();
         foodresnamefield.clear();
         foodcategoryfield.clear();
         foodpricefield.clear();
          applybutton.setVisible(false);
          String[] attributes=new String[1];
        attributes=db.searchfoodbyname(recieved);//here
                       if(attributes[0].equals("No such food item with this name")){
                                   nothingfound.setVisible(true);
                                      nothingfoundalert.setVisible(true);
                                      System.out.println("inside nothing found");
                                      foodnamefield.clear();           
                       }
                       else{
                           String[] names=new String[300];
                           names=attributes[0].split(",");
                           System.out.println(names[0]+"searched by food names first string");
                           setfoodtableview(names);       
                       }
                      flagused=0; 
        }
        else if(flagused==8){

            String recieved=new String();
        recieved=foodcategoryfield.getText();
        System.out.println(recieved+"searched by food cat flag8");
         foodnamefield.clear();
         foodresnamefield.clear();
         foodcategoryfield.clear();
         foodpricefield.clear();
          applybutton.setVisible(false);
          String[] attributes=new String[1];
        attributes=db.searchfoodbycategory(recieved);//here
                       if(attributes[0].equals("No such food item with this category")){
                                   nothingfound.setVisible(true);
                                      nothingfoundalert.setVisible(true);
                                      foodnamefield.clear();           
                       }
                       else{
                           String[] names=new String[300];
                           names=attributes[0].split(",");
                           setfoodtableview(names);       
                       }
                      flagused=0; 

        }
        else if(flagused==9){

            String recieved=new String();
        recieved=foodpricefield.getText();
        String[] gotstring=new String[2];
        gotstring=recieved.split(",");
        Double a=Double.parseDouble(gotstring[0]);
        Double b=Double.parseDouble(gotstring[1]);
        System.out.println(recieved+"searched by food price flag9");
         foodnamefield.clear();
         foodresnamefield.clear();
         foodcategoryfield.clear();
         foodpricefield.clear();
          applybutton.setVisible(false);
          String[] attributes=new String[1];
        attributes=db.foodpricerange(a,b);//here
                       if(attributes[0].equals("No such food item with this price range")){
                                   nothingfound.setVisible(true);
                                      nothingfoundalert.setVisible(true);
                                      foodnamefield.clear();           
                       }
                       else{
                           String[] names=new String[300];
                           names=attributes[0].split(",");
                           setfoodtableview(names);       
                       }
                      flagused=0; 

        }
       else if(flagused==10){
             String recieved=new String();
        recieved=foodnamefield.getText();
         String recieved1=foodresnamefield.getText();
        System.out.println(recieved+"searched by food nd res name flag10");
         foodnamefield.clear();
         foodresnamefield.clear();
         foodcategoryfield.clear();
         foodpricefield.clear();
          applybutton.setVisible(false);
          String[] attributes=new String[1];
        attributes=db.searchfoodbybothnames(recieved,recieved1);
                       if(attributes[0].equals("No such food item with this name on the menu of this restaurant")){
                                   nothingfound.setVisible(true);
                                      nothingfoundalert.setVisible(true);
                                      foodnamefield.clear();           
                       }
                       else{
                           String[] names=new String[300];
                           names=attributes[0].split(",");
                           setfoodtableview(names);       
                       }
                      flagused=0; 
       }
       else if(flagused==11){
         String recieved=new String();
        recieved=foodcategoryfield.getText();
         String recieved1=foodresnamefield.getText();
        System.out.println(recieved+"searched by cat nd res name flag10");
         foodnamefield.clear();
         foodresnamefield.clear();
         foodcategoryfield.clear();
         foodpricefield.clear();
          applybutton.setVisible(false);
          String[] attributes=new String[1];
        attributes=db.searchfoodbycategoryandrestaurant(recieved,recieved1);
                       if(attributes[0].equals("No such food item with this category on the menu of this restaurant")){
                                   nothingfound.setVisible(true);
                                      nothingfoundalert.setVisible(true);
                                      foodnamefield.clear();           
                       }
                       else{
                           String[] names=new String[300];
                           names=attributes[0].split(",");
                           setfoodtableview(names);       
                       }
                      flagused=0; 
          

       }
       else if(flagused==12){
        String recieved=new String();
        recieved=foodpricefield.getText();
        String[] splitit=new String[2];
        splitit=recieved.split(",");
        Double a=Double.parseDouble(splitit[0]);
        Double b=Double.parseDouble(splitit[1]);
         String recieved1=foodresnamefield.getText();
        System.out.println(recieved+"searched by price nd res name flag10");
         foodnamefield.clear();
         foodresnamefield.clear();
         foodcategoryfield.clear();
         foodpricefield.clear();
          applybutton.setVisible(false);
          String[] attributes=new String[1];
        attributes=db.searchbypriceandrestaurant(a,b,recieved1);
                       if(attributes[0].equals("No such food item with this price range on the menu of this restaurant")){
                                   nothingfound.setVisible(true);
                                      nothingfoundalert.setVisible(true);
                                      foodnamefield.clear();           
                       }
                       else{
                           String[] names=new String[300];
                           names=attributes[0].split(",");
                           setfoodtableview(names);       
                       }
                      flagused=0; 
        
       }
       else if(flagused==13){
         String recieved=foodresnamefield.getText();
         foodnamefield.clear();
         foodresnamefield.clear();
         foodcategoryfield.clear();
         foodpricefield.clear();
          applybutton.setVisible(false);
          String[] attributes=new String[1];
        attributes=db.costliestfood(recieved);
                       if(attributes[0].equals("No such restaurant with food there is found!")){
                                   nothingfound.setVisible(true);
                                      nothingfoundalert.setVisible(true);
                                      foodnamefield.clear();           
                       }
                       else{
                           String[] names=new String[300];
                           names=attributes[0].split(",");
                           setfoodtableview(names);       
                       }
                      flagused=0; 
       }
       
    }
    public void settableview(String[] searched){
         cart.setVisible(false);
        cartimage.setVisible(false);
        confirmcart.setVisible(false);
        orderlist.clear();
        categorywisepane.setVisible(false);
        foodtableview.setVisible(false);
        foodlist.clear();
        restulist.clear();
        tableview.setVisible(true);
        applybutton.setVisible(false);
        restulist.clear();
         for(String str:searched){
            String[] attributes=db.searchrestaurantbyname(str);
            if(attributes[0].equals("No such restaurant with this name")){
                nothingfound.setVisible(true);
                nothingfoundalert.setVisible(true);
                tableview.setVisible(false);
                break;
            }
                            tableview.setVisible(true);
                            String[] categs=attributes[5].split(",");
                            String categ1=new String();
                            String categ2=new String();
                            String categ3=new String();
                            if(categs.length==1){
                                categ1=(categs[0]);
                                categ2=" ";
                                categ3=" ";}
                            if(categs.length==2){
                                categ1=(categs[0]);
                                categ2=(categs[1]);
                                categ3=" ";}
                                 if(categs.length==3){
                                categ1=(categs[0]);
                                categ2=(categs[1]);
                                categ3=(categs[2]);}
 restaurant r= new restaurant(Integer.parseInt(attributes[0]),attributes[1],Double.parseDouble(attributes[2]),attributes[3],attributes[4],categ1,categ2,categ3);
                                restulist.add(r);
                              tableview.setItems(restulist);
                            
         }
    }
     public void setfoodtableview(String[] searched){
        categorywisepane.setVisible(false);
        foodtableview.setVisible(true);
        tableview.setVisible(false);
        restulist.clear();
        applybutton.setVisible(false);
        foodlist.clear();
         for(String str:searched){
            String[] attributes=db.fooddetailsbyname(str);
            if(attributes[0].equals("No such food item with this name")){
                nothingfound.setVisible(true);
                nothingfoundalert.setVisible(true);
                foodtableview.setVisible(false);
                break;
            }
                            foodtableview.setVisible(true);
 food r= new food(Integer.parseInt(attributes[0]),attributes[1],attributes[2],Double.parseDouble(attributes[3]));
                                foodlist.add(r);
                              foodtableview.setItems(foodlist);
                            
         }
    }
    @FXML
    public void signupbuttonpressed(ActionEvent event) {
        executeInBackground(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("customersignup.fxml"));
                Parent root1 = loader.load();
                Scene scene1 = new Scene(root1, 800, 700);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Platform.runLater(() -> stage.setScene(scene1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void signupsubmitbuttonpressed(ActionEvent event) {
        alerttext.setText("do");
        alerttext.setVisible(false);
        String str = signupname.getText();
        String str1 = signupid.getText();
        String str2 = signuppass.getText();
        String str3 = signupage.getText();
        String str4 = signupfavfood.getText();
        String str5 = signupfavres.getText();

        executeInBackground(() -> {
            try {
                SocketWrapper socketWrapper;
                if (socketWrappers.containsKey(str1)) {
                    socketWrapper = socketWrappers.get(str1);
                } else {
                    socketWrapper = new SocketWrapper("127.0.0.1", 3333);
                    socketWrappers.put(str1, socketWrapper); // Store the SocketWrapper in the map
                    loggedinid = str1;
                }

                socketWrapper.write(str1);
                loggedinid = str1;

                String[] msg = new String[1];
                msg[0] = str1 + "," + "server" + "," + "signup" + "," + str + "," + str3 + "," + str4 + "," + str5 + ","
                        + str1 + "," + str2;
                         System.out.print(msg[0]+"customermaincontroller");
                socketWrapper.write(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Platform.runLater(() -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("customerlogin.fxml"));
                    Parent root1 = loader.load();
                    Scene scene1 = new Scene(root1, 800, 700);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
        cleartextfields();
    }

    @FXML
    public void submitbuttonpressed(ActionEvent event) {
        String id = loginid.getText();
        String pass = loginpass.getText();
        loggedinid = id;
        if (!id.isEmpty() && !pass.isEmpty()) {
            loginid.clear();
            loginpass.clear();
        }
        primarystage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SocketWrapper socketWrapper;
        if (socketWrappers.containsKey(id)) {
            // Use the existing SocketWrapper
            System.out.println("SocketWrapper exists for " + id);
            socketWrapper = socketWrappers.get(id);
        } else {
            try {
                socketWrapper = new SocketWrapper("127.0.0.1", 3333);
                socketWrappers.put(id, socketWrapper); // Store the SocketWrapper in the map
                loggedinid = id;
                System.out.println("SocketWrapper created for " + id);
            } catch (IOException e) {
                e.printStackTrace();
                return; // Handle the exception appropriately
            }
        }
        try {
            socketWrapper.write(id);
            String[] msg = new String[1];
            msg[0] = id + "," + "server" + "," + "login" + "," + id + "," + pass;

            socketWrapper.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Platform.runLater(() -> {
            new ReadThreadClient(socketWrapper, this);
        });
    }

    public void changealerttext() {
        Platform.runLater(() -> {
            alerttext.setVisible(true);
            /*SocketWrapper sock=socketWrappers.get(loggedinid);
            try {
                sock.closeConnection();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } */
            alerttext.setText("User not Found!!");
        });
    }

    public void changescreen(database db) {
        customermaincontroller.db=db;
        Platform.runLater(() -> {
            alerttext.setVisible(true);
            alerttext.setText("Login successful!!");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("customerinterface.fxml"));
                Parent root1 = loader.load();
                Scene scene1 = new Scene(root1, 800, 700);
                primarystage.setScene(scene1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        /* SocketWrapper sock=socketWrappers.get(loggedinid);
            try {
                sock.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }  */
    }
    @FXML
    public void orderdelivered(String msg){
        System.out.println("order recieved by customer"+msg);//order recieved by customerchickenfry
       String newmsg=msg+"is delivered";
        beeptxt.setText(newmsg);
        System.out.println(newmsg);
        System.out.println(beeptxt.getText());
        beeptxt.setVisible(true);
    }

    @FXML
    public void profileimageclicked() {
        tableview.setVisible(false);
        foodtableview.setVisible(false);
        foodlist.clear();
        restulist.clear();
        orderlist.clear();
        cartlist.clear();
        customtableview.setVisible(false);
        cart.setVisible(false);
        foodnamefield.clear();
        foodnamefield.setVisible(false);
        foodcategoryfield.clear();
        foodpricefield.clear();
        foodpricefield.setVisible(false);
        foodresnamefield.clear();
        foodresnamefield.setVisible(false);
        categorywisepane.setVisible(false);
        applybutton.setVisible(false);
        restaurantnamefield.setVisible(false);
        tableview.setVisible(false);
        nothingfound.setVisible(false);
        nothingfoundalert.setVisible(false);
        restaurantcategoryfield.setVisible(false);
        restaurantscorefield.setVisible(false);
        restaurantpricefield.setVisible(false);
        restaurantzipcodefield.setVisible(false);
        flagused=0;
        restaurantcategoryfield.clear();
        restaurantscorefield.clear();
        restaurantpricefield.clear();
        restaurantzipcodefield.clear();
        restaurantnamefield.clear();
        executeInBackground(() -> {
            System.out.println("Profile image clicked");
                 Platform.runLater(() -> {
                    try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("customerprofileshow.fxml"));
                Parent root1 = loader.load();
                Scene scene1 = new Scene(root1, 800, 700);
                primarystage.setScene(scene1);
            } catch (IOException e) {
                e.printStackTrace();
            }
                });
        });
    }

    @FXML
    public void logoutimageclicked() {
         tableview.setVisible(false);
        foodtableview.setVisible(false);
        foodlist.clear();
        restulist.clear();
        cartlist.clear();
        orderlist.clear();
        customtableview.setVisible(false);
        cart.setVisible(false);
        foodnamefield.clear();
        foodnamefield.setVisible(false);
        foodcategoryfield.clear();
        foodpricefield.clear();
        foodpricefield.setVisible(false);
        foodresnamefield.clear();
        foodresnamefield.setVisible(false);
        categorywisepane.setVisible(false);
        applybutton.setVisible(false);
        restaurantnamefield.setVisible(false);
        tableview.setVisible(false);
        nothingfound.setVisible(false);
        nothingfoundalert.setVisible(false);
        restaurantcategoryfield.setVisible(false);
        restaurantscorefield.setVisible(false);
        restaurantpricefield.setVisible(false);
        restaurantzipcodefield.setVisible(false);
        flagused=0;
        restaurantcategoryfield.clear();
        restaurantscorefield.clear();
        restaurantpricefield.clear();
        restaurantzipcodefield.clear();
        restaurantnamefield.clear();
        executeInBackground(() -> {
           Platform.runLater(() -> {
                    try {
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
  
    public void cleartextfields() {
        if (!signupname.getText().isEmpty()) {
            signupname.clear();
        }
        if (!signupid.getText().isEmpty()) {
            signupid.clear();
        }
        if (!signuppass.getText().isEmpty()) {
            signuppass.clear();
        }
        if (!signupage.getText().isEmpty()) {
            signupage.clear();
        }
        if (!signupfavfood.getText().isEmpty()) {
            signupfavfood.clear();
        }
        if (!signupfavres.getText().isEmpty()) {
            signupfavres.clear();
        }
    }

    @FXML
    public void logoutimageentered() {
        Platform.runLater(() -> logouttext.setVisible(true));
    }

    @FXML
    public void logoutimageexited() {
        Platform.runLater(() -> logouttext.setVisible(false));
    }

    @FXML
    public void profileimageentered() {
        Platform.runLater(() -> profiletext.setVisible(true));
    }

    @FXML
    public void profileimageexited() {
        Platform.runLater(() -> profiletext.setVisible(false));
    }
    private void executeInBackground(Runnable task) {
        executorService.execute(task);
    }
} 