package com.fred.spring.transaction.demo.customnamespace;

import java.util.List;

public class MyDAO {
    private List<String> fields;

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }
}
