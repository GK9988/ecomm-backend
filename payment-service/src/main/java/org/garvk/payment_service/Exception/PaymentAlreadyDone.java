package org.garvk.payment_service.Exception;

public class PaymentAlreadyDone extends RuntimeException {
    public PaymentAlreadyDone() {
        super("Payment Already Complete for the Order");
    }
}
