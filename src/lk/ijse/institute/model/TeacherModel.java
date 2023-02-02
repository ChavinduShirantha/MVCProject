package lk.ijse.institute.model;

import lk.ijse.institute.db.DBConnection;
import lk.ijse.institute.to.Student;
import lk.ijse.institute.to.Teacher;
import lk.ijse.institute.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherModel {
    public static boolean save(Teacher teacher) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO Teacher VALUES(?,?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,teacher.getT_id(),teacher.getFirstName(),teacher.getLastName(),teacher.getAddress(),teacher.getGender(),teacher.getContact(),teacher.getEmail(),teacher.getDate_of_birth());
    }

    public static boolean delete(Teacher teacher) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM Teacher WHERE t_id=?";
        return CrudUtil.execute(sql,teacher.getT_id());
    }

    public static Teacher search(String t_id) throws SQLException, ClassNotFoundException {
        /*PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Teacher WHERE t_id=?");
        pstm.setString(1,teacher.getT_id());

        return pstm.executeQuery();*/

        String sql = "SELECT  * FROM Teacher WHERE t_id = ?";
        ResultSet result = CrudUtil.execute(sql, t_id);

        if (result.next()) {
            return new Teacher(
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

    public static boolean update(Teacher teacher) throws SQLException, ClassNotFoundException {
        String sql="UPDATE Teacher SET first_name=?,last_name=?,address=?,gender=?,contact=?,email=?,date_of_birth=? WHERE t_id=?";
        return CrudUtil.execute(sql,teacher.getFirstName(),teacher.getLastName(),teacher.getAddress(),teacher.getGender(),teacher.getContact(),teacher.getEmail(),teacher.getDate_of_birth(),teacher.getT_id());
    }

    public static ResultSet loadAllTeacherCount(Teacher teacher) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm=DBConnection.getInstance().getConnection().prepareStatement("SELECT COUNT(t_id) FROM Teacher");
        return pstm.executeQuery();
    }

    public static ResultSet loadAllMaleTeacherCount(Teacher teacher) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm=DBConnection.getInstance().getConnection().prepareStatement("SELECT COUNT(t_id) FROM Teacher WHERE gender='Male'");
        return pstm.executeQuery();
    }

    public static ResultSet loadAllFemaleTeacherCount(Teacher teacher) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm=DBConnection.getInstance().getConnection().prepareStatement("SELECT COUNT(t_id) FROM Teacher WHERE gender='Female'");
        return pstm.executeQuery();
    }

    public static ArrayList<String> loadTeacherIDs() throws SQLException, ClassNotFoundException {
        String sql = "SELECT t_id FROM Teacher";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }

    public static ResultSet getTeacherNames(String t_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM Teacher WHERE t_id=?",t_id);
    }
}
