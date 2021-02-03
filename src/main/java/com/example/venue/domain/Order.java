package com.example.venue.domain;

import java.util.Date;

public class Order {
    private Integer id;
    private String uName;
    private String uPhone;
    private String uIdNumber;
    private String uDate;
    private Integer uPeriod;
    private Date uCreateTime;
    private Date uUpdateTime;
    private Integer uChildNum;

    private String uuid;
    private Integer checked;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuIdNumber() {
        return uIdNumber;
    }

    public void setuIdNumber(String uIdNumber) {
        this.uIdNumber = uIdNumber;
    }

    public String getuDate() {
        return uDate;
    }

    public void setuDate(String uDate) {
        this.uDate = uDate;
    }

    public Integer getuPeriod() {
        return uPeriod;
    }

    public void setuPeriod(Integer uPeriod) {
        this.uPeriod = uPeriod;
    }

    public Date getuCreateTime() {
        return uCreateTime;
    }

    public void setuCreateTime(Date uCreateTime) {
        this.uCreateTime = uCreateTime;
    }

    public Date getuUpdateTime() {
        return uUpdateTime;
    }

    public void setuUpdateTime(Date uUpdateTime) {
        this.uUpdateTime = uUpdateTime;
    }

    public Integer getuChildNum() {
        return uChildNum;
    }

    public void setuChildNum(Integer uChildNum) {
        this.uChildNum = uChildNum;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
