package com.leo.pd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author leo
 * @since 2023-11-30
 */
@TableName("ampere_his_data")
public class AmpereHisData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer year;

    private Integer userIotDevId;

    private Integer devPort;

    private Double m1;

    private Double m2;

    private Double m3;

    private Double m4;

    private Double m5;

    private Double m6;

    private Double m7;

    private Double m8;

    private Double m9;

    private Double m10;

    private Double m11;

    private Double m12;

    @TableField(exist = false)
    private Double[] dataList;

    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
    public Integer getUserIotDevId() {
        return userIotDevId;
    }

    public void setUserIotDevId(Integer userIotDevId) {
        this.userIotDevId = userIotDevId;
    }
    public Integer getDevPort() {
        return devPort;
    }

    public void setDevPort(Integer devPort) {
        this.devPort = devPort;
    }
    public Double getm1() {
        return m1;
    }

    public void setm1(Double m1) {
        this.m1 = m1;
    }
    public Double getm2() {
        return m2;
    }

    public void setm2(Double m2) {
        this.m2 = m2;
    }
    public Double getm3() {
        return m3;
    }

    public void setm3(Double m3) {
        this.m3 = m3;
    }
    public Double getm4() {
        return m4;
    }

    public void setm4(Double m4) {
        this.m4 = m4;
    }
    public Double getm5() {
        return m5;
    }

    public void setm5(Double m5) {
        this.m5 = m5;
    }
    public Double getm6() {
        return m6;
    }

    public void setm6(Double m6) {
        this.m6 = m6;
    }
    public Double getm7() {
        return m7;
    }

    public void setm7(Double m7) {
        this.m7 = m7;
    }
    public Double getm8() {
        return m8;
    }

    public void setm8(Double m8) {
        this.m8 = m8;
    }
    public Double getm9() {
        return m9;
    }

    public void setm9(Double m9) {
        this.m9 = m9;
    }
    public Double getm10() {
        return m10;
    }

    public void setm10(Double m10) {
        this.m10 = m10;
    }
    public Double getm11() {
        return m11;
    }

    public void setm11(Double m11) {
        this.m11 = m11;
    }
    public Double getm12() {
        return m12;
    }

    public void setm12(Double m12) {
        this.m12 = m12;
    }

    public Double[] getDataList() {
        return new Double[]{m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12};
    }

    public void setDataList(int index, double value) {
        switch (index){
            case 1:
                m1 = value;
                break;
            case 2:
                m2 = value;
                break;
            case 3:
                m3 = value;
                break;
            case 4:
                m4 = value;
                break;
            case 5:
                m5 = value;
                break;
            case 6:
                m6 = value;
                break;
            case 7:
                m7 = value;
                break;
            case 8:
                m8 = value;
                break;
            case 9:
                m9 = value;
                break;
            case 10:
                m10 = value;
                break;
            case 11:
                m11 = value;
                break;
            case 12:
                m12 = value;
                break;
        }
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "AmpereHisData{" +
                "id=" + id +
                ", year=" + year +
                ", userIotDevId=" + userIotDevId +
                ", devPort=" + devPort +
                ", m1=" + m1 +
                ", m2=" + m2 +
                ", m3=" + m3 +
                ", m4=" + m4 +
                ", m5=" + m5 +
                ", m6=" + m6 +
                ", m7=" + m7 +
                ", m8=" + m8 +
                ", m9=" + m9 +
                ", m10=" + m10 +
                ", m11=" + m11 +
                ", m12=" + m12 +
                ", deleted=" + deleted +
                "}";
    }
}
