package payment;

import java.security.PublicKey;

public class CreditCardPayments implements PaymentStrategy{
    @Override
    public boolean pay(double amount){
        System.out.println("Processing Credit Card Payment of ₹" + amount);
return true;
    }
}
