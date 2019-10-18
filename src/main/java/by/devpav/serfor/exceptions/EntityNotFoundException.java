package by.devpav.serfor.exceptions;

public class EntityNotFoundException extends RuntimeException {

    private String reason;

    public EntityNotFoundException(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
