package org.opencps.dossiermgt.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ViettelPostUpdateOrder")
public class ViettelPostUpdateOrder {
    private ViettelPostDataUpdate DATA;
    private String TOKEN;

    public ViettelPostDataUpdate getDATA() {
        return DATA;
    }

    public void setDATA(ViettelPostDataUpdate DATA) {
        this.DATA = DATA;
    }

    public String getTOKEN() {
        return TOKEN;
    }

    public void setTOKEN(String TOKEN) {
        this.TOKEN = TOKEN;
    }
}
