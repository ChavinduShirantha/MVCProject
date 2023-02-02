package lk.ijse.institute.model;

import lk.ijse.institute.db.DBConnection;
import lk.ijse.institute.to.Student;
import lk.ijse.institute.to.StudentMark;
import lk.ijse.institute.to.Subject;
import lk.ijse.institute.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMarkModel {
    public static boolean save(StudentMark studentMark) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO Student_Marks VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql,studentMark.getStd_id(),studentMark.getStd_name(),studentMark.getSub_id(),studentMark.getSub_name(),studentMark.getMarks());
    }

    public static boolean delete(StudentMark studentMark) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM Student_Marks WHERE std_id=?";
        return CrudUtil.execute(sql,studentMark.getStd_id());
    }

    public static ResultSet search(StudentMark studentMark) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM student_marks WHERE std_id=?");
        pstm.setString(1,studentMark.getStd_id());

        return pstm.executeQuery();
    }

    public static boolean update(StudentMark studentMark) throws SQLException, ClassNotFoundException {
        String sql="UPDATE Student_Marks SET std_name=?,sub_id=?,sub_name=?,marks=? WHERE std_id=?";
        return CrudUtil.execute(sql,studentMark.getStd_name(),studentMark.getSub_id(),studentMark.getSub_id(),studentMark.getStd_name(),studentMark.getStd_id());
    }

}
