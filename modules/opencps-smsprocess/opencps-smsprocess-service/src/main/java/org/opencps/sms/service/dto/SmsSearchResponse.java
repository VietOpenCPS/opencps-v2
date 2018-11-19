package org.opencps.sms.service.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
public class SmsSearchResponse {
    private int total;
    
    @XmlElement(name = "data")
    private List<SmsSearchData> data;
    
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    
    public List<SmsSearchData> getData() {
        return data;
    }
    public void setData(List<SmsSearchData> data) {
        this.data = data;
    }
}
