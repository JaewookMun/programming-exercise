package practice.oopquerydsl.booklending.service;

public class NoBookException extends Exception {
    public NoBookException(String message) {
        super(message);
    }
}
