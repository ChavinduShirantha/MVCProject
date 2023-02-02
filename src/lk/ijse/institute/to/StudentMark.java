package lk.ijse.institute.to;

public class StudentMark {
    private String std_id;
    private String std_name;
    private String sub_id;
    private String sub_name;
    private double marks;

    public StudentMark(String std_id, String std_name, String sub_id, String sub_name, double marks) {
        this.setStd_id(std_id);
        this.setStd_name(std_name);
        this.setSub_id(sub_id);
        this.setSub_name(sub_name);
        this.setMarks(marks);
    }

    public StudentMark(String std_id) {
        this.std_id=std_id;
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

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "StudentMark{" +
                "std_id='" + std_id + '\'' +
                ", std_name='" + std_name + '\'' +
                ", sub_id='" + sub_id + '\'' +
                ", sub_name='" + sub_name + '\'' +
                ", marks=" + marks +
                '}';
    }
}
