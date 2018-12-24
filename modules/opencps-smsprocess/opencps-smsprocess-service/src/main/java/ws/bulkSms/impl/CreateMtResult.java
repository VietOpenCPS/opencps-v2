/**
 * CreateMtResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.bulkSms.impl;

public class CreateMtResult  implements java.io.Serializable {
    private long errCode;

    private java.lang.String errDesc;

    private ws.bulkSms.impl.ResponseMt[] responseMt;

    public CreateMtResult() {
    }

    public CreateMtResult(
           long errCode,
           java.lang.String errDesc,
           ws.bulkSms.impl.ResponseMt[] responseMt) {
           this.errCode = errCode;
           this.errDesc = errDesc;
           this.responseMt = responseMt;
    }


    /**
     * Gets the errCode value for this CreateMtResult.
     * 
     * @return errCode
     */
    public long getErrCode() {
        return errCode;
    }


    /**
     * Sets the errCode value for this CreateMtResult.
     * 
     * @param errCode
     */
    public void setErrCode(long errCode) {
        this.errCode = errCode;
    }


    /**
     * Gets the errDesc value for this CreateMtResult.
     * 
     * @return errDesc
     */
    public java.lang.String getErrDesc() {
        return errDesc;
    }


    /**
     * Sets the errDesc value for this CreateMtResult.
     * 
     * @param errDesc
     */
    public void setErrDesc(java.lang.String errDesc) {
        this.errDesc = errDesc;
    }


    /**
     * Gets the responseMt value for this CreateMtResult.
     * 
     * @return responseMt
     */
    public ws.bulkSms.impl.ResponseMt[] getResponseMt() {
        return responseMt;
    }


    /**
     * Sets the responseMt value for this CreateMtResult.
     * 
     * @param responseMt
     */
    public void setResponseMt(ws.bulkSms.impl.ResponseMt[] responseMt) {
        this.responseMt = responseMt;
    }

    public ws.bulkSms.impl.ResponseMt getResponseMt(int i) {
        return this.responseMt[i];
    }

    public void setResponseMt(int i, ws.bulkSms.impl.ResponseMt _value) {
        this.responseMt[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateMtResult)) return false;
        CreateMtResult other = (CreateMtResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.errCode == other.getErrCode() &&
            ((this.errDesc==null && other.getErrDesc()==null) || 
             (this.errDesc!=null &&
              this.errDesc.equals(other.getErrDesc()))) &&
            ((this.responseMt==null && other.getResponseMt()==null) || 
             (this.responseMt!=null &&
              java.util.Arrays.equals(this.responseMt, other.getResponseMt())));
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
        _hashCode += new Long(getErrCode()).hashCode();
        if (getErrDesc() != null) {
            _hashCode += getErrDesc().hashCode();
        }
        if (getResponseMt() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResponseMt());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResponseMt(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateMtResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://impl.bulkSms.ws/", "createMtResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseMt");
        elemField.setXmlName(new javax.xml.namespace.QName("http://impl.bulkSms.ws/", "responseMt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://impl.bulkSms.ws/", "responseMt"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
