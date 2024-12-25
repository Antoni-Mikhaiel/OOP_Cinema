package javaapplication1;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.util.Random;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User implements Serializable{
    public static ArrayList<User> users = new ArrayList<>();
     private String username;
     private String password ;
     private int userID;
     private String userType;
     
     public User(){
     username = null;
     password = null;
     userID = 0;
     userType = null;
     }

     public User(String username ,String password){
     this.username = username;
     this.password=password;
     
 }
     
     //SETTERS
    public void setID(int ID){
this.userID=ID;
}
    public void setUserName(String username){
    this.username = username;
    }
   public void setPassword(String password){
   this.password = password;
   }
   public void setUserType(String userType){
   this.userType = userType;
   }
    //GETTERS
public String getUserName(){
return username;
}
public String getPassword(){
return password;
}
public int getUserID(){
return userID;
}
public String getUserType(){
return userType;
}

@Override
public String toString(){
return userID + "\n" + username + "\n" + password;
}

public void createUserID(){
Random random = new Random();
userID = random.nextInt(999);
}
//either clear arraylist after validating or at start of Admin
public static void WriteUser(String userType) throws IOException{
       File file;
        switch (userType) {
            case "Admin":
                file = new File("C:/Users/ok/Desktop/OOP_Cinema/Admins.txt");
                break;
            case "Receptionist":
                file = new File("C:/Users/ok/Desktop/OOP_Cinema/Receptionists.txt");
                break;
            default:
                file = new File("C:/Users/ok/Desktop/OOP_Cinema/Guests.txt");
                break;
        }
        try(ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(file))){ //Overwrites + ensures output is closed after try-catch statement 
       write.writeObject(users);
}catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
}


public static void ReadUser(String userType , Scanner input) throws IOException{
  File file;
        switch (userType) {
            case "Admin":
                file = new File("C:/Users/ok/Desktop/OOP_Cinema/Admins.txt");
                break;
            case "Receptionist":
                file = new File("C:/Users/ok/Desktop/OOP_Cinema/Receptionists.txt");
                break;
            default:
                file = new File("C:/Users/ok/Desktop/OOP_Cinema/Guests.txt");
                break;
        }
        if (!file.exists()) {
        System.out.println(userType + " file does not exist.");
        return; 
    }
    try (ObjectInputStream read= new ObjectInputStream(new FileInputStream(file))) {
 users = (ArrayList<User>) read.readObject(); //static cast to turn binary into type object of class movies
} catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage()); 
}
}


     public boolean logIn() throws IOException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Log In");
    System.out.println("--------");
    while (true) {
        System.out.println("Account Type:\n1. Admin\n2. Receptionist\n3. Guest");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        switch (choice) {
            case 1:
                userType = "Admin";
                break;
            case 2:
                userType = "Receptionist";
                break;
            case 3:
                userType = "Guest";
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                continue; // Restart the loop for a valid choice
        }

        // Load user data from the file
        ReadUser(userType, scanner);

        // Log the userType and users list
        System.out.println("Loaded userType: " + userType);
        System.out.println("Users loaded: " + users.size() + " users.");

        System.out.print("User ID: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid ID format. Please try again.");
            scanner.nextLine(); // Clear invalid input
            continue;
        }
        userID = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        System.out.print("Password: ");
        password = scanner.nextLine();
        if (password.isEmpty()) {
            System.out.println("Password cannot be empty. Please try again.");
            continue;
        }

        // Validate credentials
       boolean credentialsValid = false;
        for (User user : User.users) {
            // Log each user's details for debugging
            System.out.println("Checking user: " + user.getUserName() + ", UserID: " + user.getUserID() + ", UserType: " + user.getUserType());

            // Check if the userID matches and then validate the password
            if (user.getUserID() == userID && password.equals(user.getPassword())) {
                System.out.println("Welcome, " + user.getUserName() + "!");
                credentialsValid = true;
                break;
            }
        }

        if (credentialsValid) {
            return true; // Successfully logged in
        } else {
            System.out.println("Error: Invalid credentials. Please try again.");
        }

        System.out.println("I <3 H");
    // If we exit the loop without a valid login
    System.out.println("Account not found. Returning to the main menu.");
    return false;
     }
 }

}