public final class Credential {
    private final int credId;
    private final String credName;
    private final String value;

    Credential(int credId, String credName, String value) {
        this.credId = credId;
        this.credName = credName;
        this.value = value;
    }

    public int getCredId() {
        return credId;
    }

    public String getCredName() {
        return credName;
    }

    public String getValue() {
        return this.value;
    }

}
