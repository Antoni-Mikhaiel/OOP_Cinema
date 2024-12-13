package javaapplication1;
import java.io.File;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class Booking implements Serializable{
    File file = new File("C:/Users/ok/Desktop/Bookings.txt"); 
public static ArrayList<Booking> bookings=new ArrayList<>(); 

  //attributes
private int bookingID; //Ticket ID
private static int[][] seatsBooked; 
private double rating; //price of show
//private String guestname, showname;  //no need for guest name  or show name beacause you can fetch them from class guest & show

private transient Movie movie; // Make movie transient to prevent serialization
private String movieName; // Store movieName explicitly
private transient Show show; 
private int hall;

//Constructor
public Booking(Movie movie , Show show , int[][] seatsBooked , double rating){
    this.movie = movie;
    this.movieName = movie.getMovieName(); //at this point, eh lazmt el movie object? can just fetch it directly mel arraylist?
    this.show = show;
    this.hall = show.getHall();
    this.seatsBooked = seatsBooked;
    this.rating = rating;
}

public int getBookingID(){
return bookingID;
}
public double getRating(){
return rating;
}
public int[][] getSeatsBooked(){
return seatsBooked;
}
public String getMovieName(){
return movie.getMovieName();
//return movie.getMovieName();
}

public void createBookingID(){
    Random random = new Random();
    bookingID = random.nextInt(999);
}

public void writeBooking() throws FileNotFoundException{
      try(ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(file))){ //Overwrites + ensures output is closed after try-catch statement 
          write.writeObject(bookings);
}catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
}
     
public void readBooking(File file) throws FileNotFoundException{
    if (!file.exists()) {
        System.out.println("Booking file does not exist.");
        return; 
    }
     try (ObjectInputStream read= new ObjectInputStream(new FileInputStream(file))) {
     //movie.setMovieName((Movie)read.readObject());
 bookings = (ArrayList<Booking>) read.readObject(); //static cast to turn binary into type object of class movies
} catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage()); 
}
}
     
//da 3shan el guest y view el details bt3to
    //overload
public String toString(int index){
    return bookingID + "\n" +  Movie.movies.get(index).getMovieName() + "\n" + Show.shows.get(index).getHall()+ "\n" + Arrays.toString(seatsBooked) + "\n" + rating;
}

/*
public void bookSeats(){
for(int i = 0; )
}
*/

}