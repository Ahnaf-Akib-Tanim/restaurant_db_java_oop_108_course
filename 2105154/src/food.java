public class food implements java.io.Serializable{
    private Integer restaurantid;
    private String foodcategory;
    private String foodname;
    private Double foodprice;
    public food (int info1,String info2,String info3,double info4){
        restaurantid=info1;
        foodcategory=info2;
        foodname=info3;
        foodprice=info4;
    }
   public Integer getid(){
        return restaurantid;
    }
    public String getname(){

        return foodname;
    }
    public String getcategory(){
        return foodcategory;
    }
    public Double getprice(){
        return foodprice;
    }
    public boolean withinrange(double a,double b){
        if((foodprice>=a && foodprice<=b)|| (foodprice<=a && foodprice>=b)){
            return true;
        }
        return  false;
    }
    public String[] show(){
        String[] arr=new String[4];
        arr[0]=restaurantid.toString();
        arr[1]=foodcategory;
        arr[2]=foodname;
        arr[3]=foodprice.toString();
        return arr;
    }

}
