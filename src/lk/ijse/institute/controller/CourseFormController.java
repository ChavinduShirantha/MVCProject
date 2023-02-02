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
import lk.ijse.institute.model.CourseModel;
import lk.ijse.institute.model.StudentMarkModel;
import lk.ijse.institute.to.Course;
import lk.ijse.institute.to.CourseDetail;
import lk.ijse.institute.to.StudentMark;
import lk.ijse.institute.view.tm.CourseFormTM;
import org.apache.poi.hssf.record.formula.functions.T;
import rex.utils.S;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

public class CourseFormController {
    @FXML
    private TableColumn colsubId;
    @FXML
    private TableColumn colsubName;
    @FXML
    private TableColumn colcoursefee;
    @FXML
    private JFXTextField txtCourseId;
    @FXML
    private JFXTextField txtCourseName;
    @FXML
    private JFXTextField txtCourseFee;
    @FXML
    private JFXComboBox cmbSubId;
    @FXML
    private Label lblSubName;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnResetFields;
    @FXML
    private TableView<CourseFormTM> tblCourse;
    @FXML
    private TableColumn colAction;
    @FXML
    private JFXButton btnAddSubjectCourse;
    @FXML
    private JFXButton btnAddCourse;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;

    private ObservableList<CourseFormTM> obList = FXCollections.observableArrayList();

    public void initialize(){
        loadDate();
        loadTime();
        loadSubjectIDs();
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

    public void resetFieldsOnAction(ActionEvent actionEvent) {
        txtCourseId.clear();
        txtCourseName.clear();
        txtCourseFee.clear();
        cmbSubId.setValue("");
        lblSubName.setText("");
    }

    public void searchOnAction(ActionEvent actionEvent) {
        String c_id=txtCourseId.getText();

        try {
            Course course= CourseModel.search(c_id);
            if (course != null){
                fillData(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void fillData(Course course){
        txtCourseId.setText(course.getCourse_id());
        txtCourseName.setText(course.getCourse_name());
        txtCourseFee.setText(String.valueOf(course.getCourse_fee()));
        cmbSubId.setValue(course.getStd_id());
        lblSubName.setText(course.getStd_name());
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String c_id=txtCourseId.getText();
        String c_name=txtCourseName.getText();
        double coursefee= Double.parseDouble((txtCourseFee.getText()));
        String sub_id= String.valueOf(cmbSubId.getValue());
        String sub_name=lblSubName.getText();

        Course course=new Course(c_id,c_name,coursefee,sub_id,sub_name);

        try {
            boolean update = CourseModel.update(course);

            if (update){
                new Alert(Alert.AlertType.CONFIRMATION, "Course Updated Successfully!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Course Updated Failed!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String c_id=txtCourseId.getText();
        Course course=new Course(c_id);

        try {
            boolean isDeleted=CourseModel.delete(course);

            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Course Deleted Successfully!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Course Deleted Failed!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void cmbSubIdOnAction(ActionEvent actionEvent) {
        try {
            ResultSet set = CourseModel.getSubNames(String.valueOf(cmbSubId.getValue()));
            if (set.next()) {
                lblSubName.setText(set.getString(2));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addSubjectToCourseOnAction(ActionEvent actionEvent) {
        String subId = String.valueOf(cmbSubId.getValue());
        String subName = lblSubName.getText();
        double coursefee = Double.parseDouble(txtCourseFee.getText());
        Button btnDelete = new Button("Delete");


        if (!obList.isEmpty()) {

            for (int i = 0; i < tblCourse.getItems().size(); i++) {
                if (colsubId.getCellData(i).equals(subId)) {
                    obList.get(i).setSub_name(subName);
                    obList.get(i).setCourseFee(coursefee);
                    tblCourse.refresh();
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
                CourseFormTM tm = tblCourse.getSelectionModel().getSelectedItem();

                tblCourse.getItems().removeAll(tblCourse.getSelectionModel().getSelectedItem());
            }
        });
        obList.add(new CourseFormTM(subId, subName, coursefee,btnDelete));
        tblCourse.setItems(obList);
        String course_id = txtCourseId.getText();
        String course_name = txtCourseName.getText();
        Double course_fee= Double.valueOf(txtCourseFee.getText());
        String sub_id= String.valueOf(cmbSubId.getValue());
        String sub_name=lblSubName.getText();
        Course course = new Course(course_id, course_name, course_fee,sub_id,sub_name);
        try {
            CourseModel.addcourse(course);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addCourseOnAction(ActionEvent actionEvent) {
        /*String course_id = txtCourseId.getText();
        String course_name = txtCourseName.getText();
        Double course_fee= Double.valueOf(txtCourseFee.getText());
        String sub_id= String.valueOf(cmbSubId.getValue());
        String sub_name=lblSubName.getText();
*/
        ArrayList<CourseDetail> courseDetails = new ArrayList<>();


        for (int i = 0; i < tblCourse.getItems().size(); i++) {

            CourseFormTM tm = obList.get(i);
            courseDetails.add(new CourseDetail(tm.getSub_id(), tm.getSub_name(), tm.getCourseFee()));
        }
        obList.clear();
        new Alert(Alert.AlertType.CONFIRMATION, "Course Added Successfully!").show();
    }

    private void loadSubjectIDs() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> itemList = CourseModel.loadSubjectIDs();

            for (String code : itemList) {
                observableList.add(code);
            }
            cmbSubId.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colsubId.setCellValueFactory(new PropertyValueFactory("sub_id"));
        colsubName.setCellValueFactory(new PropertyValueFactory("sub_name"));
        colcoursefee.setCellValueFactory(new PropertyValueFactory("courseFee"));
        colAction.setCellValueFactory(new PropertyValueFactory("btnDelete"));
    }


}
