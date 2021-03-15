package br.com.calazans.chain.handler;

import br.com.calazans.chain.model.UserData;

public abstract class RequestHandler {
    private RequestHandler nextRequestHandler;

    public RequestHandler linkHandler(RequestHandler nextRequestHandler) {
        this.nextRequestHandler = nextRequestHandler;
        return nextRequestHandler;
    }

    public abstract Boolean verifyUserData(String cpf, UserData userData);

    protected Boolean validateNextHandler(String cpf, UserData userData) {
        if (nextRequestHandler == null) {
            return true;
        }
        return nextRequestHandler.verifyUserData(cpf, userData);
    }

}
