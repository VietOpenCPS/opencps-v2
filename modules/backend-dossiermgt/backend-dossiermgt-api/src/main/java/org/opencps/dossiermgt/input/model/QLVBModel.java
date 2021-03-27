package org.opencps.dossiermgt.input.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class QLVBModel {
    public String trichYeu;
    public String maHoSo;
    public List<FileVBModel> files;
    public String tenHoSo;
    public String documentCode;
}
