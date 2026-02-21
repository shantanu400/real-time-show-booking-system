package payment;

public class UpiPayments implements PaymentStrategy{
    @Override
    public boolean pay(double amount){
        System.out.println("Processing UPI Payment of ₹" + amount);
return true;
    }
}
