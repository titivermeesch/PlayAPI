package me.playbosswar.playapi.storage.exceptions;

public class AdapterTransactionException extends Exception {
    public AdapterTransactionException(String message) {
        super(message);
    }

    public AdapterTransactionException(String message, Exception e) {
        super(message, e);
    }
}
