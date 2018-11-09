package org.opencps.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

/**
 * UsersUserItem
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-09T07:01:00.204Z")

public class UsersUserItem   {
  @JsonProperty("className")
  private String className = null;

  @JsonProperty("classPK")
  private Long classPK = null;

  @JsonProperty("userId")
  private Long userId = null;

  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("avatar")
  private String avatar = null;

  @JsonProperty("mappingUserId")
  private Long mappingUserId = null;

  @JsonProperty("screenName")
  private String screenName = null;

  @JsonProperty("employeeFullName")
  private String employeeFullName = null;

  @JsonProperty("employeeNo")
  private String employeeNo = null;

  @JsonProperty("employeeGender")
  private Integer employeeGender = null;

  @JsonProperty("employeeBirthDate")
  private String employeeBirthDate = null;

  @JsonProperty("employeeTelNo")
  private String employeeTelNo = null;

  @JsonProperty("employeeMobile")
  private String employeeMobile = null;

  @JsonProperty("employeeEmail")
  private String employeeEmail = null;

  @JsonProperty("employeeWorkingStatus")
  private Integer employeeWorkingStatus = null;

  @JsonProperty("employeeMainJobPostId")
  private Long employeeMainJobPostId = null;

  @JsonProperty("employeeMainJobPostName")
  private Long employeeMainJobPostName = null;

  @JsonProperty("employeePhotoFileEntryId")
  private Long employeePhotoFileEntryId = null;

  @JsonProperty("employeeFileCerId")
  private Long employeeFileCerId = null;

  @JsonProperty("employeeFileSignId")
  private Long employeeFileSignId = null;

  @JsonProperty("applicantName")
  private String applicantName = null;

  @JsonProperty("applicantType")
  private String applicantType = null;

  @JsonProperty("applicantNo")
  private String applicantNo = null;

  @JsonProperty("applicantIdNo")
  private String applicantIdNo = null;

  @JsonProperty("applicantIdDate")
  private String applicantIdDate = null;

  @JsonProperty("applicantAddress")
  private String applicantAddress = null;

  @JsonProperty("applicantCityCode")
  private String applicantCityCode = null;

  @JsonProperty("applicantCityName")
  private String applicantCityName = null;

  @JsonProperty("applicantDistrictCode")
  private String applicantDistrictCode = null;

  @JsonProperty("applicantDistrictName")
  private String applicantDistrictName = null;

  @JsonProperty("applicantWardCode")
  private String applicantWardCode = null;

  @JsonProperty("applicantWardName")
  private String applicantWardName = null;

  @JsonProperty("applicantContactName")
  private String applicantContactName = null;

  @JsonProperty("applicantContactTelNo")
  private String applicantContactTelNo = null;

  @JsonProperty("applicantContactEmail")
  private String applicantContactEmail = null;

  @JsonProperty("applicantActivationCode")
  private String applicantActivationCode = null;

  @JsonProperty("applicantLock")
  private Integer applicantLock = null;

  @JsonProperty("applicantTmpPass")
  private String applicantTmpPass = null;

  public UsersUserItem className(String className) {
    this.className = className;
    return this;
  }

  /**
   * Get className
   * @return className
  **/
  @ApiModelProperty(example = "", value = "")


  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public UsersUserItem classPK(Long classPK) {
    this.classPK = classPK;
    return this;
  }

  /**
   * Get classPK
   * @return classPK
  **/
  @ApiModelProperty(value = "")


  public Long getClassPK() {
    return classPK;
  }

  public void setClassPK(Long classPK) {
    this.classPK = classPK;
  }

  public UsersUserItem userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(value = "")


  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public UsersUserItem userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * Get userName
   * @return userName
  **/
  @ApiModelProperty(example = "", value = "")


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public UsersUserItem avatar(String avatar) {
    this.avatar = avatar;
    return this;
  }

  /**
   * Get avatar
   * @return avatar
  **/
  @ApiModelProperty(example = "", value = "")


  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public UsersUserItem mappingUserId(Long mappingUserId) {
    this.mappingUserId = mappingUserId;
    return this;
  }

  /**
   * Get mappingUserId
   * @return mappingUserId
  **/
  @ApiModelProperty(value = "")


  public Long getMappingUserId() {
    return mappingUserId;
  }

  public void setMappingUserId(Long mappingUserId) {
    this.mappingUserId = mappingUserId;
  }

  public UsersUserItem screenName(String screenName) {
    this.screenName = screenName;
    return this;
  }

  /**
   * Get screenName
   * @return screenName
  **/
  @ApiModelProperty(example = "", value = "")


  public String getScreenName() {
    return screenName;
  }

  public void setScreenName(String screenName) {
    this.screenName = screenName;
  }

  public UsersUserItem employeeFullName(String employeeFullName) {
    this.employeeFullName = employeeFullName;
    return this;
  }

  /**
   * Get employeeFullName
   * @return employeeFullName
  **/
  @ApiModelProperty(example = "", value = "")


  public String getEmployeeFullName() {
    return employeeFullName;
  }

  public void setEmployeeFullName(String employeeFullName) {
    this.employeeFullName = employeeFullName;
  }

  public UsersUserItem employeeNo(String employeeNo) {
    this.employeeNo = employeeNo;
    return this;
  }

  /**
   * Get employeeNo
   * @return employeeNo
  **/
  @ApiModelProperty(example = "", value = "")


  public String getEmployeeNo() {
    return employeeNo;
  }

  public void setEmployeeNo(String employeeNo) {
    this.employeeNo = employeeNo;
  }

  public UsersUserItem employeeGender(Integer employeeGender) {
    this.employeeGender = employeeGender;
    return this;
  }

  /**
   * Get employeeGender
   * @return employeeGender
  **/
  @ApiModelProperty(value = "")


  public Integer getEmployeeGender() {
    return employeeGender;
  }

  public void setEmployeeGender(Integer employeeGender) {
    this.employeeGender = employeeGender;
  }

  public UsersUserItem employeeBirthDate(String employeeBirthDate) {
    this.employeeBirthDate = employeeBirthDate;
    return this;
  }

  /**
   * Get employeeBirthDate
   * @return employeeBirthDate
  **/
  @ApiModelProperty(example = "", value = "")


  public String getEmployeeBirthDate() {
    return employeeBirthDate;
  }

  public void setEmployeeBirthDate(String employeeBirthDate) {
    this.employeeBirthDate = employeeBirthDate;
  }

  public UsersUserItem employeeTelNo(String employeeTelNo) {
    this.employeeTelNo = employeeTelNo;
    return this;
  }

  /**
   * Get employeeTelNo
   * @return employeeTelNo
  **/
  @ApiModelProperty(example = "", value = "")


  public String getEmployeeTelNo() {
    return employeeTelNo;
  }

  public void setEmployeeTelNo(String employeeTelNo) {
    this.employeeTelNo = employeeTelNo;
  }

  public UsersUserItem employeeMobile(String employeeMobile) {
    this.employeeMobile = employeeMobile;
    return this;
  }

  /**
   * Get employeeMobile
   * @return employeeMobile
  **/
  @ApiModelProperty(example = "", value = "")


  public String getEmployeeMobile() {
    return employeeMobile;
  }

  public void setEmployeeMobile(String employeeMobile) {
    this.employeeMobile = employeeMobile;
  }

  public UsersUserItem employeeEmail(String employeeEmail) {
    this.employeeEmail = employeeEmail;
    return this;
  }

  /**
   * Get employeeEmail
   * @return employeeEmail
  **/
  @ApiModelProperty(example = "", value = "")


  public String getEmployeeEmail() {
    return employeeEmail;
  }

  public void setEmployeeEmail(String employeeEmail) {
    this.employeeEmail = employeeEmail;
  }

  public UsersUserItem employeeWorkingStatus(Integer employeeWorkingStatus) {
    this.employeeWorkingStatus = employeeWorkingStatus;
    return this;
  }

  /**
   * Get employeeWorkingStatus
   * @return employeeWorkingStatus
  **/
  @ApiModelProperty(value = "")


  public Integer getEmployeeWorkingStatus() {
    return employeeWorkingStatus;
  }

  public void setEmployeeWorkingStatus(Integer employeeWorkingStatus) {
    this.employeeWorkingStatus = employeeWorkingStatus;
  }

  public UsersUserItem employeeMainJobPostId(Long employeeMainJobPostId) {
    this.employeeMainJobPostId = employeeMainJobPostId;
    return this;
  }

  /**
   * Get employeeMainJobPostId
   * @return employeeMainJobPostId
  **/
  @ApiModelProperty(value = "")


  public Long getEmployeeMainJobPostId() {
    return employeeMainJobPostId;
  }

  public void setEmployeeMainJobPostId(Long employeeMainJobPostId) {
    this.employeeMainJobPostId = employeeMainJobPostId;
  }

  public UsersUserItem employeeMainJobPostName(Long employeeMainJobPostName) {
    this.employeeMainJobPostName = employeeMainJobPostName;
    return this;
  }

  /**
   * Get employeeMainJobPostName
   * @return employeeMainJobPostName
  **/
  @ApiModelProperty(value = "")


  public Long getEmployeeMainJobPostName() {
    return employeeMainJobPostName;
  }

  public void setEmployeeMainJobPostName(Long employeeMainJobPostName) {
    this.employeeMainJobPostName = employeeMainJobPostName;
  }

  public UsersUserItem employeePhotoFileEntryId(Long employeePhotoFileEntryId) {
    this.employeePhotoFileEntryId = employeePhotoFileEntryId;
    return this;
  }

  /**
   * Get employeePhotoFileEntryId
   * @return employeePhotoFileEntryId
  **/
  @ApiModelProperty(value = "")


  public Long getEmployeePhotoFileEntryId() {
    return employeePhotoFileEntryId;
  }

  public void setEmployeePhotoFileEntryId(Long employeePhotoFileEntryId) {
    this.employeePhotoFileEntryId = employeePhotoFileEntryId;
  }

  public UsersUserItem employeeFileCerId(Long employeeFileCerId) {
    this.employeeFileCerId = employeeFileCerId;
    return this;
  }

  /**
   * Get employeeFileCerId
   * @return employeeFileCerId
  **/
  @ApiModelProperty(value = "")


  public Long getEmployeeFileCerId() {
    return employeeFileCerId;
  }

  public void setEmployeeFileCerId(Long employeeFileCerId) {
    this.employeeFileCerId = employeeFileCerId;
  }

  public UsersUserItem employeeFileSignId(Long employeeFileSignId) {
    this.employeeFileSignId = employeeFileSignId;
    return this;
  }

  /**
   * Get employeeFileSignId
   * @return employeeFileSignId
  **/
  @ApiModelProperty(value = "")


  public Long getEmployeeFileSignId() {
    return employeeFileSignId;
  }

  public void setEmployeeFileSignId(Long employeeFileSignId) {
    this.employeeFileSignId = employeeFileSignId;
  }

  public UsersUserItem applicantName(String applicantName) {
    this.applicantName = applicantName;
    return this;
  }

  /**
   * Get applicantName
   * @return applicantName
  **/
  @ApiModelProperty(example = "", value = "")


  public String getApplicantName() {
    return applicantName;
  }

  public void setApplicantName(String applicantName) {
    this.applicantName = applicantName;
  }

  public UsersUserItem applicantType(String applicantType) {
    this.applicantType = applicantType;
    return this;
  }

  /**
   * Get applicantType
   * @return applicantType
  **/
  @ApiModelProperty(example = "", value = "")


  public String getApplicantType() {
    return applicantType;
  }

  public void setApplicantType(String applicantType) {
    this.applicantType = applicantType;
  }

  public UsersUserItem applicantNo(String applicantNo) {
    this.applicantNo = applicantNo;
    return this;
  }

  /**
   * Get applicantNo
   * @return applicantNo
  **/
  @ApiModelProperty(example = "", value = "")


  public String getApplicantNo() {
    return applicantNo;
  }

  public void setApplicantNo(String applicantNo) {
    this.applicantNo = applicantNo;
  }

  public UsersUserItem applicantIdNo(String applicantIdNo) {
    this.applicantIdNo = applicantIdNo;
    return this;
  }

  /**
   * Get applicantIdNo
   * @return applicantIdNo
  **/
  @ApiModelProperty(example = "", value = "")


  public String getApplicantIdNo() {
    return applicantIdNo;
  }

  public void setApplicantIdNo(String applicantIdNo) {
    this.applicantIdNo = applicantIdNo;
  }

  public UsersUserItem applicantIdDate(String applicantIdDate) {
    this.applicantIdDate = applicantIdDate;
    return this;
  }

  /**
   * Get applicantIdDate
   * @return applicantIdDate
  **/
  @ApiModelProperty(example = "", value = "")


  public String getApplicantIdDate() {
    return applicantIdDate;
  }

  public void setApplicantIdDate(String applicantIdDate) {
    this.applicantIdDate = applicantIdDate;
  }

  public UsersUserItem applicantAddress(String applicantAddress) {
    this.applicantAddress = applicantAddress;
    return this;
  }

  /**
   * Get applicantAddress
   * @return applicantAddress
  **/
  @ApiModelProperty(example = "", value = "")


  public String getApplicantAddress() {
    return applicantAddress;
  }

  public void setApplicantAddress(String applicantAddress) {
    this.applicantAddress = applicantAddress;
  }

  public UsersUserItem applicantCityCode(String applicantCityCode) {
    this.applicantCityCode = applicantCityCode;
    return this;
  }

  /**
   * Get applicantCityCode
   * @return applicantCityCode
  **/
  @ApiModelProperty(example = "", value = "")


  public String getApplicantCityCode() {
    return applicantCityCode;
  }

  public void setApplicantCityCode(String applicantCityCode) {
    this.applicantCityCode = applicantCityCode;
  }

  public UsersUserItem applicantCityName(String applicantCityName) {
    this.applicantCityName = applicantCityName;
    return this;
  }

  /**
   * Get applicantCityName
   * @return applicantCityName
  **/
  @ApiModelProperty(example = "", value = "")


  public String getApplicantCityName() {
    return applicantCityName;
  }

  public void setApplicantCityName(String applicantCityName) {
    this.applicantCityName = applicantCityName;
  }

  public UsersUserItem applicantDistrictCode(String applicantDistrictCode) {
    this.applicantDistrictCode = applicantDistrictCode;
    return this;
  }

  /**
   * Get applicantDistrictCode
   * @return applicantDistrictCode
  **/
  @ApiModelProperty(example = "", value = "")


  public String getApplicantDistrictCode() {
    return applicantDistrictCode;
  }

  public void setApplicantDistrictCode(String applicantDistrictCode) {
    this.applicantDistrictCode = applicantDistrictCode;
  }

  public UsersUserItem applicantDistrictName(String applicantDistrictName) {
    this.applicantDistrictName = applicantDistrictName;
    return this;
  }

  /**
   * Get applicantDistrictName
   * @return applicantDistrictName
  **/
  @ApiModelProperty(example = "", value = "")


  public String getApplicantDistrictName() {
    return applicantDistrictName;
  }

  public void setApplicantDistrictName(String applicantDistrictName) {
    this.applicantDistrictName = applicantDistrictName;
  }

  public UsersUserItem applicantWardCode(String applicantWardCode) {
    this.applicantWardCode = applicantWardCode;
    return this;
  }

  /**
   * Get applicantWardCode
   * @return applicantWardCode
  **/
  @ApiModelProperty(example = "", value = "")


  public String getApplicantWardCode() {
    return applicantWardCode;
  }

  public void setApplicantWardCode(String applicantWardCode) {
    this.applicantWardCode = applicantWardCode;
  }

  public UsersUserItem applicantWardName(String applicantWardName) {
    this.applicantWardName = applicantWardName;
    return this;
  }

  /**
   * Get applicantWardName
   * @return applicantWardName
  **/
  @ApiModelProperty(example = "", value = "")


  public String getApplicantWardName() {
    return applicantWardName;
  }

  public void setApplicantWardName(String applicantWardName) {
    this.applicantWardName = applicantWardName;
  }

  public UsersUserItem applicantContactName(String applicantContactName) {
    this.applicantContactName = applicantContactName;
    return this;
  }

  /**
   * Get applicantContactName
   * @return applicantContactName
  **/
  @ApiModelProperty(example = "", value = "")


  public String getApplicantContactName() {
    return applicantContactName;
  }

  public void setApplicantContactName(String applicantContactName) {
    this.applicantContactName = applicantContactName;
  }

  public UsersUserItem applicantContactTelNo(String applicantContactTelNo) {
    this.applicantContactTelNo = applicantContactTelNo;
    return this;
  }

  /**
   * Get applicantContactTelNo
   * @return applicantContactTelNo
  **/
  @ApiModelProperty(example = "", value = "")


  public String getApplicantContactTelNo() {
    return applicantContactTelNo;
  }

  public void setApplicantContactTelNo(String applicantContactTelNo) {
    this.applicantContactTelNo = applicantContactTelNo;
  }

  public UsersUserItem applicantContactEmail(String applicantContactEmail) {
    this.applicantContactEmail = applicantContactEmail;
    return this;
  }

  /**
   * Get applicantContactEmail
   * @return applicantContactEmail
  **/
  @ApiModelProperty(example = "", value = "")


  public String getApplicantContactEmail() {
    return applicantContactEmail;
  }

  public void setApplicantContactEmail(String applicantContactEmail) {
    this.applicantContactEmail = applicantContactEmail;
  }

  public UsersUserItem applicantActivationCode(String applicantActivationCode) {
    this.applicantActivationCode = applicantActivationCode;
    return this;
  }

  /**
   * Get applicantActivationCode
   * @return applicantActivationCode
  **/
  @ApiModelProperty(example = "", value = "")


  public String getApplicantActivationCode() {
    return applicantActivationCode;
  }

  public void setApplicantActivationCode(String applicantActivationCode) {
    this.applicantActivationCode = applicantActivationCode;
  }

  public UsersUserItem applicantLock(Integer applicantLock) {
    this.applicantLock = applicantLock;
    return this;
  }

  /**
   * Get applicantLock
   * @return applicantLock
  **/
  @ApiModelProperty(value = "")


  public Integer getApplicantLock() {
    return applicantLock;
  }

  public void setApplicantLock(Integer applicantLock) {
    this.applicantLock = applicantLock;
  }

  public UsersUserItem applicantTmpPass(String applicantTmpPass) {
    this.applicantTmpPass = applicantTmpPass;
    return this;
  }

  /**
   * Get applicantTmpPass
   * @return applicantTmpPass
  **/
  @ApiModelProperty(example = "", value = "")


  public String getApplicantTmpPass() {
    return applicantTmpPass;
  }

  public void setApplicantTmpPass(String applicantTmpPass) {
    this.applicantTmpPass = applicantTmpPass;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UsersUserItem usersUserItem = (UsersUserItem) o;
    return Objects.equals(this.className, usersUserItem.className) &&
        Objects.equals(this.classPK, usersUserItem.classPK) &&
        Objects.equals(this.userId, usersUserItem.userId) &&
        Objects.equals(this.userName, usersUserItem.userName) &&
        Objects.equals(this.avatar, usersUserItem.avatar) &&
        Objects.equals(this.mappingUserId, usersUserItem.mappingUserId) &&
        Objects.equals(this.screenName, usersUserItem.screenName) &&
        Objects.equals(this.employeeFullName, usersUserItem.employeeFullName) &&
        Objects.equals(this.employeeNo, usersUserItem.employeeNo) &&
        Objects.equals(this.employeeGender, usersUserItem.employeeGender) &&
        Objects.equals(this.employeeBirthDate, usersUserItem.employeeBirthDate) &&
        Objects.equals(this.employeeTelNo, usersUserItem.employeeTelNo) &&
        Objects.equals(this.employeeMobile, usersUserItem.employeeMobile) &&
        Objects.equals(this.employeeEmail, usersUserItem.employeeEmail) &&
        Objects.equals(this.employeeWorkingStatus, usersUserItem.employeeWorkingStatus) &&
        Objects.equals(this.employeeMainJobPostId, usersUserItem.employeeMainJobPostId) &&
        Objects.equals(this.employeeMainJobPostName, usersUserItem.employeeMainJobPostName) &&
        Objects.equals(this.employeePhotoFileEntryId, usersUserItem.employeePhotoFileEntryId) &&
        Objects.equals(this.employeeFileCerId, usersUserItem.employeeFileCerId) &&
        Objects.equals(this.employeeFileSignId, usersUserItem.employeeFileSignId) &&
        Objects.equals(this.applicantName, usersUserItem.applicantName) &&
        Objects.equals(this.applicantType, usersUserItem.applicantType) &&
        Objects.equals(this.applicantNo, usersUserItem.applicantNo) &&
        Objects.equals(this.applicantIdNo, usersUserItem.applicantIdNo) &&
        Objects.equals(this.applicantIdDate, usersUserItem.applicantIdDate) &&
        Objects.equals(this.applicantAddress, usersUserItem.applicantAddress) &&
        Objects.equals(this.applicantCityCode, usersUserItem.applicantCityCode) &&
        Objects.equals(this.applicantCityName, usersUserItem.applicantCityName) &&
        Objects.equals(this.applicantDistrictCode, usersUserItem.applicantDistrictCode) &&
        Objects.equals(this.applicantDistrictName, usersUserItem.applicantDistrictName) &&
        Objects.equals(this.applicantWardCode, usersUserItem.applicantWardCode) &&
        Objects.equals(this.applicantWardName, usersUserItem.applicantWardName) &&
        Objects.equals(this.applicantContactName, usersUserItem.applicantContactName) &&
        Objects.equals(this.applicantContactTelNo, usersUserItem.applicantContactTelNo) &&
        Objects.equals(this.applicantContactEmail, usersUserItem.applicantContactEmail) &&
        Objects.equals(this.applicantActivationCode, usersUserItem.applicantActivationCode) &&
        Objects.equals(this.applicantLock, usersUserItem.applicantLock) &&
        Objects.equals(this.applicantTmpPass, usersUserItem.applicantTmpPass);
  }

  @Override
  public int hashCode() {
    return Objects.hash(className, classPK, userId, userName, avatar, mappingUserId, screenName, employeeFullName, employeeNo, employeeGender, employeeBirthDate, employeeTelNo, employeeMobile, employeeEmail, employeeWorkingStatus, employeeMainJobPostId, employeeMainJobPostName, employeePhotoFileEntryId, employeeFileCerId, employeeFileSignId, applicantName, applicantType, applicantNo, applicantIdNo, applicantIdDate, applicantAddress, applicantCityCode, applicantCityName, applicantDistrictCode, applicantDistrictName, applicantWardCode, applicantWardName, applicantContactName, applicantContactTelNo, applicantContactEmail, applicantActivationCode, applicantLock, applicantTmpPass);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UsersUserItem {\n");
    
    sb.append("    className: ").append(toIndentedString(className)).append("\n");
    sb.append("    classPK: ").append(toIndentedString(classPK)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    avatar: ").append(toIndentedString(avatar)).append("\n");
    sb.append("    mappingUserId: ").append(toIndentedString(mappingUserId)).append("\n");
    sb.append("    screenName: ").append(toIndentedString(screenName)).append("\n");
    sb.append("    employeeFullName: ").append(toIndentedString(employeeFullName)).append("\n");
    sb.append("    employeeNo: ").append(toIndentedString(employeeNo)).append("\n");
    sb.append("    employeeGender: ").append(toIndentedString(employeeGender)).append("\n");
    sb.append("    employeeBirthDate: ").append(toIndentedString(employeeBirthDate)).append("\n");
    sb.append("    employeeTelNo: ").append(toIndentedString(employeeTelNo)).append("\n");
    sb.append("    employeeMobile: ").append(toIndentedString(employeeMobile)).append("\n");
    sb.append("    employeeEmail: ").append(toIndentedString(employeeEmail)).append("\n");
    sb.append("    employeeWorkingStatus: ").append(toIndentedString(employeeWorkingStatus)).append("\n");
    sb.append("    employeeMainJobPostId: ").append(toIndentedString(employeeMainJobPostId)).append("\n");
    sb.append("    employeeMainJobPostName: ").append(toIndentedString(employeeMainJobPostName)).append("\n");
    sb.append("    employeePhotoFileEntryId: ").append(toIndentedString(employeePhotoFileEntryId)).append("\n");
    sb.append("    employeeFileCerId: ").append(toIndentedString(employeeFileCerId)).append("\n");
    sb.append("    employeeFileSignId: ").append(toIndentedString(employeeFileSignId)).append("\n");
    sb.append("    applicantName: ").append(toIndentedString(applicantName)).append("\n");
    sb.append("    applicantType: ").append(toIndentedString(applicantType)).append("\n");
    sb.append("    applicantNo: ").append(toIndentedString(applicantNo)).append("\n");
    sb.append("    applicantIdNo: ").append(toIndentedString(applicantIdNo)).append("\n");
    sb.append("    applicantIdDate: ").append(toIndentedString(applicantIdDate)).append("\n");
    sb.append("    applicantAddress: ").append(toIndentedString(applicantAddress)).append("\n");
    sb.append("    applicantCityCode: ").append(toIndentedString(applicantCityCode)).append("\n");
    sb.append("    applicantCityName: ").append(toIndentedString(applicantCityName)).append("\n");
    sb.append("    applicantDistrictCode: ").append(toIndentedString(applicantDistrictCode)).append("\n");
    sb.append("    applicantDistrictName: ").append(toIndentedString(applicantDistrictName)).append("\n");
    sb.append("    applicantWardCode: ").append(toIndentedString(applicantWardCode)).append("\n");
    sb.append("    applicantWardName: ").append(toIndentedString(applicantWardName)).append("\n");
    sb.append("    applicantContactName: ").append(toIndentedString(applicantContactName)).append("\n");
    sb.append("    applicantContactTelNo: ").append(toIndentedString(applicantContactTelNo)).append("\n");
    sb.append("    applicantContactEmail: ").append(toIndentedString(applicantContactEmail)).append("\n");
    sb.append("    applicantActivationCode: ").append(toIndentedString(applicantActivationCode)).append("\n");
    sb.append("    applicantLock: ").append(toIndentedString(applicantLock)).append("\n");
    sb.append("    applicantTmpPass: ").append(toIndentedString(applicantTmpPass)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

