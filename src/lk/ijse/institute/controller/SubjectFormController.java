package lk.ijse.institute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import lk.ijse.institute.model.StudentModel;
import lk.ijse.institute.model.SubjectModel;
import lk.ijse.institute.to.Student;
import lk.ijse.institute.to.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Pattern;

public class SubjectFormController {
    @FXML
    private JFXTextField txtSubId;
    @FXML
    private JFXTextField txtSubName;
    @FXML
    private JFXTextField txtSubHours;
    @FXML
    private JFXTextField txtTeacherId;
    @FXML
    private JFXTextField txtTeacherName;
    @FXML
    private JFXButton btnReset;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;

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

    public void addOnAction(ActionEvent actionEvent) {
        String subId=txtSubId.getText();
        String subName=txtSubName.getText();
        int subHours= Integer.parseInt(txtSubHours.getText());
        String tId=txtTeacherId.getText();
        String tName=txtTeacherName.getText();

//        Pattern patternID = Pattern.compile("^[sub][0-9]{3}+$");
        Pattern patternName = Pattern.compile("^[A-Z][a-zA-Z]+$");
        Pattern patternTid = Pattern.compile("^[T][0-9]{3}+$");

        if (patternTid.matcher(tId).matches() /*& patternID.matcher(subId).matches()*/ & patternName.matcher(subName).matches()) {
            Subject subject = new Subject(subId, subName, subHours, tId, tName);

            try {
                boolean isAdded = SubjectModel.save(subject);

                if (isAdded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Subject Added Successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Subject Added Failed!").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
           /* if (!patternID.matcher(subId).matches()){
                txtSubId.setFocusColor(Paint.valueOf("Red"));
            }*/
            if (!patternTid.matcher(tId).matches()){
                txtTeacherId.setFocusColor(Paint.valueOf("res"));
            }
            if (!patternName.matcher(tName).matches()){
                txtTeacherName.setFocusColor(Paint.valueOf("red"));
            }
            new Alert(Alert.AlertType.CONFIRMATION, "Subject Added Failed!").show();
        }

    }

    public void searchOnAction(ActionEvent actionEvent) {
        String sub_id=txtSubId.getText();

        try {
            Subject subject= SubjectModel.search(sub_id);
            if (subject != null){
                fillData(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void fillData(Subject subject){
        txtSubId.setText(subject.getSubId());
        txtSubName.setText(subject.getSubName());
        txtSubHours.setText(String.valueOf(subject.getSubHours()));
        txtTeacherId.setText(subject.getT_id());
        txtTeacherName.setText(subject.getT_name());
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String subId=txtSubId.getText();
        String subName=txtSubName.getText();
        int subHours= Integer.parseInt(txtSubHours.getText());
        String tId=txtTeacherId.getText();
        String tName=txtTeacherName.getText();

        Subject subject=new Subject(subId,subName,subHours,tId,tName);

        try {
            boolean update = SubjectModel.update(subject);

            if (update){
                new Alert(Alert.AlertType.CONFIRMATION, "Subject Updated Successfully!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Subject Updated Failed!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String sub_id=txtSubId.getText();

        Subject subject=new Subject(sub_id);

        try {
            boolean isDeleted=SubjectModel.delete(subject);

            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Subject Deleted Successfully!").show();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Subject Deleted Failed!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void resetOnAction(ActionEvent actionEvent) {
        txtSubId.clear();
        txtSubName.clear();
        txtTeacherId.clear();
        txtSubHours.clear();
        txtTeacherName.clear();
    }

    public void subIdOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode()== KeyCode.ENTER){
            txtSubName.requestFocus();
        }
    }

    public void subNameOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode()== KeyCode.ENTER){
            txtSubHours.requestFocus();
        }
    }

    public void subHoursOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode()== KeyCode.ENTER){
            txtTeacherId.requestFocus();
        }
    }

    public void TeacherIDOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode()== KeyCode.ENTER){
            txtTeacherName.requestFocus();
        }
    }
}
