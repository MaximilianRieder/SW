package de.othr.sw.quickstart.dtos;

import java.io.Serializable;

public class TransactionReturnDto implements Serializable {
    long id;
    boolean status;
    String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
