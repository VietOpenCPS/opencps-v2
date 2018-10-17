package org.opencps.rest.application.model;

import java.util.ArrayList;
import java.util.List;
import org.opencps.rest.application.model.MenuConfigCountItem;
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
@XmlRootElement(name = "data") public class MenuConfigCountItemResults  {
  
  @ApiModelProperty(example = "null", required = true, value = "")
  private Long total = null;
  @ApiModelProperty(example = "null", required = true, value = "")
  private List<MenuConfigCountItem> data = new ArrayList<MenuConfigCountItem>();

 /**
   * Get total
   * @return total
  **/
  @NotNull
  public Long getTotal() {
    return total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public MenuConfigCountItemResults total(Long total) {
    this.total = total;
    return this;
  }

 /**
   * Get data
   * @return data
  **/
  @NotNull
  public List<MenuConfigCountItem> getData() {
    return data;
  }

  public void setData(List<MenuConfigCountItem> data) {
    this.data = data;
  }

  public MenuConfigCountItemResults data(List<MenuConfigCountItem> data) {
    this.data = data;
    return this;
  }

  public MenuConfigCountItemResults addDataItem(MenuConfigCountItem dataItem) {
    this.data.add(dataItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MenuConfigCountItemResults {\n");
    
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

