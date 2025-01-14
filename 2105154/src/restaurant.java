import java.util.ArrayList;
import java.util.List;
public class restaurant implements java.io.Serializable{
private Integer id;
private String name;
private Double score;
private String zipcode;
private String price;
private String category1;
private String category2;
private String category3;
private int numofcat;
    List<food> menu = new ArrayList<>();
public restaurant(Integer id,String name,Double score, String price,String zipcode,String category1){
    this.id=id;
    this.name=name;
    this.score=score;
    this.price=price;
    this.zipcode=zipcode;
    this.category1=category1;
    numofcat=1;
}

    public restaurant (Integer id,String name,Double score, String price,String zipcode,String category1,String category2){
        this.id=id;
        this.name=name;
        this.score=score;
        this.price=price;
        this.zipcode=zipcode;
        this.category1=category1;
        this.category2=category2;
        numofcat=2;
    }
    public restaurant (Integer id,String name,Double score, String price,String zipcode,String category1,String category2,String category3){
        this.id=id;
        this.name=name;
        this.score=score;
        this.price=price;
        this.zipcode=zipcode;
        this.category1=category1;
        this.category2=category2;
        this.category3=category3;
        numofcat=3;
    }
    public Integer getnumofcat() {return numofcat;}
   public Integer getid(){
    return id;
   }
   public void setid(Integer id){
    this.id=id;
   }
   public String getname(){
    return name;
   }
    public void setname(String name){
        this.name=name;
    }
    public Double getscore(){
        return score;
    }
    public void setscore(Double score){
        this.score=score;
    }
    public String getprice(){
    return  price;
    }
    public void setprice(String price){
    this.price=price;
    }
    public String getzipcode(){
    return zipcode;
    }
    public void setzipcode(String zipcode){ this.zipcode=zipcode;}
    public String getCategory1(){
    return category1;
    }
    public String getCategory2(){
        return category2;
    }
    public String getCategory3(){
        return category3;
    }
    public void addfood(food f){
    menu.add(f);
    }
    public int getnumoffoods(){
    return menu.size();
    }
    public boolean categoryexist(String cat){
    String cat1,cat2,cat3;
    cat=cat.toLowerCase();
    cat1=category1.toLowerCase();
    if(numofcat==1){
        if(cat1.contains(cat)){
            return true;
        }
        else{
            return false;
        }
    }
        if(numofcat==2){
            cat2=category2.toLowerCase();
            if(cat1.contains(cat) || cat2.contains(cat)){
                return true;
            }
            else{
                return false;
            }
        }
        if(numofcat==3){
             cat3=category3.toLowerCase();
             cat2=category2.toLowerCase();
            if(cat1.contains(cat) || cat2.contains(cat) || cat3.contains(cat)){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
    //this function yet to code
    public String[] show(){
        String[] arofstr=new String[6];
        arofstr[0]=Integer.toString(id);
        arofstr[1]=name;
        arofstr[2]=Double.toString(score);
        arofstr[3]=zipcode;
        arofstr[4]=price;
        arofstr[5]=category1;
        if(numofcat==2){
            arofstr[5]=arofstr[5]+","+category2;
        }
        
        if(numofcat==3){
            arofstr[5]=arofstr[5]+","+category2;
            arofstr[5]=arofstr[5]+","+category3;
        }
       
        return arofstr;
    }
    public boolean withinrange(double a,double b){
    if((score>=a && score<=b)|| (score>=b && score<=a)){
        return true;
    }
    return false;
    }
    public boolean priceexist(String str){
    if(str.equals(price)){
        return true;
    }
    return false;
    }
    public boolean foodexist(String str){
   str=str.toLowerCase();
   for(food f:menu){
       String foodname=f.getname();
       foodname=foodname.toLowerCase();
       if(foodname.contains(str)){
           return true;
       }
   }
    return false;
    }
    public int gettotalnumoffoods(){
    return menu.size();
    }
  public boolean foodexistcatandname(String str1,String str2){
    str1=str1.toLowerCase();
    str2=str2.toLowerCase();
    for(food f:menu){
        String str4=f.getname();
        String str3=f.getcategory();
        str3=str3.toLowerCase();
        str4=str4.toLowerCase();
        if(str3.contains(str1) && str4.contains(str2)){
            return true;
        }

    }
    return false;
  }

}
