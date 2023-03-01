package com.dany.order.management.commons.exceptions;

public class EntityNotExistException extends IllegalArgumentException {
    public EntityNotExistException(String msg) {
        super(msg);
    }
}
