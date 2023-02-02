package lk.ijse.institute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.institute.model.UserModel;
import lk.ijse.institute.to.User;
import lk.ijse.institute.util.Navigation;
import lk.ijse.institute.util.Routes;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginFormController {
    @FXML
    private JFXButton btnShowPassword;
    @FXML
    private ToggleGroup role;
    @FXML
    private JFXTextField txtShowPassword;
    @FXML
    private JFXButton btnHidePassword;
    @FXML
    private JFXRadioButton rbtnAdmin;
    @FXML
    private JFXRadioButton rbtnManager;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXButton btnCreateNewAccount;
    @FXML
    private AnchorPane pane;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        String user_name=txtUserName.getText();
        String  pwd= password.getText();

        User user=new User(user_name,pwd);
        if (rbtnAdmin.isSelected()) {
            try {
                ResultSet resultSet = UserModel.login(user);

                if (txtUserName.getText().isEmpty() || password.getText().isEmpty()) {
                    new Alert(Alert.AlertType.ERROR, "Please fill all blank fields").show();
                } else {
                    if (resultSet.next()) {
                        new Alert(Alert.AlertType.INFORMATION, "Login Success").show();
                        Navigation.navigate(Routes.DashBoard, pane);
                    }else{
                        new  Alert(Alert.AlertType.WARNING,"Login Failed").show();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (rbtnManager.isSelected()) {
            try {
                ResultSet resultSet = UserModel.loginManager(user);

                if (txtUserName.getText().isEmpty() || password.getText().isEmpty()) {
                    new Alert(Alert.AlertType.ERROR, "Please fill all blank fields").show();
                } else {
                    if (resultSet.next()) {
                        new Alert(Alert.AlertType.INFORMATION, "Login Success").show();
                        Navigation.navigate(Routes.ManagerDashBoard, pane);
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Login Failed").show();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void createNewAccountOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CreateNewAccount,pane);
    }

    public void txtUserNameOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode()== KeyCode.ENTER){
            password.requestFocus();
        }
    }

    public void passwordOnAction(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode()==KeyCode.ENTER){
            String user_name=txtUserName.getText();
            String  pwd= password.getText();

            User user=new User(user_name,pwd);

            if (rbtnAdmin.isSelected()) {
                try {
                    ResultSet resultSet = UserModel.login(user);

                    if (txtUserName.getText().isEmpty() || password.getText().isEmpty()) {
                        new Alert(Alert.AlertType.ERROR, "Please fill all blank fields").show();
                    } else {
                        if (resultSet.next()) {
                            new Alert(Alert.AlertType.INFORMATION, "Login Success").show();
                            Navigation.navigate(Routes.DashBoard, pane);
                        }else{
                            new  Alert(Alert.AlertType.WARNING,"Login Failed").show();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            if (rbtnManager.isSelected()) {
                try {
                    ResultSet resultSet = UserModel.loginManager(user);

                    if (txtUserName.getText().isEmpty() || password.getText().isEmpty()) {
                        new Alert(Alert.AlertType.ERROR, "Please fill all blank fields").show();
                    } else {
                        if (resultSet.next()) {
                            new Alert(Alert.AlertType.INFORMATION, "Login Success").show();
                            Navigation.navigate(Routes.ManagerDashBoard, pane);
                        }else{
                        new  Alert(Alert.AlertType.WARNING,"Login Failed").show();
                    }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    public void btnShowPasswordOnAction(ActionEvent actionEvent) {
        String pwd = password.getText();
        password.setVisible(false);
        txtShowPassword.setText(pwd);
        btnShowPassword.setVisible(false);
        btnHidePassword.setVisible(true);
        txtShowPassword.setVisible(true);
    }

    public void btnHidePasswordOnAction(ActionEvent actionEvent) {
        password.setVisible(true);
        btnHidePassword.setVisible(false);
        btnShowPassword.setVisible(true);
        txtShowPassword.setVisible(false);
    }
}
