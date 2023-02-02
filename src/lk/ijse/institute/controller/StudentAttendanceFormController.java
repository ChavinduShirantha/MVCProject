package lk.ijse.institute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import lk.ijse.institute.db.DBConnection;
import lk.ijse.institute.model.CourseModel;
import lk.ijse.institute.model.StudentAttendanceModel;
import lk.ijse.institute.model.StudentModel;
import lk.ijse.institute.to.Student;
import lk.ijse.institute.to.StudentAttendance;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class StudentAttendanceFormController {
    @FXML
    private Label lblDateAttend;
    @FXML
    private JFXComboBox cmbStudentId;
    @FXML
    private Label lblStudentName;
    @FXML
    private JFXButton btngetReport;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnReset;
    @FXML
    private DatePicker adddate;
    @FXML
    private JFXRadioButton rbtnAbsent;
    @FXML
    private ToggleGroup Attendance;
    @FXML
    private JFXRadioButton rbtnPresent;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;

    public void initialize(){
        loadDate();
        loadTime();
        loadStudentIDs();
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

    public void cmbStudentIdOnAction(ActionEvent actionEvent) {
        try {
            ResultSet set = CourseModel.getStudentNames(String.valueOf(cmbStudentId.getValue()));
            if (set.next()) {
                lblStudentName.setText(set.getString(2)+" "+set.getString(3));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void resetOnAction(ActionEvent actionEvent) {
        lblStudentName.setText("");
        rbtnAbsent.setSelected(false);
        rbtnPresent.setSelected(false);
        cmbStudentId.setValue("");
    }

    public void addOnAction(ActionEvent actionEvent) {
        String std_id= String.valueOf(cmbStudentId.getValue());
        String std_name=lblStudentName.getText();
        String date= String.valueOf(adddate.getValue());

        if (rbtnAbsent.isSelected()){
            String attendance=rbtnAbsent.getText();
            StudentAttendance studentAttendance=new StudentAttendance(std_id,std_name,date,attendance);

            try {
                boolean isAdded= StudentAttendanceModel.save(studentAttendance);

                if (isAdded){
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Attendance Added Successfully!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Student Attendance Added Failed!").show();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (rbtnPresent.isSelected()){
            String attendance=rbtnPresent.getText();
            StudentAttendance studentAttendance=new StudentAttendance(std_id,std_name,date,attendance);

            try {
                boolean isAdded= StudentAttendanceModel.save(studentAttendance);

                if (isAdded){
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Attendance Added Successfully!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Student Attendance Added Failed!").show();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void searchOnAction(ActionEvent actionEvent) {
        String std_id= String.valueOf(cmbStudentId.getValue());
        String date= String.valueOf(adddate.getValue());

        try {
            StudentAttendance studentAttendance= StudentAttendanceModel.search(std_id,date);
            if (studentAttendance != null){
                fillData(studentAttendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void fillData(StudentAttendance studentAttendance){
        if (studentAttendance.getAttendance().equals("Absent")){
            rbtnAbsent.setSelected(true);
        }else {
            rbtnPresent.setSelected(true);
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String std_id= String.valueOf(cmbStudentId.getValue());
        String std_name=lblStudentName.getText();
        String date= String.valueOf(adddate.getValue());

        if (rbtnAbsent.isSelected()){
            String attendance=rbtnAbsent.getText();
            StudentAttendance studentAttendance=new StudentAttendance(std_id,std_name,date,attendance);

            try {
                boolean update= StudentAttendanceModel.update(studentAttendance);

                if (update){
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Attendance Updated Successfully!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Student Attendance Updated Failed!").show();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (rbtnPresent.isSelected()){
            String attendance=rbtnPresent.getText();
            StudentAttendance studentAttendance=new StudentAttendance(std_id,std_name,date,attendance);

            try {
                boolean update= StudentAttendanceModel.update(studentAttendance);

                if (update){
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Attendance Updated Successfully!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Student Attendance Updated Failed!").show();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String std_id= String.valueOf(cmbStudentId.getValue());
        String date= String.valueOf(adddate.getValue());

        StudentAttendance studentAttendance=new StudentAttendance(std_id,date);

        try {
            boolean isDeleted=StudentAttendanceModel.delete(studentAttendance);

            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Student Attendance Deleted Successfully!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Student Attendance Deleted Failed!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btngetReportOnAction(ActionEvent actionEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/institute/report/StudentAttendance.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
//            JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint);
        } catch (ClassNotFoundException | SQLException | JRException e) {
            e.printStackTrace();
        }
    }

    private void loadStudentIDs() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> itemList = CourseModel.loadStudentIDs();

            for (String code : itemList) {
                observableList.add(code);
            }
            cmbStudentId.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
