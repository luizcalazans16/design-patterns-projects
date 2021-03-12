package br.com.calazans.store.paymentMethods.interfaces;

import java.math.BigDecimal;

public interface PaymentMethod {

    Boolean pay(BigDecimal paymentAmount);
    void collectPaymentDetails();
}
