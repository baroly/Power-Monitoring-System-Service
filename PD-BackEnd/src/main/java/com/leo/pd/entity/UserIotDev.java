package com.leo.pd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * iot裝置基本訊息
 * </p>
 *
 * @author leo
 * @since 2023-11-16
 */
@TableName("user_iot_dev")
public class UserIotDev implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * iot裝置id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 感測器所屬的使用者邊緣端伺服器id
     */
    private Integer userServerId;

    /**
     * iot裝置序號
     */
    private String uid;

    private String alias;

    @TableField(exist = false)
    private String year;

    @TableField(exist = false)
    private String month;

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
    public Integer getUserServerId() {
        return userServerId;
    }

    public void setUserServerId(Integer userServerId) {
        this.userServerId = userServerId;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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
        return "UserIotDev{" +
                "id=" + id +
                ", userServerId=" + userServerId +
                ", uid='" + uid + '\'' +
                ", alias='" + alias + '\'' +
                ", createTime=" + createTime +
                ", editTime=" + editTime +
                ", deleted=" + deleted +
                '}';
    }
}
