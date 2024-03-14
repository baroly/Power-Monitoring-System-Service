package com.leo.pd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 感測器基本資訊
 * </p>
 *
 * @author leo
 * @since 2023-12-02
 */
@TableName("user_sensor")
public class UserSensor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 感測器id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * iot裝置id
     */
    private Integer iotDevId;

    /**
     * iot輸入埠
     */
    private Integer portNum;

    /**
     * 感測器別名
     */
    private String alias;

    @TableField(exist = false)
    private Double senseDate = 0.0;

    private Integer volt;

    @TableField(exist = false)
    private Boolean voltSelect;

    @TableField(exist = false)
    private String year;

    @TableField(exist = false)
    private String month;

    @TableField(exist = false)
    private Double minCount;

    /**
     * 創建時間
     */
    private LocalDateTime createTime;

    /**
     * 修改時間
     */
    private LocalDateTime editTime;

    /**
     * 已刪除 0:否, 1:是
     */
    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getIotDevId() {
        return iotDevId;
    }

    public void setIotDevId(Integer iotDevId) {
        this.iotDevId = iotDevId;
    }
    public Integer getPortNum() {
        return portNum;
    }

    public void setPortNum(Integer portNum) {
        this.portNum = portNum;
    }
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    public Double getSenseDate() {
        return senseDate;
    }

    public void setSenseDate(Double senseDate) {
        this.senseDate = senseDate;
    }

    public void formatSenseData(){
        String formatData = String.format("%.3f", senseDate);
        senseDate = Double.valueOf(formatData);
    }



    public Integer getVolt() {
        return volt;
    }

    public void setVolt(Integer volt) {
        this.volt = volt;
    }

    public Boolean getVoltSelect() {
        return voltSelect;
    }

    public void setVoltSelect(Boolean voltSelect) {
        this.voltSelect = voltSelect;
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

    public Double getMinCount() {
        return minCount;
    }

    public void setMinCount(Double minCount) {
        this.minCount = minCount;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getEditTime() {
        return editTime;
    }

    public void setEditTime(LocalDateTime editTime) {
        this.editTime = editTime;
    }
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "UserSensor{" +
                "id=" + id +
                ", iotDevId=" + iotDevId +
                ", portNum=" + portNum +
                ", alias='" + alias + '\'' +
                ", senseDate=" + senseDate +
                ", volt=" + volt +
                ", voltSelect=" + voltSelect +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", minCount=" + minCount +
                ", createTime=" + createTime +
                ", editTime=" + editTime +
                ", deleted=" + deleted +
                '}';
    }
}
