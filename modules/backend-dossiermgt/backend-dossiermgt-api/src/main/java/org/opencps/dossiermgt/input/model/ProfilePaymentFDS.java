package org.opencps.dossiermgt.input.model;

public class ProfilePaymentFDS {
    private Integer requestPayment;
    private String paymentNote;
    private Integer advanceAmount;
    private Integer feeAmount;
    private Integer serviceAmount;
    private Integer shipAmount;

    public Integer getRequestPayment() {
        return requestPayment;
    }

    public void setRequestPayment(Integer requestPayment) {
        this.requestPayment = requestPayment;
    }

    public String getPaymentNote() {
        return paymentNote;
    }

    public void setPaymentNote(String paymentNote) {
        this.paymentNote = paymentNote;
    }

    public Integer getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(Integer advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public Integer getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Integer feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Integer getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(Integer serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public Integer getShipAmount() {
        return shipAmount;
    }

    public void setShipAmount(Integer shipAmount) {
        this.shipAmount = shipAmount;
    }
}
