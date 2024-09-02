package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class DashboardForm implements Initializable {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane sidePane;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private JFXButton btnStudents;

    @FXML
    private JFXButton btnRooms;

    @FXML
    private JFXButton btnReservation;

    @FXML
    private JFXButton btnUser;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label lblTime;

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashboardForm.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("DASH BORD");
        stage.centerOnScreen();
    }

    @FXML
    void btnReservationOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/reservation.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        pane.getChildren().clear();
        pane.getChildren().add(load);

    }

    @FXML
    void btnRoomsOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/room_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        pane.getChildren().clear();
        pane.getChildren().add(load);
    }

    @FXML
    void btnStudentsOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/student_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        pane.getChildren().clear();
        pane.getChildren().add(load);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), event -> lblTime.setText("" + new SimpleDateFormat("EEEE - MMM-dd-yyyy  HH:mm:ss").format(Calendar.getInstance().getTime()))), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

}

