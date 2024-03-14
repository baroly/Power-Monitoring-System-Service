package com.leo.pd.comment;

public class ReqResult {
    private Integer code;
    private String msg;
    private Long total;
    private Object data;

    public static ReqResult success(){
        return result(200, "Success", 0L, null);
    }

    public static ReqResult success(Object data){
        return result(200, "Success", 0L, data);
    }

    public static ReqResult success(Object data, Long total){
        return result(200, "Success", total, data);
    }

    public static ReqResult fail(){
        return result(400, "Fail", 0L, null);
    }

    public static ReqResult fail(String msg){
        return result(400, msg, 0L, null);
    }

    private static ReqResult result(Integer code, String msg, Long total, Object data){
        ReqResult res = new ReqResult();
        res.setCode(code);
        res.setMsg(msg);
        res.setTotal(total);
        res.setData(data);
        return res;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ReqResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", total=" + total +
                ", data=" + data +
                '}';
    }
}
