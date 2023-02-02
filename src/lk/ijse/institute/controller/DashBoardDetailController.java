package lk.ijse.institute.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import lk.ijse.institute.model.StudentModel;
import lk.ijse.institute.model.TeacherModel;
import lk.ijse.institute.to.Student;
import lk.ijse.institute.to.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DashBoardDetailController {
    @FXML
    private Label lblTotStudent;
    @FXML
    private Label lblTotMaleStudent;
    @FXML
    private Label lblTotFemaleStudent;
    @FXML
    private Label lblTotTeachers;
    @FXML
    private Label lblTotMaleTeachers;
    @FXML
    private Label lblFemaleTeachers;
    @FXML
    private BarChart barchart;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblStudentCount;

    public void initialize(){
        loadDate();
        loadTime();
        AllCountStudent();
        AllCountMaleStudent();
        AllCountFemaleStudent();
        AllCountTeacher();
        AllCountMaleTeacher();
        AllCountFemaleTeacher();
        barChart();
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

    public void AllCountStudent(){
        Student student=new Student();
        int count=0;
        try {
            ResultSet resultSet= StudentModel.loadAllStudentCount(student);

            if (resultSet.next()){
                count=resultSet.getInt("COUNT(std_id)");
            }
            lblTotStudent.setText(String.valueOf(count));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void AllCountMaleStudent(){
        Student student=new Student();
        int count=0;
        try {
            ResultSet resultSet= StudentModel.loadAllMaleStudentCount(student);

            if (resultSet.next()){
                count=resultSet.getInt("COUNT(std_id)");
            }
            lblTotMaleStudent.setText(String.valueOf(count));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void AllCountFemaleStudent(){
        Student student=new Student();
        int count=0;
        try {
            ResultSet resultSet= StudentModel.loadAllFemaleStudentCount(student);

            if (resultSet.next()){
                count=resultSet.getInt("COUNT(std_id)");
            }
            lblTotFemaleStudent.setText(String.valueOf(count));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void AllCountTeacher(){
        Teacher teacher=new Teacher();
        int count=0;
        try {
            ResultSet resultSet= TeacherModel.loadAllTeacherCount(teacher);

            if (resultSet.next()){
                count=resultSet.getInt("COUNT(t_id)");
            }
            lblTotTeachers.setText(String.valueOf(count));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void AllCountMaleTeacher(){
        Teacher teacher=new Teacher();
        int count=0;
        try {
            ResultSet resultSet= TeacherModel.loadAllMaleTeacherCount(teacher);

            if (resultSet.next()){
                count=resultSet.getInt("COUNT(t_id)");
            }
            lblTotMaleTeachers.setText(String.valueOf(count));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void AllCountFemaleTeacher(){
        Teacher teacher=new Teacher();
        int count=0;
        try {
            ResultSet resultSet= TeacherModel.loadAllFemaleTeacherCount(teacher);

            if (resultSet.next()){
                count=resultSet.getInt("COUNT(t_id)");
            }
            lblFemaleTeachers.setText(String.valueOf(count));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void barChart(){
        barchart.setTitle("Enrolled Participant Details");
        XYChart.Series series1=new XYChart.Series();
        series1.setName("Students");
        XYChart.Series series2=new XYChart.Series();
        series2.setName("Teachers");

        Student student=new Student();
        int count=0;
        try {
            ResultSet resultSet=StudentModel.loadAllStudentCount(student);

            if (resultSet.next()){
                count=resultSet.getInt("COUNT(std_id)");
            }
            series1.getData().add(new XYChart.Data("Total Student ",count ));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Teacher teacher=new Teacher();
        int count1=0;
        try {
            ResultSet resultSet= TeacherModel.loadAllTeacherCount(teacher);

            if (resultSet.next()){
                count1=resultSet.getInt("COUNT(t_id)");
            }
            series2.getData().add(new XYChart.Data("Total Teachers ",count1 ));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int count2=0;
        try {
            ResultSet resultSet= StudentModel.loadAllMaleStudentCount(student);

            if (resultSet.next()){
                count2=resultSet.getInt("COUNT(std_id)");
            }
            series1.getData().add(new XYChart.Data("Total Male Students ",count2 ));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int count3=0;
        try {
            ResultSet resultSet= StudentModel.loadAllFemaleStudentCount(student);

            if (resultSet.next()){
                count3=resultSet.getInt("COUNT(std_id)");
            }
            series1.getData().add(new XYChart.Data("Total Female Students ",count3 ));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int count4=0;
        try {
            ResultSet resultSet= TeacherModel.loadAllMaleTeacherCount(teacher);

            if (resultSet.next()){
                count4=resultSet.getInt("COUNT(t_id)");
            }
            series2.getData().add(new XYChart.Data("Total Male Teachers ",count4 ));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int count5=0;
        try {
            ResultSet resultSet= TeacherModel.loadAllFemaleTeacherCount(teacher);

            if (resultSet.next()){
                count5=resultSet.getInt("COUNT(t_id)");
            }
            series2.getData().add(new XYChart.Data("Total Female Teachers ",count5 ));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        barchart.getData().addAll(series1,series2);
    }

}
