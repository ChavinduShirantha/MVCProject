package lk.ijse.institute.to;

public class PayPayment {
    String std_id;
    double money;

    public PayPayment(String std_id, double money) {
        this.std_id = std_id;
        this.money = money;
    }

    public String getStd_id() {
        return std_id;
    }

    public void setStd_id(String std_id) {
        this.std_id = std_id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "PayPayment{" +
                "std_id='" + std_id + '\'' +
                ", money=" + money +
                '}';
    }
}
