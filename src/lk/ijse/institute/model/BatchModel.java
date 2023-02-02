package lk.ijse.institute.model;

import lk.ijse.institute.db.DBConnection;
import lk.ijse.institute.to.Batch;
import lk.ijse.institute.to.Course;
import lk.ijse.institute.util.CrudUtil;
import lk.ijse.institute.view.tm.BatchFormTM;
import lk.ijse.institute.view.tm.TableBatch;

import java.sql.*;
import java.util.ArrayList;

public class BatchModel {
    public static ArrayList<String> loadStudentIDs() throws SQLException, ClassNotFoundException {
        String sql = "SELECT std_id FROM Student";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }

    public static ResultSet getStudentNames(String std_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM Student WHERE std_id=?",std_id);
    }

    public static ArrayList<String> loadCourseIDs() throws SQLException, ClassNotFoundException {
        String sql = "SELECT c_id FROM Course";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }

    public static ResultSet getCourseNames(String c_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM Course WHERE c_id=?",c_id);
    }

    public static boolean addBatch(Batch batch) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO Batch VALUES(?,?,?,?,?,?)";
        return CrudUtil.execute(sql,batch.getBatchId(),batch.getBatchName(),batch.getCourseId(),batch.getCourseName(),batch.getStd_id(),batch.getStd_name());
    }

    public static boolean delete(Batch batch) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM Batch WHERE b_id=?";
        return CrudUtil.execute(sql,batch.getBatchId());
    }
    public static boolean update(Batch batch) throws SQLException, ClassNotFoundException {
        String sql="UPDATE Batch SET batch_name=?,c_id=?,course_name=?,std_id=?,std_name=? WHERE b_id=?";
        return CrudUtil.execute(sql,batch.getBatchName(),batch.getCourseId(),batch.getCourseName(),batch.getStd_id(),batch.getStd_name(),batch.getBatchId());
    }

    public static Batch search(String b_id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Batch WHERE b_id = ?";
        ResultSet result = CrudUtil.execute(sql, b_id);

        if (result.next()) {
            return new Batch(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6)

            );
        }
        return null;
    }

    public static ArrayList<TableBatch> loadTable() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Batch");

        ArrayList<TableBatch> tableData = new ArrayList<>();

        while (resultSet.next()) {
            tableData.add(
                    new TableBatch(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }

        return tableData;
    }
}
