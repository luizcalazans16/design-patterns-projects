package store;

import store.model.Order;
import store.paymentMethods.CreditCardPayment;
import store.paymentMethods.PayPalPayment;
import store.paymentMethods.TicketPayment;
import store.paymentMethods.interfaces.PaymentMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class GamingStore {

    private static final String PAYPAL = "1";
    private static final String CREDIT_CARD = "2";
    private static final String TICKET = "3";
    private static Map<Integer, BigDecimal> productsPrices = new HashMap<>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static PaymentMethod paymentMethod;
    private static Order order = new Order();

    static {
        productsPrices.put(1, new BigDecimal(3000));
        productsPrices.put(2, new BigDecimal(5000));
        productsPrices.put(3, new BigDecimal(250));
        productsPrices.put(4, new BigDecimal(150));
    }

    public static void main(String[] args) throws IOException {
        while (!order.isClosed()) {
            BigDecimal cost;
            Integer choice;
            String continueChoice;

            do {
                System.out.print("Selecione o(s) produto(s): " + "\n" +
                        "1 - XBOX Series X" + "\n" +
                        "2 - Playstation 5" + "\n" +
                        "3 - Jogo XBOX" + "\n" +
                        "4 - Mouse RGB" + "\n");

                choice = Integer.parseInt(reader.readLine());
                cost = productsPrices.get(choice);
                System.out.println("Quantidade: ");
                Integer productAmount = Integer.parseInt(reader.readLine());
                order.setTotalCost(cost.multiply(new BigDecimal(productAmount)));

                System.out.println("Deseja seguir para o pagamento? S/N: ");
                continueChoice = reader.readLine();
            } while (continueChoice.equalsIgnoreCase("N"));

            if (paymentMethod == null) {
                System.out.println("Selecione o meio de pagamento: " + "\n"
                        + "1 - PayPal" + "\n"
                        + "2 - Cartão de crédito" + "\n"
                        + "3 - Boleto");

                String selectedPaymentMethod = reader.readLine();
                switch (selectedPaymentMethod) {
                    case PAYPAL:
                        paymentMethod = new PayPalPayment();
                        break;
                    case CREDIT_CARD:
                        paymentMethod = new CreditCardPayment();
                        break;
                    case TICKET:
                        paymentMethod = new TicketPayment();
                        break;
                }
                order.processOrder(paymentMethod);

                System.out.println("Total do pedido: " + order.getTotalCost() + "\n"
                        + "P - Pagar" + "\n"
                        + "C - Continuar");

                String proceed = reader.readLine();
                if (proceed.equalsIgnoreCase("P")) {
                    if (paymentMethod.pay(order.getTotalCost())) {
                        System.out.println("Pedido gerado com sucesso!");
                    } else {
                        System.out.println("Erro! Valide os dados, por favor");
                    }
                    order.setClosed();
                }
            }
        }
    }
}

