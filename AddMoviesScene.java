package javaapplication1;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.util.ArrayList;
import javafx.beans.property.*;


/**
 *
 * @author ok
 */
public class AddMoviesScene {

    private final Stage stage;
    private final Scene scene;

    public AddMoviesScene(Stage stage) {
        this.stage = stage;

        VBox sidebar = SideBar.createSidebar(stage);

        // Center Pane
        GridPane centerPane = new GridPane();
        centerPane.setPadding(new Insets(20));
        centerPane.setHgap(10);
        centerPane.setVgap(10);

        ImageView imageView = new ImageView();
        imageView.setFitWidth(100);
        imageView.setFitHeight(150);
        Button importButton = new Button("Import");

        TextField movieTitleField = new TextField();
        movieTitleField.setPromptText("Movie Title");
        TextField genreField = new TextField();
        genreField.setPromptText("Genre");
        TextField durationField = new TextField();
        durationField.setPromptText("Duration");
        DatePicker showingDatePicker = new DatePicker();

        Button insertButton = new Button("Insert");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button clearButton = new Button("Clear");

        HBox actionButtons = new HBox(10, insertButton, updateButton, deleteButton, clearButton);

        centerPane.add(imageView, 0, 0, 1, 2);
        centerPane.add(importButton, 0, 2);
        centerPane.add(new Label("Movie Title:"), 1, 0);
        centerPane.add(movieTitleField, 2, 0);
        centerPane.add(new Label("Genre:"), 1, 1);
        centerPane.add(genreField, 2, 1);
        centerPane.add(new Label("Duration:"), 1, 2);
        centerPane.add(durationField, 2, 2);
        centerPane.add(new Label("Showing Date:"), 1, 3);
        centerPane.add(showingDatePicker, 2, 3);
        centerPane.add(actionButtons, 1, 4, 2, 1);

        // Table View
        TableView<Movie> movieTable = new TableView<>();
        TableColumn<Movie, String> titleColumn = new TableColumn<>("Movie Title");
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMovieName()));

        TableColumn<Movie, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMovieGenre()));

        TableColumn<Movie, String> durationColumn = new TableColumn<>("Duration");
        durationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMovieDuration()));

       TableColumn<Movie, Integer> bookingsColumn = new TableColumn<>("Bookings");
       bookingsColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMovieBooking()).asObject());

        TableColumn<Movie, Double> revenueColumn = new TableColumn<>("Revenue");
        revenueColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getMovieRevenue()).asObject());
         
       movieTable.getColumns().addAll(titleColumn, genreColumn, durationColumn, bookingsColumn, revenueColumn);
        movieTable.setPrefWidth(600);
        Movie.readMovie();
        movieTable.getItems().addAll(Movie.movies);

        VBox rightPane = new VBox(10, new TextField("Search"), movieTable);
        rightPane.setPadding(new Insets(20));

        // Main Layout
        BorderPane root = new BorderPane();
        root.setLeft(sidebar);
        root.setCenter(centerPane);
        root.setRight(rightPane);

        this.scene = new Scene(root, 800, 600);
    }

    public Scene getScene() {
        return scene;
    }
}
