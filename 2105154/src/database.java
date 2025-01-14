import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

public class database implements Serializable {
    private List<restaurant> restaurants= new ArrayList<>();
    private List<food> menu= new ArrayList<>();
    private Set<String> allcategories = new HashSet<>();
    private String[] restunames=new String[400];
    private int restunamescount=0;
    public database(){
        try (BufferedReader br = new BufferedReader(new FileReader("restaurant.txt"))) {
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                String[] array = line.split(",");
                    if (array.length == 6) {
                        restaurant res = new restaurant(Integer.parseInt(array[0]), array[1], Double.parseDouble(array[2]), array[3], array[4], array[5]);
                        restaurants.add(res);
                        restunames[restunamescount++]=res.getname();
                        allcategories.add(res.getCategory1());
                    } else if (array.length == 7) {
                        restaurant res = new restaurant(Integer.parseInt(array[0]), array[1], Double.parseDouble(array[2]), array[3], array[4], array[5], array[6]);
                        restaurants.add(res);
                        allcategories.add(res.getCategory1());
                        allcategories.add(res.getCategory2());
                        restunames[restunamescount++]=res.getname();
                    } else if (array.length == 8) {
                        restaurant res = new restaurant(Integer.parseInt(array[0]), array[1], Double.parseDouble(array[2]), array[3], array[4], array[5], array[6], array[7]);
                        restaurants.add(res);
                        allcategories.add(res.getCategory1());
                        allcategories.add(res.getCategory2());
                        allcategories.add(res.getCategory3());
                        restunames[restunamescount++]=res.getname();
                    }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("menu.txt"))) {
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                String[] array = line.split(",(?! )");
                food f=new food(Integer.parseInt(array[0]),array[1],array[2],Double.parseDouble(array[3]));
                menu.add(f);
                for(restaurant res:restaurants){
                    if(f.getid()==res.getid()){
                        res.addfood(f);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String[] searchrestaurantbyname(String str){
     str=str.toLowerCase();
     String str2;
     String[] arr=new String[200];
     for(restaurant res:restaurants){
         str2=res.getname();
         str2=str2.toLowerCase();
         if(str2.contains(str)){
            arr= res.show();
             return arr;
         }
     }
         arr[0]="No such restaurant with this name";
         return arr;
    }
    public String[] showcategorywiserestaurants(){
        int counting;
        int i=0;
        String[] arr=new String[400];
        for(String str:allcategories){
            counting=0;
            arr[i]=str+":";
            for(restaurant res:restaurants){
                      if(res.categoryexist(str)){
                          counting++;
                          if(counting==1){
                              arr[i]=arr[i]+res.getname();
                          }
                          else{
                              arr[i]=arr[i]+","+res.getname();
                          }
                      }
            }
            i++;
        }
        return arr;
    }
    public String[] displayrestaurantswithinrange(double a,double b){
        int counting=0;
        String[] arr=new String[400];
        for(restaurant res:restaurants){
            if(res.withinrange(a,b)){
                 counting++;
                 if(counting==1){
                     arr[0]=res.getname();
                 }
                 else{
                        arr[0]=arr[0]+","+res.getname();
                 }
            }
        }
   if(counting==0){
       arr[0]="No such restaurant with this score range";
   }
   return arr;
    }
    public String[] categorywiserestaurant(String str){
        int counting=0;
        String[] arr=new String[400];
        str=str.toLowerCase();
        for(restaurant res:restaurants){
            if(res.categoryexist(str)){
                counting++;
                if(counting==1){
                    arr[0]=res.getname();
                }
                else{
                    arr[0]=arr[0]+","+res.getname();
                }
            }

        }
        if(counting==0){
            arr[0]="No such restaurant with this category";
        }
             return arr;
    }
    public String[] pricewiserestaurant(String str){
        int counting=0;
        String[] arr=new String[400];
        for(restaurant res:restaurants){
            if(res.priceexist(str)){
                counting++;
                if(counting==1){
                  arr[0]=res.getname();
                }
                else{
                    arr[0]=arr[0]+","+res.getname();
                }
                }
            }
        if(counting==0){
            arr[0]="No such restaurant with this price";
        }
        return arr;
    }
    public String[] zipcodewiserestaurant(String str){
        int counting=0;
        String arr[]=new String[400];
        for(restaurant res:restaurants){
            if(res.getzipcode().equals(str)){
                counting++;
                if(counting==1){
                    arr[0]=res.getname();
                }
                else{
                    arr[0]=arr[0]+","+res.getname();
                }
            }

        }
        if(counting==0){
                arr[0]="No such restaurant with this zipcode";
        }
       return arr; 
    }
public String[] searchfoodbyname(String foodname){
        foodname=foodname.toLowerCase();
        String[] arr=new String[500];
        int i=1;
        int counting=0;
        for(food f:menu){
            if(f.getname().toLowerCase().contains(foodname)){
                counting++;
                if(counting==1){
                   arr[0]=f.getname();
                }
                else{
                    System.out.println("inside database"+f.getname());
                   arr[0]=arr[0]+","+f.getname();
                }
            }
        }
        if(counting==0){
           arr[0]="No such food item with this name";
        }
        return arr;
}
public String[] fooddetailsbyname(String foodname){
        foodname=foodname.toLowerCase();
        String[] arr=new String[400];
        int i=1;
        int counting=0;
        for(food f:menu){
            if(f.getname().toLowerCase().contains(foodname)){
                counting++;
                arr=f.show();
                return arr;
            }
        }
        if(counting==0){
           arr[0]="No such food item with this name";
        }
        return arr;
}
public String[] searchfoodbybothnames(String foodname,String restaurantname){
foodname=foodname.toLowerCase();
String[] arr=new String[400];
int i=1;
restaurantname=restaurantname.toLowerCase();
int counting=0;
for(restaurant res:restaurants){
    for(food f:menu){
        String fname=f.getname().toLowerCase();
        String resname=res.getname().toLowerCase();
        if(fname.contains(foodname) && resname.contains(restaurantname) && f.getid()==res.getid()){
            counting++;
           if(counting==1){
                   arr[0]=f.getname();
                }
                else{
                    System.out.println("inside database"+f.getname());
                   arr[0]=arr[0]+","+f.getname();
                }
        }
    }
}
if(counting==0){
    arr[0]=("No such food item with this name on the menu of this restaurant");
}
return arr;
}
public String[] searchfoodbycategory(String foodcat){

    foodcat=foodcat.toLowerCase();
    String[] arr=new String[400];
    int counting=0;
    int i=1;
    for(food f:menu){
        if(f.getcategory().toLowerCase().contains(foodcat)){
            counting++;
            if(counting==1){
                arr[0]=f.getname();
            }
            else{
                arr[0]=arr[0]+","+f.getname();
            }
        }
    }
    if(counting==0){
        arr[0]="No such food item with this category";
    }
    return arr;

}
public String[] searchfoodbycategoryandrestaurant(String cat,String resname){
    cat=cat.toLowerCase();
    String[] arr=new String[400];
    int i=1;
    resname=resname.toLowerCase();
    int counting=0;
    for(restaurant res:restaurants){
        for(food f:menu){
            String catname=f.getcategory().toLowerCase();
            String resname2=res.getname().toLowerCase();
            if(catname.contains(cat) && resname2.contains(resname) && f.getid()==res.getid()){
                counting++;
                 if(counting==1){
                arr[0]=f.getname();
            }
            else{
                arr[0]=arr[0]+","+f.getname();
            }
            }
        }
    }
    if(counting==0){
        System.out.println("No such food item with this category on the menu of this restaurant");
    }
return arr;
}
public String[] foodpricerange(double a,double b){
int counting=0;
String[] arr=new String[450];
int i=1;
for(food f:menu){
    if(f.withinrange(a,b)){
        counting++;
         if(counting==1){
                arr[0]=f.getname();
            }
            else{
                arr[0]=arr[0]+","+f.getname();
            }
    }

}
if(counting==0){
    arr[0]="No such food item with this price range";
}
return arr;
}
public String[] searchbypriceandrestaurant(double a,double b,String resname){
resname=resname.toLowerCase();
int counting=0;
int i=1;
String[] arr=new String[400];
for(food f:menu){
    for(restaurant res:restaurants){
        if(f.withinrange(a,b) && f.getid()==res.getid() && res.getname().toLowerCase().contains(resname)){
            counting++;
             if(counting==1){
                arr[0]=f.getname();
            }
            else{
                arr[0]=arr[0]+","+f.getname();
            }
        }

    }
}
if(counting==0){
    arr[0]="No such food item with this price range on the menu of this restaurant";
}
return arr;
}
public String[] costliestfood(String resname){
        resname=resname.toLowerCase();
        String[] arr=new String[400];
double maximum=-1;
int i=1;
for(food f:menu){
    for(restaurant res:restaurants){
        if(res.getname().toLowerCase().contains(resname) && f.getid()==res.getid()){
                maximum=Math.max(maximum,f.getprice());
        }

    }
}
if(maximum==-1){
    arr[0]="No such restaurant with food there is found!";
    return arr;
}
    for(food f:menu){
        for(restaurant res:restaurants){
            if(res.getname().toLowerCase().contains(resname) && f.getid()==res.getid() && f.getprice()==maximum){
                 arr[0]=f.getname();
            }

        }
    }
    return arr;
}
public String[] restaurantwithtotalfoods(){
    String[] arr=new String[400];
    int i=0;
        for(restaurant res:restaurants){
            arr[i++]=res.getname()+":"+Integer.toString(res.gettotalnumoffoods());
            System.out.println(arr[i-1]);
        }
        return arr;
}
public boolean restunamecheck(String str){
for(restaurant x:restaurants){
    if(x.getname().toLowerCase().equals(str.toLowerCase())){
        return true;
    }
}
return false;
}
public boolean restuidcheck(int i){
    for(restaurant x:restaurants){
    if(x.getid()==i){
        return true;
    }
}
return false;
}
public void addrestaurant(int id, String name,double score, String price,String zipcode,String cat1){
    restaurant res=new restaurant(id, name, score, price, zipcode, cat1);
    restaurants.add(res);
  allcategories.add(cat1);
  restunames[restunamescount++]=res.getname();
}
public void addrestaurant(int id, String name,double score, String price,String zipcode,String cat1,String cat2){
    restaurant res=new restaurant(id, name, score, price, zipcode, cat1,cat2);
    restaurants.add(res);
   allcategories.add(cat1);
   allcategories.add(cat2);
   restunames[restunamescount++]=res.getname();
}
public void addrestaurant(int id, String name,double score, String price,String zipcode,String cat1,String cat2,String cat3){
    restaurant res=new restaurant(id, name, score, price, zipcode, cat1,cat2,cat3);
    restaurants.add(res);
    allcategories.add(cat1);
    allcategories.add(cat2);
    allcategories.add(cat3);
    restunames[restunamescount++]=res.getname();
   
}
public void addfood(String resname,String cat,String foodname,Double price){

for(restaurant res:restaurants){
    if(resname.equals(res.getname().toLowerCase())){
        int id=res.getid();
        food f=new food(id,cat,foodname,price);
        res.addfood(f);
        menu.add(f);
       return;
    }
}


}
public boolean samenamecatinrestu(String restname, String foodname,String cat){
for(restaurant x:restaurants){
    if(x.getname().toLowerCase().equals(restname.toLowerCase())){
        if(x.foodexistcatandname(cat, foodname)){
            return true;
        }
    }
}
return false;
}
    public  void  exitdatabase() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("restaurant.txt"))) {
            String line;
            for (restaurant res : restaurants) {
                line=Integer.toString(res.getid())+","+res.getname()+","+Double.toString(res.getscore())+","+res.getprice()+","+res.getzipcode()+","+res.getCategory1();
                if(res.getnumofcat()==2){
                    line=line+","+res.getCategory2();
                }
                else if(res.getnumofcat()==3){
                    line=line+","+res.getCategory2()+","+res.getCategory3();
                }
                bw.write(line);
                bw.write(System.lineSeparator());
            }
            bw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter second = new BufferedWriter(new FileWriter("menu.txt"))) {
            String line;
            for (food f : menu) {
               line=Integer.toString(f.getid())+","+f.getcategory()+","+f.getname()+","+Double.toString(f.getprice());
                second.write(line);
                second.write(System.lineSeparator());
            }
            second.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        catch (IOException e) {
            e.printStackTrace();
        }



    }
    public restaurant getrestaurant(String id){
        for(restaurant res:restaurants){
            if(Integer.toString(res.getid()).equals(id)){
                return res;
            }
        }
        return null;
    }
    public String[] getrestunames(){
        return restunames;
    }
    public String getrestaurantid(String resname){
        for(restaurant res:restaurants){
            if(res.getname().toLowerCase().equals(resname.toLowerCase())){
                return Integer.toString(res.getid());
            }
        }
        return "no such restaurant";
    }
    public food[] getallfoods(String resname){
        food[] fo=new food[200];
        int i=0;
        for(food f:menu){
            for(restaurant res:restaurants){
                if(res.getname().toLowerCase().equals(resname.toLowerCase()) && f.getid()==res.getid()){
                    fo[i++]=f;
                }
            }
        }
return fo;
    }
    public String[] showdetailsbyid(int id){

        for(restaurant res:restaurants){
            if(res.getid()==id){

                String[] ans= res.show();
                return ans;

            }
        }
        String[] ans=new String[1];
        ans[0]=" no such restaurant with this id";
        return ans;
    }
    private static final long serialVersionUID = 1L;

}
