/**
 * MessageEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.opencps.sms.service.client;

public class MessageEntity  implements java.io.Serializable {
    private java.lang.String mtseq;

    private java.lang.String moid;

    private java.lang.String moseq;

    private java.lang.String src;

    private java.lang.String dest;

    private java.lang.String cmdcode;

    private java.lang.String msgbody;

    private java.lang.String mttotalseg;

    private java.lang.String mtseqref;

    public MessageEntity() {
    }

    public MessageEntity(
           java.lang.String mtseq,
           java.lang.String moid,
           java.lang.String moseq,
           java.lang.String src,
           java.lang.String dest,
           java.lang.String cmdcode,
           java.lang.String msgbody,
           java.lang.String mttotalseg,
           java.lang.String mtseqref) {
           this.mtseq = mtseq;
           this.moid = moid;
           this.moseq = moseq;
           this.src = src;
           this.dest = dest;
           this.cmdcode = cmdcode;
           this.msgbody = msgbody;
           this.mttotalseg = mttotalseg;
           this.mtseqref = mtseqref;
    }


    /**
     * Gets the mtseq value for this MessageEntity.
     * 
     * @return mtseq
     */
    public java.lang.String getMtseq() {
        return mtseq;
    }


    /**
     * Sets the mtseq value for this MessageEntity.
     * 
     * @param mtseq
     */
    public void setMtseq(java.lang.String mtseq) {
        this.mtseq = mtseq;
    }


    /**
     * Gets the moid value for this MessageEntity.
     * 
     * @return moid
     */
    public java.lang.String getMoid() {
        return moid;
    }


    /**
     * Sets the moid value for this MessageEntity.
     * 
     * @param moid
     */
    public void setMoid(java.lang.String moid) {
        this.moid = moid;
    }


    /**
     * Gets the moseq value for this MessageEntity.
     * 
     * @return moseq
     */
    public java.lang.String getMoseq() {
        return moseq;
    }


    /**
     * Sets the moseq value for this MessageEntity.
     * 
     * @param moseq
     */
    public void setMoseq(java.lang.String moseq) {
        this.moseq = moseq;
    }


    /**
     * Gets the src value for this MessageEntity.
     * 
     * @return src
     */
    public java.lang.String getSrc() {
        return src;
    }


    /**
     * Sets the src value for this MessageEntity.
     * 
     * @param src
     */
    public void setSrc(java.lang.String src) {
        this.src = src;
    }


    /**
     * Gets the dest value for this MessageEntity.
     * 
     * @return dest
     */
    public java.lang.String getDest() {
        return dest;
    }


    /**
     * Sets the dest value for this MessageEntity.
     * 
     * @param dest
     */
    public void setDest(java.lang.String dest) {
        this.dest = dest;
    }


    /**
     * Gets the cmdcode value for this MessageEntity.
     * 
     * @return cmdcode
     */
    public java.lang.String getCmdcode() {
        return cmdcode;
    }


    /**
     * Sets the cmdcode value for this MessageEntity.
     * 
     * @param cmdcode
     */
    public void setCmdcode(java.lang.String cmdcode) {
        this.cmdcode = cmdcode;
    }


    /**
     * Gets the msgbody value for this MessageEntity.
     * 
     * @return msgbody
     */
    public java.lang.String getMsgbody() {
        return msgbody;
    }


    /**
     * Sets the msgbody value for this MessageEntity.
     * 
     * @param msgbody
     */
    public void setMsgbody(java.lang.String msgbody) {
        this.msgbody = msgbody;
    }


    /**
     * Gets the mttotalseg value for this MessageEntity.
     * 
     * @return mttotalseg
     */
    public java.lang.String getMttotalseg() {
        return mttotalseg;
    }


    /**
     * Sets the mttotalseg value for this MessageEntity.
     * 
     * @param mttotalseg
     */
    public void setMttotalseg(java.lang.String mttotalseg) {
        this.mttotalseg = mttotalseg;
    }


    /**
     * Gets the mtseqref value for this MessageEntity.
     * 
     * @return mtseqref
     */
    public java.lang.String getMtseqref() {
        return mtseqref;
    }


    /**
     * Sets the mtseqref value for this MessageEntity.
     * 
     * @param mtseqref
     */
    public void setMtseqref(java.lang.String mtseqref) {
        this.mtseqref = mtseqref;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MessageEntity)) return false;
        MessageEntity other = (MessageEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.mtseq==null && other.getMtseq()==null) || 
             (this.mtseq!=null &&
              this.mtseq.equals(other.getMtseq()))) &&
            ((this.moid==null && other.getMoid()==null) || 
             (this.moid!=null &&
              this.moid.equals(other.getMoid()))) &&
            ((this.moseq==null && other.getMoseq()==null) || 
             (this.moseq!=null &&
              this.moseq.equals(other.getMoseq()))) &&
            ((this.src==null && other.getSrc()==null) || 
             (this.src!=null &&
              this.src.equals(other.getSrc()))) &&
            ((this.dest==null && other.getDest()==null) || 
             (this.dest!=null &&
              this.dest.equals(other.getDest()))) &&
            ((this.cmdcode==null && other.getCmdcode()==null) || 
             (this.cmdcode!=null &&
              this.cmdcode.equals(other.getCmdcode()))) &&
            ((this.msgbody==null && other.getMsgbody()==null) || 
             (this.msgbody!=null &&
              this.msgbody.equals(other.getMsgbody()))) &&
            ((this.mttotalseg==null && other.getMttotalseg()==null) || 
             (this.mttotalseg!=null &&
              this.mttotalseg.equals(other.getMttotalseg()))) &&
            ((this.mtseqref==null && other.getMtseqref()==null) || 
             (this.mtseqref!=null &&
              this.mtseqref.equals(other.getMtseqref())));
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
        if (getMtseq() != null) {
            _hashCode += getMtseq().hashCode();
        }
        if (getMoid() != null) {
            _hashCode += getMoid().hashCode();
        }
        if (getMoseq() != null) {
            _hashCode += getMoseq().hashCode();
        }
        if (getSrc() != null) {
            _hashCode += getSrc().hashCode();
        }
        if (getDest() != null) {
            _hashCode += getDest().hashCode();
        }
        if (getCmdcode() != null) {
            _hashCode += getCmdcode().hashCode();
        }
        if (getMsgbody() != null) {
            _hashCode += getMsgbody().hashCode();
        }
        if (getMttotalseg() != null) {
            _hashCode += getMttotalseg().hashCode();
        }
        if (getMtseqref() != null) {
            _hashCode += getMtseqref().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MessageEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "MessageEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mtseq");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "mtseq"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("moid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "moid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("moseq");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "moseq"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("src");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "src"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "dest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmdcode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "cmdcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgbody");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "msgbody"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mttotalseg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "mttotalseg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mtseqref");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "mtseqref"));
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
