import java.io.Serializable;

import javafx.scene.control.Button;

public class customizedclass3 implements Serializable {
    private String orderuserid;
    private String orderrestaurantid;
    private String orderfoodname;
    private transient Button orderbutton; // Use transient to exclude the Button from serialization

    public customizedclass3(String a, String b, String c, Button d) {
        orderuserid = a;
        orderrestaurantid = b;
        orderfoodname = c;
        orderbutton = d;
    }

    public String getorderuserid() {
        return orderuserid;
    }

    public String getorderrestaurantid() {
        return orderrestaurantid;
    }

    public String getorderfoodname() {
        return orderfoodname;
    }

    public Button getbutton() {
        return orderbutton;
    }
}
