package lk.ijse.institute.view.tm;

public class TableBatch {
    String std_id;
    String std_name;
    String c_id;
    String course_name;
    String batch_id;
    String batch_name;

    public TableBatch(String std_id, String std_name, String c_id, String course_name, String batch_id, String batch_name) {
        this.std_id = std_id;
        this.std_name = std_name;
        this.c_id = c_id;
        this.course_name = course_name;
        this.batch_id = batch_id;
        this.batch_name = batch_name;
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

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public String getBatch_name() {
        return batch_name;
    }

    public void setBatch_name(String batch_name) {
        this.batch_name = batch_name;
    }


}
