package com.immimg.immimg.model.vo;

import java.util.List;

/**
 * @version v1.0
 * @ProjectName: immimg
 * @ClassName: Message
 * @Description: Controller 层响应 前端的实体类
 * @Author: Nlqiong
 * @Date: 2020/1/15 11:34
 */
public class Message {
    private Integer code;
    private String msg;
    private Integer count;
    private List<Object> data;

    public Message(Integer code, String msg, Integer count, List<Object> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public Message() {
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
