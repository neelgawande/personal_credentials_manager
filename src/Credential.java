final class Credential {
    String credName;
    private String value;

    Credential(String credName, String value){
        this.credName = credName;
        this.value = value;
    }
    String getValue(){
        return this.value;
    }

}
