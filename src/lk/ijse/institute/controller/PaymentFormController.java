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
import lk.ijse.institute.model.CourseModel;
import lk.ijse.institute.model.FundModel;

import lk.ijse.institute.model.PaymentModel;
import lk.ijse.institute.to.Fund;
import lk.ijse.institute.to.PayPayment;
import lk.ijse.institute.to.Payment;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class PaymentFormController {
    @FXML
    private JFXTextField txtPayment;
    @FXML
    private JFXComboBox cmbStudentId;
    @FXML
    private Label lblStudentName;
    @FXML
    private Label lblBatch;
    @FXML
    private Label lblCourse;
    @FXML
    private JFXTextField txtAmount;
    @FXML
    private JFXTextField txtTime;
    @FXML
    private DatePicker adddate;
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
            ResultSet set = PaymentModel.getStudentNames(String.valueOf(cmbStudentId.getValue()));
            ResultSet set1=PaymentModel.getBatchId(String.valueOf(cmbStudentId.getValue()));
            ResultSet set2=PaymentModel.getCourseName(String.valueOf(cmbStudentId.getValue()));

            if (set.next()) {
                lblStudentName.setText(set.getString(2)+" "+set.getString(3));
            }
            if (set1.next()){
                lblBatch.setText(set1.getString(1));
            }
            if (set2.next()){
                lblCourse.setText(set2.getString(4));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addStudentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String pid=txtPayment.getText();
        String std_id= String.valueOf(cmbStudentId.getValue());
        String std_name=lblStudentName.getText();
        String batch_id=lblBatch.getText();
        String course=lblCourse.getText();
        double amount= Double.parseDouble(txtAmount.getText());
        String date= String.valueOf(adddate.getValue());
        String time=txtTime.getText();

        Payment payment=new Payment(pid,std_id,std_name,batch_id,course,amount,date,time);

        Fund fund=new Fund(amount);
        try {
            try{
                DBConnection.getInstance().getConnection().setAutoCommit(false);
                boolean IsAdd = PaymentModel.save(payment);
                if (IsAdd){
                    boolean IsAddFund = FundModel.updatePayment(fund);
                    if (IsAddFund){
                        DBConnection.getInstance().getConnection().commit();
                        new Alert(Alert.AlertType.CONFIRMATION,"Payment Added Successfully!").show();
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
        String pid=txtPayment.getText();

        Payment payment=new Payment(pid);

        try {
            boolean isDeleted=PaymentModel.delete(payment);

            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Deleted Successfully!").show();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Deleted Failed!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void updateOnAction(ActionEvent actionEvent) {
        String pid=txtPayment.getText();
        String std_id= String.valueOf(cmbStudentId.getValue());
        String std_name=lblStudentName.getText();
        String batch_id=lblBatch.getText();
        String course=lblCourse.getText();
        double amount= Double.parseDouble(txtAmount.getText());
        String date= String.valueOf(adddate.getValue());
        String time=txtTime.getText();

        Payment payment=new Payment(pid,std_id,std_name,batch_id,course,amount,date,time);

        try {
            boolean isUpdated=PaymentModel.update(payment);

            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Updated Successfully!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Payment Updated Failed!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void searchOnAction(ActionEvent actionEvent) {
        String pid=txtPayment.getText();

        try {
            Payment payment=PaymentModel.search(pid);
            if (payment !=null){
                fillData(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void fillData(Payment payment){
        txtPayment.setText(payment.getPayment_id());
        cmbStudentId.setValue(payment.getStd_id());
        lblStudentName.setText(payment.getStd_name());
        lblBatch.setText(payment.getBatch_id());
        lblCourse.setText(payment.getCourse_name());
        txtAmount.setText(String.valueOf(payment.getAmount()));
        adddate.setValue(LocalDate.parse(payment.getDate()));
        txtTime.setText(payment.getTime());
    }

    public void resetFieldsOnAction(ActionEvent actionEvent) {
        txtPayment.clear();
        cmbStudentId.setValue("");
        lblStudentName.setText("");
        lblBatch.setText("");
        lblCourse.setText("");
        txtAmount.clear();
        txtTime.clear();
    }

    private void loadStudentIDs() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> itemList = PaymentModel.loadStudentIDs();

            for (String code : itemList) {
                observableList.add(code);
            }
            cmbStudentId.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
