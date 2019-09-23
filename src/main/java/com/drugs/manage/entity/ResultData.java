package com.drugs.manage.entity;

import java.util.HashMap;
import java.util.Map;

public class ResultData {
    private boolean result;
    private Map<String,Object> value;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Map<String, Object> getValue() {
        return value;
    }

    public void setValue(Map<String, Object> value) {
        this.value = value;
    }
}
