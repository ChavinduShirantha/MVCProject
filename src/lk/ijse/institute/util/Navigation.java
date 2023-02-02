package lk.ijse.institute.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;
    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route) {
            case Login:
                window.setTitle("Login Form");
                initUI("LoginForm.fxml");
                break;
            case DashBoard:
                window.setTitle("DashBoard Form");
                initUI("DashBoardForm.fxml");
                break;
            case CreateNewAccount:
                window.setTitle("Create New Account Form");
                initUI("CreateNewAccount.fxml");
                break;
            case Student:
                window.setTitle("Student Form");
                initUI("StudentForm.fxml");
                break;
            case DashBoardDetail:
                window.setTitle("DashBoard Detail");
                initUI("DashBoardDetail.fxml");
                break;
            case Teacher:
                window.setTitle("Teacher Detail");
                initUI("TeacherForm.fxml");
                break;
            case Marks:
                window.setTitle("Student Marks Form");
                initUI("StudentMarksForm.fxml");
                break;
            case Subject:
                window.setTitle("Subject Form");
                initUI("SubjectForm.fxml");
                break;
            case Attendance:
                window.setTitle("Attendance details Form");
                initUI("StudentAttendanceForm.fxml");
                break;
            case Exam:
                window.setTitle("Exam Schedule Form");
                initUI("ExamForm.fxml");
                break;
            case Payment:
                window.setTitle("Payment Form");
                initUI("PaymentForm.fxml");
                break;
            case Course:
                window.setTitle("Course Details Form");
                initUI("CourseForm.fxml");
                break;
            case Batch:
                window.setTitle("Batch Details Form");
                initUI("BatchForm.fxml");
                break;
            case Salary:
                window.setTitle("Salary Details Form");
                initUI("SalaryForm.fxml");
                break;
            case ManagerDashBoard:
                window.setTitle("Manager DashBoard");
                initUI("ManagerDashBoardForm.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();
        }
    }

    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class
                .getResource("/lk/ijse/institute/view/" + location)));
    }
}

