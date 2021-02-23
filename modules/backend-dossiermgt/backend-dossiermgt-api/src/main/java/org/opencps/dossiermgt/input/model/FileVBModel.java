package org.opencps.dossiermgt.input.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FileVBModel {
    public String fileName;
    public String fileByte;
    public long size;
}
