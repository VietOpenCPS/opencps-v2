package org.opencps.dossiermgt.input.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProfileDocPaper {
    public Integer id;

    public String profile_id;

    public String paper_name;

    public String paper_type;

    public Integer amount;

    public String description;
}
