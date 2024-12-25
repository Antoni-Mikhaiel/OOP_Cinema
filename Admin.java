package javaapplication1;
import java.util.Scanner;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication1.Movie;
import static javaapplication1.Movie.movies;


public class Admin{//extends receptionist
 //public void start(Stage primaryStage) {}
    
    
      public Admin() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Admin Menu");
    while (true) {
        System.out.println("1. Users");
        System.out.println("2. Movies");
        System.out.println("3. Bookings");
        System.out.println("4. Shows");
        System.out.println("5. Logout");
        System.out.print("Enter choice: ");
        
        int choice = input.nextInt();
        input.nextLine(); // Clear buffer

        String field = " ";

        switch (choice) {
            case 1:
                System.out.println("Which user type?");
                System.out.println("1. Admin\n2. Receptionist\n3. Guest\n4. Go Back");
                System.out.print("Enter choice: ");
                int userType = input.nextInt();
                input.nextLine(); // Clear buffer
                switch (userType) {
                    case 1: field = "Admin"; User.ReadUser(field , input); break;
                    case 2: field = "Receptionist"; User.ReadUser(field , input); break;
                    case 3: field = "Guest"; User.ReadUser(field , input); break;
                    case 4: continue; // Go back to main menu
                    default:
                        System.out.println("Invalid choice. Returning to main menu.");
                        continue;
                }
                break;
            case 2: field = "Movie"; Movie.readMovie(); break;
            case 3: field = "Booking"; break; //Booking.readBooking() B4 break
            case 4: field = "Show"; break; //Show.readShow() B4 break
            case 5:
               System.out.println("Exiting Admin Menu. Goodbye!");
                Movie.writeMovie();
                Movie.movies.clear();
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                continue;
        }

        // Call the access menu for the chosen field
        accessMenu(input, field);
    }
}


    public void accessMenu(Scanner input, String field) throws IOException { //how to identify which method to call? do a third paramter called identifier then if conditions in each case
        while (true) {
            System.out.println("1. View " + field);
            System.out.println("2. Add " + field);
            System.out.println("3. Edit " + field);
            System.out.println("4. Remove " + field);
            System.out.println("5. Go Back");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Buffer to consume ENTER button entry
            
            switch (choice) {
                case 1:
                    System.out.println("Viewing " + field + "...");  
                    if(field.equals("Movie")) displayMovie(input); 
                    else if(field.equals("Admin") || field.equals("Receptionist") || field.equals("Guest")) displayUser(field , input);
                   // else if(field.equals("Booking")) //displayBooking(input);
                    //else //displayShow(input);
                    break;

                 case 2:
                    System.out.println("Adding " + field + "...");
                   if(field.equals("Movie")) addMovie(input); 
                   else if(field.equals("Admin") || field.equals("Receptionist") || field.equals("Guest")) addUser(field , input);
                   // else if(field.equals("Booking")) //addBooking(input);
                    //else //addShow(input);
                    break;
                case 3:
                    System.out.println("Editing " + field + "...");
                    if(field.equals("Movie")) editMovie(input); 
                    else if(field.equals("Admin") || field.equals("Receptionist") || field.equals("Guest")) editUser(field , input);
                   // else if(field.equals("Booking")) //editBooking(input);
                    //else //editShow(input);
                    break;

                case 4:
                    if(field.equals("Movie")) removeMovie(input); 
                    else if(field.equals("Admin") || field.equals("Receptionist") || field.equals("Guest")) removeUser(field , input);
                   // else if(field.equals("Booking")) //removeBooking(input);
                    //else //removeShow(input);
                    break;
                case 5:
                    System.out.println("Returning to the Admin Menu.");
                    if(field.equals("Admin") || field.equals("Receptionist") || field.equals("Guest")){
                    User.WriteUser(field);
                    User.users.clear();
                    }
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

   public void addMovie(Scanner input) throws IOException {
    System.out.println("Enter Movie Name: ");
    String movieName = input.nextLine();
    
    System.out.println("Enter Movie Genre: ");
    String movieGenre = input.nextLine();
    
    System.out.println("Enter Movie Duration (mins): ");
    int movieDuration = 0;
    while (true) { // Validate integer input
        try {
            movieDuration = Integer.parseInt(input.nextLine());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer for the duration.");
        }
    }

    System.out.println("Enter Movie Rating: ");
    double movieRating = 0.0;
    while (true) { // Validate double input
        try {
            movieRating = Double.parseDouble(input.nextLine());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid decimal number for the rating.");
        }
    }

    Movie newMovie = new Movie(movieName, movieGenre, movieDuration, movieRating);
    
    //add object to ArrayList
    Movie.movies.add(newMovie);
   newMovie.createMovieID();
    System.out.println("Movie Created Successfuly. Movie ID: " + newMovie.getMovieID());
} 
   
   public void editMovie(Scanner input) throws IOException{ //PARAMETER != NOT. PARAMETER = ARRAYLIST OF OBJECT MOVIE
     int index = movieIDChecker(input , "edit");
        if(index == -1 ) {
    return;
    }
    System.out.println("Editing Movie: " + Movie.movies.get(index).getMovieID());
    System.out.println("Current Name: " +  Movie.movies.get(index).getMovieName());
    System.out.print("Enter new name (or press Enter to keep current): ");
    String newName = input.nextLine();
    if (!newName.isEmpty()) Movie.movies.get(index).setMovieName(newName);
   
    System.out.println("Current Genre: " + Movie.movies.get(index).getMovieGenre());
    System.out.print("Enter new genre (or press Enter to keep current): ");
    String newGenre = input.nextLine();
    if (!newGenre.isEmpty()) Movie.movies.get(index).setMovieGenre(newGenre);
   
    System.out.println("Current Duration: " + Movie.movies.get(index).getMovieDuration());
    System.out.print("Enter new Duration (or press Enter to keep current): ");
    String newDurationInput = input.nextLine();
    if (!newDurationInput.trim().isEmpty()) {
        try {
            int newDuration = Integer.parseInt(newDurationInput);
            Movie.movies.get(index).setMovieDuration(newDuration);
        } catch (NumberFormatException e) {
            System.out.println("Invalid duration input. Keeping current value.");
        }
    }
    
    System.out.println("Current Rating: " + Movie.movies.get(index).getMovieRating());
    System.out.print("Enter new Rating (or press Enter to keep current): ");
    String newRatingInput = input.nextLine();
    if (!newRatingInput.isEmpty()) {
        double newRating = Double.parseDouble(newRatingInput); // Convert to double
        Movie.movies.get(index).setMovieRating(newRating);
    }
    System.out.println("Edit Successful!");
   }
   
   public void displayMovie(Scanner input) throws IOException{
    int index = movieIDChecker(input , "display");
    if(index == -1 ) {
    return;
    }
    
     // Display the movie details
    System.out.println("----- Movie Details -----");
    System.out.println("Movie ID: " + Movie.movies.get(index).getMovieID());
    System.out.println("Name: " + Movie.movies.get(index).getMovieName());
    System.out.println("Genre: " + Movie.movies.get(index).getMovieGenre());
    System.out.println("Duration: " + Movie.movies.get(index).getMovieDuration() + " minutes");
    System.out.println("Rating: " + Movie.movies.get(index).getMovieRating() + " / 5");
    System.out.println("Bookings: " + Movie.movies.get(index).getMovieBooking());
    System.out.println("Revenue: $" + Movie.movies.get(index).getMovieRevenue());
   }
   
   public void removeMovie(Scanner input) throws IOException {
     while(true){
   System.out.print("Enter Movie ID to remove: ");
    int movieID = input.nextInt();
    input.nextLine(); // Clear buffer
    
    boolean found = false;
   for (int i = 0; i < Movie.movies.size(); i++) {
            if (Movie.movies.get(i).getMovieID() == movieID) {
                System.out.println("Removing " + Movie.movies.get(i).getMovieName() + "...");
                Movie.movies.remove(i); // Remove the movie directly
                System.out.println(movieID + " has been removed.");
                found = true;
                break;
            }
        }
    if (!found) {
            System.out.println("No movie found with ID: " + movieID);
            System.out.println("1. Try again\n2. Go back");
            if (!input.hasNextInt()) { // Validate user inputted an INTEGER
                System.out.println("Invalid choice. Returning to the menu.");
                input.next(); // Clear invalid input
                return;
            }
            int choice = input.nextInt();
            if (choice == 2) return;
        } else {
            break; // Exit the loop after successful removal
        }
    }
   
}
   
   public int movieIDChecker(Scanner input , String action){
     while(true){
   System.out.print("Enter Movie ID to " + action + ": ");
    int movieID = input.nextInt();
    input.nextLine(); // Clear buffer
    
    for (int i = 0; i < Movie.movies.size(); i++) {
            if (Movie.movies.get(i).getMovieID() == movieID) {
                return i; // Return index if ID is found
            }
        }
    
   // If no movie found, prompt for action
        System.out.println("No movie found with ID: " + movieID + "\n1. Try again\n2. Go Back");
        int choice = input.nextInt();
        input.nextLine(); // Clear buffer
        if (choice == 2) {
            return -1; // Use -1 as an indicator for "Go Back"
        }
}
   }
   
   public void addUser(String userType , Scanner input) throws IOException{
    System.out.println("Create " + userType + " username: ");
    String username = input.nextLine();
    System.out.println("Create " + userType + " Password: ");
    String password = input.nextLine();
    User user = new User(username, password);
    user.createUserID();
    user.setUserType(userType);
    User.users.add(user);
    System.out.println("Account Successfuly Created. Your ID is " + user.getUserID());
   }
   
   public void editUser(String userType , Scanner input){
    int index = userIDChecker(input , userType , "edit");
    if(index == -1 ) {
    return;
    }
    System.out.println("Editing " + userType + ": " + User.users.get(index).getUserID());
    System.out.println("Current Name: " +  User.users.get(index).getUserName());
    System.out.print("Enter new name (or press Enter to keep current): ");
    String newName = input.nextLine();
    if (!newName.isEmpty()) User.users.get(index).setUserName(newName);
    
    System.out.println("Current Password: " +  User.users.get(index).getPassword());
    System.out.print("Enter new password (or press Enter to keep current): ");
    String newPassword = input.nextLine();
    if (!newPassword.isEmpty()) User.users.get(index).setPassword(newPassword);
    System.out.println("Edit Successful!");
   /*
    try {
            User.WriteUser(userType);
    } catch (IOException ex) {
        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
    }
*/
   }
   
   public void displayUser(String userType , Scanner input){
       int index = userIDChecker(input , userType , "display");
    if(index == -1 ) {
         try {
            User.WriteUser(userType);
    } catch (IOException ex) {
        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
    }
    return;
    }
       System.out.println("----- Details -----");
       System.out.println("UserName: " + User.users.get(index).getUserName());
         System.out.println("Password: " + User.users.get(index).getPassword());
       
   }
   
   public void removeUser(String userType , Scanner input) throws IOException{
     while(true){
   System.out.print("Enter " + userType +" ID to remove: ");
    int userID = input.nextInt();
    input.nextLine(); // Clear buffer
    
    boolean found = false;
    for (int i = 0; i < User.users.size(); i++) { //
    if (User.users.get(i).getUserID() == userID) {
          System.out.println("Removing " + User.users.get(i).getUserName() + "...");
          User.users.remove(i); //Remove user Directly 
          User.WriteUser(userType);

          System.out.println("UserID: " + userID + " has been removed.");
        found = true;
        break;
    }
}
    if(!found) { System.out.println("No " + userType + " found with ID: " + userID + "\n1.Try again\n2.Go Back");
    if (input.nextInt() == 2)
       /*
        try {
            User.WriteUser(userType);
    } catch (IOException ex) {
        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
    }
*/
        return; 
       }else{
    break;
    }
       }
   }
   
   public int userIDChecker(Scanner input , String userType , String action){
     while(true){
   System.out.print("Enter " + userType +" ID to " + action + ": ");
    int userID = input.nextInt();
    input.nextLine(); // Clear buffer
    
    for (int i = 0; i < User.users.size(); i++) {
            if (User.users.get(i).getUserID() == userID) {
                return i; // Return index if ID is found
            }
        }
    
   // If no movie found, prompt for action
        System.out.println("No " + userType + " found with ID: " + userID + "\n1.Try again\n2.Go Back");
        int choice = input.nextInt();
        input.nextLine(); // Clear buffer
        if (choice == 2) {
            return -1; // Use -1 as an indicator for "Go Back"
        }
}
   }
   
   public void displayBooking(Scanner input) throws IOException{
       int index = -1;
           while(true){
   System.out.print("Enter Booking  ID:");
    int bookingID = input.nextInt();
    input.nextLine(); // Clear buffer
    
    for (int i = 0; i < Booking.bookings.size(); i++) {
            if (Booking.bookings.get(i).getBookingID() == bookingID) {
                index = i;
            }
    }
    if(index == -1){
    System.out.println("No Booking found with ID: " + bookingID + "\n1.Try again\n2.Go Back");
        int choice = input.nextInt();
        input.nextLine(); // Clear buffer
        if (choice == 2) {
            return;
        }
    }else{
    break;
    }
}
    System.out.println("----- Booking Details -----");
    System.out.println("Booking ID: " + Booking.bookings.get(index).getBookingID());
    //Rest of credentials will be done when Booking , show & receptionist is complete
    System.out.println("Movie Name: " + Booking.bookings.get(index).getMovieName());
   }
}
