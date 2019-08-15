package controller;

public class ControllerException extends RuntimeException {

    private String string;

    public ControllerException(String string) {
        this.string = string;
    }

    public ControllerException() {
    }
}
