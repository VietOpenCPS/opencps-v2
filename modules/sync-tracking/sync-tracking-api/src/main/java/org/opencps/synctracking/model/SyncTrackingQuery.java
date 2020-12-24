package org.opencps.synctracking.model;

import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SyncTrackingQuery")
public class SyncTrackingQuery {
    public Long groupId;
    @QueryParam(value = "dossierNo")
    public String dossierNo;
    @QueryParam(value = "referenceUid")
    public String referenceUid;
    @QueryParam(value = "fromDate")
    public String fromDate;
    @QueryParam(value = "toDate")
    public String toDate;
    @QueryParam(value = "unit")
    public String unit;
    @QueryParam(value = "protocol")
    public String protocol;
    @QueryParam(value = "stateSync")
    public Integer stateSync;
    @QueryParam(value = "serviceCode")
    public String serviceCode;
    @QueryParam(value = "api")
    public String api;
    @QueryParam(value = "fromUnit")
    public String fromUnit;
    @QueryParam(value = "toUnit")
    public String[] toUnit;
    @QueryParam(value = "start")
    public Integer start;
    @QueryParam(value = "end")
    public Integer end;
    public String bodyRequest;
    public String bodyResponse;
    public String toUnitSingle;
    public Long trackingId;
}
