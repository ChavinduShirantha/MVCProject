package lk.ijse.institute.to;

public class Subject {
    private String subId;
    private String  subName;
    private int subHours;
    private String t_id;
    private String  t_name;

    public Subject(String subId, String subName, int subHours, String t_id, String t_name) {
        this.setSubId(subId);
        this.setSubName(subName);
        this.setSubHours(subHours);
        this.setT_id(t_id);
        this.setT_name(t_name);
    }

    public Subject(String sub_id) {
        this.subId=sub_id;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public int getSubHours() {
        return subHours;
    }

    public void setSubHours(int subHours) {
        this.subHours = subHours;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subId='" + subId + '\'' +
                ", subName='" + subName + '\'' +
                ", subHours=" + subHours +
                ", t_id='" + t_id + '\'' +
                ", t_name='" + t_name + '\'' +
                '}';
    }
}
