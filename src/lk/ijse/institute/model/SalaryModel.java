package lk.ijse.institute.model;

import lk.ijse.institute.to.Exam;
import lk.ijse.institute.to.Salary;
import lk.ijse.institute.to.Subject;
import lk.ijse.institute.util.CrudUtil;
import rex.utils.S;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryModel {
    public static boolean save(Salary salary) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO Salary VALUES(?,?,?,?,?,?)";
        return CrudUtil.execute(sql,salary.getSal_id(),salary.getT_id(),salary.getT_name(),salary.getAmount(),salary.getTime(),salary.getDate());
    }

    public static boolean delete(Salary salary) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM Salary WHERE sal_id=?";
        return CrudUtil.execute(sql,salary.getSal_id());
    }

    public static Salary search(String sal_id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Salary WHERE sal_id = ?";
        ResultSet result = CrudUtil.execute(sql, sal_id);

        if (result.next()) {
            return new Salary(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getDouble(4),
                    result.getString(5),
                    result.getString(6)
            );
        }
        return null;
    }

    public static boolean update(Salary salary) throws SQLException, ClassNotFoundException {
        String sql="UPDATE Salary SET t_id=?,t_name=?,amount=?,time=?,date=? WHERE sal_id=?";
        return CrudUtil.execute(sql,salary.getT_id(),salary.getT_name(),salary.getAmount(),salary.getTime(),salary.getDate(),salary.getSal_id());
    }
}
