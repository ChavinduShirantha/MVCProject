package lk.ijse.institute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.institute.model.UserModel;
import lk.ijse.institute.to.User;
import lk.ijse.institute.util.Navigation;
import lk.ijse.institute.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Pattern;

public class CreateNewAccountController {
    @FXML
    private JFXButton btnHidePassword;
    @FXML
    private JFXButton btnShowPassword;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private JFXRadioButton rbtnAdmin;
    @FXML
    private ToggleGroup role;
    @FXML
    private JFXRadioButton rbtnManager;
    @FXML
    private JFXButton btnResetFields;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblUserNameError;
    @FXML
    private Label lblPasswordError;
    @FXML
    private Label lblEmailError;
    @FXML
    private Label lblContactError;
    @FXML
    private Label lblCityError;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtContact;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    @FXML
    private Label lblNameError;
    @FXML
    private Label lblSurNameError;
    @FXML
    private JFXButton btnSignUp;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXButton btnBack;

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Login,pane);
    }

    public void signUpOnAction(ActionEvent actionEvent) throws IOException {
        String name=txtName.getText();
        String surName=txtSurname.getText();
        String city=txtCity.getText();
        String contact=txtContact.getText();
        String email=txtEmail.getText();
        String userName=txtUserName.getText();
//        String password=txtPassword.getText();
        String password=pwdPassword.getText();

        Pattern patternName = Pattern.compile("^[A-Z][a-zA-Z]+$");
        Pattern patternSurName = Pattern.compile("^[A-Z][a-zA-Z]+$");
        Pattern patternCity = Pattern.compile("^[A-Z][a-zA-Z]+$");
        Pattern patternContact = Pattern.compile("^(?:\\+94|070|071|072|074|075|076|077|078)[0-9]{7,9}$");
        Pattern patternEmail = Pattern.compile("([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})");
        Pattern patternUserName = Pattern.compile("^[A-Z][a-zA-Z]+$");
        Pattern patternPassword = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");



        if (patternName.matcher(name).matches() & patternSurName.matcher(surName).matches() & patternCity.matcher(city).matches() &
                patternContact.matcher(contact).matches() & patternEmail.matcher(email).matches() & patternUserName.matcher(userName).matches() &
                patternPassword.matcher(password).matches()){
//        new Alert(Alert.AlertType.CONFIRMATION, "Create New Account Successfully!").show();
            if (rbtnAdmin.isSelected()){
                String role=rbtnAdmin.getText();
                User user=new User(name,surName,city,contact,email,userName,password,role);
                try {
                    boolean isAdded= UserModel.save(user);

                    if (isAdded){
                        new Alert(Alert.AlertType.CONFIRMATION, "Create New Account Successfully!").show();
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Navigation.navigate(Routes.Login,pane);
            }else{
                if (!patternName.matcher(name).matches()){
                    lblNameError.setText("Incorrect Name Pattern");
                    txtName.setStyle("-fx-border-color: red");
                }
                if (!patternSurName.matcher(surName).matches()){
                    lblSurNameError.setText("Incorrect Name Pattern");
                    txtSurname.setStyle(" -fx-border-color: red");
                }
                if (!patternCity.matcher(city).matches()){
                    lblCityError.setText("Incorrect City Name Pattern");
                    txtCity.setStyle("-fx-border-color: red");
                }
                if (!patternContact.matcher(contact).matches()){
                    lblContactError.setText("Incorrect Contact Number Pattern");
                    txtContact.setStyle("-fx-border-color: red");
                }
                if (!patternEmail.matcher(email).matches()){
                    lblEmailError.setText("Incorrect Email Pattern");
                    txtEmail.setStyle("-fx-border-color: red");
                }
                if (!patternUserName.matcher(userName).matches()){
                    lblUserNameError.setText("Incorrect User Name Pattern");
                    txtUserName.setStyle("-fx-border-color: red");
                }
                if (!patternPassword.matcher(password).matches()){
                    lblPasswordError.setText("Incorrect Password Pattern");
                    txtPassword.setStyle("-fx-border-color: red");
                    new Alert(Alert.AlertType.ERROR, "New User Added Failed!").show();
                }

            }
        }

        if (rbtnManager.isSelected()){
            String role=rbtnManager.getText();
            User user=new User(name,surName,city,contact,email,userName,password,role);
            try {
                boolean isAdded= UserModel.save(user);

                if (isAdded){
                    new Alert(Alert.AlertType.CONFIRMATION, "Create New Account Successfully!").show();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Navigation.navigate(Routes.Login,pane);
        }else{
            if (!patternName.matcher(name).matches()){
                lblNameError.setText("Incorrect Name Pattern");
                txtName.setStyle("-fx-border-color: red");
            }
            if (!patternSurName.matcher(surName).matches()){
                lblSurNameError.setText("Incorrect Name Pattern");
                txtSurname.setStyle(" -fx-border-color: red");
            }
            if (!patternCity.matcher(city).matches()){
                lblCityError.setText("Incorrect City Name Pattern");
                txtCity.setStyle("-fx-border-color: red");
            }
            if (!patternContact.matcher(contact).matches()){
                lblContactError.setText("Incorrect Contact Number Pattern");
                txtContact.setStyle("-fx-border-color: red");
            }
            if (!patternEmail.matcher(email).matches()){
                lblEmailError.setText("Incorrect Email Pattern");
                txtEmail.setStyle("-fx-border-color: red");
            }
            if (!patternUserName.matcher(userName).matches()){
                lblUserNameError.setText("Incorrect User Name Pattern");
                txtUserName.setStyle("-fx-border-color: red");
            }
            if (!patternPassword.matcher(password).matches()){
                lblPasswordError.setText("Incorrect Password Pattern");
                txtPassword.setStyle("-fx-border-color: red");
            }
            new Alert(Alert.AlertType.ERROR, "New User Added Failed!").show();

        }

    }

    public void initialize(){
        loadDate();
        loadTime();
    }

    public void loadDate(){
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    public void loadTime(){
        Thread thread = new Thread(()->{
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss");
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final String time=simpleDateFormat.format(new Date());
                Platform.runLater(()->{
                    lblTime.setText(time);
                });

            }

        });
        thread.start();
    }

    public void resetFieldsOnAction(ActionEvent actionEvent) {
        txtName.clear();
        txtSurname.clear();
        txtCity.clear();
        txtContact.clear();
        txtEmail.clear();
        txtUserName.clear();
        txtPassword.clear();
        lblNameError.setText("");
        lblSurNameError.setText("");
        lblCityError.setText("");
        lblContactError.setText("");
        lblEmailError.setText("");
        lblUserNameError.setText("");
        lblPasswordError.setText("");
        txtName.setStyle("-fx-border-color: grey");
        txtSurname.setStyle("-fx-border-color: grey");
        txtContact.setStyle("-fx-border-color: grey");
        txtCity.setStyle("-fx-border-color: grey");
        txtUserName.setStyle("-fx-border-color: grey");
        txtEmail.setStyle("-fx-border-color: grey");
        txtPassword.setStyle("-fx-border-color: grey");
        txtName.setStyle("-fx-background-radius: 30");
        txtSurname.setStyle("-fx-background-radius: 30");
        txtContact.setStyle("-fx-background-radius: 30");
        txtCity.setStyle("-fx-background-radius: 30");
        txtUserName.setStyle("-fx-background-radius: 30");
        txtEmail.setStyle("-fx-background-radius: 30");
        txtPassword.setStyle("-fx-background-radius: 30");
    }

    public void btnHidePasswordOnAction(ActionEvent actionEvent) {
        pwdPassword.setVisible(true);
        btnHidePassword.setVisible(false);
        btnShowPassword.setVisible(true);
        txtPassword.setVisible(false);
    }

    public void btnShowPasswordOnAction(ActionEvent actionEvent) {
        String pwd = pwdPassword.getText();
        pwdPassword.setVisible(false);
        txtPassword.setText(pwd);
        btnShowPassword.setVisible(false);
        btnHidePassword.setVisible(true);
        txtPassword.setVisible(true);
    }
}
