package org.opencps.rest.application.model;

import java.util.ArrayList;
import java.util.List;
import org.opencps.rest.application.model.MenuConfigStepsItem;
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
@XmlRootElement(name = "data") public class MenuConfigItem  {
  
  @ApiModelProperty(example = "null", value = "")
  private Long menuConfigId = null;
  @ApiModelProperty(example = "2018-06-11T08:04:12.129Z", value = "")
  private String createDate = null;
  @ApiModelProperty(example = "001", value = "")
  private String menuGroup = null;
  @ApiModelProperty(example = "step name 001", value = "")
  private String menuName = null;
  @ApiModelProperty(example = "0", value = "")
  private Integer order = null;
  @ApiModelProperty(example = "0", value = "")
  private Integer menuType = null;
  @ApiModelProperty(example = "queryParams", value = "")
  private String queryParams = null;
  @ApiModelProperty(example = "tableConfig", value = "")
  private String tableConfig = null;
  @ApiModelProperty(example = "buttonConfig", value = "")
  private String buttonConfig = null;
  @ApiModelProperty(example = "icon", value = "")
  private String icon = null;
  @ApiModelProperty(example = "null", value = "")
  private List<MenuConfigStepsItem> steps = new ArrayList<MenuConfigStepsItem>();

 /**
   * Get menuConfigId
   * @return menuConfigId
  **/
  public Long getMenuConfigId() {
    return menuConfigId;
  }

  public void setMenuConfigId(Long menuConfigId) {
    this.menuConfigId = menuConfigId;
  }

  public MenuConfigItem menuConfigId(Long menuConfigId) {
    this.menuConfigId = menuConfigId;
    return this;
  }

 /**
   * Get createDate
   * @return createDate
  **/
  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

  public MenuConfigItem createDate(String createDate) {
    this.createDate = createDate;
    return this;
  }

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

  public MenuConfigItem menuGroup(String menuGroup) {
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

  public MenuConfigItem menuName(String menuName) {
    this.menuName = menuName;
    return this;
  }

 /**
   * Get order
   * @return order
  **/
  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  public MenuConfigItem order(Integer order) {
    this.order = order;
    return this;
  }

 /**
   * Get menuType
   * @return menuType
  **/
  public Integer getMenuType() {
    return menuType;
  }

  public void setMenuType(Integer menuType) {
    this.menuType = menuType;
  }

  public MenuConfigItem menuType(Integer menuType) {
    this.menuType = menuType;
    return this;
  }

 /**
   * Get queryParams
   * @return queryParams
  **/
  public String getQueryParams() {
    return queryParams;
  }

  public void setQueryParams(String queryParams) {
    this.queryParams = queryParams;
  }

  public MenuConfigItem queryParams(String queryParams) {
    this.queryParams = queryParams;
    return this;
  }

 /**
   * Get tableConfig
   * @return tableConfig
  **/
  public String getTableConfig() {
    return tableConfig;
  }

  public void setTableConfig(String tableConfig) {
    this.tableConfig = tableConfig;
  }

  public MenuConfigItem tableConfig(String tableConfig) {
    this.tableConfig = tableConfig;
    return this;
  }

 /**
   * Get buttonConfig
   * @return buttonConfig
  **/
  public String getButtonConfig() {
    return buttonConfig;
  }

  public void setButtonConfig(String buttonConfig) {
    this.buttonConfig = buttonConfig;
  }

  public MenuConfigItem buttonConfig(String buttonConfig) {
    this.buttonConfig = buttonConfig;
    return this;
  }

 /**
   * Get steps
   * @return steps
  **/
  public List<MenuConfigStepsItem> getSteps() {
    return steps;
  }

  public void setSteps(List<MenuConfigStepsItem> steps) {
    this.steps = steps;
  }

  public MenuConfigItem steps(List<MenuConfigStepsItem> steps) {
    this.steps = steps;
    return this;
  }

  public MenuConfigItem addStepsItem(MenuConfigStepsItem stepsItem) {
    this.steps.add(stepsItem);
    return this;
  }


  public String getIcon() {
	return icon;
}

public void setIcon(String icon) {
	this.icon = icon;
}

@Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MenuConfigItem {\n");
    
    sb.append("    menuConfigId: ").append(toIndentedString(menuConfigId)).append("\n");
    sb.append("    createDate: ").append(toIndentedString(createDate)).append("\n");
    sb.append("    menuGroup: ").append(toIndentedString(menuGroup)).append("\n");
    sb.append("    menuName: ").append(toIndentedString(menuName)).append("\n");
    sb.append("    order: ").append(toIndentedString(order)).append("\n");
    sb.append("    menuType: ").append(toIndentedString(menuType)).append("\n");
    sb.append("    queryParams: ").append(toIndentedString(queryParams)).append("\n");
    sb.append("    tableConfig: ").append(toIndentedString(tableConfig)).append("\n");
    sb.append("    buttonConfig: ").append(toIndentedString(buttonConfig)).append("\n");
    sb.append("    steps: ").append(toIndentedString(steps)).append("\n");
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

