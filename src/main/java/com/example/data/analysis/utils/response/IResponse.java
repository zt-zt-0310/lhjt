package com.example.data.analysis.utils.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @Author zt
 * @Description TODO
 */
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY
)
public class IResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code = "0000";
    private String msg = "success";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public IResponse() {
    }

    public IResponse(T data) {
        this.data = data;
    }

    public IResponse(T data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public IResponse(Throwable e) {
        this.msg = e.getMessage();
        this.code = "9999";
    }

    public static IResponse fail(String msg) {
        return (new IResponse((Object)null, msg)).setCode("9999");
    }

    public static <T> IResponse success(T data) {
        return (new IResponse()).setData(data);
    }

    public static IResponse decode(Object object) {
        if (object == null) {
            return fail("错误数据");
        } else {
            return object instanceof IResponse ? (IResponse)object : success(object);
        }
    }

    public String toString() {
        return "IResponse(code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }

    public String getCode() {
        return this.code;
    }

    public IResponse<T> setCode(final String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return this.msg;
    }

    public IResponse<T> setMsg(final String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public IResponse<T> setData(final T data) {
        this.data = data;
        return this;
    }

}
