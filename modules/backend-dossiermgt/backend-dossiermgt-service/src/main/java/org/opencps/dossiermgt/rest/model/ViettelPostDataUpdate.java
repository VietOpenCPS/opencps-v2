package org.opencps.dossiermgt.rest.model;

public class ViettelPostDataUpdate {
    private String ORDER_NUMBER;
    private Integer ORDER_STATUS;

    public String getORDER_NUMBER() {
        return ORDER_NUMBER;
    }

    public void setORDER_NUMBER(String ORDER_NUMBER) {
        this.ORDER_NUMBER = ORDER_NUMBER;
    }

    public Integer getORDER_STATUS() {
        return ORDER_STATUS;
    }

    public void setORDER_STATUS(Integer ORDER_STATUS) {
        this.ORDER_STATUS = ORDER_STATUS;
    }
}
