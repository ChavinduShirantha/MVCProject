package lk.ijse.institute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.institute.model.BatchModel;
import lk.ijse.institute.model.CourseModel;
import lk.ijse.institute.to.Batch;
import lk.ijse.institute.view.tm.BatchFormTM;
import lk.ijse.institute.view.tm.TableBatch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class BatchFormController {

    @FXML
    private TableColumn colCourseId;
    @FXML
    private TableColumn colCourseName;
    @FXML
    private TableColumn colBatchId;
    @FXML
    private JFXComboBox cmbStudentId;
    @FXML
    private Label lblStudentName;
    @FXML
    private JFXComboBox cmbCourseId;
    @FXML
    private TableColumn colstd_id;
    @FXML
    private TableColumn colStudentName;
    @FXML
    private TableColumn colAction;
    @FXML
    private JFXButton btnAddTable;
    @FXML
    private JFXTextField txtBatchid;
    @FXML
    private JFXTextField txtbatchname;
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
    private JFXTextField txtCourseId;
    @FXML
    private JFXTextField txtCourseName;
    @FXML
    private TableView<BatchFormTM> tblStudentdetails;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;

    private ObservableList<BatchFormTM> obList = FXCollections.observableArrayList();

    public void initialize(){
        loadDate();
        loadTime();
        loadStudentIDs();
        loadCourseIDs();
        setCellValueFactory();

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
        /*String batch_id=txtBatchid.getText();
        String batch_name=txtbatchname.getText();
        String course_id = String.valueOf(cmbCourseId.getValue());
        String course_name = txtCourseName.getText();
        String std_id= String.valueOf(cmbStudentId.getValue());
        String std_name=lblStudentName.getText();*/

       /* Batch batch = new Batch(batch_id, batch_name, course_id,course_name,std_id,std_name);
        try {
            boolean isPlaced = BatchModel.addBatch(batch);
            if (isPlaced) {*/
        obList.clear();
        new Alert(Alert.AlertType.CONFIRMATION, "Batch Added Successfully!").show();
        /* } else {
                new Alert(Alert.AlertType.ERROR, "Batch Added Failed!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/

    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String b_id=txtBatchid.getText();

        Batch batch=new Batch(b_id);

        try {
            boolean isDeleted=BatchModel.delete(batch);

            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Batch Deleted Successfully!").show();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Batch Deleted Failed!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void updateOnAction(ActionEvent actionEvent) {
        String batch_id=txtBatchid.getText();
        String batch_name=txtbatchname.getText();
        String course_id = String.valueOf(cmbCourseId.getValue());
        String course_name = txtCourseName.getText();
        String std_id= String.valueOf(cmbStudentId.getValue());
        String std_name=lblStudentName.getText();

        Batch batch=new Batch(batch_id,batch_name,course_id,course_name,std_id,std_name);

        try {
            boolean update = BatchModel.update(batch);

            if (update){
                new Alert(Alert.AlertType.CONFIRMATION, "Batch Updated Successfully!").show();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Batch Updated Failed!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void searchOnAction(ActionEvent actionEvent) {
        String b_id=txtBatchid.getText();
        try {
            Batch batch= BatchModel.search(b_id);
            if (batch != null){
                fillData(batch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void fillData(Batch batch){
        txtBatchid.setText(batch.getBatchId());
        txtbatchname.setText(batch.getBatchName());
        cmbCourseId.setValue(batch.getCourseId());
        txtCourseName.setText(batch.getCourseName());
        cmbStudentId.setValue(batch.getStd_id());
        lblStudentName.setText(batch.getStd_name());
    }

    public void resetFieldsOnAction(ActionEvent actionEvent) {
        txtBatchid.clear();
        txtbatchname.clear();
        cmbCourseId.setValue("");
        txtCourseName.clear();
        cmbStudentId.setValue("");
        lblStudentName.setText("");
    }

    public void addTableOnAction(ActionEvent actionEvent) {
        String b_id = txtBatchid.getText();
        String b_name = txtbatchname.getText();
        String c_id= String.valueOf(cmbCourseId.getValue());
        String c_name=txtCourseName.getText();
        String std_id= String.valueOf(cmbStudentId.getValue());
        String stdName=lblStudentName.getText();
        Button btnDelete = new Button("Delete");

        if (!obList.isEmpty()) {

            for (int i = 0; i < tblStudentdetails.getItems().size(); i++) {
                if (colstd_id.getCellData(i).equals(std_id)) {
                    obList.get(i).setStd_id(stdName);
                    tblStudentdetails.refresh();
                    return;
                }
            }
        }

        btnDelete.setOnAction((e) -> {
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(no) == ok) {
                BatchFormTM tm = tblStudentdetails.getSelectionModel().getSelectedItem();

                tblStudentdetails.getItems().removeAll(tblStudentdetails.getSelectionModel().getSelectedItem());
            }
        });
        obList.add(new BatchFormTM(b_id,c_id,c_name,std_id,stdName,btnDelete));
        tblStudentdetails.setItems(obList);

        Batch batch = new Batch(b_id, b_name, c_id,c_name,std_id,stdName);
        try {
            BatchModel.addBatch(batch);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colBatchId.setCellValueFactory(new PropertyValueFactory("batch_id"));
        colCourseId.setCellValueFactory(new PropertyValueFactory("c_id"));
        colCourseName.setCellValueFactory(new PropertyValueFactory("course_name"));
        colstd_id.setCellValueFactory(new PropertyValueFactory("std_id"));
        colStudentName.setCellValueFactory(new PropertyValueFactory("std_name"));
        colAction.setCellValueFactory(new PropertyValueFactory("btnDelete"));
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

    public void cmbCourseIdOnAction(ActionEvent actionEvent) {
        try {
            ResultSet set = BatchModel.getCourseNames(String.valueOf(cmbCourseId.getValue()));
            if (set.next()) {
                txtCourseName.setText(set.getString(2));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
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

    private void loadCourseIDs() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> itemList = BatchModel.loadCourseIDs();

            for (String code : itemList) {
                observableList.add(code);
            }
            cmbCourseId.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
