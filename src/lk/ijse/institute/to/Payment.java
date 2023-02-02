package lk.ijse.institute.to;

public class Payment {
    private String payment_id;
    private String std_id;
    private String std_name;
    private String batch_id;
    private String course_name;
    private double amount;
    private String date;
    private String time;

    public Payment(String payment_id, String std_id, String std_name, String batch_id, String course_name, double amount, String date, String time) {
        this.setPayment_id(payment_id);
        this.setStd_id(std_id);
        this.setStd_name(std_name);
        this.setBatch_id(batch_id);
        this.setCourse_name(course_name);
        this.setAmount(amount);
        this.setDate(date);
        this.setTime(time);
    }

    public Payment(String pid) {
        this.payment_id=pid;
    }


    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
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

    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
        return "Payment{" +
                "payment_id='" + payment_id + '\'' +
                ", std_id='" + std_id + '\'' +
                ", std_name='" + std_name + '\'' +
                ", batch_id='" + batch_id + '\'' +
                ", course_name='" + course_name + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
