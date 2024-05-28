package org.example.studentregister;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomePageController {

    @FXML
    private AnchorPane anchor;

    @FXML
    private Button loginbutton;

    @FXML
    private PasswordField pwbox;

    @FXML
    private Button registerbutton;

    @FXML
    private MenuButton rolemenu;

    @FXML
    private TextField usrbox;

    @FXML
    void goregpage(ActionEvent event) {

    }

    @FXML
    void login(ActionEvent event) {

    }
private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private double x = 0 ;
    private double y = 0;
    public void loginAdmin(){
        String sql= "SELECT * FROM admin WHERE username = ? and password = ?";
        connect = database.connectDb();

        try{
            Alert alert;

            prepare = connect.prepareStatement(sql);
            prepare.setString(1,usrbox.getText());
            prepare.setString(2,pwbox.getText());
            result= prepare.executeQuery();

           if(usrbox.getText().isEmpty() || pwbox.getText().isEmpty()) {
               alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText(null);
               alert.setContentText("Inavlis");
               alert.showAndWait();

           }else{
            if(result.next()){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Info");
                alert.setHeaderText(null);
                alert.setContentText("Success");
                alert.showAndWait();
                loginbutton.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                root.setOnMousePressed((MouseEvent event) ->{
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) ->{
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                });

                stage.initStyle(StageStyle.TRANSPARENT);


                stage.setScene(scene);
                stage.show();

            }



            }

        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    }


