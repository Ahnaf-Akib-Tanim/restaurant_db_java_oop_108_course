import javafx.scene.control.Button;

public class customedclass {
   private String customfoodname;
   private String customfoodprice;
   private String customfoodcategory;
   private Button customedbutton;
   public customedclass(String a,String b,String c,Button d){
           customfoodcategory=c;
              customfoodname=a;
                customfoodprice=b;
                customedbutton=d;

    }
    public String getcustomfoodname(){
        return customfoodname;
    }
    public String getcustomfoodprice(){
        return customfoodprice;
    }
    public String getcustomfoodcategory(){
        return customfoodcategory;
    }
    public Button getbutton(){
        return customedbutton;
    }
}
