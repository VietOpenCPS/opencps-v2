package org.opencps.rest.opencps.model;

import java.util.ArrayList;
import java.util.List;
import org.opencps.rest.opencps.model.DossierDocumentModel;
import javax.validation.constraints.*;

import io.swagger.annotations.ApiModelProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

import javax.xml.bind.annotation.XmlRootElement; 
@XmlRootElement(name = "data") public class DossierDocumentResultModel  {
  
  @ApiModelProperty(example = "0", required = true, value = "")
  private Integer total = null;
  @ApiModelProperty(example = "null", value = "")
  private List<DossierDocumentModel> data = new ArrayList<DossierDocumentModel>();

 /**
   * Get total
   * @return total
  **/
  @NotNull
  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public DossierDocumentResultModel total(Integer total) {
    this.total = total;
    return this;
  }

 /**
   * Get data
   * @return data
  **/
  public List<DossierDocumentModel> getData() {
    return data;
  }

  public void setData(List<DossierDocumentModel> data) {
    this.data = data;
  }

  public DossierDocumentResultModel data(List<DossierDocumentModel> data) {
    this.data = data;
    return this;
  }

  public DossierDocumentResultModel addDataItem(DossierDocumentModel dataItem) {
    this.data.add(dataItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DossierDocumentResultModel {\n");
    
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

