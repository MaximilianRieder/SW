package de.othr.sw.quickstart.dtos;

public class TransactionReturnDto {
    long id;
    boolean status;
    TransactionMessage message;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public TransactionMessage getMessage() {
        return message;
    }

    public void setMessage(TransactionMessage message) {
        this.message = message;
    }
}
