package repository;

import entity.Payment;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentRepository {

    private final Map<String, Payment> payments = new ConcurrentHashMap<>();

    public void save(Payment payment) {
        payments.put(payment.getPaymentId(), payment);
    }

    public Payment findById(String paymentId) {
        return payments.get(paymentId);
    }
}