package lk.ijse.institute.model;

import lk.ijse.institute.to.Payment;
import lk.ijse.institute.to.StudentAttendance;
import lk.ijse.institute.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentModel {
    public static ResultSet getStudentNames(String std_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM Student WHERE std_id=?",std_id);
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

    public static boolean save(Payment payment) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO Payment VALUES(?,?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,payment.getPayment_id(),payment.getStd_id(),payment.getStd_name(),payment.getBatch_id(),payment.getCourse_name(),payment.getAmount(),payment.getDate(),payment.getTime());
    }

    public static boolean delete(Payment payment) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM Payment WHERE pid=?";
        return CrudUtil.execute(sql,payment.getPayment_id());
    }

    public static boolean update(Payment payment) throws SQLException, ClassNotFoundException {
        String sql="UPDATE Payment SET std_id=?,std_name=?,batch_id=?,course_name=?,amount=?,date=?,time=? WHERE pid=?";
        return CrudUtil.execute(sql,payment.getStd_id(),payment.getStd_name(),payment.getBatch_id(),payment.getCourse_name(),payment.getAmount(),payment.getDate(),payment.getTime(),payment.getPayment_id());
    }

    public static Payment search(String pid) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM Payment WHERE pid =?";
        ResultSet result = CrudUtil.execute(sql, pid);

        if (result.next()) {
            return new Payment(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getDouble(6),
                    result.getString(7),
                    result.getString(8)
            );
        }
        return null;
    }

    public static ResultSet getBatchId(String std_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM Batch WHERE std_id=?",std_id);
    }

    public static ResultSet getCourseName(String std_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM Batch WHERE std_id=?",std_id);
    }

}
