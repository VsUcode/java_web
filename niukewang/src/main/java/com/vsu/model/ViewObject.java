package com.vsu.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vsu on 2018/02/10.
 */
public class ViewObject {
    private Map<String, Object> objs = new HashMap<>();

    public void set(String key, Object value){
        objs.put(key, value);
    }

    public Object get(String key){
        return objs.get(key);
    }

}
