package store.paymentMethods;

import store.paymentMethods.interfaces.PaymentMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PayPalPayment implements PaymentMethod {

    private static final Map<String, String> customerDataBase = new HashMap<>();
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String email;
    private String password;
    private boolean signedIn;

    static {
        customerDataBase.put("vasco123", "marcos16@gmail.com");
        customerDataBase.put("familia1580", "joaquim@outlook.com");
    }

    @Override
    public void collectPaymentDetails() {
        try {
            while (!signedIn) {
                System.out.println("Enter e-mail: ");
                email = reader.readLine();
                System.out.println("Enter password: ");
                password = reader.readLine();
                if (verify()) {
                    System.out.println("Data verification has been successful.");
                } else {
                    System.out.println("Wrong email or password!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }

    private boolean verify() {
        setSignedIn(email.equals(customerDataBase.get(password)));
        return signedIn;
    }

    @Override
    public Boolean pay(BigDecimal paymentAmount) {
        if (signedIn) {
            System.out.println("Realizando pagamento de: R$ " + paymentAmount + " utilizando PayPal.");
            return true;
        } else {
            return false;
        }
    }
}
