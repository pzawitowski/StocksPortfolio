package com.stock.portfolio.core;

public class PortfolioException extends RuntimeException {
    private ErrorMessage error;
    private Object[] arguments;

    public PortfolioException(ErrorMessage error) {
        this.error = error;
    }

    public PortfolioException(ErrorMessage error, Object... arguments) {
        this(error);
        this.arguments = arguments;
    }

    @Override
    public String getMessage() {
        if (error == null) {
            return super.getMessage();
        } else {
            String message = error.getMessage();
            if (arguments == null) {
                return message;
            }
            else {
                return addArgumentsToTheMessage(message);

            }

        }
    }

    private String addArgumentsToTheMessage(String message) {
        for (int i = 0; i < arguments.length; i++) {
            message = message.replaceFirst("\\{\\}", arguments[i].toString());
        }
        return message;
    }
}
