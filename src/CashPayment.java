public class CashPayment implements Payment{
    @Override
    public boolean processPayment(double price) {
        return true;
    }
}
