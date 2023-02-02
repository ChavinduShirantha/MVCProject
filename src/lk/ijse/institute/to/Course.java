package lk.ijse.institute.to;

public class Course {
    String course_id;
    String course_name;
    double course_fee;
    String sub_id;
    String sub_name;

    public Course(String course_id, String course_name, double course_fee, String sub_id, String sub_name) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.course_fee = course_fee;
        this.sub_id = sub_id;
        this.sub_name = sub_name;
    }

    public Course(String c_id) {
        this.course_id=c_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public double getCourse_fee() {
        return course_fee;
    }

    public void setCourse_fee(double course_fee) {
        this.course_fee = course_fee;
    }

    public String getStd_id() {
        return sub_id;
    }

    public void setStd_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getStd_name() {
        return sub_name;
    }

    public void setStd_name(String sub_name) {
        this.sub_name = sub_name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "course_id='" + course_id + '\'' +
                ", course_name='" + course_name + '\'' +
                ", course_fee=" + course_fee +
                ", std_id='" + sub_id + '\'' +
                ", std_name='" + sub_name + '\'' +
                '}';
    }
}
