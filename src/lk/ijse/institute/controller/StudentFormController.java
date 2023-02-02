package lk.ijse.institute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.institute.db.DBConnection;
import lk.ijse.institute.model.StudentModel;
import lk.ijse.institute.to.Student;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Pattern;

public class StudentFormController {
    @FXML
    private JFXButton btngetReport;
    @FXML
    private DatePicker DateofBirth;
    @FXML
    private ToggleGroup gender;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private JFXButton btnAddStudent;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnResetFields;
    @FXML
    private JFXRadioButton rbtnMale;
    @FXML
    private JFXRadioButton rbtnFemale;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXTextField txtFirstName;
    @FXML
    private JFXTextField txtLastName;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private AnchorPane pane;


    public void addStudentOnAction(ActionEvent actionEvent) {
        String std_id=txtId.getText();
        String firstName=txtFirstName.getText();
        String lastName=txtLastName.getText();
        String address=txtAddress.getText();
        String contact=txtContact.getText();
        String email=txtEmail.getText();
        String dob= String.valueOf(DateofBirth.getValue());

        Pattern patternID = Pattern.compile("^[S][0-9]{3}+$");
        Pattern patternName = Pattern.compile("^[A-Z][a-zA-Z]+$");
        Pattern patternAddress = Pattern.compile("^[A-Z][a-zA-Z]+$");
        Pattern patternContact = Pattern.compile("^(?:\\+94|070|071|072|074|075|076|077|078)[0-9]{7,9}$");
        Pattern patternEmail = Pattern.compile("([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})");

        if (patternID.matcher(std_id).matches() & patternName.matcher(firstName).matches() & patternName.matcher(lastName).matches() &
            patternAddress.matcher(address).matches() & patternContact.matcher(contact).matches() & patternEmail.matcher(email).matches()){

            if (rbtnMale.isSelected()){
                String gender=rbtnMale.getText();
                Student student=new Student(std_id,firstName,lastName,address,gender,contact,email,dob);

                try {
                    boolean isAdded=StudentModel.save(student);

                    if (isAdded){
                        new Alert(Alert.AlertType.CONFIRMATION, "Student Added Successfully!").show();
                    }else {
                        new Alert(Alert.AlertType.ERROR, "Student Added Failed!").show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }else if(rbtnFemale.isSelected()){
                String gender=rbtnFemale.getText();
                Student student=new Student(std_id,firstName,lastName,address,gender,contact,email,dob);

                try {
                    boolean isAdded=StudentModel.save(student);

                    if (isAdded){
                        new Alert(Alert.AlertType.CONFIRMATION, "Student Added Successfully!").show();
                    }else {
                        new Alert(Alert.AlertType.ERROR, "Student Added Failed!").show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }else {
            if (!patternName.matcher(firstName).matches()){
                txtFirstName.setFocusColor(Paint.valueOf("Red"));
            }
            if (!patternID.matcher(std_id).matches()){
                txtId.setFocusColor(Paint.valueOf("Red"));
            }
            if (!patternName.matcher(lastName).matches()){
                txtLastName.setFocusColor(Paint.valueOf("Red"));
            }
            if (!patternAddress.matcher(address).matches()){
                txtAddress.setFocusColor(Paint.valueOf("Red"));
            }
            if (!patternEmail.matcher(email).matches()){
                txtEmail.setFocusColor(Paint.valueOf("Red"));
            }
            if (!patternContact.matcher(lastName).matches()){
                txtContact.setFocusColor(Paint.valueOf("Red"));
            }

            new Alert(Alert.AlertType.ERROR, "New Student Added Failed!").show();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String std_id=txtId.getText();

        Student student=new Student(std_id);

        try {
            boolean isDeleted=StudentModel.delete(student);

            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Student Deleted Successfully!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Student Deleted Failed!").show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String std_id=txtId.getText();
        String firstName=txtFirstName.getText();
        String lastName=txtLastName.getText();
        String address=txtAddress.getText();
        String contact=txtContact.getText();
        String email=txtEmail.getText();
        String dob= String.valueOf(DateofBirth.getValue());

        if (rbtnMale.isSelected()){
            String gender=rbtnMale.getText();
            Student student=new Student(std_id,firstName,lastName,address,gender,contact,email,dob);

            try {
                boolean update= StudentModel.update(student);

                if (update){
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Updated Successfully!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Student Updated Failed!").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else if(rbtnFemale.isSelected()){
            String gender=rbtnFemale.getText();
            Student student=new Student(std_id,firstName,lastName,address,gender,contact,email,dob);

            try {
                boolean update= StudentModel.update(student);

                if (update){
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Updated Successfully!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Student Updated Failed!").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void searchOnAction(ActionEvent actionEvent) {
        String std_id=txtId.getText();

        try {
            Student student=StudentModel.search(std_id);
            if (student != null){
                fillData(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void fillData(Student student){
        txtId.setText(student.getStd_id());
        txtFirstName.setText(student.getFirstName());
        txtLastName.setText(student.getLastName());
        txtAddress.setText(student.getAddress());
        if (student.getGender().equals("Male")){
            rbtnMale.setSelected(true);
        }else {
            rbtnFemale.setSelected(true);
        }
        txtContact.setText(student.getContact());
        txtEmail.setText(student.getEmail());
        DateofBirth.setValue(LocalDate.parse(student.getDate_of_birth()));
    }

    public void resetFieldsOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtAddress.clear();
        rbtnMale.setSelected(false);
        rbtnFemale.setSelected(false);
        txtContact.clear();
        txtEmail.clear();
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

    public void txtStudentIdOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            txtFirstName.requestFocus();
        }
    }

    public void txtFirstNameOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode()== KeyCode.ENTER){
                txtLastName.requestFocus();
        }
    }

    public void txtLastNameOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode()== KeyCode.ENTER){
            txtAddress.requestFocus();
        }
    }

    public void txtContactOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode()== KeyCode.ENTER){
            txtEmail.requestFocus();
        }
    }

    public void txtEmailOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode()== KeyCode.ENTER){
            DateofBirth.requestFocus();
        }
    }

    public void btngetReportOnAction(ActionEvent actionEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/institute/report/StudentReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
//            JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint);
        } catch (ClassNotFoundException | SQLException | JRException e) {
            e.printStackTrace();
        }
    }
}
