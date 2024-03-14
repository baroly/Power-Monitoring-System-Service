package com.leo.pd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 使用者邊緣端伺服器訊息
 * </p>
 *
 * @author leo
 * @since 2023-11-16
 */
@TableName("user_server")
public class UserServer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 使用者邊緣端伺服器id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 使用者id
     */
    private Integer userId;

    /**
     * 使用者邊緣端伺服器序號
     */
    private String uid;

    /**
     * 使用者AP的ssid
     */
    private String ssid;

    /**
     * 使用者AP的password
     */
    private String password;

    /**
     * 使用者伺服器別名
     */
    private String alias;

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
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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
        return "UserServer{" +
                "id=" + id +
                ", userId=" + userId +
                ", uid=" + uid +
                ", ssid=" + ssid +
                ", password=" + password +
                ", alias=" + alias +
                ", createTime=" + createTime +
                ", editTime=" + editTime +
                ", deleted=" + deleted +
                "}";
    }
}
