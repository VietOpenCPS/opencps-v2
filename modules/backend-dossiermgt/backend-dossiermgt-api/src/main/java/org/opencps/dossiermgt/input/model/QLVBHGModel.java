package org.opencps.dossiermgt.input.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class QLVBHGModel {
    public String trichYeu;
    public String maHoSo;
    public List<FileVBHGModel> files;
}
