/**
 * ResultCp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.bulkSms.impl;

public class ResultCp  extends ws.bulkSms.impl.Result  implements java.io.Serializable {
    private java.lang.String[] alias;

    private java.lang.String cpCode;

    public ResultCp() {
    }

    public ResultCp(
           java.lang.String message,
           java.lang.Long result,
           java.lang.String[] alias,
           java.lang.String cpCode) {
        super(
            message,
            result);
        this.alias = alias;
        this.cpCode = cpCode;
    }


    /**
     * Gets the alias value for this ResultCp.
     * 
     * @return alias
     */
    public java.lang.String[] getAlias() {
        return alias;
    }


    /**
     * Sets the alias value for this ResultCp.
     * 
     * @param alias
     */
    public void setAlias(java.lang.String[] alias) {
        this.alias = alias;
    }

    public java.lang.String getAlias(int i) {
        return this.alias[i];
    }

    public void setAlias(int i, java.lang.String _value) {
        this.alias[i] = _value;
    }


    /**
     * Gets the cpCode value for this ResultCp.
     * 
     * @return cpCode
     */
    public java.lang.String getCpCode() {
        return cpCode;
    }


    /**
     * Sets the cpCode value for this ResultCp.
     * 
     * @param cpCode
     */
    public void setCpCode(java.lang.String cpCode) {
        this.cpCode = cpCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultCp)) return false;
        ResultCp other = (ResultCp) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.alias==null && other.getAlias()==null) || 
             (this.alias!=null &&
              java.util.Arrays.equals(this.alias, other.getAlias()))) &&
            ((this.cpCode==null && other.getCpCode()==null) || 
             (this.cpCode!=null &&
              this.cpCode.equals(other.getCpCode())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getAlias() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAlias());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAlias(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCpCode() != null) {
            _hashCode += getCpCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultCp.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://impl.bulkSms.ws/", "resultCp"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alias");
        elemField.setXmlName(new javax.xml.namespace.QName("", "alias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cpCode"));
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
