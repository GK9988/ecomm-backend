package org.garvk.payment_service.Exception;

public class PaymentCredPresent extends RuntimeException {
    public PaymentCredPresent() {
        super("PayId Already Present");
    }
}
