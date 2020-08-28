package org.opencps.api.dossier.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DossierRequestDVCQGModel {
    @FormParam(value = "madonvi")
    private String madonvi;
    @FormParam(value = "service")
    private String service;
    @FormParam(value = "masohoso")
    private String masohoso;

    public String getMadonvi() {
        return madonvi;
    }

    public void setMadonvi(String madonvi) {
        this.madonvi = madonvi;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMasohoso() {
        return masohoso;
    }

    public void setMasohoso(String masohoso) {
        this.masohoso = masohoso;
    }
}
