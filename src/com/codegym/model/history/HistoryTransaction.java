package com.codegym.model.history;


import java.sql.Date;

public class HistoryTransaction {
    private String id;
    private String objectId;

    private HistoryType historyType;

    private double totalMoney;

    private Date date;

    public HistoryTransaction(String id, String objectId, HistoryType historyType, double totalMoney, Date date) {
        this.id = id;
        this.objectId = objectId;
        this.historyType = historyType;
        this.totalMoney = totalMoney;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public HistoryType getHistoryType() {
        return historyType;
    }

    public void setHistoryType(HistoryType historyType) {
        this.historyType = historyType;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "HistoryTransaction{" +
                "id='" + id + '\'' +
                ", objectId='" + objectId + '\'' +
                ", historyType=" + historyType +
                ", totalMoney=" + totalMoney +
                ", date=" + date +
                '}';
    }
}
