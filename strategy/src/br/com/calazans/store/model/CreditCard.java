package br.com.calazans.store.model;

import java.math.BigDecimal;

public class CreditCard {

    private BigDecimal amount;
    private String cardNumber;
    private String cvv;
    private String expirationDate;


    public CreditCard(String number, String cvv, String expirationDate) {
        this.amount = new BigDecimal(100000);
        this.cardNumber = number;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
