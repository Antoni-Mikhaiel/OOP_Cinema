package javaapplication1;
import java.util.ArrayList;
import java.io.*;
//import java.util.LocalTime;
public class Show {
    public static ArrayList<Show> shows= new ArrayList<>();
    private int showid ;
    private String showname;
    private Movie movie ;
    private float time;
    private char hall;
    private int totalseats;
    private int availableseats;
    private int ticketprice;
    private double revenue=0;
    private int numofbooking;
//    private String showcategory;
//    private List<Booking> bookings; 
//LIST FROM THE CLASS Booking AND ITS NAME IS bookings as the relationship of the show with Booking is aggregtion 
    public Show(int showid,String showname, float time,char hall,int ticketprice,int totalseats){
    this.showid=showid;
    this.showname=showname;
    //this.movie=movie;
    this.time=time;
    this.hall=hall;
    this.ticketprice=ticketprice;
    this.totalseats =totalseats;

    }
     public int getshowid(){return showid;}
     public void setshowid(int showid){this.showid=showid;}
     
     public double getRevenue(){return this.revenue;}
     public void setRevenue(double rev){this.revenue=rev;}
         
     public float getshowtime(){return time;}
     public void setshowtime(float time){ this.time=time;}
     
     public int getnumofbooking(){
         return this.numofbooking;
     }
     public void setnumofbooking(int num){
         this.numofbooking=num;
     }
     
      public char gethallno(){return hall;}
      
     
     public int gettotalseats(){return totalseats;}
     public void settotalseats(int totalseats){ this.totalseats =totalseats;}
      
     public int getavailableseats(){return availableseats;}
     
     public int getticketprice(){return ticketprice;}
     public void setticketprice(int ticketprice){ this.ticketprice=ticketprice;}
      public String getshowname(){return showname;}
     public void setshowname(String showname){ this.showname=showname;}
     
public void showdetails(){
     System.out.println("show: "+getshowname());
     System.out.println("time: "+time);
     System.out.println("hall: "+hall);
     System.out.println("totalseats: "+totalseats);
     System.out.println("available seats: "+availableseats);
     System.out.println("ticket price is: "+ticketprice);
    }


public static void saveshow(){ //This puts the entire Movie object attributes in the file
     File file = new File("C:/Users/ok/Desktop/OOP_Cinema/Shows.txt");
      try(ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(file))){  
       write.writeObject(shows);
}catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
}

public static void readshow() throws FileNotFoundException{
    File file = new File("C:/Users/ok/Desktop/OOP_Cinema/Shows.txt");
    if (!file.exists()) {
        System.out.println("Shows file does not exist.");
        return; 
    }
     try (ObjectInputStream read= new ObjectInputStream(new FileInputStream(file))) {
 shows = (ArrayList<Show>) read.readObject();
} catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage()); 
}

}

}