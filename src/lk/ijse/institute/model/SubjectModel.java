package lk.ijse.institute.model;

import lk.ijse.institute.db.DBConnection;
import lk.ijse.institute.to.Student;
import lk.ijse.institute.to.StudentAttendance;
import lk.ijse.institute.to.Subject;
import lk.ijse.institute.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectModel {
    public static boolean save(Subject subject) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO Subject VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql,subject.getSubId(),subject.getSubName(),subject.getSubHours(),subject.getT_id(),subject.getT_name());
    }

    public static boolean delete(Subject subject) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM Subject WHERE sub_id=?";
        return CrudUtil.execute(sql,subject.getSubId());
    }

    public static Subject search(String sub_id) throws SQLException, ClassNotFoundException {
        /*PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Subject WHERE sub_id=?");
        pstm.setString(1,subject.getSubId());

        return pstm.executeQuery();*/
        String sql = "SELECT  * FROM Subject WHERE sub_id = ?";
        ResultSet result = CrudUtil.execute(sql, sub_id);

        if (result.next()) {
            return new Subject(
                    result.getString(1),
                    result.getString(2),
                    result.getInt(3),
                    result.getString(4),
                    result.getString(5)

            );
        }
        return null;
    }

    public static boolean update(Subject subject) throws SQLException, ClassNotFoundException {
        String sql="UPDATE Subject SET sub_name=?,Sub_hours=?,t_id=?,t_name=? WHERE sub_id=?";
        return CrudUtil.execute(sql,subject.getSubName(),subject.getSubHours(),subject.getT_id(),subject.getT_name(),subject.getSubId());
    }

}
