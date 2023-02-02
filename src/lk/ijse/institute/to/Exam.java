package lk.ijse.institute.to;

public class Exam {
    private String exam_id;
    private String sub_id;
    private String sub_name;
    private String date;
    private String time;

    public Exam(String exam_id, String sub_id, String sub_name, String date, String time) {
        this.setExam_id(exam_id);
        this.setSub_id(sub_id);
        this.setSub_name(sub_name);
        this.setDate(date);
        this.setTime(time);
    }

    public Exam(String exam_id) {
        this.exam_id=exam_id;
    }


    public String getExam_id() {
        return exam_id;
    }

    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "exam_id='" + exam_id + '\'' +
                ", sub_id='" + sub_id + '\'' +
                ", sub_name='" + sub_name + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
