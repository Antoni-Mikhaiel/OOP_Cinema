/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class SideBar extends Application{

    public static VBox createSidebar(Stage stage) {
        VBox sidebar = new VBox(20);
        sidebar.setStyle("-fx-background-color: #7D1515; -fx-padding: 20;");
        sidebar.setAlignment(Pos.CENTER);
        
        ImageView logo = new ImageView(new Image("file:src/profile_icon.jpg"));
        logo.setFitWidth(100);
        logo.setPreserveRatio(true);
        Label welcomeLabel = new Label("Welcome, Admin"); //I want to make this "Welcome, AdminName"
        welcomeLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18;");
        
        Button dashboardButton = new Button("Dashboard");
        Button addMoviesButton = new Button("Add Movies");
        Button customersButton = new Button("Customers");
        Button editScreeningButton = new Button("Edit Screening");
        Button accountsButton = new Button("Accounts");
        Button signOutButton = new Button("Sign Out");
        signOutButton.setStyle("-fx-background-color: #A00000; -fx-text-fill: white;");
        

        for (Button button : new Button[]{dashboardButton, addMoviesButton, customersButton, editScreeningButton, accountsButton}) {
            button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 16;");
            button.setPrefWidth(150);
        }

        // Add event handlers to navigate between scenes
while(true){
        dashboardButton.setOnAction(e -> stage.setScene(new DashboardScene(stage).getScene()));
        addMoviesButton.setOnAction(e -> stage.setScene(new AddMoviesScene(stage).getScene()));
        //customersButton.setOnAction(e -> stage.setScene(new CustomersScene(stage).getScene()));
        //editScreeningButton.setOnAction(e -> stage.setScene(new EditScreeningScene(stage).getScene()));
        //accountsButton.setOnAction(e -> stage.setScene(new AccountsScene(stage).getScene()));

        sidebar.getChildren().addAll(logo, welcomeLabel, dashboardButton, addMoviesButton, customersButton, editScreeningButton, accountsButton , signOutButton);
        return sidebar;
    }
    }
    
     @Override
    public void start(Stage primaryStage) {
        // Start with the Dashboard scene
        DashboardScene dashboardScene = new DashboardScene(primaryStage);
        primaryStage.setTitle("Admin Menu");
        primaryStage.setScene(dashboardScene.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

