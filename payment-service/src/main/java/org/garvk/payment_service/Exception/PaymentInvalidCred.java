package org.garvk.payment_service.Exception;

public class PaymentInvalidCred extends RuntimeException {
    public PaymentInvalidCred() {
        super("Invalid Payment Credentials");
    }
}
