package lk.ijse.institute.to;

public class StudentAttendance {
    private String std_id;
    private String std_name;
    private String date;
    private String attendance;

    public StudentAttendance(String std_id, String std_name, String date, String attendance) {
        this.setStd_id(std_id);
        this.setStd_name(std_name);
        this.setDate(date);
        this.setAttendance(attendance);
    }

    public StudentAttendance(String std_id) {
        this.std_id=std_id;
    }

    public StudentAttendance(String std_id, String date) {
        this.std_id=std_id;
        this.date=date;
    }

    public String getStd_id() {
        return std_id;
    }

    public void setStd_id(String std_id) {
        this.std_id = std_id;
    }

    public String getStd_name() {
        return std_name;
    }

    public void setStd_name(String std_name) {
        this.std_name = std_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return "StudentAttendance{" +
                "std_id='" + std_id + '\'' +
                ", std_name='" + std_name + '\'' +
                ", date='" + date + '\'' +
                ", attendance='" + attendance + '\'' +
                '}';
    }
}
