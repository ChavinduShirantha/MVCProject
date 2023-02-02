package lk.ijse.institute.to;

public class Salary {
    String sal_id;
    String t_id;
    String t_name;
    double amount;
    String date;
    String time;

    public Salary(String sal_id, String t_id, String t_name, double amount,String time,String date) {
        this.sal_id = sal_id;
        this.t_id = t_id;
        this.t_name = t_name;
        this.amount = amount;
        this.time = time;
        this.date = date;

    }

    public Salary(String sal_id) {
        this.sal_id = sal_id;
    }

    public String getSal_id() {
        return sal_id;
    }

    public void setSal_id(String sal_id) {
        this.sal_id = sal_id;
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
        return "Salary{" +
                "sal_id='" + sal_id + '\'' +
                ", t_id='" + t_id + '\'' +
                ", t_name='" + t_name + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
