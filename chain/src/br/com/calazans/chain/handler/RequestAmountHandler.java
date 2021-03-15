package br.com.calazans.chain.handler;

import br.com.calazans.chain.model.UserData;

public class RequestAmountHandler extends RequestHandler {

    private int requestPerMinute;
    private int request;
    private long currentTime;

    public RequestAmountHandler(int requestPerMinute) {
        this.requestPerMinute = requestPerMinute;
        this.currentTime = System.currentTimeMillis();
    }

    public Boolean verifyUserData(String email, UserData userData) {
        if (System.currentTimeMillis() > currentTime + 60_000) {
            request = 0;
            currentTime = System.currentTimeMillis();
        }

        request++;

        if (request > requestPerMinute) {
            System.out.println("Request limit exceeded!");
            Thread.currentThread().interrupt();
        }
        return validateNextHandler(email, userData);
    }
}

