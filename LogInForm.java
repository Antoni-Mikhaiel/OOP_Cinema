/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LogInForm extends Scene{
    GridPane pane = new GridPane();
    public static StackPane pane2=new StackPane();
    HBox box=new HBox();
    Label sign= new Label();
    Label user= new Label();
    Label pass=new Label();
    Label img=new Label();
    TextField username = new TextField();
    PasswordField password = new PasswordField();
    Label acc = new Label();
    Button signIn = new Button();
    Label msg = new Label();
    Alert alert=new Alert(Alert.AlertType.ERROR);
    Image im=new Image("file:/C:/Users/ok/Desktop/test.jpg");
    ImageView view=new ImageView(im);
    Hyperlink link=new Hyperlink();
     
    static User currUser;
  
    
    public LogInForm(Parent parent, double d, double d1) 
    {
        super(parent, d, d1);
        
        sign.setText("Sign In");
        sign.setFont(Font.font("Times New Roman",FontWeight.BOLD,30));
        user.setText("Username: ");
        user.setFont(Font.font("Times New Roman",20));
        pass.setText("Password: ");
        pass.setFont(Font.font("Times New Roman",20));
        
        
        signIn.setText("Sign In");
        signIn.setFont(Font.font("Arial",18));
       // signIn.setOnAction(new SignInHandler());
        signIn.setAlignment(Pos.CENTER);
        signIn.setMaxWidth(500);
        signIn.setMaxHeight(200);
        signIn.setStyle(" -fx-background-color: #74175E; -fx-background-radius: 10px; -fx-text-fill: #ffffff;");
        signIn.setCursor(Cursor.HAND);
        
        DropShadow shadow = new DropShadow();  
        signIn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
        signIn.setEffect(shadow);
        });
    
        signIn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
        signIn.setEffect(null);
        });
        
        
        acc.setText("Don't have an account?");
        acc.setFont(Font.font("Times New Roman",17));
        acc.setAlignment(Pos.CENTER);
        
        msg.setFont(Font.font("Times New Roman",16));
        
        view.setPreserveRatio(true);
        view.setFitHeight(600);
        view.setFitWidth(500);
        img.setGraphic(view);
        
        box.setStyle("-fx-background-color: #74175E;");
        pane.setStyle("-fx-background-color: #FFFFFF;");
        
        link.setText("Sign Up");
        link.setFont(Font.font("Times New Roman",17));
       // SignUpHandler handler2 = new SignUpHandler();
       // link.setOnAction(handler2);
        
        HBox u=new HBox(user,username);
        HBox p=new HBox(pass,password);
        u.setSpacing(5);
        p.setSpacing(8);
        VBox v=new VBox(u,p);
        v.setSpacing(20);
        
        HBox b=new HBox(acc,link);
        b.setSpacing(5);
        
        pane.add(sign,0,1);
        pane.add(v,0,4);
        pane.add(signIn,0,7);
        pane.add(b,0,10);
        pane.add(msg,0,11);
        //pane2.add(img, 0, 0);
        pane2.getChildren().add(img);
        
       // pane.setPadding(new Insets(3));
       GridPane.setHalignment(v, HPos.LEFT);
        b.setAlignment(Pos.CENTER);
        pane.setPrefWidth(400);
        pane.setMaxHeight(500);
        pane.setTranslateY(50);
        pane.setTranslateX(30);
        pane2.setTranslateX(60);
        pane2.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(70));
        pane.setVgap(0.5);
        pane.setVgap(15);
        box.getChildren().addAll(pane2,pane);
        

        setRoot(box);
        
    }
}

 