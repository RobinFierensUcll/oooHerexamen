package model.domain;

public class DomainException extends RuntimeException {
    private String string;

    public DomainException(String string) {
        this.string = string;
    }

    public DomainException() {
    }
}
