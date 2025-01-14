
import java.io.IOException;

import javafx.application.Platform;
public class ReadThreadClient implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;
    private Object controller;

    public ReadThreadClient(SocketWrapper socketWrapper,Object controller) {
        this.socketWrapper = socketWrapper;
        this.controller = controller;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = socketWrapper.read();
                if(o instanceof database){
                    database d=(database) o;
                    controller= (customermaincontroller) controller;
                    ((customermaincontroller) controller).changescreen(d);
                }
               else if (o instanceof String[]) {
                    String[] Obj = (String[]) o;
                    System.out.println(Obj[0]);
                    if (Obj.length == 1 && Obj[0].equals("No user found!!")) {
                        System.out.println("No user found!! from client");
                        Platform.runLater(() -> {
                            controller= (customermaincontroller) controller;
                            ((customermaincontroller) controller).changealerttext();
                        });
                    } //"No user found by res!!"
                      if (Obj.length == 1 && Obj[0].equals("No user found by res!!")) {
                        System.out.println("No user found!! from restaurant");
                        Platform.runLater(() -> {
                            controller= (restaurantcontroller) controller;
                            ((restaurantcontroller) controller).changealerttext();
                        });
                    } 
                    else{
                         controller= (customermaincontroller) controller;
                         String[] str=Obj[0].split(",");
                            ((customermaincontroller) controller).orderdelivered(str[0]);
                    }
                   
                }
                else if(o instanceof mixedclass){
                    mixedclass m=(mixedclass) o;
                    controller= (restaurantcontroller) controller;
                    ((restaurantcontroller) controller).changescreen(m.getdatabase());
                }
                else if(o instanceof customizedclass2[]){
                    customizedclass2[] ob=(customizedclass2[])o;
                    controller= (restaurantcontroller) controller;
                    ((restaurantcontroller) controller).orderrecieved(ob);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                socketWrapper.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
