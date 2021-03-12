package store.paymentMethods;

import store.paymentMethods.interfaces.PaymentMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class TicketPayment implements PaymentMethod {

    private static final Integer CPF_CONFIRMADO = 1;
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private Boolean cpfIsValid = false;


    @Override
    public Boolean pay(BigDecimal paymentAmount) {
        if (cpfIsValid) {
            System.out.println("Realizando pagamento de: R$ " + paymentAmount + " por boleto.");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void collectPaymentDetails() {
        try {
            System.out.println("Digite o CPF: ");
            String cpf = READER.readLine();
            confirmCPF(cpf);
            System.out.println("Boleto gerado." + "\n"
                    + "Código: " + UUID.randomUUID() + "\n"
                    + "Data de validade: " + LocalDate.now().plusDays(3));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void confirmCPF(String cpf) {
        System.out.println("O CPF " + cpf + " está correto?" + "\n"
                + "1 - SIM" + "\n"
                + "2 - NÃO");
        try {
            while (!cpfIsValid) {
                Integer customerAnswer = Integer.parseInt(READER.readLine());
                if (validateCPF(customerAnswer)) {
                    System.out.println("CPF confirmado!");
                } else {
                    System.out.println("Digite o CPF novamente");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Boolean validateCPF(Integer customerAnswer) {
        setCpfIsValid(customerAnswer.equals(CPF_CONFIRMADO));
        return cpfIsValid;
    }

    public void setCpfIsValid(Boolean cpfIsValid) {
        this.cpfIsValid = cpfIsValid;
    }
}
