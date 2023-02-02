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
import lk.ijse.institute.db.DBConnection;
import lk.ijse.institute.model.*;
import lk.ijse.institute.to.Exam;
import lk.ijse.institute.to.Fund;
import lk.ijse.institute.to.Salary;
import lk.ijse.institute.to.Subject;
import rex.utils.S;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class SalaryFormController {
    @FXML
    private DatePicker date1;
    @FXML
    private JFXTextField txtsalaryId;
    @FXML
    private JFXTextField txtAmount;
    @FXML
    private JFXTextField txtTime;
    @FXML
    private Label lblTeacherName;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnResetFields;
    @FXML
    private JFXComboBox cmbTeacherId;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;

    public void initialize(){
        loadDate();
        loadTime();
        loadTeacherIDs();
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
        String sal_id=txtsalaryId.getText();
        String t_id= String.valueOf(cmbTeacherId.getValue());
        String t_name=lblTeacherName.getText();
        double amount= Double.parseDouble(txtAmount.getText());
        String date= String.valueOf(date1.getValue());
        String time=txtTime.getText();

        Salary salary=new Salary(sal_id,t_id,t_name,amount,time,date);

        /*try {
            boolean isAdded = SalaryModel.save(salary);

            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Salary Detail Added Successfully!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Salary Detail Added Failed!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        Fund fund=new Fund(amount);
        try {
            try{
                DBConnection.getInstance().getConnection().setAutoCommit(false);
                boolean IsAdd = SalaryModel.save(salary);
                if (IsAdd){
                    boolean IsAddFund = FundModel.updateSalary(fund);
                    if (IsAddFund){
                        DBConnection.getInstance().getConnection().commit();
                        new Alert(Alert.AlertType.CONFIRMATION,"Salary Detail Added Successfully!").show();
                    }
                }
                DBConnection.getInstance().getConnection().rollback();
            }finally {
                DBConnection.getInstance().getConnection().setAutoCommit(true);
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String sal_id=txtsalaryId.getText();

        Salary salary=new Salary(sal_id);

        try {
            boolean isDeleted= SalaryModel.delete(salary);

            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Salary Detail Deleted Successfully!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Salary Detail Deleted Failed!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void updateOnAction(ActionEvent actionEvent) {
        String sal_id=txtsalaryId.getText();
        String t_id= String.valueOf(cmbTeacherId.getValue());
        String t_name=lblTeacherName.getText();
        double amount= Double.parseDouble(txtAmount.getText());
        String date= String.valueOf(date1.getValue());
        String time=txtTime.getText();

        Salary salary=new Salary(sal_id,t_id,t_name,amount,time,date);

        try {
            boolean update = SalaryModel.update(salary);

            if (update){
                new Alert(Alert.AlertType.CONFIRMATION, "Salary Detail Updated Successfully!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Salary Detail Updated Failed!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void searchOnAction(ActionEvent actionEvent) {
        String sal_id=txtsalaryId.getText();

        try {
            Salary salary= SalaryModel.search(sal_id);
            if (salary != null){
                fillData(salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void fillData(Salary salary){
        txtsalaryId.setText(salary.getSal_id());
        cmbTeacherId.setValue(salary.getT_id());
        lblTeacherName.setText(salary.getT_name());
        txtAmount.setText(String.valueOf(salary.getAmount()));
        txtTime.setText(salary.getTime());
        date1.setValue(LocalDate.parse(salary.getDate()));

    }

    public void resetFieldsOnAction(ActionEvent actionEvent) {
        txtsalaryId.clear();
        txtTime.clear();
        txtAmount.clear();
        lblTeacherName.setText("");
        cmbTeacherId.setValue("");

    }

    private void loadTeacherIDs() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> itemList = TeacherModel.loadTeacherIDs();

            for (String code : itemList) {
                observableList.add(code);
            }
            cmbTeacherId.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbTeacherIdOnAction(ActionEvent actionEvent) {
        try {
            ResultSet set = TeacherModel.getTeacherNames(String.valueOf(cmbTeacherId.getValue()));
            if (set.next()) {
                lblTeacherName.setText(set.getString(2)+" "+set.getString(3));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
