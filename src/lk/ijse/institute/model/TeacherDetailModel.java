package lk.ijse.institute.model;

import lk.ijse.institute.db.DBConnection;
import lk.ijse.institute.to.*;

import java.sql.SQLException;

public class TeacherDetailModel {
    public static boolean deleteTeacher(TeacherDetail teacherDetail) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isTeacherDelete = TeacherModel.delete(new Teacher(teacherDetail.getT_id()));
            if (isTeacherDelete) {
                /*boolean isSalaryDelete=SalaryModel.delete(new Salary(teacherDetail.getT_id()));
                if (isSalaryDelete) {*/
                    DBConnection.getInstance().getConnection().commit();
                    return true;
//                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
}
