import java.io.Serializable;

public class customizedclass2 implements Serializable {
    private String orderuserid;
    private String orderrestaurantid;
    private String orderfoodname;

    public customizedclass2(String a, String b, String c) {
        orderuserid = a;
        orderrestaurantid = b;
        orderfoodname = c;
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
}
