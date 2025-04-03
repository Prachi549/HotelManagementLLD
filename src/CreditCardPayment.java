public class CreditCardPayment implements Payment{
    @Override
    public boolean processPayment(double price) {
        return true;
    }
}
