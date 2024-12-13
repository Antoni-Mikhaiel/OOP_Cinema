package javaapplication1;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
public class Receptionist{
 public static ArrayList<Receptionist> receptionists=new ArrayList<>();
 
public static void Receptionist() throws IOException{
Scanner input=new Scanner(System.in);
int choice=0;
        do{
System.out.println("what do you need to do?");
System.out.println("1. create booking.");
System.out.println("2. cancel booking.");
System.out.println("3. calculate payment.");
System.out.println("4.exist receptionist menu");
choice=input.nextInt();
switch(choice){
    case 1:
     Receptionist.createBooking();
     break;

    case 2:
       Receptionist.cancelBooking();
        break;
    case 3:
        calculatePayment();
        break;
    case 4:
        Receptionist.saveRecp("C:/Users/Giza Ahmed Seif/Desktop/Receptionist.txt");
        Receptionist.receptionists.clear();
        break;
    default:
        System.out.println("invaild choice.try again");
}
}while(choice!=4);
        }
    public static void createBooking() throws IOException{ 
        
        Scanner input=new Scanner(System.in);
        System.out.println("enter booking id :");
        int id=input.nextInt();
        input.nextLine();
        
        System.out.println("enter guest username :");                                                       ////////////
        String guestusername=input.nextLine();
          
         System.out.println("enter showName:");
         String showName=input.nextLine(); 
   input.nextLine();
         
         System.out.println("enter seats booked :");
         int seats=input.nextInt();
         input.nextLine();
         input.close();
         Booking booking=new Booking(id,guestusername,showName,seats);
         Booking.bookings.add(booking);
   return;
    }
    public static void cancelBooking(){
        Scanner input=new Scanner(System.in);
        System.out.print("enter booking id: ");
        int id=input.nextInt();
        for(Booking b:Booking.bookings){
            if(b.getbookingid()==id){
                Booking.bookings.remove(b);
                System.out.println("booking "+id+" canceled.");
                return;
            }
        }
         System.out.print("no booking with id: "+id+" found");
    }
    public static void calculatePayment(){
        double payment=0;
        for(Booking b:Booking.bookings){
            payment+=b.getPayment();
        }
         System.out.println("Total payment for all bookings: "+payment);
         return;
    }
    
public static void saveRecp(String pathname) throws FileNotFoundException{
PrintWriter pw= new PrintWriter(pathname);
for(User user: User.users){
pw.println(user.getUserName()+"/n"+user.getPassword()+"/n");
}
}
}