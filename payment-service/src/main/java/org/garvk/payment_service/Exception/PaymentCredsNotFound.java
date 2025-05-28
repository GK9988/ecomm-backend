package org.garvk.payment_service.Exception;

public class PaymentCredsNotFound extends RuntimeException {
    public PaymentCredsNotFound() {
        super("Payment Credentials Not Found for Given Id");
    }
}
