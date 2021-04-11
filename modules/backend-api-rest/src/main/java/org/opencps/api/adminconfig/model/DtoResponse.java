package org.opencps.api.adminconfig.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "total",
    "data"
})
@XmlRootElement(name = "DtoResponse")
public class DtoResponse {
	protected Integer total;
    protected List<SyncTrackingResponse> data;
    
    public Integer getTotal() {
        return total;
    }
    
    public void setTotal(Integer value) {
        this.total = value;
    }
    
    public List<SyncTrackingResponse> getData() {
        if (data == null) {
            data = new ArrayList<SyncTrackingResponse>();
        }
        return this.data;
    }
}
