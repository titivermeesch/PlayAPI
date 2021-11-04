package me.playbosswar.playapi.exceptions;

/**
 * Thrown when an entity (team, arena,...) is already registered
 */
public class AlreadyRegisteredException extends Exception {
    public AlreadyRegisteredException(String message) {
        super(message);
    }
}
