import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
public class ReadThreadServer implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;
    public HashMap<String, SocketWrapper> clientMap;
    private static String customerid;
    private static String customerpass;

    public ReadThreadServer(HashMap<String, SocketWrapper> map, SocketWrapper socketWrapper) {
        this.clientMap = map;
        this.socketWrapper = socketWrapper;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = socketWrapper.read();
                if (o instanceof String[]) {
                   String[] Obj = (String[]) o;
                       String[] obj=Obj[0].split(",");
                       System.out.println(Obj[0]+"from server");
                 if(Obj.length==1 && obj.length>=3 && obj[2].equals("signup")){
                    System.out.println(Obj[0]+"signup here in server");
                    String name=obj[3];
                    String age=obj[4];
                    String favfood=obj[5];
                    String favrest=obj[6];
                    String id=obj[7];
                    String pass=obj[8];
                    String msg=id+","+pass+","+name+","+age+","+favfood+","+favrest+"\n";
                       try (BufferedWriter bw = new BufferedWriter(new FileWriter("customerlogin.txt",true))) {
            bw.write(msg);
            bw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        }  
        else if(Obj.length==1 && obj.length>=3 && obj[2].equals("login")){
                       String id=obj[3];
                       String pass=obj[4];  
                       BufferedReader reader = null;
                       System.out.println("login");
        try {
            reader = new BufferedReader(new FileReader("customerlogin.txt"));
            String line;
            boolean flag=false;
            while ((line = reader.readLine()) != null) {
                String[] str=line.split(",");
                if(str[0].equals(id) && str[1].equals(pass)){
                        String[] msg=new String[1];
                    msg[0]="login successfull!!";
                    System.out.println("login successfull!!");
                    customerid=id;
                    customerpass=pass;
                    SocketWrapper to = clientMap.get(id);
                    database db=new database();
                    to.write(db);
                    flag=true;
                    break;
                }
                
            }
            if(flag==false){
                String[] msg=new String[1];
                    msg[0]="No user found!!";
                    System.out.println("No user found!!");
                     SocketWrapper to = clientMap.get(id);
                    to.write(msg);
                    flag=true;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }              
        }


  else if(Obj.length==1 && obj.length>=3 && obj[2].equals("loginrestaurant")){
                       String id=obj[3];
                       String pass=obj[4];  
                       BufferedReader reader = null;
                       System.out.println("login restaurant");
        try {
            reader = new BufferedReader(new FileReader("restaurantlogin.txt"));
            String line;
            boolean flag=false;
            while ((line = reader.readLine()) != null) {
                String[] str=line.split(",");
                if(str[0].equals(id) && str[1].equals(pass)){
                        String[] msg=new String[1];
                    msg[0]="login successfull by res!!";
                    System.out.println("login successfull!!");
                    customerid=id;
                    customerpass=pass;
                    SocketWrapper to = clientMap.get(id);
                    database db=new database();
                    mixedclass mx=new mixedclass(db,"restaurant");
                    to.write(mx);
                    flag=true;
                    break;
                }
                
            }
            if(flag==false){
                String[] msg=new String[1];
                    msg[0]="No user found by res!!";
                    System.out.println("No user found!!");
                     SocketWrapper to = clientMap.get(id);
                    to.write(msg);
                    flag=true;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }              
        }

       else if(Obj.length==1 && obj[0].equals("getprofile")){
                              System.out.println("profile info from server is on the way");
                         String[] pro=new String[2];        

                         try {
           BufferedReader reader = new BufferedReader(new FileReader("customerlogin.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] str=line.split(",");
                if(str[0].equals(customerid) && str[1].equals(customerpass)){
                      pro[0]="profile";
                      pro[1]=str[0]+","+str[1]+","+str[2]+","+str[3]+","+str[4]+","+str[5];
                        SocketWrapper to = clientMap.get(customerid);
                    to.write(pro);
                    System.out.print("the socketwrapper from server:");
                    to.print();
                    System.out.println("profile info from server is sent to"+customerid);
                    break;
                }
                
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    else if(Obj.length==1 && obj[2].equals("delivery")){
        System.out.println("ok form server"+obj[0]+obj[1]+obj[2]);
                 String toward=new String();
                 SocketWrapper socke= clientMap.get(obj[0]);
                  String[] foodname=new String[1];
                  foodname[0]=obj[1]+","+"delivery";
                socke.write(foodname);
                System.out.println("ok still now2"+obj[1]);

    }

  }
       else if(o instanceof customizedclass2[]){
                    customizedclass2[] obj=(customizedclass2[]) o;
                    String to=obj[0].getorderrestaurantid();
                    SocketWrapper to1 = clientMap.get(to);
                    to1.write(obj);
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
