package javaapplication1;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class JavaApplication1 extends Application{
    
    public static void main(String[] args) throws IOException {
        // launch(args);
        
        while(true){
        User user = new User();
        user.logIn();
        User.users.clear();
        switch(user.getUserType()){
            case "Admin":
                Admin admin = new Admin();
                break;
            case "Receptionist":
                Receptionist receptionist = new Receptionist();
                break;
            case "Guest":
                //Guest guest = new Guest();
                break;
        }
        }

     
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
     //MyScene scene = new MyScene(new HBox(), 400.0 ,300);
   /* LogInForm scene = new LogInForm(new HBox() , 400 , 300);
        primaryStage.setTitle("Trial Run");
        primaryStage.setScene(scene);
        primaryStage.show();
*/
        
    }
}