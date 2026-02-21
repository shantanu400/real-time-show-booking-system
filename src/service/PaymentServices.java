package service;

import entity.Payment;
import enums.PaymentMode;
import enums.PaymentStatus;
import payment.PaymentStrategy;
import repository.PaymentRepository;

public class PaymentServices {

    private final PaymentRepository paymentRepository;

    public PaymentServices(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment processPayment(String bookingId,
                                  double amount,
                                  PaymentMode mode,
                                  PaymentStrategy strategy) {

        Payment payment = new Payment(bookingId, amount, mode);

        boolean success = strategy.pay(amount);

        if (success) {
            payment.markSuccess();
        } else {
            payment.markFailed();
        }

        paymentRepository.save(payment);

        return payment;
    }
}