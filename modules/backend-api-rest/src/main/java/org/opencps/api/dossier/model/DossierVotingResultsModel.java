package org.opencps.api.dossier.model;

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
@XmlRootElement(name = "data")
public class DossierVotingResultsModel {
	protected Integer total;
    protected List<DossierVotingDataModel> data;
    
    public Integer getTotal() {
        return total;
    }
    
    public void setTotal(Integer value) {
        this.total = value;
    }
    
    public List<DossierVotingDataModel> getData() {
        if (data == null) {
            data = new ArrayList<DossierVotingDataModel>();
        }
        return this.data;
    }
}
