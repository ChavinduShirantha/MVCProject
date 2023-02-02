package lk.ijse.institute.model;

import lk.ijse.institute.db.DBConnection;
import lk.ijse.institute.to.Student;
import lk.ijse.institute.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentModel {
    public static boolean save(Student student) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO Student VALUES(?,?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,student.getStd_id(),student.getFirstName(),student.getLastName(),student.getAddress(),student.getGender(),student.getContact(),student.getEmail(),student.getDate_of_birth());
    }

    public static boolean delete(Student student) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM Student WHERE std_id=?";
        return CrudUtil.execute(sql,student.getStd_id());
    }

    public static Student search(String std_id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM Student WHERE std_id = ?";
        ResultSet result = CrudUtil.execute(sql, std_id);

        if (result.next()) {
            return new Student(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getString(7),
                    result.getString(8)
            );
        }
        return null;
    }

    public static boolean update(Student student) throws SQLException, ClassNotFoundException {
        String sql="UPDATE Student SET first_name=?,last_name=?,address=?,gender=?,contact=?,email=?,date_of_birth=? WHERE std_id=?";
        return CrudUtil.execute(sql,student.getFirstName(),student.getLastName(),student.getAddress(),student.getGender(),student.getContact(),student.getEmail(),student.getDate_of_birth(),student.getStd_id());
    }

    public static ResultSet loadAllStudentCount(Student student) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm=DBConnection.getInstance().getConnection().prepareStatement("SELECT COUNT(std_id) FROM Student");
        return pstm.executeQuery();
    }

    public static ResultSet loadAllMaleStudentCount(Student student) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm=DBConnection.getInstance().getConnection().prepareStatement("SELECT COUNT(std_id) FROM Student WHERE gender='Male'");
        return pstm.executeQuery();
    }

    public static ResultSet loadAllFemaleStudentCount(Student student) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm=DBConnection.getInstance().getConnection().prepareStatement("SELECT COUNT(std_id) FROM Student WHERE gender='Female'");
        return pstm.executeQuery();
    }


}
