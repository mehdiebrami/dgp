package com.dgp.paymentservice.exception;

public class PaymentException extends Exception {
    private int errorCode;

    public PaymentException(String message) {
        super(message);
    }

    public PaymentException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public PaymentException(String message, Throwable cause) {
        super(message, cause);
    }


    public int getErrorCode() {
        return errorCode;
    }

}
