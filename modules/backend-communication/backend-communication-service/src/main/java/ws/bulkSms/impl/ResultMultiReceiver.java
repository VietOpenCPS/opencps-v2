/**
 * ResultMultiReceiver.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.bulkSms.impl;

public class ResultMultiReceiver  extends ws.bulkSms.impl.Result  implements java.io.Serializable {
    private ws.bulkSms.impl.ResultMultiReceiver[] details;

    private java.lang.String msgId;

    private java.lang.String receiver;

    public ResultMultiReceiver() {
    }

    public ResultMultiReceiver(
           java.lang.String message,
           java.lang.Long result,
           ws.bulkSms.impl.ResultMultiReceiver[] details,
           java.lang.String msgId,
           java.lang.String receiver) {
        super(
            message,
            result);
        this.details = details;
        this.msgId = msgId;
        this.receiver = receiver;
    }


    /**
     * Gets the details value for this ResultMultiReceiver.
     * 
     * @return details
     */
    public ws.bulkSms.impl.ResultMultiReceiver[] getDetails() {
        return details;
    }


    /**
     * Sets the details value for this ResultMultiReceiver.
     * 
     * @param details
     */
    public void setDetails(ws.bulkSms.impl.ResultMultiReceiver[] details) {
        this.details = details;
    }

    public ws.bulkSms.impl.ResultMultiReceiver getDetails(int i) {
        return this.details[i];
    }

    public void setDetails(int i, ws.bulkSms.impl.ResultMultiReceiver _value) {
        this.details[i] = _value;
    }


    /**
     * Gets the msgId value for this ResultMultiReceiver.
     * 
     * @return msgId
     */
    public java.lang.String getMsgId() {
        return msgId;
    }


    /**
     * Sets the msgId value for this ResultMultiReceiver.
     * 
     * @param msgId
     */
    public void setMsgId(java.lang.String msgId) {
        this.msgId = msgId;
    }


    /**
     * Gets the receiver value for this ResultMultiReceiver.
     * 
     * @return receiver
     */
    public java.lang.String getReceiver() {
        return receiver;
    }


    /**
     * Sets the receiver value for this ResultMultiReceiver.
     * 
     * @param receiver
     */
    public void setReceiver(java.lang.String receiver) {
        this.receiver = receiver;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultMultiReceiver)) return false;
        ResultMultiReceiver other = (ResultMultiReceiver) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.details==null && other.getDetails()==null) || 
             (this.details!=null &&
              java.util.Arrays.equals(this.details, other.getDetails()))) &&
            ((this.msgId==null && other.getMsgId()==null) || 
             (this.msgId!=null &&
              this.msgId.equals(other.getMsgId()))) &&
            ((this.receiver==null && other.getReceiver()==null) || 
             (this.receiver!=null &&
              this.receiver.equals(other.getReceiver())));
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
        if (getDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMsgId() != null) {
            _hashCode += getMsgId().hashCode();
        }
        if (getReceiver() != null) {
            _hashCode += getReceiver().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultMultiReceiver.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://impl.bulkSms.ws/", "resultMultiReceiver"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details");
        elemField.setXmlName(new javax.xml.namespace.QName("", "details"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://impl.bulkSms.ws/", "resultMultiReceiver"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "msgId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receiver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "receiver"));
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
