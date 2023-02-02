package lk.ijse.institute.view.tm;

import javafx.scene.control.Button;

public class CourseFormTM {
    private String sub_id;
    private String sub_name;
    private Double courseFee;
    private Button btnDelete;

    public CourseFormTM(String sub_id, String sub_name, Double courseFee, Button btnDelete) {
        this.setSub_id(sub_id);
        this.setSub_name(sub_name);
        this.setCourseFee(courseFee);
        this.setBtnDelete(btnDelete);
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

    public Double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(Double courseFee) {
        this.courseFee = courseFee;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "CourseFormTM{" +
                "sub_id='" + sub_id + '\'' +
                ", sub_name='" + sub_name + '\'' +
                ", courseFee=" + courseFee +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
