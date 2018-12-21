/**
 * CpBalance.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.viettel.smsbrand;

public class CpBalance  implements java.io.Serializable {
    private java.math.BigDecimal balance;

    private int errCode;

    private java.lang.String errDesc;

    public CpBalance() {
    }

    public CpBalance(
           java.math.BigDecimal balance,
           int errCode,
           java.lang.String errDesc) {
           this.balance = balance;
           this.errCode = errCode;
           this.errDesc = errDesc;
    }


    /**
     * Gets the balance value for this CpBalance.
     * 
     * @return balance
     */
    public java.math.BigDecimal getBalance() {
        return balance;
    }


    /**
     * Sets the balance value for this CpBalance.
     * 
     * @param balance
     */
    public void setBalance(java.math.BigDecimal balance) {
        this.balance = balance;
    }


    /**
     * Gets the errCode value for this CpBalance.
     * 
     * @return errCode
     */
    public int getErrCode() {
        return errCode;
    }


    /**
     * Sets the errCode value for this CpBalance.
     * 
     * @param errCode
     */
    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }


    /**
     * Gets the errDesc value for this CpBalance.
     * 
     * @return errDesc
     */
    public java.lang.String getErrDesc() {
        return errDesc;
    }


    /**
     * Sets the errDesc value for this CpBalance.
     * 
     * @param errDesc
     */
    public void setErrDesc(java.lang.String errDesc) {
        this.errDesc = errDesc;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CpBalance)) return false;
        CpBalance other = (CpBalance) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.balance==null && other.getBalance()==null) || 
             (this.balance!=null &&
              this.balance.equals(other.getBalance()))) &&
            this.errCode == other.getErrCode() &&
            ((this.errDesc==null && other.getErrDesc()==null) || 
             (this.errDesc!=null &&
              this.errDesc.equals(other.getErrDesc())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getBalance() != null) {
            _hashCode += getBalance().hashCode();
        }
        _hashCode += getErrCode();
        if (getErrDesc() != null) {
            _hashCode += getErrDesc().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CpBalance.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://smsbrand.viettel.com/", "cpBalance"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("balance");
        elemField.setXmlName(new javax.xml.namespace.QName("", "balance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
