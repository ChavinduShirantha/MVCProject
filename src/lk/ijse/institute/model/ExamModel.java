package lk.ijse.institute.model;

import lk.ijse.institute.db.DBConnection;
import lk.ijse.institute.to.Exam;
import lk.ijse.institute.to.Student;
import lk.ijse.institute.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamModel {
    public static boolean save(Exam exam) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO Exam_Shedule VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql,exam.getExam_id(),exam.getSub_id(),exam.getSub_name(),exam.getDate(),exam.getTime());
    }

    public static boolean delete(Exam exam) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM Exam_Shedule WHERE exam_id=?";
        return CrudUtil.execute(sql,exam.getExam_id());
    }

    public static Exam search(String exam_id) throws SQLException, ClassNotFoundException {
        /*PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Exam_Shedule WHERE exam_id=?");
        pstm.setString(1,exam.getExam_id());

        return pstm.executeQuery();*/

        String sql = "SELECT  * FROM Exam_Shedule WHERE exam_id = ?";
        ResultSet result = CrudUtil.execute(sql, exam_id);

        if (result.next()) {
            return new Exam(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            );
        }
        return null;
    }

    public static boolean update(Exam exam) throws SQLException, ClassNotFoundException {
        String sql="UPDATE Exam_Shedule SET sub_id=?,date=?,time=? WHERE exam_id=?";
        return CrudUtil.execute(sql,exam.getSub_id(),exam.getDate(),exam.getTime(),exam.getExam_id());
    }
}
