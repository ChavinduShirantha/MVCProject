package lk.ijse.institute.view.tm;


import javafx.scene.control.Button;

public class StudentMarkFormTM {
    private String std_id;
    private String std_name;
    private String sub_id;
    private String sub_name;
    private Double mark;
    private Button btnDelete;

    public StudentMarkFormTM(String std_id, String std_name, String sub_id, String sub_name, Double mark, Button btnDelete) {
        this.setStd_id(std_id);
        this.setStd_name(std_name);
        this.setSub_id(sub_id);
        this.setSub_name(sub_name);
        this.setMark(mark);
        this.setBtnDelete(btnDelete);
    }

    public StudentMarkFormTM(String string, String string1, String string2, String string3, double anInt) {
        this.setStd_id(string);
        this.setStd_name(string1);
        this.setSub_id(string2);
        this.setSub_name(string3);
        this.setMark(anInt);
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

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "StudentMarkFormTM{" +
                "std_id='" + std_id + '\'' +
                ", std_name='" + std_name + '\'' +
                ", sub_id='" + sub_id + '\'' +
                ", sub_name='" + sub_name + '\'' +
                ", mark=" + mark +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
