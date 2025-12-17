package com.algaworks.algafoodapi.api.exceptionhandler;

public class ProblemField {
    private String field;
    private String userMessage;

    private ProblemField(Builder builder) {
        this.field = builder.campo;
        this.userMessage = builder.userMessage;
    }

    public String getField() {
        return field;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public static class Builder {
        private String campo;
        private String userMessage;

        public Builder name(String name) {
            this.campo = name;
            return this;
        }

        public Builder userMessage(String userMessage) {
            this.userMessage = userMessage;
            return this;
        }

        public ProblemField build() {
            return new ProblemField(this);
        }
    }
}
