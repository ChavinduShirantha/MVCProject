package lk.ijse.institute.model;

import lk.ijse.institute.to.Fund;
import lk.ijse.institute.util.CrudUtil;
import java.sql.SQLException;


public class FundModel {
    public static boolean addFund(Fund fund) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO Fund VALUES(?)";
        return CrudUtil.execute(sql,fund.getMoney());
    }

    public static boolean updatePayment(Fund fund) throws SQLException, ClassNotFoundException {
        String sql="UPDATE Fund SET money=money+?";
        return CrudUtil.execute(sql,fund.getMoney());
    }

    public static boolean updateSalary(Fund fund) throws SQLException, ClassNotFoundException {
        String sql="UPDATE Fund SET money=money-?";
        return CrudUtil.execute(sql,fund.getMoney());
    }
}
