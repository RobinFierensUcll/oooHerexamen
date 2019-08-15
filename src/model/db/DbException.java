package model.db;

public class DbException extends RuntimeException {
    private String string;

    public DbException(String string) {
        this.string = string;
    }

    public DbException() {
    }
}
