package org.opencps.rest.opencps.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.opencps.dossiermgt.action.util.ConstantUtils;

import io.swagger.annotations.ApiModelProperty; 
@XmlRootElement(name = ConstantUtils.DATA) public class MenuConfigItemResult  {
  
  @ApiModelProperty(example = "null", required = true, value = "")
  private Long total = null;
  @ApiModelProperty(example = "null", required = true, value = "")
  private List<MenuConfigItem> data = new ArrayList<MenuConfigItem>();

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

  public MenuConfigItemResult total(Long total) {
    this.total = total;
    return this;
  }

 /**
   * Get data
   * @return data
  **/
  @NotNull
  public List<MenuConfigItem> getData() {
    return data;
  }

  public void setData(List<MenuConfigItem> data) {
    this.data = data;
  }

  public MenuConfigItemResult data(List<MenuConfigItem> data) {
    this.data = data;
    return this;
  }

  public MenuConfigItemResult addDataItem(MenuConfigItem dataItem) {
    this.data.add(dataItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MenuConfigItemResult {\n");
    
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

