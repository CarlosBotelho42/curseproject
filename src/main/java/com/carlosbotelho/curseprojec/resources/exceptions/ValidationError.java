package com.carlosbotelho.curseprojec.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

    private List<FieldMessage> messages = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FieldMessage> getMessages() {
        return messages;
    }

    public void addError(String fieldName, String message) {
        messages.add(new FieldMessage(fieldName, message));
    }
}
