package org.nbu.data;

public class Cashier {
    public int cashier_id;
    public String cashier_name;
    public double monthlySalary;

    public Cashier(int cashier_id, String cashier_name, double monthlySalary) {
        this.cashier_id = cashier_id;
        this.cashier_name = cashier_name;
        this.monthlySalary = monthlySalary;
    }

    public int getCashier_id() {
        return cashier_id;
    }

    public void setCashier_id(int cashier_id) {
        this.cashier_id = cashier_id;
    }

    public String getCashier_name() {
        return cashier_name;
    }

    public void setCashier_name(String cashier_name) {
        this.cashier_name = cashier_name;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }
    @Override
    public String toString() {
        return "Cashier{" +
                "cashier_id=" + cashier_id +
                ", cashier_name='" + cashier_name + '\'' +
                ", monthlySalary=" + monthlySalary +
                '}';
    }
}
