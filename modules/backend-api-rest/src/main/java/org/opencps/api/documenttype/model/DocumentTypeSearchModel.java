package org.opencps.api.documenttype.model;

import io.swagger.annotations.ApiModelProperty;
import javax.xml.bind.annotation.XmlRootElement; 
@XmlRootElement public class DocumentTypeSearchModel  {
  
  @ApiModelProperty(example = "key search", value = "")
  private String keyword = null;
  @ApiModelProperty(example = "0", value = "")
  private Integer start = null;
  @ApiModelProperty(example = "15", value = "")
  private Integer end = null;
  @ApiModelProperty(example = "field sort", value = "")
  private String sort = null;
  @ApiModelProperty(example = "field order", value = "")
  private String order = null;

 /**
   * Get keyword
   * @return keyword
  **/
  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public DocumentTypeSearchModel keyword(String keyword) {
    this.keyword = keyword;
    return this;
  }

 /**
   * Get start
   * @return start
  **/
  public Integer getStart() {
    return start;
  }

  public void setStart(Integer start) {
    this.start = start;
  }

  public DocumentTypeSearchModel start(Integer start) {
    this.start = start;
    return this;
  }

 /**
   * Get end
   * @return end
  **/
  public Integer getEnd() {
    return end;
  }

  public void setEnd(Integer end) {
    this.end = end;
  }

  public DocumentTypeSearchModel end(Integer end) {
    this.end = end;
    return this;
  }

 /**
   * Get sort
   * @return sort
  **/
  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public DocumentTypeSearchModel sort(String sort) {
    this.sort = sort;
    return this;
  }

 /**
   * Get order
   * @return order
  **/
  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  public DocumentTypeSearchModel order(String order) {
    this.order = order;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DocumentTypeSearchModel {\n");
    
    sb.append("    keyword: ").append(toIndentedString(keyword)).append("\n");
    sb.append("    start: ").append(toIndentedString(start)).append("\n");
    sb.append("    end: ").append(toIndentedString(end)).append("\n");
    sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
    sb.append("    order: ").append(toIndentedString(order)).append("\n");
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

