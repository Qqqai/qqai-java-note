package qqai.cloud.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qqai
 * @createTime 2020/11/15 00:59
 * @description：通用返回
 */


public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public <T> T getData(String key, TypeReference<T> typeReference) {
        Object data = get(key);
        String s = JSON.toJSONString(data);
        return JSON.parseObject(s, typeReference);
    }

    public <T> T getData(TypeReference<T> typeReference) {
        Object data = get("data");
        String s = JSON.toJSONString(data);
        return JSON.parseObject(s, typeReference);
    }

    public R setData(Object data) {
        put("data", data);
        return this;
    }


    public R() {
        put("code", 200);
        put("msg", "success");
    }

    public static R error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public Integer getCode() {
        return (Integer) this.get("code");
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }


    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
