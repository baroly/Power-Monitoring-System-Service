package com.leo.pd.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 霍爾感測器感測數據
 * </p>
 *
 * @author leo
 * @since 2023-11-30
 */
@TableName("ampere_data")
public class AmpereData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 綁定感測器id
     */
    private Integer userIotDevId;

    private Integer port;

    /**
     * 感測資料
     */
    private Double senseData;

    private String year;

    private String month;

    /**
     * 創建時間
     */
    private LocalDateTime createTime;

    @TableField(exist = false)
    private LocalDateTime endTime;

    /**
     * 已刪除 0:否, 1:是
     */
    private Integer deleted;

    public AmpereData(){

    }

    public AmpereData(Integer userIotDevId, Integer port, Double senseData, LocalDateTime createTime) {
        this.userIotDevId = userIotDevId;
        this.port = port;
        this.senseData = senseData;
        this.createTime = createTime;
    }

    public Integer getUserIotDevId() {
        return userIotDevId;
    }

    public void setUserIotDevId(Integer userIotDevId) {
        this.userIotDevId = userIotDevId;
    }
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
    public Double getSenseData() {
        return this.senseData;
    }

    public void setSenseData(Double senseData) {
        this.senseData = senseData;
    }

    public void formatSenseData(){
        String x = String.format("%.3f", this.senseData.toString());
        this.senseData = Double.valueOf(x);
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public Integer getDeleted() {
        return deleted;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "AmpereData{" +
                "userIotDevId=" + userIotDevId +
                ", port=" + port +
                ", senseData=" + senseData +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", createTime=" + createTime +
                ", endTime=" + endTime +
                ", deleted=" + deleted +
                '}';
    }
}
