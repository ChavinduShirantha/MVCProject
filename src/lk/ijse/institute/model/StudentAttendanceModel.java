package lk.ijse.institute.model;

import lk.ijse.institute.to.StudentAttendance;
import lk.ijse.institute.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentAttendanceModel {
    public static boolean save(StudentAttendance studentAttendance) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO Student_Attendence VALUES(?,?,?,?)";
        return CrudUtil.execute(sql,studentAttendance.getStd_id(),studentAttendance.getStd_name(),studentAttendance.getDate(),studentAttendance.getAttendance());
    }

    public static boolean delete(StudentAttendance studentAttendance) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM Student_Attendence WHERE std_id=? AND dateAttend=?";
        return CrudUtil.execute(sql,studentAttendance.getStd_id(),studentAttendance.getDate());
    }

    public static StudentAttendance search(String std_id,String date) throws SQLException, ClassNotFoundException {
        /*PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Student_Attendence WHERE std_id=? AND dateAttend =?");
        pstm.setString(1,studentAttendance.getStd_id());
        pstm.setString(2,studentAttendance.getDate());

        return pstm.executeQuery();*/
        String sql = "SELECT  * FROM Student_Attendence WHERE std_id = ? AND dateAttend=?";
        ResultSet result = CrudUtil.execute(sql, std_id,date);

        if (result.next()) {
            return new StudentAttendance(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
        }
        return null;
    }

    public static boolean update(StudentAttendance studentAttendance) throws SQLException, ClassNotFoundException {
        String sql="UPDATE Student_Attendence SET std_name=?,dateAttend=?,attendance=? WHERE std_id=?";
        return CrudUtil.execute(sql,studentAttendance.getStd_name(),studentAttendance.getDate(),studentAttendance.getAttendance(),studentAttendance.getStd_id());
    }
}
