package br.com.calazans.store.paymentMethods;

import br.com.calazans.store.paymentMethods.interfaces.PaymentMethod;
import br.com.calazans.store.model.CreditCard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class CreditCardPayment implements PaymentMethod {

    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private CreditCard card;

    @Override
    public void collectPaymentDetails() {
        try {
            System.out.print("Digite o número do cartão: ");
            String number = READER.readLine();
            System.out.print("Digite a data de validade no formato --> 'mm/yy': ");
            String date = READER.readLine();
            System.out.print("Digite o CVV ");
            String cvv = READER.readLine();
            card = new CreditCard(number, date, cvv);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Boolean pay(BigDecimal paymentAmount) {
        if (isCardValid()) {
            System.out.println("Realizando o pagemento de: R$" + paymentAmount + " utlizando cartão de crédito.");
            card.setAmount(card.getAmount().subtract(paymentAmount));
            return true;
        } else {
            return false;
        }
    }

    private boolean isCardValid() {
        return card != null;
    }
}
