package org.garvk.payment_service.Exception;

public class PaymentNotFound extends RuntimeException {
    public PaymentNotFound() {
        super("Payment Not Found for Given Order Id");
    }
}
