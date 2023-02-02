package lk.ijse.institute.to;

public class Fund {
    double money;

    public Fund(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Fund{" +
                "money=" + money +
                '}';
    }
}
