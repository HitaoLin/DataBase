package com.example.admin.database;

/**
 * Created by Admin on 2019/1/7.
 */

public class RechargeBean {
    private int id;
    private int carId;
    private int rechargeMoney;
    private String operator;
    private String time;

    public RechargeBean(){
        super();
    }

    public RechargeBean(int id, int carId, int rechargeMoney, String operator, String time) {
        this.id = id;
        this.carId = carId;
        this.rechargeMoney = rechargeMoney;
        this.operator = operator;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(int rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
