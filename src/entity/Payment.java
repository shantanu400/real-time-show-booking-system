package entity;

import enums.PaymentMode;
import enums.PaymentStatus;

import java.util.UUID;

public class Payment {

    private String paymentId;
    private String bookingId;
    private double amount;
    private PaymentMode paymentMode;
    private PaymentStatus status;

    public Payment(String bookingId,
                   double amount,
                   PaymentMode paymentMode) {

        this.paymentId = UUID.randomUUID().toString();
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.status = PaymentStatus.PENDING;
    }

    public void markSuccess() {
        this.status = PaymentStatus.SUCCESS;
    }

    public void markFailed() {
        this.status = PaymentStatus.FAILED;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }
}