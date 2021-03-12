package br.com.calazans.store.model;

import br.com.calazans.store.paymentMethods.interfaces.PaymentMethod;

import java.math.BigDecimal;

public class Order {
    private BigDecimal totalCost = BigDecimal.ZERO;
    private Boolean isClosed = false;


    public void processOrder(PaymentMethod paymentMethod) {
        paymentMethod.collectPaymentDetails();
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Boolean isClosed() {
        return isClosed;
    }

    public void setClosed() {
        isClosed = true;
    }
}
