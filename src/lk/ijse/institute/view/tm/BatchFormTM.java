package lk.ijse.institute.view.tm;

import javafx.scene.control.Button;

public class BatchFormTM {
    String std_id;
    String std_name;
    String c_id;
    String course_name;
    String batch_id;
    String batch_name;
    private Button btnDelete;

    public BatchFormTM(String b_id, String c_id, String c_name, String std_id, String stdName, Button btnDelete) {
        this.batch_id = b_id;
        this.std_id = std_id;
        this.std_name = stdName;
        this.c_id = c_id;
        this.course_name = c_name;
        this.batch_id = b_id;
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "BatchFormTM{" +
                "std_id='" + std_id + '\'' +
                ", std_name='" + std_name + '\'' +
                ", c_id='" + c_id + '\'' +
                ", course_name='" + course_name + '\'' +
                ", batch_id='" + batch_id + '\'' +
                ", batch_name='" + batch_name + '\'' +
                ", btnDelete=" + btnDelete +
                '}';
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
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
