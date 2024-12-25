package javaapplication1;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
public class Receptionist{
 public static ArrayList<Receptionist> receptionists=new ArrayList<>();
 
public static void Receptionist() throws IOException{
 Movie.readMovie();
 Show.readshow();
 Guest.readGuest();
 Booking.readBooking();
Scanner input=new Scanner(System.in);
int choice=0;
        do{
System.out.println("what do you need to do?");
System.out.println("1. create booking.");
System.out.println("2. cancel booking.");
System.out.println("3.exist receptionist menu");
choice=input.nextInt();
switch(choice){
    case 1:
     Receptionist.createBooking();
     break;

    case 2:
       Receptionist.cancelBooking();
        break;
    case 3:
        Movie.writeMovie();
        Movie.movies.clear();
        Show.saveshow();
        Show.shows.clear();
        Guest.writeGuest();
        Guest.guests.clear();
        Booking.bookings.clear();
        break;
    default:
        System.out.println("invaild choice.try again");
}
}while(choice!=4);
        }
    public static void createBooking() throws IOException{ 
  Scanner input=new Scanner(System.in);
  System.out.println("enter guest username :");
  String username=input.nextLine();
  System.out.println("enter guest password :");
  String password=input.nextLine();
  Guest guest=getguest(username,password);
  
  Movie movie=getmovie(input);
  Show show=getshow(input,movie);

   System.out.println("enter seats booked :");
   int seats=input.nextInt();
   input.nextLine();
         
  float payment=calculatePayment(seats,show);
  
  show.setRevenue((show.getRevenue())+payment);
  show.setnumofbooking((show.getnumofbooking())+1);
  
  movie.setMovieRevenue((movie.getMovieRevenue())+payment);
  movie.setMovieBooking((movie.getMovieBooking())+1);
                                                              
         input.close();
         Booking booking=new Booking(movie,show,guest,seats,payment);
         Booking.bookings.add(booking);
         guest.historyOfbookings.add(booking);
         booking.writeBooking();
   return;
    }
    public static void cancelBooking(){
        Scanner input=new Scanner(System.in);
        System.out.print("enter booking id: ");
        int id=input.nextInt();
        for(Booking b:Booking.bookings){
            if(b.getBookingID()==id){
                Booking.bookings.remove(b);
                System.out.println("booking "+id+" canceled.");
                return;
            }
        }
         System.out.print("no booking with id: "+id+" found");
    }
    public static float calculatePayment(int seats,Show show){
    return (show.getticketprice())*seats;
    }
    
public static void saveRecp(){
      File file = new File("C:/Users/Giza Ahmed Seif/Desktop/MovieList.txt");
      try(ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(file))){  
       write.writeObject(receptionists);
}catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
}
public static void readRecp() { 
    File file = new File("C:/Users/Giza Ahmed Seif/Desktop/Receptionists.txt");
    if (!file.exists()) {
        System.out.println("Receptionist file does not exist.");
        return; 
    }
     try (ObjectInputStream read= new ObjectInputStream(new FileInputStream(file))) {
 receptionists = (ArrayList<Receptionist>) read.readObject();
} catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage()); 
}
}
public static Movie getmovie(Scanner input){
   System.out.println("enter the movie name: ");
      String mn=input.nextLine();
   for(Movie m:Movie.movies){
   if(m.getMovieName().equalsIgnoreCase(mn))
   return m;
   }
   System.out.println("movie not found :"); 
   return null;
}
public static Show getshow(Scanner input,Movie movie){
System.out.println("enter the show time :"); 
float time=input.nextFloat();
input.nextLine();
for(Show s:Show.shows){
    if(time==s.getshowtime() && s.getshowname().equalsIgnoreCase(movie.getMovieName()))
        return s;
}
System.out.println("no shows for this movie at that time :"); 
return null;
}
public static Guest getguest(String username,String password){
    for(Guest g:Guest.guests){
        if(g.getUserName().equals(username) && g.getPassword().equals(password)){
            return g;
        }
    }
    return new Guest(username,password);
}
}