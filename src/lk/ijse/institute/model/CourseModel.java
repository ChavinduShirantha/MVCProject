package lk.ijse.institute.model;

import lk.ijse.institute.db.DBConnection;
import lk.ijse.institute.to.Course;
import lk.ijse.institute.to.Subject;
import lk.ijse.institute.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseModel {
    public static ArrayList<String> loadSubjectIDs() throws SQLException, ClassNotFoundException {
        String sql = "SELECT sub_id FROM Subject";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }

    public static ArrayList<String> loadStudentIDs() throws SQLException, ClassNotFoundException {
        String sql = "SELECT std_id FROM Student";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }

    public static ResultSet getSubNames(String sub_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM Subject WHERE sub_id=?",sub_id);
    }

    public static ResultSet getStudentNames(String std_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM Student WHERE std_id=?",std_id);
    }

    public static boolean addcourse(Course course) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO Course VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql,course.getCourse_id(),course.getCourse_name(),course.getCourse_fee(),course.getStd_id(),course.getStd_name());
    }

    public static boolean delete(Course course) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM Course WHERE c_id=?";
        return CrudUtil.execute(sql,course.getCourse_id());
    }
    public static boolean update(Course course) throws SQLException, ClassNotFoundException {
        String sql="UPDATE Course SET name=?,coursefee=?,sub_id=?,sub_name=? WHERE c_id=?";
        return CrudUtil.execute(sql,course.getCourse_name(),course.getCourse_fee(),course.getStd_id(),course.getStd_name(),course.getCourse_id());
    }

    public static Course search(String c_id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM Course WHERE c_id = ?";
        ResultSet result = CrudUtil.execute(sql, c_id);

        if (result.next()) {
            return new Course(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3),
                    result.getString(4),
                    result.getString(5)

            );
        }
        return null;
    }

    public static ResultSet searchAll(Course course) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Course WHERE c_id=?");
        pstm.setString(1,course.getCourse_id());

        return pstm.executeQuery();
    }

}
