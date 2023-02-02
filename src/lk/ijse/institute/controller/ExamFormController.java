package lk.ijse.institute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import lk.ijse.institute.model.CourseModel;
import lk.ijse.institute.model.ExamModel;
import lk.ijse.institute.model.StudentModel;
import lk.ijse.institute.model.SubjectModel;
import lk.ijse.institute.to.Exam;
import lk.ijse.institute.to.Student;
import lk.ijse.institute.to.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ExamFormController {
    @FXML
    private DatePicker date1;
    @FXML
    private JFXComboBox cmbSubjectId;
    @FXML
    private Label lblSubjectName;
    @FXML
    private JFXTextField txtExamId;
    @FXML
    private JFXTextField txtTime;
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

    public void cmbSubjectIdOnAction(ActionEvent actionEvent) {
        try {
            ResultSet set = CourseModel.getSubNames(String.valueOf(cmbSubjectId.getValue()));
            if (set.next()) {
                lblSubjectName.setText(set.getString(2));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void resetOnAction(ActionEvent actionEvent) {
        txtExamId.clear();
        cmbSubjectId.setValue("");
        lblSubjectName.setText("");
        txtTime.clear();
    }

    public void addOnAction(ActionEvent actionEvent) {
        String exam_id=txtExamId.getText();
        String sub_id= String.valueOf(cmbSubjectId.getValue());
        String subName= lblSubjectName.getText();
        String date= String.valueOf(date1.getValue());
        String time=txtTime.getText();

        Exam exam=new Exam(exam_id,sub_id,subName,date,time);

        try {
            boolean isAdded = ExamModel.save(exam);

            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Exam Schedule Added Successfully!").show();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Exam Schedule Added Failed!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        String exam_id=txtExamId.getText();

        try {
            Exam exam= ExamModel.search(exam_id);
            if (exam != null){
                fillData(exam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void fillData(Exam exam){
        txtExamId.setText(exam.getExam_id());
        cmbSubjectId.setValue(exam.getSub_id());
        lblSubjectName.setText(exam.getSub_name());
        date1.setValue(LocalDate.parse(exam.getDate()));
        txtTime.setText(exam.getTime());
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String exam_id=txtExamId.getText();
        String subId= String.valueOf(cmbSubjectId.getValue());
        String subName=lblSubjectName.getText();
        String date= String.valueOf(date1.getValue());
        String time=txtTime.getText();

        Exam exam=new Exam(exam_id,subId,subName,date,time);

        try {
            boolean update = ExamModel.update(exam);

            if (update){
                new Alert(Alert.AlertType.CONFIRMATION, "Exam Schedule Updated Successfully!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Exam Schedule Updated Failed!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String exam_id=txtExamId.getText();

        Exam exam=new Exam(exam_id);

        try {
            boolean isDeleted=ExamModel.delete(exam);

            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Exam Schedule Deleted Successfully!").show();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Exam Schedule Deleted Failed!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
        loadDate();
        loadTime();
        loadSubjectIDs();
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

    private void loadSubjectIDs() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> itemList = CourseModel.loadSubjectIDs();

            for (String code : itemList) {
                observableList.add(code);
            }
            cmbSubjectId.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
