package lk.ijse.institute.to;

public class TeacherDetail {
    String t_id;

    public TeacherDetail(String t_id) {
        this.t_id = t_id;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    @Override
    public String toString() {
        return "TeacherDetail{" +
                "t_id='" + t_id + '\'' +
                '}';
    }
}
