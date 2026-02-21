package payment;

public class NetBankingPayments implements  PaymentStrategy{

    @Override
    public boolean pay(double amount){
        System.out.println("Processing NetBanking Payment of ₹" + amount);
return true;
    }
}
