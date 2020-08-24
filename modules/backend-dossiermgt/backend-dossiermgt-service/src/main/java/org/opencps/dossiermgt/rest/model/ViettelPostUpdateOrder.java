package org.opencps.dossiermgt.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ViettelPostUpdateOrder")
public class ViettelPostUpdateOrder {
    private Integer TYPE;
    private String ORDER_NUMBER;
    private String NOTE;

    public Integer getTYPE() {
        return TYPE;
    }

    public void setTYPE(Integer TYPE) {
        this.TYPE = TYPE;
    }

    public String getORDER_NUMBER() {
        return ORDER_NUMBER;
    }

    public void setORDER_NUMBER(String ORDER_NUMBER) {
        this.ORDER_NUMBER = ORDER_NUMBER;
    }

    public String getNOTE() {
        return NOTE;
    }

    public void setNOTE(String NOTE) {
        this.NOTE = NOTE;
    }
}
