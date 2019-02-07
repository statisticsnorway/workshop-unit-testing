package store.model;

public class Email {
    private String fromAddress;
    private String toAddress;
    private int uniqueId;

    public Email(String fromAddress, String toAddress, int uniqueId) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.uniqueId = uniqueId;
    }

    public Email() {

    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }
}
