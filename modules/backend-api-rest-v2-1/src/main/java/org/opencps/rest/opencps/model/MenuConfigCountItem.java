package org.opencps.rest.opencps.model;

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
@XmlRootElement(name = "data") public class MenuConfigCountItem  {
  
  @ApiModelProperty(example = "", value = "")
  private String menuGroup = null;
  @ApiModelProperty(example = "001", value = "")
  private String menuName = null;
  @ApiModelProperty(example = "null", value = "")
  private Long counter = null;

 /**
   * Get menuGroup
   * @return menuGroup
  **/
  public String getMenuGroup() {
    return menuGroup;
  }

  public void setMenuGroup(String menuGroup) {
    this.menuGroup = menuGroup;
  }

  public MenuConfigCountItem menuGroup(String menuGroup) {
    this.menuGroup = menuGroup;
    return this;
  }

 /**
   * Get menuName
   * @return menuName
  **/
  public String getMenuName() {
    return menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }

  public MenuConfigCountItem menuName(String menuName) {
    this.menuName = menuName;
    return this;
  }

 /**
   * Get counter
   * @return counter
  **/
  public Long getCounter() {
    return counter;
  }

  public void setCounter(Long counter) {
    this.counter = counter;
  }

  public MenuConfigCountItem counter(Long counter) {
    this.counter = counter;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MenuConfigCountItem {\n");
    
    sb.append("    menuGroup: ").append(toIndentedString(menuGroup)).append("\n");
    sb.append("    menuName: ").append(toIndentedString(menuName)).append("\n");
    sb.append("    counter: ").append(toIndentedString(counter)).append("\n");
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

