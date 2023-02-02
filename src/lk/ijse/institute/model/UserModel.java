package lk.ijse.institute.model;

import lk.ijse.institute.db.DBConnection;
import lk.ijse.institute.to.Student;
import lk.ijse.institute.to.User;
import lk.ijse.institute.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public static boolean save(User user) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO User VALUES(?,?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,user.getName(),user.getSurName(),user.getCity(),user.getContact(),user.getEmail(),user.getUserName(),user.getPassword(),user.getRole());
    }

    public static ResultSet login(User user) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM User WHERE user_name=? AND password=? AND role='Admin'");
        pstm.setString(1,user.getUserName());
        pstm.setString(2, user.getPassword());

        return pstm.executeQuery();
    }

    public static ResultSet loginManager(User user) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM User WHERE user_name=? AND password=? AND role='Manager'");
        pstm.setString(1,user.getUserName());
        pstm.setString(2, user.getPassword());

        return pstm.executeQuery();
    }

}
