package org.opencps.dossiermgt.input.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProfileAttachment {
    public Integer id;

    public String profile_id;

    public String content_type;

    public String attachment_name;

    public String content_transfer_encoded;

    public Integer is_verified;

    public String description;

    public Integer is_deleted;

    public String type;

    public String stringContent_transfer_encoded;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(String profile_id) {
        this.profile_id = profile_id;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getAttachment_name() {
        return attachment_name;
    }

    public void setAttachment_name(String attachment_name) {
        this.attachment_name = attachment_name;
    }

    public String getContent_transfer_encoded() {
        return content_transfer_encoded;
    }

    public void setContent_transfer_encoded(String content_transfer_encoded) {
        this.content_transfer_encoded = content_transfer_encoded;
    }

    public Integer getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(Integer is_verified) {
        this.is_verified = is_verified;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Integer is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStringContent_transfer_encoded() {
        return stringContent_transfer_encoded;
    }

    public void setStringContent_transfer_encoded(String stringContent_transfer_encoded) {
        this.stringContent_transfer_encoded = stringContent_transfer_encoded;
    }
}
