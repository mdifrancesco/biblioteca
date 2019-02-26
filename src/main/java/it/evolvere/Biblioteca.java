package it.evolvere;

import it.evolvere.factory.ConnectionFactory;
import it.evolvere.model.Libro;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Biblioteca extends Application {

    public static void main(String[] args) {
        Biblioteca.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setTitle("Biblioteca");
        primaryStage.setScene(scene);



        ConnectionFactory.getConnection();  //metodo di Classe!!!!!

        primaryStage.show();
    }
}
