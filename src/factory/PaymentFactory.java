package factory;

import enums.PaymentMode;
import payment.CreditCardPayments;
import payment.NetBankingPayments;
import payment.PaymentStrategy;
import payment.UpiPayments;


public class PaymentFactory {
    private PaymentFactory() {}

    public static PaymentStrategy getStrategy(PaymentMode mode) {

        switch (mode) {
            case CREDITCARD:
                return new CreditCardPayments();

            case UPI:
                return new UpiPayments();

            case NETBANKING:
                return new NetBankingPayments();

            default:
                throw new IllegalArgumentException("Invalid payment mode");
        }
    }

}
