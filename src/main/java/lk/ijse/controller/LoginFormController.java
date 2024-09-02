package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField lblUserName;

    @FXML
    private PasswordField lblPassword;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private RadioButton rBtnShowPassword;

    @FXML
    private Label txtPassword;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        if (lblUserName.getText().equals("tharu") && lblPassword.getText().equals("1234")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashboardForm.fxml"));
            AnchorPane anchorPane = loader.load();
            Scene scene = new Scene(anchorPane);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("DASH BORD");
            stage.centerOnScreen();
        } else {
            new Alert(Alert.AlertType.ERROR, "Sorry").show();
            lblUserName.clear();
            lblPassword.clear();
        }
    }


    @FXML
    void btnRegisterOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/SignUpForm.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Register");
        stage.centerOnScreen();

    }


    public void rBtnOnMouseClicked(javafx.scene.input.MouseEvent mouseEvent) {
        txtPassword.setText(lblPassword.getText());
        txtPassword.setVisible(rBtnShowPassword.isSelected());

    }

    public void txtPasswordOnKeyReleased(javafx.scene.input.KeyEvent keyEvent) {

    }
}

