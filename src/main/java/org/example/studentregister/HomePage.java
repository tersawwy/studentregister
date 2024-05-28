package org.example.studentregister;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class HomePage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
      try {
         Parent root=  FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
          throw new RuntimeException(ex);
      }
    }


    public static void main(String args[]) {
        launch();
    }
}