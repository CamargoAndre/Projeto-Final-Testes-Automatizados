package tech.ada.bookstore.exceptions;

import tech.ada.bookstore.util.MessageUtils;

public class NotFoundException extends RuntimeException{

    public NotFoundException() {
        super(MessageUtils.NO_RECORDS_FOUND);
    }
}
