package lk.ijse.institute.to;

public class CourseDetail {
    String sub_id;
    String sub_name;
    double course_fee;

    public CourseDetail(String sub_id, String sub_name, double course_fee) {
        this.sub_id = sub_id;
        this.sub_name = sub_name;
        this.course_fee = course_fee;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public double getCourse_fee() {
        return course_fee;
    }

    public void setCourse_fee(double course_fee) {
        this.course_fee = course_fee;
    }

    @Override
    public String toString() {
        return "CourseDetail{" +
                "sub_id='" + sub_id + '\'' +
                ", sub_name='" + sub_name + '\'' +
                ", course_fee=" + course_fee +
                '}';
    }
}
